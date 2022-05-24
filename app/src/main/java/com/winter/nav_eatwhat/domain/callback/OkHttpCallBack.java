package com.winter.nav_eatwhat.domain.callback;

import androidx.annotation.NonNull;

import java.io.IOException;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class OkHttpCallBack<T> implements Callback<T> {
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if(response.body()!=null){
            success(response.body());
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {

    }


    public abstract void success(T body);
}
