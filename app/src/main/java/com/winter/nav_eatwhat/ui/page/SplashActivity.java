package com.winter.nav_eatwhat.ui.page;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.tencent.mmkv.MMKV;
import com.winter.lib_common.ui.page.BaseActivity;
import com.winter.lib_common.utils.ThreadUtils;
import com.winter.nav_eatwhat.BR;
import com.winter.nav_eatwhat.R;
import com.winter.nav_eatwhat.ui.MainActivity;
import com.winter.nav_eatwhat.ui.state.LoginViewModel;
import com.winter.nav_eatwhat.ui.state.SplashViewModel;

public class SplashActivity extends BaseActivity {

    SplashViewModel viewModel;

    @Override
    protected void initViewModel() {
        viewModel = getActivityScopeViewModel(SplashViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_splash_layout, BR.vm, viewModel);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.Theme_Nav_EatWhat);
        super.onCreate(savedInstanceState);
        ImageView imageView = findViewById(R.id.sp_bg);
        ThreadUtils.runOnUiThreadDelayed(new Runnable() {
            @Override
            public void run() {
                String account = MMKV.defaultMMKV().decodeString("account","");
                if(account == null || TextUtils.isEmpty(account)){
//                    LoginActivity.start(SplashActivity.this);
                    LoginActivity.actionStartWithTransition(SplashActivity.this,imageView);
                }else{
                    MainActivity.start(SplashActivity.this);
                }
            }
        },1000);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
