package com.yonatanbetzer.imagesearch.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

public class ImageSearchApplication extends Application {
    private static ImageSearchApplication mInstance;
    private static Context mAppContext;
    private static Activity currentActivity;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mAppContext = getApplicationContext();
    }

    public static ImageSearchApplication getInstance(){
        return mInstance;
    }

    public static Context getAppContext() {
        return mAppContext;
    }

    public static Activity getCurrentActivity() {
        return currentActivity;
    }

    public static void setCurrentActivity(Activity currentActivity) {
        ImageSearchApplication.currentActivity = currentActivity;
    }
}
