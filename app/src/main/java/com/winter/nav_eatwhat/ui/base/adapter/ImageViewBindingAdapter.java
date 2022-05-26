package com.winter.nav_eatwhat.ui.base.adapter;

import android.text.TextUtils;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.android.liuzhuang.rcimageview.RoundCornerImageView;
import com.bumptech.glide.Glide;
import com.winter.nav_eatwhat.R;

public class ImageViewBindingAdapter {
    @BindingAdapter(value = {"imgUrl"}, requireAll = false)
    public static void openDrawer(RoundCornerImageView imageView, String imgUrl) {
        Glide.with(imageView.getContext()).load(imgUrl).into(imageView);
    }

    @BindingAdapter(value = {"thumbSrc"}, requireAll = false)
    public static void thumbSrc(ImageView imageView, String isThumbs) {
        if(TextUtils.equals("1",isThumbs)){
            imageView.setImageResource(R.drawable.thumb_fill);
        }else{
            imageView.setImageResource(R.drawable.thumb);
        }
    }
}
