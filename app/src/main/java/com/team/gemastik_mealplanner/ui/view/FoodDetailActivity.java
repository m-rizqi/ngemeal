package com.team.gemastik_mealplanner.ui.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;
import com.team.gemastik_mealplanner.R;
import com.team.gemastik_mealplanner.data.model.Food;
import com.team.gemastik_mealplanner.data.model.TrackRecord;
import com.team.gemastik_mealplanner.ui.view.dashboard.DashboardActivity;
import com.team.gemastik_mealplanner.ui.viewmodel.adapter.SaranCalendarAdapter;
import com.team.gemastik_mealplanner.util.DummyDao;

import java.util.Arrays;

public class FoodDetailActivity extends AppCompatActivity {

    //vars
    Intent intent;
    Food food;

    //widgets
    ImageButton backButton;
    ImageView foodImage;
    TextView foodnameTextView,kaloriTextView,carbTextView, fatTextView, proteinTextView;
    MaterialButton makanButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        intent = getIntent();
        int food_id = (int) intent.getSerializableExtra("food_id");
        food = DummyDao.getInstance().getFood(food_id);

        //get widgets
        backButton = findViewById(R.id.fooddetail_imagebutton_back);
        foodImage = findViewById(R.id.fooddetail_imageview_foodimage);
        foodnameTextView = findViewById(R.id.fooddetail_textview_foodname);
        kaloriTextView = findViewById(R.id.fooddetail_textview_kalori);
        carbTextView = findViewById(R.id.fooddetail_textview_carb);
        fatTextView = findViewById(R.id.fooddetail_textview_fat);
        proteinTextView = findViewById(R.id.fooddetail_textview_protein);
        makanButton = findViewById(R.id.fooddetail_button_makan);

        //config
        Picasso.get().load(food.getImage()).into(foodImage);
        foodnameTextView.setText(food.getName());
        kaloriTextView.setText(String.valueOf(food.getCalories()+" kkal"));
        carbTextView.setText(String.valueOf(food.getCarb()+" g"));
        fatTextView.setText(String.valueOf(food.getFat()+" g"));
        proteinTextView.setText(String.valueOf(food.getProtein()+" g"));

        //widget utility
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        makanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TrackRecord trackRecord = TrackRecord.getInstance();
                trackRecord.setTodayEnergy(trackRecord.getTodayEnergy()+food.getCalories());
                trackRecord.setTodayCarb(trackRecord.getTodayCarb()+food.getCarb());
                trackRecord.setTodayFat(trackRecord.getTodayFat()+food.getFat());
                trackRecord.setTodayProtein(trackRecord.getTodayProtein()+food.getFat());
                DummyDao dummyDao = DummyDao.getInstance();
                dummyDao.foodisEaten(food);
                final Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }
}