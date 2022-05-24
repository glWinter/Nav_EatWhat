package com.winter.nav_eatwhat.data.api;

import com.winter.nav_eatwhat.data.bean.BaseResponse;
import com.winter.nav_eatwhat.data.bean.Food;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Create by KunMinX at 2021/6/3
 */
public interface AccountService {

    @POST("login")
    @FormUrlEncoded
    Call<String> login(
        @Field("username") String username,
        @Field("password") String password
    );

    @GET("foodList")
    Call<BaseResponse<List<Food>>> getFoodList();
}
