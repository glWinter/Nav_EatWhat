package com.winter.nav_eatwhat.utils;

import android.widget.Toast;

public class ToastUtils {


    private static Toast toast;
    public static void show(String msg){
        if(toast == null){
            toast = Toast.makeText(App.getApp(),msg,Toast.LENGTH_SHORT);
        }else{
            toast.setText(msg);
        }
        toast.show();
    }
}
