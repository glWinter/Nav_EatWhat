package com.winter.nav_eatwhat.ui.state;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.winter.nav_eatwhat.data.bean.Food;

import java.util.List;

public class HistoryViewModel extends ViewModel {
    public final MutableLiveData<List<Food>> list = new MutableLiveData<>();
}
