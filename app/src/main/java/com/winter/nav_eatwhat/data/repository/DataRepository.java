
package com.winter.nav_eatwhat.data.repository;

import android.util.Log;

import com.ayvytr.okhttploginterceptor.LoggingInterceptor;
import com.ayvytr.okhttploginterceptor.Priority;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.winter.lib_common.response.DataResult;
import com.winter.lib_common.response.ResponseStatus;
import com.winter.lib_common.response.ResultSource;
import com.winter.lib_common.utils.Utils;
import com.winter.nav_eatwhat.R;
import com.winter.nav_eatwhat.data.api.APIs;
import com.winter.nav_eatwhat.data.api.AccountService;
import com.winter.nav_eatwhat.data.bean.BaseResponse;
import com.winter.nav_eatwhat.data.bean.DownloadFile;
import com.winter.nav_eatwhat.data.bean.Food;
import com.winter.nav_eatwhat.data.bean.LibraryInfo;
import com.winter.nav_eatwhat.data.bean.TestAlbum;
import com.winter.nav_eatwhat.data.bean.User;
import com.winter.nav_eatwhat.data.dao.FoodDao;
import com.winter.nav_eatwhat.domain.callback.OkHttpCallBack;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class DataRepository {



    private static class HOLDER{
        private static final DataRepository S_REQUEST_MANAGER = new DataRepository();
    }
    private DataRepository() {
    }

    public static DataRepository getInstance() {
        return HOLDER.S_REQUEST_MANAGER;
    }

    private final Retrofit retrofit;

    {
        LoggingInterceptor interceptor = new LoggingInterceptor();
        interceptor.setShowAll(true);
        interceptor.setPriority(Priority.E);
        interceptor.setTag("eatWhat");

        OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(8, TimeUnit.SECONDS)
            .readTimeout(8, TimeUnit.SECONDS)
            .writeTimeout(8, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build();
        retrofit = new Retrofit.Builder()
            .baseUrl(APIs.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }

    private AccountService getApiService(){
        return retrofit.create(AccountService.class);
    }

    public void getFoodList(DataResult.Result<List<Food>> result){
        Call<BaseResponse<List<Food>>> callFoodList = getApiService().getFoodList();
        callFoodList.enqueue(new OkHttpCallBack<BaseResponse<List<Food>>>() {
            @Override
            public void success(BaseResponse<List<Food>> body) {
                if(body.data!=null&&body.errorCode == 200){
                    for (Food food : body.data) {
                        FoodDao.getInstance().add(food);
                    }
                    result.onResult(new DataResult<>(body.data, new ResponseStatus()));
                }

            }
        });
    }

    public void changeFoodIsThumb(String foodId,String isThumb,DataResult.Result<Food> result){
        Call<BaseResponse<Food>> changeCall = getApiService().changeThumb(foodId, isThumb);
        changeCall.enqueue(new OkHttpCallBack<BaseResponse<Food>>() {
            @Override
            public void success(BaseResponse<Food> body) {
                result.onResult(new DataResult<>(body.data, new ResponseStatus()));
            }
        });
    }



    //TODO tip????????????????????????????????????
    //
    // Call ???????????????????????????????????????????????????????????? accountRequest???
    // ?????????????????????????????????????????????????????????????????????????????????
    // ???????????????????????????????????????????????????????????????????????????????????????????????????

    private Call<String> mUserCall;

    /**
     * TODO ???????????????????????????
     *
     * @param user   ui ????????????????????????
     * @param result ??????????????????????????? token
     */
    public void login(User user, DataResult.Result<String> result) {
        mUserCall = retrofit.create(AccountService.class).login(user.getName(), user.getPassword());
        mUserCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NotNull Call<String> call, @NotNull Response<String> response) {
                ResponseStatus responseStatus = new ResponseStatus(
                    String.valueOf(response.code()), response.isSuccessful(), ResultSource.NETWORK);
                result.onResult(new DataResult<>(response.body(), responseStatus));
                mUserCall = null;
            }

            @Override
            public void onFailure(@NotNull Call<String> call, @NotNull Throwable t) {
                result.onResult(new DataResult<>(null,
                    new ResponseStatus(t.getMessage(), false, ResultSource.NETWORK)));
                mUserCall = null;
            }
        });
    }

    public void cancelLogin() {
        if (mUserCall != null && !mUserCall.isCanceled()) {
            mUserCall.cancel();
            mUserCall = null;
        }
    }

}
