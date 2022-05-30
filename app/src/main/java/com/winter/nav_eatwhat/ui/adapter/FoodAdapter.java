package com.winter.nav_eatwhat.ui.adapter;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kunminx.binding_recyclerview.adapter.SimpleDataBindingAdapter;
import com.winter.nav_eatwhat.R;
import com.winter.nav_eatwhat.data.bean.Food;
import com.winter.nav_eatwhat.databinding.FoodCardItemBinding;
import com.winter.nav_eatwhat.ui.edit.EditFragment;
import com.winter.nav_eatwhat.ui.edit.EditViewModel;


import java.util.List;


public class FoodAdapter extends SimpleDataBindingAdapter<Food, FoodCardItemBinding> {

    private View.OnClickListener clickListener;
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
        binding.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(item);
            }
        });
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
        view.findViewById(R.id.tv_cancel).setOnClickListener(view1 -> {
            if (mShareDialog != null && mShareDialog.isShowing()) {
                mShareDialog.dismiss();
            }
        });
        window.setContentView(view);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);//设置横向全屏
    }

}