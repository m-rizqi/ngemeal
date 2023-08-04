package com.team.gemastik_mealplanner.ui.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import com.team.gemastik_mealplanner.R;
import com.team.gemastik_mealplanner.ui.view.auth.AuthentificationActivity;
import com.team.gemastik_mealplanner.ui.view.dashboard.DashboardActivity;

public class OnBoardingActivity extends AppCompatActivity {
    
    //Widget
    AppCompatButton appCompatButton_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        //Widget
        appCompatButton_1 = findViewById(R.id.onBoarding_button_1);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isLogin = preferences.getBoolean("isLogin", false);
        if (isLogin){
            startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
        }

        //Widget utility
        appCompatButton_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnBoardingActivity.this, AuthentificationActivity.class);
                startActivity(intent);
            }
        });
        
    }
}