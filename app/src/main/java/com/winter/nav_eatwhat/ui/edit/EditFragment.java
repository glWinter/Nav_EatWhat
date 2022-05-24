package com.winter.nav_eatwhat.ui.edit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.winter.lib_common.response.DataResult;
import com.winter.lib_common.ui.page.BaseFragment;
import com.winter.nav_eatwhat.BR;
import com.winter.nav_eatwhat.R;
import com.winter.nav_eatwhat.data.bean.Food;
import com.winter.nav_eatwhat.ui.adapter.DrawerAdapter;
import com.winter.nav_eatwhat.ui.adapter.FoodAdapter;
import com.winter.nav_eatwhat.ui.drawer.DrawerFragment;
import com.winter.nav_eatwhat.ui.state.DrawerViewModel;
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
        return new DataBindingConfig(R.layout.fragment_edit, BR.vm, mState)
                .addBindingParam(BR.adapter, new FoodAdapter(getContext()));
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
    public class ClickProxy {
        public void requestFoodList() {
            mActivityScopeState.downloadRequest.requestDownloadFile();
        }
    }
}