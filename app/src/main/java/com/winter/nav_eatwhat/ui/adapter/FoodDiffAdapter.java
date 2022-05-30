package com.winter.nav_eatwhat.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.winter.nav_eatwhat.R;
import com.winter.nav_eatwhat.data.bean.Food;
import com.winter.nav_eatwhat.databinding.FoodCardItemBinding;

import java.util.List;

public class FoodDiffAdapter extends RecyclerView.Adapter<FoodDiffAdapter.FoodViewHolder> {
    private Context context;
    private List<Food> foodList;

    public List<Food> getData() {
        return foodList;
    }

    public static class FoodViewHolder extends RecyclerView.ViewHolder{
        private ImageView pic;
        private ImageView thump;
        private TextView foodName;
        private TextView desc;
        private TextView useTime;
        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);

            pic = itemView.findViewById(R.id.pic);
            thump = itemView.findViewById(R.id.thump);
            foodName = itemView.findViewById(R.id.foodName);
            desc = itemView.findViewById(R.id.foodDesc);
            useTime = itemView.findViewById(R.id.foodUseTime);
        }
    }
    private static final String THUMBS = "1";
    private static final String NO_THUMBS = "0";
    public FoodDiffAdapter(Context context,List<Food> foodList) {

        this.context = context;
        this.foodList = foodList;
    }

    public void setData(List<Food> list){
        this.foodList = list;
    }
    public void setNewData(List<Food> list){
        this.foodList = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FoodViewHolder(LayoutInflater.from(context).inflate(R.layout.food_card_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        holder.thump.setOnClickListener(v -> {
            if(TextUtils.equals(foodList.get(position).getIsThumbsUp(),THUMBS)){
                foodList.get(position).setIsThumbsUp(NO_THUMBS);
                notifyItemChanged(foodList.indexOf(foodList.get(position)),NO_THUMBS);
            }else{
                foodList.get(position).setIsThumbsUp(THUMBS);
                notifyItemChanged(foodList.indexOf(foodList.get(position)),THUMBS);
            }
        });
        if(foodList.get(position).getIsThumbsUp().equals(THUMBS)){
            holder.thump.setImageResource(R.drawable.thumb_fill);
        }else{
            holder.thump.setImageResource(R.drawable.thumb);
        }
        holder.foodName.setText(foodList.get(position).getFoodName());
        holder.desc.setText(foodList.get(position).getFoodDesc());
        holder.useTime.setText(foodList.get(position).getFoodUseTime());
        Glide.with(context).load(foodList.get(position).getFoodImgUrl()).into(holder.pic);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            ImageView imageView = holder.itemView.findViewById(R.id.thump);
            if(payloads.get(0).equals(THUMBS)){
                imageView.setImageResource(R.drawable.thumb_fill);
            }else{
                imageView.setImageResource(R.drawable.thumb);
            }
        }
    }

    @Override
    public int getItemCount() {
        return foodList == null?0:foodList.size();
    }


}

