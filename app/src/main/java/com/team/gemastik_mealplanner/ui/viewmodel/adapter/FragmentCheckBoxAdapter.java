package com.team.gemastik_mealplanner.ui.viewmodel.adapter;

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

import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;
import com.team.gemastik_mealplanner.R;
import com.team.gemastik_mealplanner.data.model.Food;
import com.team.gemastik_mealplanner.ui.view.FoodDetailActivity;
import com.team.gemastik_mealplanner.util.DummyDao;

import java.io.Serializable;
import java.util.ArrayList;

public class FragmentCheckBoxAdapter extends RecyclerView.Adapter<FragmentCheckBoxAdapter.FragmentCheckBoxHolder>{

    ArrayList<Food> foods = new ArrayList<>();
    ArrayList<String> eatTimeList;
    DummyDao dummyDao = DummyDao.getInstance();

    public FragmentCheckBoxAdapter(ArrayList<Food> foods, ArrayList<String> eatTimeList){
        this.foods = foods;
        this.eatTimeList = eatTimeList;
    }

    @NonNull
    @Override
    public FragmentCheckBoxHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_recyclerview_itemlist,parent,false);
        FragmentCheckBoxHolder FragmentCheckBoxHolder = new FragmentCheckBoxHolder(itemView);
        return FragmentCheckBoxHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull  FragmentCheckBoxAdapter.FragmentCheckBoxHolder holder, int position) {
        Food food = foods.get(position);
        holder.eatTimeTextView.setText(eatTimeList.get(position));
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FoodDetailActivity.class);
                intent.putExtra("food", (Serializable) food);
                v.getContext().startActivity(intent);
            }
        });
//        holder.foodImage.setImageResource(food.getImage());
        Picasso.get().load(food.getImage()).into(holder.foodImage);
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
                    dummyDao.addLikedFood(food);
                }else {
                    holder.loveButton.setBackgroundDrawable(ContextCompat.getDrawable(v.getContext(), R.drawable.ic_heart_regular_bold) );
                    dummyDao.removeLikedFood(food);
                }
            }
        });
        holder.makanLagiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FoodDetailActivity.class);
                intent.putExtra("food_id", food.getId());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public class FragmentCheckBoxHolder extends RecyclerView.ViewHolder{

        ImageView foodImage;
        TextView foodNameTextView, foodCaloriesTextView, eatTimeTextView;
        CheckBox loveButton;
        ConstraintLayout constraintLayout;
        MaterialButton makanLagiButton;

        public FragmentCheckBoxHolder(@NonNull View itemView) {
            super(itemView);

            foodImage = itemView.findViewById(R.id.historylist_layout_imageview_1);
            foodNameTextView = itemView.findViewById(R.id.historylist_layout_textview_nama);
            foodCaloriesTextView = itemView.findViewById(R.id.historylist_layout_textview_kalori);
            loveButton = itemView.findViewById(R.id.historylist_imagebutton_like);
            constraintLayout = itemView.findViewById(R.id.historylist_constraintlayout_1);
            eatTimeTextView = itemView.findViewById(R.id.historylist_layout_textview_eattime);
            makanLagiButton = itemView.findViewById(R.id.historylist_materialbutton_makanlagi);

        }
    }
}
