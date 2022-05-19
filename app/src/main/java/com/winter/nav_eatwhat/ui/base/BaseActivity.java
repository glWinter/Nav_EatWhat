package com.winter.nav_eatwhat.ui.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gyf.immersionbar.ImmersionBar;
import com.winter.nav_eatwhat.R;
import com.winter.nav_eatwhat.utils.dialog.MyLoading;


public class BaseActivity extends AppCompatActivity {
    private Handler handler = null;
    private MyLoading mmLoading;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new Handler(getMainLooper());
        setStatus();

    }
    public void setStatus(){
        ImmersionBar.with(this)
                .statusBarColor(R.color.white)
                .statusBarDarkFont(true)
                .navigationBarDarkIcon(true)
                .init();
    }
    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
    protected void showLoading() {
        if (mmLoading == null) {
            MyLoading.Builder builder = new MyLoading.Builder(this)
                    .setCancelable(false)
                    .setCancelOutside(false);
            mmLoading = builder.create();
        }else {
            mmLoading.dismiss();
            MyLoading.Builder builder = new MyLoading.Builder(this)
                    .setCancelable(false)
                    .setCancelOutside(false);
            mmLoading = builder.create();
        }
        mmLoading.show();
    }
    protected void hideLoading() {
        if (mmLoading != null && mmLoading.isShowing()) {
            mmLoading.dismiss();
        }
    }

    public void startActivityDelay(Context context, Class<? extends BaseActivity> clazz, long time){
        handler.postDelayed(() -> context.startActivity(new Intent(context,clazz)),time);
    }
}
