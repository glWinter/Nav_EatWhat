package com.winter.nav_eatwhat.data.bean;


import android.text.TextUtils;

import androidx.annotation.DrawableRes;

import com.winter.nav_eatwhat.R;

public class Food {


    private String foodName;

    private String foodType;

    private String foodLevel;

    private String foodOption;

    private Integer foodId;

    private String foodIsVegetable;

    private String foodAddTime;

    private String foodImgUrl;

    private String foodUseTime;

    private String foodDesc;

    private String isThumbsUp;

    public String getIsThumbsUp() {
        return isThumbsUp;
    }

    public void setIsThumbsUp(String isThumbsUp) {
        this.isThumbsUp = isThumbsUp;
    }

    public String getFoodUseTime() {
        return foodUseTime;
    }

    public void setFoodUseTime(String foodUseTime) {
        this.foodUseTime = foodUseTime;
    }

    public String getFoodDesc() {
        return foodDesc;
    }

    public void setFoodDesc(String foodDesc) {
        this.foodDesc = foodDesc;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public String getFoodLevel() {
        return foodLevel;
    }

    public void setFoodLevel(String foodLevel) {
        this.foodLevel = foodLevel;
    }

    public String getFoodOption() {
        return foodOption;
    }

    public void setFoodOption(String foodOption) {
        this.foodOption = foodOption;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public String getFoodIsVegetable() {
        return foodIsVegetable;
    }

    public void setFoodIsVegetable(String foodIsVegetable) {
        this.foodIsVegetable = foodIsVegetable;
    }

    public String getFoodAddTime() {
        return foodAddTime;
    }

    public void setFoodAddTime(String foodAddTime) {
        this.foodAddTime = foodAddTime;
    }

    public String getFoodImgUrl() {
        return foodImgUrl;
    }

    public void setFoodImgUrl(String foodImgUrl) {
        this.foodImgUrl = foodImgUrl;
    }


    @Override
    public String toString() {
        return "Food{" +
                "foodName='" + foodName + '\'' +
                ", foodType='" + foodType + '\'' +
                ", foodLevel='" + foodLevel + '\'' +
                ", foodOption='" + foodOption + '\'' +
                ", foodId=" + foodId +
                ", foodIsVegetable='" + foodIsVegetable + '\'' +
                ", foodAddTime='" + foodAddTime + '\'' +
                ", foodImgUrl='" + foodImgUrl + '\'' +
                ", foodUseTime='" + foodUseTime + '\'' +
                ", foodDesc='" + foodDesc + '\'' +
                '}';
    }
}
