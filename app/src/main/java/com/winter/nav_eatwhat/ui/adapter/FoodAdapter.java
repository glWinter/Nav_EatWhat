package com.winter.nav_eatwhat.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.kunminx.binding_recyclerview.adapter.SimpleDataBindingAdapter;
import com.winter.lib_common.utils.ToastUtils;
import com.winter.nav_eatwhat.R;
import com.winter.nav_eatwhat.data.bean.Food;
import com.winter.nav_eatwhat.databinding.FoodCardItemBinding;
import com.winter.nav_eatwhat.databinding.FoodItemBinding;

import java.util.List;

public class FoodAdapter extends SimpleDataBindingAdapter<Food, FoodCardItemBinding> {

    private static final String THUMBS = "1";
    private static final String NO_THUMBS = "0";
    public FoodAdapter(Context context) {
        super(context, R.layout.food_card_item, DiffUtils.getInstance().getFoodItemCallback());
    }

    public void setClickItem(OnItemClickListener<Food> listener){
        setOnItemClickListener(listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            ImageView imageView = holder.itemView.findViewById(R.id.thump);
            if(payloads.get(0).equals(THUMBS)){
                imageView.setImageResource(R.drawable.thumb_fill);
            }else{
                imageView.setImageResource(R.drawable.thumb);
            }
        }
    }

    @Override
    protected void onBindItem(FoodCardItemBinding binding, Food item, RecyclerView.ViewHolder holder) {
        binding.setFood(item);
        binding.thump.setOnClickListener(v -> {
            if(TextUtils.equals(item.getIsThumbsUp(),THUMBS)){
                item.setIsThumbsUp(NO_THUMBS);
                notifyItemChanged(getCurrentList().indexOf(item),NO_THUMBS);
            }else{
                item.setIsThumbsUp(THUMBS);
                notifyItemChanged(getCurrentList().indexOf(item),THUMBS);
            }
        });
    }
}
