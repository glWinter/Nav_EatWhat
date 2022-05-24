package com.winter.nav_eatwhat.ui;

import android.os.Bundle;
import android.view.View;


import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

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
    private boolean mIsListened = false;


    @Override
    protected void initViewModel() {
        mState = getActivityScopeViewModel(MainActivityViewModel.class);
        mEvent = getApplicationScopeViewModel(SharedViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_main, BR.vm, mState)
                .addBindingParam(BR.listener, new ListenerHandler());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }
    private void init() {
        mEvent.isToCloseActivityIfAllowed().observe(this, aBoolean -> {
            NavController nav = Navigation.findNavController(this, R.id.main_fragment_host);
            if (nav.getCurrentDestination() != null && nav.getCurrentDestination().getId() != R.id.navigation_dashboard) {
                nav.navigateUp();

            } else if (mState.isDrawerOpened.get()) {
                mEvent.requestToOpenOrCloseDrawer(false);
            } else {
                super.onBackPressed();
            }
        });

        mEvent.isToOpenOrCloseDrawer().observe(this, aBoolean -> {
            mState.openDrawer.setValue(aBoolean);
        });

        DrawerCoordinateManager.getInstance().isEnableSwipeDrawer().observe(this, aBoolean -> {
            mState.allowDrawerOpen.setValue(aBoolean);
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (!mIsListened) {
            mEvent.requestToAddSlideListener(true);
            mIsListened = true;
        }
    }

    @Override
    public void onBackPressed() {
        mEvent.requestToOpenOrCloseDrawer(true);

    }

    public class ListenerHandler extends DrawerLayout.SimpleDrawerListener {
        @Override
        public void onDrawerOpened(View drawerView) {
            super.onDrawerOpened(drawerView);
            mState.isDrawerOpened.set(true);
        }

        @Override
        public void onDrawerClosed(View drawerView) {
            super.onDrawerClosed(drawerView);
            mState.isDrawerOpened.set(false);
            mState.openDrawer.setValue(false);
        }
    }
}