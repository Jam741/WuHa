package com.pisces.android.test;

import android.app.Application;

import com.pisces.android.locationlibrary.GDLocationUtil;

/**
 * Created by Chris Li on 2017/9/12.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        GDLocationUtil.init(getApplicationContext());
    }

}
