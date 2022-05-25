package com.winter.nav_eatwhat.ui.base.adapter;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.android.liuzhuang.rcimageview.RoundCornerImageView;
import com.bumptech.glide.Glide;

public class ImageViewBindingAdapter {
    @BindingAdapter(value = {"imgUrl"}, requireAll = false)
    public static void openDrawer(RoundCornerImageView imageView, String imgUrl) {
        Glide.with(imageView.getContext()).load(imgUrl).into(imageView);
    }
}
