package com.winter.nav_eatwhat.data.dao;

import com.winter.nav_eatwhat.data.bean.Food;

import org.litepal.LitePal;

import java.util.List;

public class FoodDao {
    private static class HOLDER {
        private static FoodDao foodDao = new FoodDao();
    }

    public static FoodDao getInstance() {
        return HOLDER.foodDao;
    }

    public void add(Food food) {
        List<Food> foods = LitePal.where("foodId = ?", String.valueOf(food.getFoodId())).find(Food.class);
        if (foods == null || foods.size() == 0) {
            food.save();
        }
    }

    public List<Food> getThumbFoods(boolean isThumb) {
        if (isThumb) {
            return LitePal.where("isThumbsUp = 1").find(Food.class);
        } else {
            return LitePal.findAll(Food.class);
        }
    }

    public void updateFoodThumb(Food food) {
        List<Food> foods = LitePal.where("foodId = ?", String.valueOf(food.getFoodId())).find(Food.class);
        Food f = foods.get(0);
        f.setIsThumbsUp(food.getIsThumbsUp());
        f.save();
    }

}
