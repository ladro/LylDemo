package org.piwik.sdk.dispatcher;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.piwik.sdk.Piwik;
import org.piwik.sdk.Tracker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;


public class EventDiskCache {
    private static final String TAG = Piwik.LOGGER_PREFIX + "EventDiskCache";
    private static final String CACHE_DIR_NAME = "piwik_cache";
    private static final String VERSION = "1";
    private final LinkedBlockingQueue<File> mEventContainer = new LinkedBlockingQueue<>();
    private final File mCacheDir;
    private final long mMaxAge;
    private final long mMaxSize;
    private long mCurrentSize = 0;
    private boolean mDelayedClear = false;

    public EventDiskCache(Tracker tracker) {
        mMaxAge = tracker.getOfflineCacheAge();
        mMaxSize = tracker.getOfflineCacheSize();
        File baseDir = new File(tracker.getPiwik().getContext().getCacheDir(), CACHE_DIR_NAME);
        mCacheDir = new File(baseDir, tracker.getAPIUrl().getHost());
        File[] storedContainers = mCacheDir.listFiles();
        if (storedContainers != null) {
            Arrays.sort(storedContainers);
            for (File container : storedContainers) {
                mCurrentSize += container.length();
                mEventContainer.add(container);
            }
        } else {
            if (!mCacheDir.mkdirs()) ;
        }
    }

    private void checkCacheLimits() {
        long startTime = System.currentTimeMillis();
        if (mMaxAge < 0) {
            while (!mEventContainer.isEmpty()) {
                File head = mEventContainer.poll();
                if (head.delete()) {
                }
            }
        } else if (mMaxAge > 0) {
            final Iterator<File> iterator = mEventContainer.iterator();
            while (iterator.hasNext()) {
                File head = iterator.next();
                long timestamp;
                try {
                    final String[] split = head.getName().split("_");
                    timestamp = Long.valueOf(split[1]);
                } catch (Exception e) {
                    timestamp = 0;
                }
                if (timestamp < (System.currentTimeMillis() - mMaxAge)) {
                    iterator.remove();
                } else {
                    // List is sorted by age
                    break;
                }
            }
        }
        if (mMaxSize != 0) {
            final Iterator<File> iterator = mEventContainer.iterator();
            while (iterator.hasNext() && mCurrentSize > mMaxSize) {
                File head = iterator.next();
                mCurrentSize -= head.length();
                iterator.remove();
            }
        }
        long stopTime = System.currentTimeMillis();
    }

    private boolean isCachingEnabled() {
        return mMaxAge >= 0;
    }

    public synchronized void cache(@NonNull List<Event> toCache) {
        if (!isCachingEnabled() || toCache.isEmpty()) return;

        checkCacheLimits();

        long startTime = System.currentTimeMillis();

        File container = writeEventFile(toCache);
        if (container != null) {
            mEventContainer.add(container);
            mCurrentSize += container.length();
        }
        long stopTime = System.currentTimeMillis();
    }

    @NonNull
    public synchronized List<Event> uncache() {
        List<Event> events = new ArrayList<>();
        if (!isCachingEnabled()) return events;

        checkCacheLimits();

        long startTime = System.currentTimeMillis();
        while (!mEventContainer.isEmpty()) {
            File head = mEventContainer.poll();
            if (head != null) {
                events.addAll(readEventFile(head));
            }
        }

        long stopTime = System.currentTimeMillis();
        return events;
    }

    public synchronized boolean isEmpty() {
        if (!mDelayedClear) {
            checkCacheLimits();
            mDelayedClear = true;
        }
        return mEventContainer.isEmpty();
    }

    private List<Event> readEventFile(@NonNull File file) {
        List<Event> events = new ArrayList<>();
        if (!file.exists()) return events;

        InputStream in = null;
        try {
            in = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String versionLine = bufferedReader.readLine();
            if (!VERSION.equals(versionLine)) return events;

            final long cutoff = System.currentTimeMillis() - mMaxAge;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final int split = line.indexOf(" ");
                if (split == -1) continue;

                try {
                    long timestamp = Long.parseLong(line.substring(0, split));
                    if (mMaxAge > 0 && timestamp < cutoff) continue;

                    String query = line.substring(split + 1);
                    events.add(new Event(timestamp, query));
                } catch (Exception e) {  }
            }
        } catch (IOException e) {
        } finally {
            if (in != null) {
                try { in.close(); } catch (IOException e) { }
            }
        }

        return events;
    }

    @Nullable
    private File writeEventFile(@NonNull List<Event> events) {
        if (events.isEmpty()) return null;

        File newFile = new File(mCacheDir, "events_" + events.get(events.size() - 1).getTimeStamp());
        FileWriter out = null;
        boolean dataWritten = false;
        try {
            out = new FileWriter(newFile);
            out.append(VERSION).append("\n");

            final long cutoff = System.currentTimeMillis() - mMaxAge;
            for (Event event : events) {
                if (mMaxAge > 0 && event.getTimeStamp() < cutoff) continue;
                out.append(String.valueOf(event.getTimeStamp())).append(" ").append(event.getEncodedQuery()).append("\n");
                dataWritten = true;
            }
        } catch (IOException e) {
            newFile.deleteOnExit();
            return null;
        } finally {
            if (out != null) {
                try { out.close(); } catch (IOException e) { }
            }
        }


        // If just version data was written delete the file.
        if (dataWritten) return newFile;
        else {
            //noinspection ResultOfMethodCallIgnored
            newFile.delete();
            return null;
        }
    }

}
