package com.team.gemastik_mealplanner.ui.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.team.gemastik_mealplanner.R;
import com.team.gemastik_mealplanner.data.api.ApiService;
import com.team.gemastik_mealplanner.ui.view.dashboard.DashboardActivity;

public class UserProfileActivity extends AppCompatActivity {

    //widgets
    ImageButton backImageButton, akunsayaButton, informasipribadiButton, logoutButton;
    CircularImageView userPhotoProfile;
    TextView userNameTextView, userWeightTextView, userHeightTextView;
    AppCompatTextView akunsayaCompactTextView, informasipribadiComppactTextView, logoutCompatTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        //extract widget
        backImageButton = findViewById(R.id.userprofile_button_back);
        userPhotoProfile = findViewById(R.id.userprofile_circleimage_userphoto);
        userNameTextView = findViewById(R.id.userprofile_textview_username);
        userHeightTextView = findViewById(R.id.userprofile_textview_userheight);
        userWeightTextView = findViewById(R.id.userprofile_textview_userweight);
        akunsayaCompactTextView = findViewById(R.id.userprofile_apptextview_akunsaya);
        informasipribadiComppactTextView = findViewById(R.id.userprofile_apptextview_informasipribadi);
        akunsayaButton = findViewById(R.id.userprofile_button_akunsayaarrow);
        informasipribadiButton = findViewById(R.id.userprofile_button_informasipribadiarrow);
        logoutCompatTextview = findViewById(R.id.userprofile_apptextview_logout);
        logoutButton = findViewById(R.id.userprofile_button_logoutarrow);

        //config
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("name", "Inas");
        userNameTextView.setText(name);
        int height = preferences.getInt("height", 0);
        userHeightTextView.setText("Tinggi "+height+" cm");
        int weight = preferences.getInt("weight", 0);
        userWeightTextView.setText("Berat "+weight+" Kg");

        //utility
        backImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        akunsayaCompactTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAkunSaya();
            }
        });
        akunsayaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAkunSaya();
            }
        });

        informasipribadiComppactTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToInformasiPribadi();
            }
        });
        informasipribadiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToInformasiPribadi();
            }
        });
        logoutCompatTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = preferences.edit();
//                ApiService.getInstance().logoutUser(preferences.getString("token",""));
                editor.putBoolean("isLogin",false);
                editor.putInt("id",0);
                editor.putString("token",null);
                editor.putString("name",null);
                editor.putString("email",null);
                editor.putString("password",null);
                editor.putString("gender",null);
                editor.putInt("height",0);
                editor.putInt("weight",0);
                editor.putInt("age",0);
                editor.putFloat("bmi", 0);
                editor.putString("type",null);
                editor.putFloat("calorie", 0);
                editor.putFloat("carb", 0);
                editor.putFloat("fat", 0);
                editor.putFloat("protein", 0);
                editor.apply();
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), OnBoardingActivity.class));
            }
        });
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = preferences.edit();
//                ApiService.getInstance().logoutUser(preferences.getString("token",""));
                editor.putBoolean("isLogin",false);
                editor.putInt("id",0);
                editor.putString("token",null);
                editor.putString("name",null);
                editor.putString("email",null);
                editor.putString("password",null);
                editor.putString("gender",null);
                editor.putInt("height",0);
                editor.putInt("weight",0);
                editor.putInt("age",0);
                editor.putFloat("bmi", 0);
                editor.putString("type",null);
                editor.putFloat("calorie", 0);
                editor.putFloat("carb", 0);
                editor.putFloat("fat", 0);
                editor.putFloat("protein", 0);
                editor.apply();
                startActivity(new Intent(getApplicationContext(), OnBoardingActivity.class));
            }
        });

    }

    private void goToAkunSaya(){
        startActivity(new Intent(getApplicationContext(), AkunSayaActivity.class));
    }

    private void goToInformasiPribadi(){
        startActivity(new Intent(getApplicationContext(), InformasiPribadiActivity.class));
    }

}