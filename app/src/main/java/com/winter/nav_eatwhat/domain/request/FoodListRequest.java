package com.winter.nav_eatwhat.domain.request;

import com.kunminx.architecture.ui.callback.UnPeekLiveData;
import com.winter.lib_common.domain.request.BaseRequest;
import com.winter.lib_common.response.DataResult;
import com.winter.nav_eatwhat.data.bean.Food;
import com.winter.nav_eatwhat.data.repository.DataRepository;


import java.util.List;

public class FoodListRequest extends BaseRequest {

    private final UnPeekLiveData<DataResult<List<Food>>> mFoodLiveData = new UnPeekLiveData<>();

    private final UnPeekLiveData<DataResult<Food>> mFoodChangeLiveData = new UnPeekLiveData<>();

    public UnPeekLiveData<DataResult<List<Food>>> getFoodLiveData() {
        return mFoodLiveData;
    }

    public UnPeekLiveData<DataResult<Food>> getFoodChangeLiveData() {
        return mFoodChangeLiveData;
    }

    public void requestFoodThumb(String foodId,String isThumb){
        DataRepository.getInstance().changeFoodIsThumb(foodId,isThumb,mFoodChangeLiveData::setValue);
    }

    public void requestFoodListInfo() {
        DataRepository.getInstance().getFoodList(mFoodLiveData::setValue);
    }


}
