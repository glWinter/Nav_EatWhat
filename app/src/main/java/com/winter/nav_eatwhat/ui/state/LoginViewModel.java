package com.winter.nav_eatwhat.ui.state;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {
    public MutableLiveData<String> phoneNum = new MutableLiveData<>();

    public MutableLiveData<Boolean> buttonEnable = new MutableLiveData<>();

    public LoginViewModel(){
        phoneNum.setValue("");
        buttonEnable.setValue(false);
    }
}
