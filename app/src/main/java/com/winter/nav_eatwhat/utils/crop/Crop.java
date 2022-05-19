package com.winter.nav_eatwhat.utils.crop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

import androidx.activity.result.ActivityResultLauncher;

public class Crop {
    public static final int RESULT_ERROR = 404;
    interface Extra {
        String ASPECT_X = "aspect_x";
        String ASPECT_Y = "aspect_y";
        String MAX_X = "max_x";
        String MAX_Y = "max_y";
        String AS_PNG = "as_png";
        String ERROR = "error";
    }
    private Intent cropIntent;

    public static Crop of(Uri source, String outPath) {
        return new Crop(source, outPath);
    }

    private Crop(Uri source, String outPath) {
        cropIntent = new Intent();
        cropIntent.setData(source);
        cropIntent.putExtra(MediaStore.EXTRA_OUTPUT, outPath);
    }

    public static void pickImage(ActivityResultLauncher<Intent> intentActivityResultLauncher) {
        intentActivityResultLauncher.launch(getImagePicker());
    }
    private static Intent getImagePicker() {
        return new Intent(Intent.ACTION_GET_CONTENT).setType("image/*");
    }

    public Crop asSquare() {
        cropIntent.putExtra(Extra.ASPECT_X, 1);
        cropIntent.putExtra(Extra.ASPECT_Y, 1);
        return this;
    }

    public void start(Activity activity,ActivityResultLauncher<Intent> intentActivityResultLauncher) {
        intentActivityResultLauncher.launch(getIntent(activity));
    }

    public Intent getIntent(Context context) {
        cropIntent.setClass(context, CropImageActivity.class);
        return cropIntent;
    }
    public static String getOutput(Intent result) {
        return result.getStringExtra(MediaStore.EXTRA_OUTPUT);
    }
}
