package com.winter.nav_eatwhat.ui.base.adapter;

import android.text.TextWatcher;
import android.widget.EditText;

import androidx.databinding.BindingAdapter;

import com.android.liuzhuang.rcimageview.RoundCornerImageView;
import com.bumptech.glide.Glide;

public class EditTextViewBindingAdapter {
    @BindingAdapter(value = {"watcher"}, requireAll = false)
    public static void watcher(EditText editText, TextWatcher watcher) {
       editText.addTextChangedListener(watcher);
    }

}
