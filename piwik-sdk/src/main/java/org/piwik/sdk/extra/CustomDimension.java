package org.piwik.sdk.extra;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.piwik.sdk.Piwik;
import org.piwik.sdk.TrackMe;


/**
 * Allows you to track Custom Dimensions.
 * In order to use this functionality install and configure
 * https://plugins.piwik.org/CustomDimensions plugin.
 */
public class CustomDimension {
    private static final String LOGGER_TAG = Piwik.LOGGER_PREFIX + "CustomDimension";

    /**
     * This method sets a tracking API parameter dimension%dimensionId%=%dimensionValue%.
     * Eg dimension1=foo or dimension2=bar.
     * So the tracking API parameter starts with dimension followed by the set dimensionId.
     * <p>
     * Requires <a href="https://plugins.piwik.org/CustomDimensions">Custom Dimensions</a> plugin (server-side)
     *
     * @param trackMe        into which the data should be inserted
     * @param dimensionId    accepts values greater than 0
     * @param dimensionValue is limited to 255 characters, you can pass null to delete a value
     * @return true if the value was valid
     */
    public static boolean setDimension(@NonNull TrackMe trackMe, int dimensionId, @Nullable String dimensionValue) {
        if (dimensionId < 1) {
            return false;
        }
        if (dimensionValue != null && dimensionValue.length() > 255) {
            dimensionValue = dimensionValue.substring(0, 255);
        }
        if (dimensionValue != null && dimensionValue.length() == 0) {
            dimensionValue = null;
        }
        trackMe.set(formatDimensionId(dimensionId), dimensionValue);
        return true;
    }

    @Nullable
    public static String getDimension(TrackMe trackMe, int dimensionId) {
        return trackMe.get(formatDimensionId(dimensionId));
    }

    private static String formatDimensionId(int id) {
        return "dimension" + id;
    }
}
