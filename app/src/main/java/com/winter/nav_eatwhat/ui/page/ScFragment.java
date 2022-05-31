package com.winter.nav_eatwhat.ui.page;

import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.winter.lib_common.ui.page.BaseFragment;
import com.winter.nav_eatwhat.BR;
import com.winter.nav_eatwhat.R;
import com.winter.nav_eatwhat.domain.message.SharedViewModel;
import com.winter.nav_eatwhat.ui.mine.MineViewModel;
import com.winter.nav_eatwhat.ui.state.ScViewModel;

public class ScFragment extends BaseFragment {
    ScViewModel scViewModel;
    SharedViewModel sharedViewModel;
    @Override
    protected void initViewModel() {
        scViewModel = getFragmentScopeViewModel(ScViewModel.class);
        sharedViewModel = getApplicationScopeViewModel(SharedViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_sc, BR.vm, scViewModel);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sharedViewModel.requestHideBottomView(false);
    }
}
