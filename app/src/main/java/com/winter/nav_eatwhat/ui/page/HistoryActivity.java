package com.winter.nav_eatwhat.ui.page;

import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.tencent.bugly.crashreport.CrashReport;
import com.winter.lib_common.ui.page.BaseActivity;
import com.winter.nav_eatwhat.BR;
import com.winter.nav_eatwhat.R;
import com.winter.nav_eatwhat.ui.state.HistoryViewModel;

public class HistoryActivity extends BaseActivity {
    HistoryViewModel viewModel;
    @Override
    protected void initViewModel() {
        viewModel = getActivityScopeViewModel(HistoryViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_history_food, BR.vm, viewModel);
    }
}
