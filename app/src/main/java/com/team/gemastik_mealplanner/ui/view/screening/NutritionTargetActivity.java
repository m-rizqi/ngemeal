package com.team.gemastik_mealplanner.ui.view.screening;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.team.gemastik_mealplanner.R;
import com.team.gemastik_mealplanner.data.model.PreRegisteredUser;
import com.team.gemastik_mealplanner.ui.view.dashboard.DashboardActivity;
import com.team.gemastik_mealplanner.ui.viewmodel.NutritionTargetViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class NutritionTargetActivity extends AppCompatActivity {

    //vars
    PreRegisteredUser preRegisteredUser = PreRegisteredUser.getInstance();
    NutritionTargetViewModel nutritionTargetViewModel = new NutritionTargetViewModel();
    int calories;
    float carbFraction,fatFraction,proteinFraction;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;


    //widgets
    PieChart nutritionPie;
    AppCompatTextView settingTargetAppCompatTextview;
    AppCompatButton nextButton;
    ImageButton backButton;
    TextView amountKalTextView;
    TextView carbTextView;
    TextView fatTextView;
    TextView proteinTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_target);

        //vars
        calories = preRegisteredUser.getCalories();
        carbFraction = (((float) preRegisteredUser.getCarbLower()) * 4.0f / ((float)calories));
        fatFraction = (((float) preRegisteredUser.getFatLower()) * 9.0f / ((float)calories));
        proteinFraction = 1.0f - (carbFraction + fatFraction);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        //get widgets
        nutritionPie = findViewById(R.id.nutritiontarget_piechart_nutritionpie);
        setupPieChart();
        loadPieChartData(carbFraction,fatFraction,proteinFraction);
        settingTargetAppCompatTextview = findViewById(R.id.nutritiontarget_appcompattextview_settingtarget);
        amountKalTextView = findViewById(R.id.nutritiontarget_textview_amountkal);
        carbTextView = findViewById(R.id.nutritiontarget_textview_amountofcarb);
        fatTextView = findViewById(R.id.nutritiontarget_textview_amountoffat);
        proteinTextView = findViewById(R.id.nutritiontarget_textview_amountofprotein);
        backButton = findViewById(R.id.nutritiontarget_button_back);
        nextButton = findViewById(R.id.nutritiontarget_imagebutton_nextarrow);

        //widget utility
        amountKalTextView.setText(String.valueOf(calories));
        carbTextView.setText(String.valueOf(preRegisteredUser.getCarbUpper()) + " g");
        fatTextView.setText(String.valueOf(preRegisteredUser.getFatLower()) + " g");
        proteinTextView.setText(String.valueOf(preRegisteredUser.getProteinLower()) + " g");
        settingTargetAppCompatTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SettingNutritionTargetActivity.class));
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                nutritionTargetViewModel.registerUser(preRegisteredUser.getName(),preRegisteredUser.getEmail(),preRegisteredUser.getPassword(),preRegisteredUser.getPassword());
//                HashMap<String, Object> loginUser = nutritionTargetViewModel.loginUser(preRegisteredUser.getEmail(),preRegisteredUser.getPassword());
//                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                SharedPreferences.Editor editor = preferences.edit();
//                editor.putBoolean("isLogin",true);
////                String token = (String) loginUser.get("token");
////                editor.putString("token",token);
////                int id = (int) loginUser.get("id");
//                editor.putInt("id",id);
//                editor.putString("name",preRegisteredUser.getName());
//                editor.putString("email",preRegisteredUser.getEmail());
//                editor.putString("password",preRegisteredUser.getPassword());
//                editor.putString("gender",preRegisteredUser.getGender());
//                editor.putInt("height",preRegisteredUser.getHeight());
//                editor.putInt("weight",preRegisteredUser.getWeight());
//                editor.putInt("age", preRegisteredUser.getAge());
//                editor.putFloat("bmi", (float) preRegisteredUser.getBmi());
//                editor.putString("type",preRegisteredUser.getDietType().getDietName());
//                editor.putFloat("calorie", preRegisteredUser.getCalories());
//                editor.putFloat("carb", preRegisteredUser.getCarbLower());
//                editor.putFloat("fat", preRegisteredUser.getFatLower());
//                editor.putFloat("protein", preRegisteredUser.getProteinLower());
//                editor.apply();
//                Intent intent = new Intent(v.getContext(), DashboardActivity.class);
//                startActivity(intent);
                firebaseSignUp(preRegisteredUser);
            }
        });
    }

    private void firebaseSignUp(PreRegisteredUser registeredUser){
        mAuth.createUserWithEmailAndPassword(preRegisteredUser.getEmail(), preRegisteredUser.getPassword())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            registeredUser.setId(task.getResult().getUser().getUid());
                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putBoolean("isLogin",true);
                            editor.putString("id",registeredUser.getId());
                            editor.putString("name",registeredUser.getName());
                            editor.putString("email",registeredUser.getEmail());
                            editor.putString("password",registeredUser.getPassword());
                            editor.putString("gender",registeredUser.getGender());
                            editor.putInt("height",registeredUser.getHeight());
                            editor.putInt("weight",registeredUser.getWeight());
                            editor.putInt("age", registeredUser.getAge());
                            editor.putFloat("bmi", (float) registeredUser.getBmi());
                            editor.putString("type",registeredUser.getDietType().getDietName());
                            editor.putFloat("calorie", registeredUser.getCalories());
                            editor.putFloat("carb", registeredUser.getCarbLower());
                            editor.putFloat("fat", registeredUser.getFatLower());
                            editor.putFloat("protein", registeredUser.getProteinLower());
                            editor.apply();
                            mDatabase.child("users").child(task.getResult().getUser().getUid()).setValue(registeredUser);
                            Intent intent = new Intent(NutritionTargetActivity.this, DashboardActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(NutritionTargetActivity.this, String.format("Sign Up Failed : %s", task.getException().getMessage()), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void setupPieChart(){
        nutritionPie.setDrawHoleEnabled(true);
        nutritionPie.setUsePercentValues(false);
        nutritionPie.setDrawEntryLabels(false);
        nutritionPie.getDescription().setEnabled(false);
        nutritionPie.setHoleRadius(80f);
        nutritionPie.animateXY(1000,1000);
        nutritionPie.setTouchEnabled(false);

        Legend legend = nutritionPie.getLegend();
        legend.setEnabled(false);
        legend.setDrawInside(false);
    }

    private void loadPieChartData(float carb, float fat, float protein){
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(carb,""));
        entries.add(new PieEntry(fat,""));
        entries.add(new PieEntry(protein,""));

        ArrayList<Integer> colors = new ArrayList<>();

        colors.add(Color.parseColor("#059255"));
        colors.add(Color.parseColor("#c2282d"));
        colors.add(Color.parseColor("#633379"));

        PieDataSet dataSet = new PieDataSet(entries,"Nutrition Calculate");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(false);

        nutritionPie.setData(data);
        nutritionPie.invalidate();

    }

//    private static void refreshPage(){
//        calories = nutritionTargetViewModel.calculateCalories(preRegisteredUser);
//        carb = (float) nutritionTargetViewModel.getCarbFraction(preRegisteredUser,calories);
//        fat = (float) nutritionTargetViewModel.getFatFraction(preRegisteredUser,calories);
//        protein = (float) nutritionTargetViewModel.getProteinFraction(preRegisteredUser,calories);
//        setupPieChart();
//        loadPieChartData(carb,fat,protein);
//        amountKalTextView.setText(String.valueOf(calories));
//        carbTextView.setText(String.valueOf(preRegisteredUser.getCarbUpper()) + " g");
//        fatTextView.setText(String.valueOf(preRegisteredUser.getFatLower()) + " g");
//        proteinTextView.setText(String.valueOf(preRegisteredUser.getProteinLower()) + " g");
//    }

}