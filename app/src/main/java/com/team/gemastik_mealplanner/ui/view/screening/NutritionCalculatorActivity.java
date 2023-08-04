package com.team.gemastik_mealplanner.ui.view.screening;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.team.gemastik_mealplanner.R;
import com.team.gemastik_mealplanner.data.model.PreRegisteredUser;
import com.team.gemastik_mealplanner.ui.viewmodel.NutritionCalculatorViewModel;

import java.util.HashMap;

public class NutritionCalculatorActivity extends AppCompatActivity {

    //vars
    PreRegisteredUser preRegisteredUser = PreRegisteredUser.getInstance();
    NutritionCalculatorViewModel viewModel = new NutritionCalculatorViewModel();
    HashMap<String,Object> temp = new HashMap<>();
    //widgets
    LinearLayout calculatedNutritionLinearLayout;
    EditText heightEditText, weightEditText, ageEditText;
    TextView saranKaloriTextView,saranKarbohidratTextView,saranLemakTextview,saranProteinTextView;
    RadioGroup bmiRadioGrup,genderRadioGrup,targetWeightRadioGrup;
    AppCompatButton hitungButton,terapkanSaranButton;
    ImageButton backButton;
    LinearLayout linearLayoutAktivitasTidakAda,linearLayoutAktivitasSedang,linearLayoutAktivitasAktif;
    int[] bmiradioButtonId = {R.id.nutritioncalculator_radiobutton_bmirendah, R.id.nutritioncalculator_radiobutton_bmiideal, R.id.nutritioncalculator_radiobutton_bmitinggi, R.id.nutritioncalculator_radiobutton_bmiobesitas};
    int[] genderradioButtonId = {R.id.nutritioncalculator_radiobutton_male, R.id.nutritioncalculator_radiobutton_female};
    int[] targetradioButtonId = {R.id.nutritioncalculator_radiobutton_turun, R.id.nutritioncalculator_radiobutton_tetap,R.id.nutritioncalculator_radiobutton_naik};
    int[] allLinearLayoutAktivitas = {R.id.nutritioncalculator_linearlayout_aktivitastidakada,R.id.nutritioncalculator_linearlayout_aktivitassedang,R.id.nutritioncalculator_linearlayout_aktivitasaktif};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_calculator);

        //vars
        temp.put("gender","male");
        temp.put("targetWeight","tetap");
        temp.put("height",0);
        temp.put("weight",0);
        temp.put("age",0);
        temp.put("bmi",0);
        temp.put("activity","nothing");

        //get widgets
        bmiRadioGrup = findViewById(R.id.nutritioncalculator_radiogrup_bmi);
        genderRadioGrup = findViewById(R.id.nutritioncalculator_radiogrup_gender);
        targetWeightRadioGrup = findViewById(R.id.nutritioncalculator_radiogrup_weighttarget);
        linearLayoutAktivitasTidakAda = findViewById(R.id.nutritioncalculator_linearlayout_aktivitastidakada);
        linearLayoutAktivitasSedang = findViewById(R.id.nutritioncalculator_linearlayout_aktivitassedang);
        linearLayoutAktivitasAktif = findViewById(R.id.nutritioncalculator_linearlayout_aktivitasaktif);
        heightEditText = findViewById(R.id.nutritioncalculator_edittext_height);
        weightEditText = findViewById(R.id.nutritioncalculator_edittext_weight);
        ageEditText = findViewById(R.id.nutritioncalculator_edittext_age);
        hitungButton = findViewById(R.id.nutritioncalculator_imagebutton_nextarrow);
        backButton = findViewById(R.id.nutritioncalculator_imagebutton_back);
        calculatedNutritionLinearLayout = findViewById(R.id.nutritioncalculator_linearlayout_resultofhitung);
        saranKaloriTextView = findViewById(R.id.nutritioncalculator_textview_sarankalori);
        saranKarbohidratTextView = findViewById(R.id.nutritioncalculator_textview_sarankarbohidrat);
        saranLemakTextview = findViewById(R.id.nutritioncalculator_textview_saranlemak);
        saranProteinTextView = findViewById(R.id.nutritioncalculator_textview_saranprotein);
        terapkanSaranButton = findViewById(R.id.nutritioncalculator_imagebutton_terapkansaran);

        //widget utility
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        hitungButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int height = Integer.parseInt(heightEditText.getText().toString());
                int weight = Integer.parseInt(weightEditText.getText().toString());
                int age = Integer.parseInt(ageEditText.getText().toString());
                int calories = viewModel.calculateCalories(String.valueOf(temp.get("gender")),height,weight,age,String.valueOf(temp.get("activity")),String.valueOf(temp.get("targetWeight")));
                int[] carb = viewModel.calculateCarb(calories);
                int[] fat = viewModel.calculateFat(calories);
                int[] protein = viewModel.calculateProtein(calories);
                saranKaloriTextView.setText(String.valueOf(calories+" kkal"));
                saranKarbohidratTextView.setText(String.valueOf(carb[0]+" g - "+carb[1]+" g"));
                saranLemakTextview.setText(String.valueOf(fat[0]+" g - "+fat[1]+" g"));
                saranProteinTextView.setText(String.valueOf(protein[0]+" g - "+protein[1]+" g"));
                calculatedNutritionLinearLayout.setVisibility(View.VISIBLE);
                hitungButton.setVisibility(View.GONE);
            }
        });
        terapkanSaranButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int height = Integer.parseInt(heightEditText.getText().toString());
                int weight = Integer.parseInt(weightEditText.getText().toString());
                int age = Integer.parseInt(ageEditText.getText().toString());
                int calories = viewModel.calculateCalories(String.valueOf(temp.get("gender")),height,weight,age,String.valueOf(temp.get("activity")),String.valueOf(temp.get("targetWeight")));
                int[] carb = viewModel.calculateCarb(calories);
                int[] fat = viewModel.calculateFat(calories);
                int[] protein = viewModel.calculateProtein(calories);
                preRegisteredUser.setHeight(height);
                preRegisteredUser.setWeight(weight);
                preRegisteredUser.setAge(age);
                viewModel.countAndSetBMI(height,weight,preRegisteredUser);
                preRegisteredUser.setCalories(calories);
                preRegisteredUser.setCarbLower(carb[0]);
                preRegisteredUser.setCarbUpper(carb[1]);
                preRegisteredUser.setFatLower(fat[0]);
                preRegisteredUser.setFatUpper(fat[1]);
                preRegisteredUser.setProteinLower(protein[0]);
                preRegisteredUser.setProteinUpper(protein[1]);
                Intent intent = new Intent(getApplicationContext(), NutritionTargetActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        linearLayoutAktivitasTidakAda.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                temp.replace("activity","nothing");
                aktivitasLayoutClicked(linearLayoutAktivitasTidakAda);
            }
        });

        linearLayoutAktivitasSedang.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                temp.replace("activity","medium");
                aktivitasLayoutClicked(linearLayoutAktivitasSedang);
            }
        });

        linearLayoutAktivitasAktif.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                temp.replace("activity","active");
                aktivitasLayoutClicked(linearLayoutAktivitasAktif);
            }
        });

        bmiRadioGrup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int id:bmiradioButtonId){
                    RadioButton radioButton = group.findViewById(id);
                    if (id == checkedId){
                        radioButton.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.outlinedbox_appgreen));
                        radioButton.setTextColor(Color.WHITE);
                    }else{
                        radioButton.setBackgroundColor(Color.TRANSPARENT);
                        radioButton.setTextColor(Color.BLACK);
                    }
                    group.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.outlinedbox_inputlayout));
                }

            }
        });

        genderRadioGrup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int id:genderradioButtonId){
                    RadioButton radioButton = group.findViewById(id);
                    if (id == checkedId){
                        radioButton.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.outlinedbox_appgreen));
                        radioButton.setTextColor(Color.WHITE);
                        temp.replace("gender",String.valueOf(radioButton.getText()).equalsIgnoreCase("Laki-Laki") ? "male" : "female");
                    }else{
                        radioButton.setBackgroundColor(Color.TRANSPARENT);
                        radioButton.setTextColor(Color.BLACK);
                    }
                    group.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.outlinedbox_inputlayout));
                }

            }
        });

        targetWeightRadioGrup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int id:targetradioButtonId){
                    RadioButton radioButton = group.findViewById(id);
                    if (id == checkedId){
                        radioButton.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.outlinedbox_appgreen));
                        radioButton.setTextColor(Color.WHITE);
                        temp.replace("targetWeight",String.valueOf(radioButton.getText()).toLowerCase());
                    }else{
                        radioButton.setBackgroundColor(Color.TRANSPARENT);
                        radioButton.setTextColor(Color.BLACK);
                    }
                    group.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.outlinedbox_inputlayout));
                }

            }
        });

    }

    private void aktivitasLayoutClicked(LinearLayout layout) {
        for (int idLayout1 : allLinearLayoutAktivitas) {
            LinearLayout tmp = findViewById(idLayout1);
            if (idLayout1 == layout.getId()){
                tmp.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.outlinedbox_appgreen));
                for (int i = 0; i < tmp.getChildCount(); i++){
                    TextView textView = (TextView) tmp.getChildAt(i);
                    textView.setTextColor(Color.WHITE);
                }
            }else{
                tmp.setBackgroundColor(Color.TRANSPARENT);
                TextView textView = (TextView) tmp.getChildAt(0);
                textView.setTextColor(Color.BLACK);
                textView = (TextView) tmp.getChildAt(1);
                textView.setTextColor(Color.argb(100,0,0,0));
            }
        }
    }

}