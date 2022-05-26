package com.winter.nav_eatwhat.ui.edit;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import androidx.annotation.Nullable;

import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.winter.lib_common.ui.page.BaseFragment;
import com.winter.lib_common.utils.ToastUtils;
import com.winter.nav_eatwhat.BR;
import com.winter.nav_eatwhat.R;
import com.winter.nav_eatwhat.ui.adapter.FoodAdapter;
import com.winter.nav_eatwhat.ui.page.AddFoodActivity;
import com.winter.nav_eatwhat.ui.state.MainActivityViewModel;


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
        FoodAdapter foodAdapter = new FoodAdapter(getContext());
        foodAdapter.setClickItem((viewId, item, position) -> ToastUtils.show(item.toString()));
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
        if (mState.foodListRequest.getFoodLiveData().getValue() == null) {
            mState.foodListRequest.requestFoodListInfo();
        }
    }
}