package com.team.gemastik_mealplanner.ui.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.slider.Slider;
import com.team.gemastik_mealplanner.R;
import com.team.gemastik_mealplanner.data.model.PreRegisteredUser;

import java.util.HashMap;

public class InformasiPribadiActivity extends AppCompatActivity {
    //vars
    HashMap<String, Object> temp = new HashMap<>();

    //widget
    ImageButton cancelButton, saveButton;
    EditText heightEditText, weightEditText, ageEditText;
    RadioGroup genderRadioGrup;
    Slider bmiSlider;
    TextView bmiLabelTextView;
    LinearLayout linearLayoutAktivitasTidakAda,linearLayoutAktivitasSedang,linearLayoutAktivitasAktif;
    int[] genderradioButtonId = {R.id.informasipribadi_radiobutton_male, R.id.informasipribadi_radiobutton_female};
    int[] allLinearLayoutAktivitas = {R.id.informasipribadi_linearlayout_aktivitastidakada,R.id.informasipribadi_linearlayout_aktivitassedang,R.id.informasipribadi_linearlayout_aktivitasaktif};

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informasi_pribadi);

        //extract widget
        cancelButton = findViewById(R.id.informasipribadi_button_cancel);
        saveButton = findViewById(R.id.informasipribadi_button_save);
        genderRadioGrup = findViewById(R.id.informasipribadi_radiogrup_gender);
        bmiSlider = findViewById(R.id.informasipribadi_slider_bmi);
        bmiLabelTextView = findViewById(R.id.informasipribadi_textview_bmilabel);
        linearLayoutAktivitasTidakAda = findViewById(R.id.informasipribadi_linearlayout_aktivitastidakada);
        linearLayoutAktivitasSedang = findViewById(R.id.informasipribadi_linearlayout_aktivitassedang);
        linearLayoutAktivitasAktif = findViewById(R.id.informasipribadi_linearlayout_aktivitasaktif);
        heightEditText = findViewById(R.id.informasipribadi_edittext_height);
        weightEditText = findViewById(R.id.informasipribadi_edittext_weight);
        ageEditText = findViewById(R.id.informasipribadi_edittext_age);

        //config
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String gender = preferences.getString("gender", "");
        setGender(genderRadioGrup, gender.equalsIgnoreCase("male")?R.id.informasipribadi_radiobutton_male : R.id.informasipribadi_radiobutton_female);
        int height = preferences.getInt("height",0);
        heightEditText.setText(String.valueOf(height));
        int weight = preferences.getInt("weight",0);
        weightEditText.setText(String.valueOf(weight));
        setBMI(height, weight);
        int age = preferences.getInt("age",0);
        ageEditText.setText(String.valueOf(age));
        String activity = preferences.getString("activity", "");
        switch (activity){
            case "nothing":
                aktivitasLayoutClicked(linearLayoutAktivitasTidakAda);
                break;
            case "medium":
                aktivitasLayoutClicked(linearLayoutAktivitasSedang);
                break;
            case "active":
                aktivitasLayoutClicked(linearLayoutAktivitasAktif);
                break;
            default:
                aktivitasLayoutClicked(linearLayoutAktivitasTidakAda);
        }

        //utility
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("gender", String.valueOf(temp.get("gender")));
                editor.putString("activity", String.valueOf(temp.get("activity")));
                editor.putInt("height",Integer.parseInt(String.valueOf(temp.get("height"))));
                editor.putInt("weight",Integer.parseInt(String.valueOf(temp.get("weight"))));
                editor.putInt("age",Integer.parseInt(String.valueOf(temp.get("age"))));
                editor.putFloat("bmi", Float.parseFloat(String.valueOf(temp.get("bmi"))));
                finish();
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
        weightEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    int weight = Integer.parseInt(s.toString());
                    int height = Integer.parseInt(heightEditText.getText().toString());
                    double bmi = countBMI(height, weight);
                    if (bmi > 0 && bmi <= 70){
                        bmiSlider.setValue((float) Math.round(bmi));
                        String text = String.format("BMI : %.1f (",bmi);
                        if (bmi < 18.5){
                            text += "berat badan kurang)";
                        }else if(bmi >= 18.5 && bmi <= 24.9){
                            text += "ideal)";
                        }else if(bmi > 24.9 && bmi <= 29.9){
                            text += "berat badan lebih)";
                        }else if(bmi > 29.9){
                            text += "obesitas)";
                        }
                        bmiLabelTextView.setText(text);
                    }
                }catch (Exception e){}
            }
        });
        heightEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    int weight = Integer.parseInt(weightEditText.getText().toString());
                    int height = Integer.parseInt(s.toString());
                    double bmi = countBMI(height, weight);
                    if (bmi > 0 && bmi <= 70){
                        bmiSlider.setValue((float) Math.round(bmi));
                        String text = String.format("BMI : %.1f (",bmi);
                        if (bmi < 18.5){
                            text += "berat badan kurang)";
                        }else if(bmi >= 18.5 && bmi <= 24.9){
                            text += "ideal)";
                        }else if(bmi > 24.9 && bmi <= 29.9){
                            text += "berat badan lebih)";
                        }else if(bmi > 29.9){
                            text += "obesitas)";
                        }
                        bmiLabelTextView.setText(text);
                    }
                }catch (Exception e){}
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
    private double countBMI(int height,int weight){

        double heightInM = ((double)height) / 100;
        double dWeight = (double)weight;
        double bmi = dWeight / (Math.pow(heightInM, 2));
        return bmi;
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setGender(RadioGroup group, int checkedId) {
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
    private void setBMI(int height, int weight){
        try {
            double bmi = countBMI(height, weight);
            if (bmi > 0 && bmi <= 70){
                bmiSlider.setValue((float) Math.round(bmi));
                String text = String.format("BMI : %.1f (",bmi);
                if (bmi < 18.5){
                    text += "berat badan kurang)";
                }else if(bmi >= 18.5 && bmi <= 24.9){
                    text += "ideal)";
                }else if(bmi > 24.9 && bmi <= 29.9){
                    text += "berat badan lebih)";
                }else if(bmi > 29.9){
                    text += "obesitas)";
                }
                bmiLabelTextView.setText(text);
                temp.put("bmi",bmi);
            }
        }catch (Exception e){}
    }
    }