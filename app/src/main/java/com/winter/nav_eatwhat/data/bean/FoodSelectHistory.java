package com.winter.nav_eatwhat.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.crud.LitePalSupport;

import java.util.List;

public class FoodSelectHistory extends LitePalSupport implements Parcelable {
    private List<Food> foodList;
    private String date;

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    protected FoodSelectHistory(Parcel in) {
        date = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FoodSelectHistory> CREATOR = new Creator<FoodSelectHistory>() {
        @Override
        public FoodSelectHistory createFromParcel(Parcel in) {
            return new FoodSelectHistory(in);
        }

        @Override
        public FoodSelectHistory[] newArray(int size) {
            return new FoodSelectHistory[size];
        }
    };
}
