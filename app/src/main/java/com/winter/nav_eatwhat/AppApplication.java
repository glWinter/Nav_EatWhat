package com.winter.nav_eatwhat;



import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.mmkv.MMKV;
import com.winter.lib_common.BaseApplication;
import com.winter.lib_common.utils.Utils;

import org.litepal.LitePal;

public class AppApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext(), "1f9a47a2a1", false);
        Utils.init(this);
        MMKV.initialize(this);
        LitePal.initialize(this);
    }
}
