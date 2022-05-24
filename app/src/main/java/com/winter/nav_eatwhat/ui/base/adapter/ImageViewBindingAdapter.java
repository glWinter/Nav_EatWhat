package com.winter.nav_eatwhat.ui.base.adapter;

import android.widget.ImageView;

import androidx.core.view.GravityCompat;
import androidx.databinding.BindingAdapter;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bumptech.glide.Glide;

public class ImageViewBindingAdapter {
    @BindingAdapter(value = {"imgUrl"}, requireAll = false)
    public static void openDrawer(ImageView imageView, String imgUrl) {
        Glide.with(imageView.getContext()).load(imgUrl).into(imageView);
    }
}
