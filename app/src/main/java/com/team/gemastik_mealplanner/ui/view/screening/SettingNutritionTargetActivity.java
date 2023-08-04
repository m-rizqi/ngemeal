package com.team.gemastik_mealplanner.ui.view.screening;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.team.gemastik_mealplanner.R;
import com.team.gemastik_mealplanner.data.model.PreRegisteredUser;

public class SettingNutritionTargetActivity extends AppCompatActivity {

    //vars
    PreRegisteredUser preRegisteredUser = PreRegisteredUser.getInstance();

    //widgets
    AppCompatTextView kalkulatorNutrisiTextview;
    ImageButton backButton;
    EditText kaloriEditText,carbfromEditText,carbtoEditText,fatfromEditText,fattoEditText,proteinfromEditText,proteintoEditText;
    AppCompatButton saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_nutrition_target);

        //get widgets
        kalkulatorNutrisiTextview = findViewById(R.id.settingnutritiontarget_appcompattextview_kalkulatornutrisi);
        backButton = findViewById(R.id.settingnutritiontarget_imagebutton_back);
        kaloriEditText = findViewById(R.id.settingnutritiontarget_edittext_kalori);
        carbfromEditText = findViewById(R.id.settingnutritiontarget_edittext_karbohidratfrom);
        carbtoEditText = findViewById(R.id.settingnutritiontarget_edittext_karbohidratto);
        fatfromEditText = findViewById(R.id.settingnutritiontarget_edittext_lemakfrom);
        fattoEditText = findViewById(R.id.settingnutritiontarget_edittext_lemakto);
        proteinfromEditText = findViewById(R.id.settingnutritiontarget_edittext_proteinfrom);
        proteintoEditText = findViewById(R.id.settingnutritiontarget_edittext_proteinto);
        saveButton = findViewById(R.id.settingnutritiontarget_imagebutton_nextarrow);

        //widget utility
        kalkulatorNutrisiTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),NutritionCalculatorActivity.class));
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int calories = Integer.parseInt(kaloriEditText.getText().toString());
                int carbLower = Integer.parseInt(carbfromEditText.getText().toString());
                int carbUpper = Integer.parseInt(carbtoEditText.getText().toString());
                int fatLower = Integer.parseInt(fatfromEditText.getText().toString());
                int fatUpper = Integer.parseInt(fattoEditText.getText().toString());
                int proteinLower = Integer.parseInt(proteinfromEditText.getText().toString());
                int proteinUpper = Integer.parseInt(proteintoEditText.getText().toString());
                preRegisteredUser.setCalories(calories);
                preRegisteredUser.setCarbLower(carbLower);
                preRegisteredUser.setCarbUpper(carbUpper);
                preRegisteredUser.setFatLower(fatLower);
                preRegisteredUser.setFatUpper(fatUpper);
                preRegisteredUser.setProteinLower(proteinLower);
                preRegisteredUser.setProteinUpper(proteinUpper);
                Intent intent = new Intent(getApplicationContext(), NutritionTargetActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}