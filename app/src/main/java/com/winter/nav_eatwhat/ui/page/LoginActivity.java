package com.winter.nav_eatwhat.ui.page;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

import com.kunminx.architecture.ui.page.DataBindingConfig;
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

    public class ClickProxy {
        public void login() {
            ToastUtils.show(viewModel.phoneNum.getValue());
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
}
