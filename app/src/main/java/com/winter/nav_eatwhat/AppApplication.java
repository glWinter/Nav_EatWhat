package com.winter.nav_eatwhat;



import com.tencent.mmkv.MMKV;
import com.winter.lib_common.BaseApplication;
import com.winter.lib_common.utils.Utils;

import org.litepal.LitePal;

public class AppApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        MMKV.initialize(this);
        LitePal.initialize(this);
    }
}
