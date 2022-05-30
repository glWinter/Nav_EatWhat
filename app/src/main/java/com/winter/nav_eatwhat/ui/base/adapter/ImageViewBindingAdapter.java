package com.winter.nav_eatwhat.ui.base.adapter;

import android.text.TextUtils;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.android.liuzhuang.rcimageview.RoundCornerImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.winter.nav_eatwhat.R;

public class ImageViewBindingAdapter {
    @BindingAdapter(value = {"imgUrl"}, requireAll = false)
    public static void openDrawer(RoundCornerImageView imageView, String imgUrl) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.glide_def)//图片加载出来前，显示的图片
                .fallback( R.drawable.glide_def) //url为空的时候,显示的图片
                .error(R.drawable.glide_def);//图片加载失败后，显示的图片

        Glide.with(imageView.getContext())
                .load(imgUrl)
                .apply(options)
                .into(imageView);
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
