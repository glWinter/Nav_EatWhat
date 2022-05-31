package com.winter.nav_eatwhat.ui.page;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.winter.lib_common.ui.page.BaseFragment;
import com.winter.nav_eatwhat.BR;
import com.winter.nav_eatwhat.R;
import com.winter.nav_eatwhat.data.bean.Food;
import com.winter.nav_eatwhat.data.dao.FoodDao;
import com.winter.nav_eatwhat.domain.message.SharedViewModel;
import com.winter.nav_eatwhat.ui.adapter.FoodAdapter;
import com.winter.nav_eatwhat.ui.mine.MineViewModel;
import com.winter.nav_eatwhat.ui.state.ScViewModel;

import java.util.List;

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
        FoodAdapter foodAdapter = new FoodAdapter(getContext());
        return new DataBindingConfig(R.layout.fragment_sc, BR.vm, scViewModel)
                .addBindingParam(BR.adapter,foodAdapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<Food> thumbFoods = FoodDao.getInstance().getThumbFoods(true);
        scViewModel.list.setValue(thumbFoods);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sharedViewModel.requestHideBottomView(false);
        sharedViewModel.requestHideFab(false);
    }
}
