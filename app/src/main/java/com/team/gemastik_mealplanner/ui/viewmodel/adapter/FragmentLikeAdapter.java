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

import com.squareup.picasso.Picasso;
import com.team.gemastik_mealplanner.R;
import com.team.gemastik_mealplanner.data.model.Food;
import com.team.gemastik_mealplanner.ui.view.FoodDetailActivity;
import com.team.gemastik_mealplanner.util.DummyDao;

import java.io.Serializable;
import java.util.ArrayList;

public class FragmentLikeAdapter extends RecyclerView.Adapter<FragmentLikeAdapter.FragmentLikeHolder>{

    ArrayList<Food> foods = new ArrayList<>();
    DummyDao dummyDao = DummyDao.getInstance();

    public FragmentLikeAdapter(ArrayList<Food> foods){
        this.foods = foods;
    }

    @NonNull
    @Override
    public FragmentLikeHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_recyclerview_layout,parent,false);
        FragmentLikeHolder fragmentLikeHolder = new FragmentLikeHolder(itemView);
        return fragmentLikeHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull  FragmentLikeAdapter.FragmentLikeHolder holder, int position) {
        Food food = foods.get(position);
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FoodDetailActivity.class);
                intent.putExtra("food_id", food.getId());
                v.getContext().startActivity(intent);
            }
        });
//        holder.foodImage.setImageResource(food.getImage());
        Picasso.get().load(food.getImage()).into(holder.foodImage);
        holder.foodNameTextView.setText(food.getName());
        holder.foodCaloriesTextView.setText(String.valueOf(food.getCalories()+" kkal"));
        holder.loveButton.setChecked(true);
        holder.loveButton.setBackgroundDrawable(ContextCompat.getDrawable(holder.loveButton.getContext(), R.drawable.ic_heart_regular_red) );

        holder.loveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (holder.loveButton.isChecked()){
//                    holder.loveButton.setBackgroundDrawable(ContextCompat.getDrawable(v.getContext(), R.drawable.ic_heart_regular_red) );
//                }else {
//                    dummyDao.removeLikedFood(food);
//                    holder.loveButton.setBackgroundDrawable(ContextCompat.getDrawable(v.getContext(), R.drawable.ic_heart_regular_bold) );
//                }
                dummyDao.removeLikedFood(food);
                foods = dummyDao.getLikedFood();
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public class FragmentLikeHolder extends RecyclerView.ViewHolder{

        ImageView foodImage;
        TextView foodNameTextView, foodCaloriesTextView;
        CheckBox loveButton;
        ConstraintLayout constraintLayout;

        public FragmentLikeHolder(@NonNull View itemView) {
            super(itemView);

            foodImage = itemView.findViewById(R.id.foodrecomendation_layout_imageview_1);
            foodNameTextView = itemView.findViewById(R.id.foodrecomendation_layout_textview_nama);
            foodCaloriesTextView = itemView.findViewById(R.id.foodrecomendation_layout_textview_kalori);
            loveButton = itemView.findViewById(R.id.foodrecomendation_imagebutton_like);
            constraintLayout = itemView.findViewById(R.id.foodrecomendation_constraintlayout_1);

        }
    }
}
