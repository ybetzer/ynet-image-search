package com.yonatanbetzer.imagesearch.utils;

import android.util.DisplayMetrics;
import android.util.TypedValue;

import com.yonatanbetzer.imagesearch.application.ImageSearchApplication;

public class Utils {
    public static int pixelsFromDP(int dp) {
        DisplayMetrics displaymetrics = ImageSearchApplication.getAppContext().getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_SP, dp, displaymetrics );
    }
}
