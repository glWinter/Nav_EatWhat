package com.winter.nav_eatwhat.ui.edit;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import androidx.annotation.Nullable;

import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.winter.lib_common.ui.page.BaseFragment;
import com.winter.nav_eatwhat.BR;
import com.winter.nav_eatwhat.R;
import com.winter.nav_eatwhat.data.bean.Food;
import com.winter.nav_eatwhat.data.dao.FoodDao;
import com.winter.nav_eatwhat.ui.MainActivity;
import com.winter.nav_eatwhat.ui.adapter.FoodAdapter;
import com.winter.nav_eatwhat.ui.page.AddFoodActivity;
import com.winter.nav_eatwhat.ui.state.MainActivityViewModel;

import java.util.List;


public class EditFragment extends BaseFragment {

    EditViewModel mState;
    MainActivityViewModel mActivityScopeState;

    @Override
    protected void initViewModel() {
        mState = getFragmentScopeViewModel(EditViewModel.class);
        mActivityScopeState = getActivityScopeViewModel(MainActivityViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        FoodAdapter foodAdapter = new FoodAdapter(getContext(),mState);
        return new DataBindingConfig(R.layout.fragment_edit, BR.vm, mState)
                .addBindingParam(BR.adapter, foodAdapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mState.foodListRequest.getFoodLiveData().observe(this, listDataResult -> {
            if (!listDataResult.getResponseStatus().isSuccess()) return;
            if (listDataResult.getResult() != null) {
                mState.list.setValue(listDataResult.getResult());
            }
        });
        mState.foodListRequest.getFoodChangeLiveData().observe(this, foodDataResult -> {
            if (!foodDataResult.getResponseStatus().isSuccess()) return;
            if (foodDataResult.getResult() != null) {
                Log.d("test", foodDataResult.getResult().toString());
            }
        });
        List<Food> thumbFoods = FoodDao.getInstance().getThumbFoods(false);
        if (thumbFoods.size()==0&&mState.foodListRequest.getFoodLiveData().getValue() == null) {
            mState.foodListRequest.requestFoodListInfo();
        }else{
            mState.list.setValue(thumbFoods);
        }
    }
}