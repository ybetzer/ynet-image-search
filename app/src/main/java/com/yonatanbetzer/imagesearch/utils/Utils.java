package com.yonatanbetzer.imagesearch.utils;

import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import com.yonatanbetzer.imagesearch.application.ImageSearchApplication;

import java.util.Random;

public class Utils {
    public static int pixelsFromDP(int dp) {
        DisplayMetrics displaymetrics = ImageSearchApplication.getAppContext().getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_SP, dp, displaymetrics );
    }

    public static int getRandomColor(){
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }
}
