package com.winter.nav_eatwhat.ui.page;

import android.content.Context;
import android.content.Intent;

import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.winter.lib_common.ui.page.BaseActivity;
import com.winter.nav_eatwhat.BR;
import com.winter.nav_eatwhat.R;
import com.winter.nav_eatwhat.ui.edit.EditFragment;
import com.winter.nav_eatwhat.ui.state.AddFoodViewModel;

public class AddFoodActivity extends BaseActivity {
    AddFoodViewModel viewModel;
    @Override
    protected void initViewModel() {
        viewModel = getActivityScopeViewModel(AddFoodViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_add_food, BR.vm, viewModel);
    }

    public static void start(Context context){
        context.startActivity(new Intent(context,AddFoodActivity.class));
    }
}
