package com.winter.nav_eatwhat.ui;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.gyf.immersionbar.ImmersionBar;

import com.winter.nav_eatwhat.R;
import com.winter.nav_eatwhat.databinding.MainLoginActivityLayoutBinding;
import com.winter.nav_eatwhat.ui.base.BaseActivity;
import com.winter.nav_eatwhat.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;



public class LoginActivity extends BaseActivity {
    MainLoginActivityLayoutBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.main_login_activity_layout);

        binding.login.setOnClickListener(v ->{
            showLoading();
            String account = binding.account.getText().toString();
            String password = binding.password.getText().toString();
            if(TextUtils.isEmpty(account)||TextUtils.isEmpty(password)){
                ToastUtils.show("请输入用户名或者密码");
                return;
            }
            Map<String,String> map = new HashMap<>();
            map.put("account",account);
            map.put("password",password);


        });

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
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) { //需要隐藏软键盘

                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }
}
