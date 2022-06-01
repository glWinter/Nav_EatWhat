package com.winter.nav_eatwhat.ui.page;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.winter.lib_common.ui.page.BaseActivity;
import com.winter.nav_eatwhat.BR;
import com.winter.nav_eatwhat.R;
import com.winter.nav_eatwhat.ui.edit.EditFragment;
import com.winter.nav_eatwhat.ui.state.AddFoodViewModel;

public class AddFoodActivity extends BaseActivity {
    AddFoodViewModel viewModel;
    @Override
    protected void initViewModel() {
        viewModel = getActivityScopeViewModel(AddFoodViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_add_food, BR.vm, viewModel);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViewById(R.id.btn).setOnClickListener(v -> CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(AddFoodActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK&&result!=null) {
                Uri resultUri = result.getUri();
                Glide.with(AddFoodActivity.this).load(resultUri).into((ImageView) findViewById(R.id.img));
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

    public static void start(Context context){
        context.startActivity(new Intent(context,AddFoodActivity.class));
    }
}
