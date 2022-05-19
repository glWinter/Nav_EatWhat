package com.winter.nav_eatwhat.utils;

import android.app.Application;

public class App {
    public static final String FOOD_IS_SOUP = "1";
    public static final String FOOD_NOT_SOUP = "0";

    public static final String FOOD_EASY = "1";
    public static final String FOOD_NORMAL = "2";
    public static final String FOOD_DIFFICULT = "3";

    public static Application getApp(){
        Application application = null;
        try {
            Class<?> activityThread = Class.forName("android.app.ActivityThread");
            Object thread = activityThread.getMethod("currentActivityThread").invoke(null);
            application = (Application) activityThread.getMethod("getApplication").invoke(thread);
        }catch (Exception e){
            e.printStackTrace();
        }

        return application;
    }
}
