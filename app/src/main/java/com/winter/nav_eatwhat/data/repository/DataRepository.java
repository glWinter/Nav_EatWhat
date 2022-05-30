
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

    public void getFreeMusic(DataResult.Result<TestAlbum> result) {
        Gson gson = new Gson();
        Type type = new TypeToken<TestAlbum>() {
        }.getType();
        TestAlbum testAlbum = gson.fromJson(Utils.getApp().getString(R.string.free_music_json), type);

        result.onResult(new DataResult<>(testAlbum, new ResponseStatus()));
    }

    public void getLibraryInfo(DataResult.Result<List<LibraryInfo>> result) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<LibraryInfo>>() {
        }.getType();
        List<LibraryInfo> list = gson.fromJson(Utils.getApp().getString(R.string.library_json), type);

        result.onResult(new DataResult<>(list, new ResponseStatus()));
    }

    /**
     * TODO：模拟下载任务:
     * 可分别用于 普通的请求，和可跟随页面生命周期叫停的请求，
     * 具体可见 ViewModel 和 UseCase 中的使用。
     *
     * @param result 从 Request-ViewModel 或 UseCase 注入 LiveData，用于 控制流程、回传进度、回传文件
     */
    public void downloadFile(DownloadFile downloadFile, DataResult.Result<DownloadFile> result) {

        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                //模拟下载，假设下载一个文件要 10秒、每 100 毫秒下载 1% 并通知 UI 层
                if (downloadFile.getProgress() < 100) {
                    downloadFile.setProgress(downloadFile.getProgress() + 1);
                    Log.d("TAG", "下载进度 " + downloadFile.getProgress() + "%");
                } else {
                    timer.cancel();
                }
                if (downloadFile.isForgive()) {
                    timer.cancel();
                    downloadFile.setProgress(0);
                    downloadFile.setForgive(false);
                    return;
                }
                result.onResult(new DataResult<>(downloadFile, new ResponseStatus()));
            }
        };

        timer.schedule(task, 100, 100);
    }

    //TODO tip：模拟可取消的登录请求：
    //
    // Call 上升为成员实例，配合可观察页面生命周期的 accountRequest，
    // 从而在页面即将退出、且登录请求由于网络延迟尚未完成时，
    // 及时通知数据层取消本次请求，以避免资源浪费和一系列不可预期的问题。

    private Call<String> mUserCall;

    /**
     * TODO 模拟登录的网络请求
     *
     * @param user   ui 层填写的用户信息
     * @param result 模拟网络请求返回的 token
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
