package com.winter.nav_eatwhat.ui.select;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.winter.lib_common.ui.page.BaseFragment;
import com.winter.lib_common.utils.ToastUtils;
import com.winter.nav_eatwhat.BR;
import com.winter.nav_eatwhat.R;

import com.winter.nav_eatwhat.ui.edit.EditFragment;
import com.winter.nav_eatwhat.ui.mine.MineViewModel;
import com.winter.nav_eatwhat.ui.page.AddFoodActivity;


public class SelectFragment extends BaseFragment {


    SelectViewModel mState;
    @Override
    protected void initViewModel() {
        mState = getFragmentScopeViewModel(SelectViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_select, BR.vm, mState)
                .addBindingParam(BR.click, new ClickProxy());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public class ClickProxy{
        public void history() {
            AddFoodActivity.start(requireActivity());
        }
        public void select(){
            ToastUtils.show("select");
        }
    }
}