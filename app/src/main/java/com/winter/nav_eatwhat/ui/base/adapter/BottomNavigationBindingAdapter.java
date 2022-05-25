package com.winter.nav_eatwhat.ui.base.adapter;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.navigation.NavController;
import androidx.navigation.ui.NavigationUI;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationBindingAdapter {
    @BindingAdapter(value = {"controller"}, requireAll = false)
    public static void setController(BottomNavigationView bottomNavigationView, NavController controller) {
        NavigationUI.setupWithNavController(bottomNavigationView, controller);
    }
}
