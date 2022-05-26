package com.winter.nav_eatwhat.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;

import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.winter.lib_common.ui.page.BaseActivity;
import com.winter.nav_eatwhat.BR;
import com.winter.nav_eatwhat.R;
import com.winter.nav_eatwhat.domain.message.DrawerCoordinateManager;
import com.winter.nav_eatwhat.domain.message.SharedViewModel;

import com.winter.nav_eatwhat.ui.state.MainActivityViewModel;

public class MainActivity extends BaseActivity {

    private MainActivityViewModel mState;
    private SharedViewModel mEvent;

    @Override
    protected void initViewModel() {
        mState = getActivityScopeViewModel(MainActivityViewModel.class);
        mEvent = getApplicationScopeViewModel(SharedViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_main, BR.vm, mState);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }
    private void init() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(this, R.id.main_fragment_host);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }

    public static void start(Context context){
        context.startActivity(new Intent(context,MainActivity.class));
    }
}