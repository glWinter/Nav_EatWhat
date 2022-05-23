package com.winter.lib_common.utils;

import android.widget.Toast;

public class ToastUtils {


    private static Toast toast;
    public static void show(String msg){
        if(toast == null){
            toast = Toast.makeText(Utils.getApp(),msg,Toast.LENGTH_SHORT);
        }else{
            toast.setText(msg);
        }
        toast.show();
    }
}
