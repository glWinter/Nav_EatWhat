package com.winter.lib_common.utils;

import android.os.Handler;
import android.os.Looper;

public class ThreadUtils {
    private static final Handler HANDLER = new Handler(Looper.getMainLooper());
    public static void runOnUiThreadDelayed(final Runnable runnable, long delayMillis) {
        HANDLER.postDelayed(runnable, delayMillis);
    }
}
