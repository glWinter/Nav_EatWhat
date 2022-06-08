package com.winter.nav_eatwhat.ui.page;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.transition.Transition;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.transition.Fade;
import androidx.transition.TransitionManager;

import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.tencent.mmkv.MMKV;
import com.winter.lib_common.ui.page.BaseActivity;
import com.winter.lib_common.utils.ToastUtils;
import com.winter.nav_eatwhat.BR;
import com.winter.nav_eatwhat.R;
import com.winter.nav_eatwhat.ui.MainActivity;
import com.winter.nav_eatwhat.ui.state.LoginViewModel;
import com.winter.nav_eatwhat.ui.state.MainActivityViewModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends BaseActivity {
    LoginViewModel viewModel;
    protected boolean isTransitioning = false;
    @Override
    protected void initViewModel() {
        viewModel = getActivityScopeViewModel(LoginViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_login_layout, BR.vm, viewModel)
                .addBindingParam(BR.click, new ClickProxy())
                .addBindingParam(BR.watcher, new PhoneTextWatcher());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginLayoutBottom = findViewById(R.id.loginLayoutBottom);
        boolean isStartWithTransition = getIntent().getBooleanExtra(LoginActivity.START_WITH_TRANSITION, false);
        if(isStartWithTransition){
            isTransitioning = true;
            getWindow().getSharedElementEnterTransition().addListener(new SimpleTransitionListener(){
                @Override
                public void onTransitionEnd(Transition transition) {
                    super.onTransitionEnd(transition);
                    isTransitioning = false;
                    fadeElementsIn();
                }
            });
        }
    }
    LinearLayout loginLayoutBottom;

    private void fadeElementsIn() {
        TransitionManager.beginDelayedTransition(loginLayoutBottom, new Fade());
        loginLayoutBottom.setVisibility(View.VISIBLE);
    }
    public class ClickProxy {
        public void login() {
            MMKV.defaultMMKV().encode("account",viewModel.phoneNum.getValue());
            MainActivity.start(LoginActivity.this);
        }
    }

    public class PhoneTextWatcher implements TextWatcher {
        String pattern = "^(13[0-9]|15[012356789]|17[013678]|18[0-9]|14[57]|19[89]|166)[0-9]{8}";
        Pattern r = Pattern.compile(pattern);
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            viewModel.phoneNum.setValue(s.toString());
            String str = s.toString();
            Matcher m = r.matcher(str);
            viewModel.buttonEnable.setValue(m.matches());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    public static void start(Context context){
        context.startActivity(new Intent(context,LoginActivity.class));
    }

    private static String  START_WITH_TRANSITION = "start_with_transition";
    private static String  ACTION_LOGIN_WITH_TRANSITION = "com.winter.nav_eatwhat.ACTION_LOGIN_WITH_TRANSITION";
    private static String  ACTION_LOGIN = "com.winter.nav_eatwhat.ACTION_LOGIN";
    public static void actionStartWithTransition(Activity activity, View logoView) {
        Intent intent = new Intent(ACTION_LOGIN_WITH_TRANSITION);
        intent.putExtra(START_WITH_TRANSITION, true);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(activity, logoView,
                activity.getString(R.string.transition_logo_splash));
        activity.startActivity(intent, options.toBundle());
    }

    public static class SimpleTransitionListener implements Transition.TransitionListener{

        @Override
        public void onTransitionStart(Transition transition) {

        }

        @Override
        public void onTransitionEnd(Transition transition) {

        }

        @Override
        public void onTransitionCancel(Transition transition) {

        }

        @Override
        public void onTransitionPause(Transition transition) {

        }

        @Override
        public void onTransitionResume(Transition transition) {

        }
    }
}
