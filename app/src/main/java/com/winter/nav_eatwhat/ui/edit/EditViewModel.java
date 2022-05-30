package com.winter.nav_eatwhat.ui.edit;

import android.view.View;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.winter.nav_eatwhat.data.bean.Food;
import com.winter.nav_eatwhat.data.bean.LibraryInfo;
import com.winter.nav_eatwhat.domain.request.FoodListRequest;
import com.winter.nav_eatwhat.domain.request.InfoRequest;

import java.util.List;

public class EditViewModel extends ViewModel implements View.OnClickListener {
    public final ObservableField<Integer> progress = new ObservableField<>();
    public final MutableLiveData<List<Food>> list = new MutableLiveData<>();
    public EditViewModel() {
    }
    public final FoodListRequest foodListRequest = new FoodListRequest();

    @Override
    public void onClick(View v) {

    }

}