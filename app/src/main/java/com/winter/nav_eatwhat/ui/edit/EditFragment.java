package com.winter.nav_eatwhat.ui.edit;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.winter.lib_common.ui.page.BaseFragment;
import com.winter.lib_common.utils.ToastUtils;
import com.winter.nav_eatwhat.BR;
import com.winter.nav_eatwhat.R;
import com.winter.nav_eatwhat.data.dao.FoodDao;
import com.winter.nav_eatwhat.ui.adapter.DiffUtils;
import com.winter.nav_eatwhat.ui.adapter.FoodDiffAdapter;
import com.winter.nav_eatwhat.ui.state.MainActivityViewModel;


public class EditFragment extends BaseFragment {

    EditViewModel mState;
    MainActivityViewModel mActivityScopeState;

    FoodDiffAdapter foodAdapter;
    @Override
    protected void initViewModel() {
        mState = getFragmentScopeViewModel(EditViewModel.class);
        mActivityScopeState = getActivityScopeViewModel(MainActivityViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        foodAdapter = new FoodDiffAdapter(getContext(),mState.list.getValue());

        return new DataBindingConfig(R.layout.fragment_edit, BR.vm, mState)
                .addBindingParam(BR.adapter, foodAdapter)
                .addBindingParam(BR.check,new CheckBoxListener());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.rv);
        recyclerView.setAdapter(foodAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(null);
        mState.foodListRequest.getFoodLiveData().observe(this, listDataResult -> {
            if (!listDataResult.getResponseStatus().isSuccess()) return;

            if (listDataResult.getResult() != null) {
                foodAdapter.setNewData(listDataResult.getResult());
            }
        });
        if (mState.foodListRequest.getFoodLiveData().getValue() == null) {
            mState.foodListRequest.requestFoodListInfo();
        }
    }

    public class CheckBoxListener implements CompoundButton.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//            mState.list.setValue(FoodDao.getInstance().getThumbFoods(isChecked));
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(DiffUtils.getInstance().getFoodDiffCallback(foodAdapter.getData(), FoodDao.getInstance().getThumbFoods(isChecked)));
            diffResult.dispatchUpdatesTo(foodAdapter);
            foodAdapter.setData(FoodDao.getInstance().getThumbFoods(isChecked));
        }
    }
}