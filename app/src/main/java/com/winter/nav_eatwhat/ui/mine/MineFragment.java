package com.winter.nav_eatwhat.ui.mine;

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
import com.winter.nav_eatwhat.BR;
import com.winter.nav_eatwhat.R;
import com.winter.nav_eatwhat.domain.message.SharedViewModel;
import com.winter.nav_eatwhat.ui.MainActivity;
import com.winter.nav_eatwhat.ui.edit.EditViewModel;
import com.winter.nav_eatwhat.ui.page.AddFoodActivity;


public class MineFragment extends BaseFragment {
    private SharedViewModel mEvent;

    MineViewModel mState;
    @Override
    protected void initViewModel() {
        mState = getFragmentScopeViewModel(MineViewModel.class);
        mEvent = getApplicationScopeViewModel(SharedViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_mine, BR.vm, mState)
                .addBindingParam(BR.click,new ClickProxy());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public class ClickProxy{
        public void gotoSc() {
            mEvent.requestHideBottomView(true);
            nav().navigate(R.id.action_mineFragment_to_scFragment);
        }
    }

}