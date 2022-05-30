package com.winter.nav_eatwhat.ui.adapter;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;


import com.winter.nav_eatwhat.data.bean.Food;
import com.winter.nav_eatwhat.data.bean.LibraryInfo;
import com.winter.nav_eatwhat.data.bean.TestAlbum;

import java.util.List;

public class DiffUtils {


    private DiffUtil.Callback mFoodDiffCallback;
    private DiffUtil.ItemCallback<Food> mFoodItemCallback;
    private DiffUtils() {
    }

    private static final DiffUtils S_DIFF_UTILS = new DiffUtils();

    public static DiffUtils getInstance() {
        return S_DIFF_UTILS;
    }
    public DiffUtil.ItemCallback<Food> getFoodItemCallback(){
        if(mFoodItemCallback == null){
            mFoodItemCallback = new DiffUtil.ItemCallback<Food>() {
                @Override
                public boolean areItemsTheSame(@NonNull Food oldItem, @NonNull Food newItem) {
                    return oldItem.equals(newItem);
                }

                @Override
                public boolean areContentsTheSame(@NonNull Food oldItem, @NonNull Food newItem) {
                    return oldItem.getFoodName().equals(newItem.getFoodName());
                }
            };
        }
        return mFoodItemCallback;
    }

    public DiffUtil.Callback getFoodDiffCallback(List<Food> oldList,List<Food> newList){
        if(mFoodDiffCallback == null){
            mFoodDiffCallback = new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return oldList.size();
                }

                @Override
                public int getNewListSize() {
                    return newList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    Food oldU = oldList.get(oldItemPosition);
                    Food newU = newList.get(newItemPosition);
                    // Name
                    return oldU.getFoodId() == newU.getFoodId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Food oldU = oldList.get(oldItemPosition);
                    Food newU = newList.get(newItemPosition);
                    // Name
                    return TextUtils.equals(oldU.getFoodName(), newU.getFoodName());
                }
            };
        }
        return mFoodDiffCallback;
    }
}
