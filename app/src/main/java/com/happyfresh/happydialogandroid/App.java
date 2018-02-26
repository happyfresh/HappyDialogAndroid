package com.happyfresh.happydialogandroid;

import android.app.Application;

/**
 * Created by adefruandta on 2/26/18.
 */

public class App extends Application {

    public static App sInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;
    }
}
