package com.yonatanbetzer.imagesearch.utils;

import android.graphics.Typeface;

import com.yonatanbetzer.imagesearch.application.ImageSearchApplication;

public class Constants {
    public static int RESULTS_PER_PAGE = 100;
    public static int IMAGE_HEIGHT = 80;
    private static final String OPEN_SANS_REGULAR_HEBREW = "OpenSansHebrew-Regular.ttf";

    public static final Typeface openSansRegularHebrew = Typeface.createFromAsset(ImageSearchApplication.getAppContext().getAssets(),Constants.OPEN_SANS_REGULAR_HEBREW);
}
