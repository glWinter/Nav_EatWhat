package com.winter.nav_eatwhat.ui;


import android.os.Bundle;

import androidx.annotation.Nullable;

import com.gyf.immersionbar.ImmersionBar;
import com.tencent.mmkv.MMKV;
import com.winter.nav_eatwhat.R;
import com.winter.nav_eatwhat.ui.base.BaseActivity;


public class FirstActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_splash_activity_layout);
        boolean isLogin = MMKV.defaultMMKV().decodeBool("isLogin", false);
        if(isLogin){
            startActivityDelay(this, MainActivity.class,2000);
        }else{
            startActivityDelay(this, LoginActivity.class,2000);
        }
    }

    @Override
    public void setStatus() {
        ImmersionBar.with(this)
                .statusBarDarkFont(true)
                .navigationBarDarkIcon(true)
                .init();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
