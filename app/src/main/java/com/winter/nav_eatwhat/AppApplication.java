package com.winter.nav_eatwhat;

import android.app.Application;

import com.tencent.mmkv.MMKV;

public class AppApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MMKV.initialize(this);
    }

}
