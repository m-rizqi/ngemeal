package com.team.gemastik_mealplanner.ui.viewmodel.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.team.gemastik_mealplanner.R;
import com.team.gemastik_mealplanner.data.model.Food;
import com.team.gemastik_mealplanner.ui.view.FoodDetailActivity;
import com.team.gemastik_mealplanner.util.DummyDao;

import java.io.Serializable;
import java.util.ArrayList;

public class SaranCalendarAdapter extends RecyclerView.Adapter<SaranCalendarAdapter.CalendarHolder>{

    ArrayList<Food> foods = new ArrayList<>();
    DummyDao dummyData = DummyDao.getInstance();
    public SaranCalendarAdapter(ArrayList<Food> foods){
        ArrayList<Food> temp = foods;
        for (Food food:
             temp) {
            if (!food.isHistory()){
                this.foods.add(food);
            }
        }
    }

    public void refresh(){
        notifyDataSetChanged();
    };

    @NonNull
    @Override
    public CalendarHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_recyclerview_layout,parent,false);
        CalendarHolder calendarHolder = new CalendarHolder(itemView);
        return calendarHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull  SaranCalendarAdapter.CalendarHolder holder, int position) {
        Food food = foods.get(position);
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FoodDetailActivity.class);
                intent.putExtra("food_id", food.getId());
                v.getContext().startActivity(intent);
            }
        });
        Picasso.get().load(food.getImage()).into(holder.foodImage);
//        holder.foodImage.setImageResource(food.getImage());
        holder.foodNameTextView.setText(food.getName());
        holder.foodCaloriesTextView.setText(String.valueOf(food.getCalories()+" kkal"));

        boolean isLiked = food.isLiked();
        if (isLiked){
            holder.loveButton.setBackgroundDrawable(ContextCompat.getDrawable(holder.loveButton.getContext(), R.drawable.ic_heart_regular_red) );
        }else {
            holder.loveButton.setBackgroundDrawable(ContextCompat.getDrawable(holder.loveButton.getContext(), R.drawable.ic_heart_regular_bold) );
        }

        holder.loveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.loveButton.isChecked()){
                    holder.loveButton.setBackgroundDrawable(ContextCompat.getDrawable(v.getContext(), R.drawable.ic_heart_regular_red) );
                    dummyData.addLikedFood(food);
                    food.setLiked(true);
                }else {
                    holder.loveButton.setBackgroundDrawable(ContextCompat.getDrawable(v.getContext(), R.drawable.ic_heart_regular_bold) );
                    dummyData.removeLikedFood(food);
                    food.setLiked(false);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public class CalendarHolder extends RecyclerView.ViewHolder{

        ImageView foodImage;
        TextView foodNameTextView, foodCaloriesTextView;
        CheckBox loveButton;
        ConstraintLayout constraintLayout;

        public CalendarHolder(@NonNull View itemView) {
            super(itemView);

            foodImage = itemView.findViewById(R.id.foodrecomendation_layout_imageview_1);
            foodNameTextView = itemView.findViewById(R.id.foodrecomendation_layout_textview_nama);
            foodCaloriesTextView = itemView.findViewById(R.id.foodrecomendation_layout_textview_kalori);
            loveButton = itemView.findViewById(R.id.foodrecomendation_imagebutton_like);
            constraintLayout = itemView.findViewById(R.id.foodrecomendation_constraintlayout_1);

        }
    }
}
