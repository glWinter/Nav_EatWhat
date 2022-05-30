package com.winter.nav_eatwhat.ui.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.rxbinding4.view.RxView;
import com.kunminx.binding_recyclerview.adapter.SimpleDataBindingAdapter;
import com.winter.lib_common.utils.ClickUtils;
import com.winter.nav_eatwhat.R;
import com.winter.nav_eatwhat.data.bean.Food;
import com.winter.nav_eatwhat.data.dao.FoodDao;
import com.winter.nav_eatwhat.databinding.FoodCardItemBinding;
import com.winter.nav_eatwhat.ui.edit.EditFragment;
import com.winter.nav_eatwhat.ui.edit.EditViewModel;


import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.functions.Consumer;
import kotlin.Unit;


public class FoodAdapter extends SimpleDataBindingAdapter<Food, FoodCardItemBinding> {
    private static final String THUMBS = "1";
    private static final String NO_THUMBS = "0";

    public FoodAdapter(Context context) {
        super(context, R.layout.food_card_item, DiffUtils.getInstance().getFoodItemCallback());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        }
    }

    @Override
    protected void onBindItem(FoodCardItemBinding binding, Food item, RecyclerView.ViewHolder holder) {
        binding.setFood(item);
        binding.menu.setOnClickListener(v -> showDialog(item));
    }

    Dialog mShareDialog;

    private void showDialog(Food item) {
        if (mShareDialog == null) {
            initShareDialog(item);
        }
        mShareDialog.show();
    }

    private void initShareDialog(Food item) {
        mShareDialog = new Dialog(mContext, R.style.dialog_bottom_full);
        mShareDialog.setCanceledOnTouchOutside(true);
        mShareDialog.setCancelable(true);
        Window window = mShareDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.share_animation);
        View view = View.inflate(mContext, R.layout.bottom_dialog, null);
        view.findViewById(R.id.tv_cancel).setOnClickListener(v -> {
            if (mShareDialog != null && mShareDialog.isShowing()) {
                mShareDialog.dismiss();
            }
        });
        TextView foodName = view.findViewById(R.id.foodName);
        foodName.setText(item.getFoodName());
        TextView sc = view.findViewById(R.id.sc);
        setTextDrawable(sc, item.getIsThumbsUp());
        ClickUtils.setClick(sc, 2, new ClickUtils.onClickListener() {
            @Override
            public void onClick() {
                if (TextUtils.equals(item.getIsThumbsUp(), THUMBS)) {
                    setTextDrawable(sc, NO_THUMBS);
                    item.setIsThumbsUp(NO_THUMBS);
                } else {
                    setTextDrawable(sc, THUMBS);
                    item.setIsThumbsUp(THUMBS);
                }
                FoodDao.getInstance().updateFoodThumb(item);
            }
        });
        window.setContentView(view);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);//设置横向全屏
    }

    private void setTextDrawable(TextView sc, String isThumb) {
        if (TextUtils.equals(isThumb, THUMBS)) {
            Drawable drawable = mContext.getResources().getDrawable(R.drawable.sc_fill);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            sc.setCompoundDrawables(null, drawable, null, null);
        } else {
            Drawable drawable = mContext.getResources().getDrawable(R.drawable.sc);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            sc.setCompoundDrawables(null, drawable, null, null);
        }
    }

}