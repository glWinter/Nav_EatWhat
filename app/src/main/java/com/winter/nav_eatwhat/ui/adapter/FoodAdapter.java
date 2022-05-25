package com.winter.nav_eatwhat.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.kunminx.binding_recyclerview.adapter.SimpleDataBindingAdapter;
import com.winter.lib_common.utils.ToastUtils;
import com.winter.nav_eatwhat.R;
import com.winter.nav_eatwhat.data.bean.Food;
import com.winter.nav_eatwhat.databinding.FoodItemBinding;

public class FoodAdapter extends SimpleDataBindingAdapter<Food, FoodItemBinding> {
    public FoodAdapter(Context context) {
        super(context, R.layout.food_item, DiffUtils.getInstance().getFoodItemCallback());
    }

    public void setClickItem(OnItemClickListener<Food> listener){
        setOnItemClickListener(listener);
    }
    @Override
    protected void onBindItem(FoodItemBinding binding, Food item, RecyclerView.ViewHolder holder) {
        binding.setFood(item);
    }
}
