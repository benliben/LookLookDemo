package com.android.jam.looklookdemo;

import android.app.Application;

/**
 * Created by Jam on 16/9/21 下午6:20.
 * Describe:
 */
public class MyApplication extends Application {


    public static MyApplication myApplication;

    public static Application getContext() {
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        myApplication = this;
    }
}
