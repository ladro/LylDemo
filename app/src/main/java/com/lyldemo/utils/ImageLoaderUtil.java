package com.lyldemo.utils;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class ImageLoaderUtil {

    public static void displayImage(String imageUrl, ImageView imageView, int defaultImage) {
        displayImage(imageUrl, imageView, defaultImage, null);
    }

    public static void displayImage(String imageUrl, ImageView imageView, int defaultImage, ImageLoadingListener callback) {
        if (TextUtils.isEmpty(imageUrl)) {
            imageView.setImageResource(defaultImage);
            return;
        }
        DisplayImageOptions options;
        if (defaultImage == 0) {
            options = new DisplayImageOptions.Builder().cacheOnDisk(true).bitmapConfig(Bitmap.Config.RGB_565).build();
        } else {
            options = new DisplayImageOptions.Builder()
                    .showImageOnLoading(defaultImage)
                    .showImageForEmptyUri(defaultImage)
                    .showImageOnFail(defaultImage).cacheOnDisk(true)
                    .bitmapConfig(Bitmap.Config.RGB_565).build();
        }

        ImageLoader.getInstance().displayImage(imageUrl, imageView, options, callback);
    }

    public static void displayImageCircle(String imageUrl, ImageView imageView, int defaultImage, int cornerRadiusPixels) {
        displayImageCircle(imageUrl, imageView, defaultImage, cornerRadiusPixels);
    }

    public static void displayImageCircle(String imageUrl, ImageView imageView, int defaultImage, int cornerRadiusPixels, ImageLoadingListener callback) {
        if (TextUtils.isEmpty(imageUrl)) {
            imageView.setImageResource(defaultImage);
            return;
        }
        DisplayImageOptions options;
        if (defaultImage == 0) {
            options = new DisplayImageOptions.Builder().cacheOnDisk(true).bitmapConfig(Bitmap.Config.RGB_565).build();
        } else {
            options = new DisplayImageOptions.Builder()
                    .showImageOnLoading(defaultImage)
                    .displayer(new RoundedBitmapDisplayer(cornerRadiusPixels))
                    .showImageForEmptyUri(defaultImage)
                    .showImageOnFail(defaultImage).cacheOnDisk(true)
                    .bitmapConfig(Bitmap.Config.RGB_565).build();
        }

        ImageLoader.getInstance().displayImage(imageUrl, imageView, options, callback);
    }

    public static void displayLoacImage(String imageUrl, ImageView imageView, int defaultImage, final Bitmap bitmap) {
        displayImage(imageUrl, imageView, defaultImage, bitmap, null);
    }

    public static void displayImage(String imageUrl, ImageView imageView, int defaultImage, final Bitmap bitmap, ImageLoadingListener callback) {
        if (TextUtils.isEmpty(imageUrl)) {
            imageView.setImageResource(defaultImage);
            return;
        }
        DisplayImageOptions options;
        if (defaultImage == 0) {
            options = new DisplayImageOptions.Builder().cacheOnDisk(true).bitmapConfig(Bitmap.Config.RGB_565).build();
        } else {
            options = new DisplayImageOptions.Builder()
                    .showImageOnLoading(defaultImage)
                    .showImageForEmptyUri(defaultImage)
                    .showImageOnFail(defaultImage).cacheOnDisk(true)
                    .bitmapConfig(Bitmap.Config.RGB_565).build();
        }

        ImageLoader.getInstance().displayImage(imageUrl, imageView, options,
                callback);

    }

    public static boolean clearCache() {
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.clearDiskCache();
        imageLoader.clearMemoryCache();

        return true;
    }

    public static boolean clearIsCache() {
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.clearMemoryCache();
        return true;
    }


}
