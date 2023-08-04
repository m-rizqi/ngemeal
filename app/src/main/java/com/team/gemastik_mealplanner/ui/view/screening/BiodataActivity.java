package com.team.gemastik_mealplanner.ui.view.screening;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.slider.Slider;
import com.team.gemastik_mealplanner.R;
import com.team.gemastik_mealplanner.data.model.PreRegisteredUser;
import com.team.gemastik_mealplanner.ui.view.dashboard.DashboardActivity;
import com.team.gemastik_mealplanner.ui.viewmodel.BiodataViewModel;

public class BiodataActivity extends AppCompatActivity {

    //vars
    PreRegisteredUser preRegisteredUser = PreRegisteredUser.getInstance();
    BiodataViewModel biodataViewModel = new BiodataViewModel();

    //widgets
    //Slider heightSlider,weightSlider;
    //TextView heightSliderLabel,weightSliderLabel;
    CardView maleCardView,femaleCardView;
    ImageView checkedicon1,checkedicon2;
    TextView bmiLabelTextView;
    //ImageButton imageButtonNext;
    Slider bmiSlider;
    RelativeLayout relativeLayoutMale, relativeLayoutFemale;
    EditText heightEditText, weightEditText, ageEditText;
    RadioGroup bmiRadioGrup,genderRadioGrup,targetWeightRadioGrup;
    AppCompatButton nextButton;
    LinearLayout linearLayoutAktivitasTidakAda,linearLayoutAktivitasSedang,linearLayoutAktivitasAktif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biodata);

        //get widgets
//        heightSlider = findViewById(R.id.biodata_slider_height);
//        heightSliderLabel = findViewById(R.id.biodata_textview_heightsliderlabel);
//        weightSlider = findViewById(R.id.biodata_slider_weight);
//        weightSliderLabel = findViewById(R.id.biodata_textview_weightsliderlabel);
        maleCardView = findViewById(R.id.biodata_cardview_male);
        femaleCardView = findViewById(R.id.biodata_cardview_female);
        checkedicon1 = findViewById(R.id.biodata_imageview_checkedicon1);
        checkedicon2 = findViewById(R.id.biodata_imageview_checkedicon2);
//        imageButtonNext = findViewById(R.id.biodata_imagebutton_nextarrow);
        bmiSlider = findViewById(R.id.biodata_slider_bmi);
        bmiLabelTextView = findViewById(R.id.biodata_textview_bmilabel);
        relativeLayoutMale = findViewById(R.id.biodata_relativelayout_male);
        relativeLayoutFemale = findViewById(R.id.biodata_relativelayout_female);
        linearLayoutAktivitasTidakAda = findViewById(R.id.biodata_linearlayout_aktivitastidakada);
        linearLayoutAktivitasSedang = findViewById(R.id.biodata_linearlayout_aktivitassedang);
        linearLayoutAktivitasAktif = findViewById(R.id.biodata_linearlayout_aktivitasaktif);
        heightEditText = findViewById(R.id.biodata_edittext_height);
        weightEditText = findViewById(R.id.biodata_edittext_weight);
        ageEditText = findViewById(R.id.biodata_edittext_age);
        nextButton = findViewById(R.id.biodata_imagebutton_nextarrow);

        //widget utility
//        heightSlider.addOnChangeListener(new Slider.OnChangeListener() {
//            @Override
//            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
//                heightSliderLabel.setText(String.valueOf((int)value)+"cm");
//                float xPos = (((heightSlider.getRight() - heightSlider.getLeft())/250)*value)+heightSlider.getLeft();
//                heightSliderLabel.setTranslationX(xPos - heightSliderLabel.getWidth());
//            }
//        });
//        weightSlider.addOnChangeListener(new Slider.OnChangeListener() {
//            @Override
//            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
//                weightSliderLabel.setText(String.valueOf((int)value)+"kg");
//                float xPos = (((weightSlider.getRight() - weightSlider.getLeft())/180)*value)+weightSlider.getLeft();
//                weightSliderLabel.setTranslationX(xPos - weightSliderLabel.getWidth()/4);
//            }
//        });
        maleCardView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
//                checkedicon1.setVisibility(View.VISIBLE);
//                checkedicon2.setVisibility(View.GONE);
                Drawable rlMaleDrawable = relativeLayoutMale.getBackground();
                rlMaleDrawable = DrawableCompat.wrap(rlMaleDrawable);
                DrawableCompat.setTint(rlMaleDrawable, Color.rgb(0,170,19));

                Drawable rlFemaleDrawable = relativeLayoutFemale.getBackground();
                rlFemaleDrawable = DrawableCompat.wrap(rlFemaleDrawable);
                DrawableCompat.setTint(rlFemaleDrawable, Color.WHITE);

                preRegisteredUser.setGender("male");

            }
        });
        femaleCardView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
//                checkedicon1.setVisibility(View.GONE);
//                checkedicon2.setVisibility(View.VISIBLE);
                Drawable rlMaleDrawable = relativeLayoutMale.getBackground();
                rlMaleDrawable = DrawableCompat.wrap(rlMaleDrawable);
                DrawableCompat.setTint(rlMaleDrawable, Color.WHITE);

                Drawable rlFemaleDrawable = relativeLayoutFemale.getBackground();
                rlFemaleDrawable = DrawableCompat.wrap(rlFemaleDrawable);
                DrawableCompat.setTint(rlFemaleDrawable, Color.rgb(0,170,19));

                preRegisteredUser.setGender("female");
            }
        });

//        bmiRadioGrup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @SuppressLint("ResourceType")
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                for (int id:bmiradioButtonId){
//                    RadioButton radioButton = group.findViewById(id);
//                    if (id == checkedId){
//                        radioButton.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.outlinedbox_appgreen));
//                        radioButton.setTextColor(Color.WHITE);
//                    }else{
//                        radioButton.setBackgroundColor(Color.TRANSPARENT);
//                        radioButton.setTextColor(Color.BLACK);
//                    }
//                    group.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.outlinedbox_inputlayout));
//                }
//
//            }
//        });

        linearLayoutAktivitasTidakAda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preRegisteredUser.setActivityLevel("nothing");
                v.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.outlinedbox_appgreen));
                for (int i = 0; i < linearLayoutAktivitasTidakAda.getChildCount(); i++){
                    TextView textView = (TextView) linearLayoutAktivitasTidakAda.getChildAt(i);
                    textView.setTextColor(Color.WHITE);
                }
                linearLayoutAktivitasSedang.setBackgroundColor(Color.TRANSPARENT);
                TextView textView = (TextView) linearLayoutAktivitasSedang.getChildAt(0);
                textView.setTextColor(Color.BLACK);
                textView = (TextView) linearLayoutAktivitasSedang.getChildAt(1);
                textView.setTextColor(Color.argb(100,0,0,0));
                linearLayoutAktivitasAktif.setBackgroundColor(Color.TRANSPARENT);
                textView = (TextView) linearLayoutAktivitasAktif.getChildAt(0);
                textView.setTextColor(Color.BLACK);
                textView = (TextView) linearLayoutAktivitasAktif.getChildAt(1);
                textView.setTextColor(Color.argb(100,0,0,0));
            }
        });
        linearLayoutAktivitasSedang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preRegisteredUser.setActivityLevel("medium");
                v.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.outlinedbox_appgreen));
                for (int i = 0; i < linearLayoutAktivitasSedang.getChildCount(); i++){
                    TextView textView = (TextView) linearLayoutAktivitasSedang.getChildAt(i);
                    textView.setTextColor(Color.WHITE);
                }
                linearLayoutAktivitasTidakAda.setBackgroundColor(Color.TRANSPARENT);
                TextView textView = (TextView) linearLayoutAktivitasTidakAda.getChildAt(0);
                textView.setTextColor(Color.BLACK);
                textView = (TextView) linearLayoutAktivitasTidakAda.getChildAt(1);
                textView.setTextColor(Color.argb(100,0,0,0));
                linearLayoutAktivitasAktif.setBackgroundColor(Color.TRANSPARENT);
                textView = (TextView) linearLayoutAktivitasAktif.getChildAt(0);
                textView.setTextColor(Color.BLACK);
                textView = (TextView) linearLayoutAktivitasAktif.getChildAt(1);
                textView.setTextColor(Color.argb(100,0,0,0));
            }
        });
        linearLayoutAktivitasAktif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preRegisteredUser.setActivityLevel("active");
                v.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.outlinedbox_appgreen));
                for (int i = 0; i < linearLayoutAktivitasAktif.getChildCount(); i++){
                    TextView textView = (TextView) linearLayoutAktivitasAktif.getChildAt(i);
                    textView.setTextColor(Color.WHITE);
                }
                linearLayoutAktivitasSedang.setBackgroundColor(Color.TRANSPARENT);
                TextView textView = (TextView) linearLayoutAktivitasSedang.getChildAt(0);
                textView.setTextColor(Color.BLACK);
                textView = (TextView) linearLayoutAktivitasSedang.getChildAt(1);
                textView.setTextColor(Color.argb(100,0,0,0));
                linearLayoutAktivitasTidakAda.setBackgroundColor(Color.TRANSPARENT);
                textView = (TextView) linearLayoutAktivitasTidakAda.getChildAt(0);
                textView.setTextColor(Color.BLACK);
                textView = (TextView) linearLayoutAktivitasTidakAda.getChildAt(1);
                textView.setTextColor(Color.argb(100,0,0,0));
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
                    double bmi = biodataViewModel.countAndSetBMI(height, weight, preRegisteredUser);
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
                    double bmi = biodataViewModel.countAndSetBMI(height, weight, preRegisteredUser);
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
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validationForm()) {
                    int height = Integer.parseInt(heightEditText.getText().toString());
                    int weight = Integer.parseInt(weightEditText.getText().toString());
                    int age = Integer.parseInt(ageEditText.getText().toString());
                    biodataViewModel.countAndSetBMI(height, weight, preRegisteredUser);
                    preRegisteredUser.setHeight(height);
                    preRegisteredUser.setWeight(weight);
                    preRegisteredUser.setAge(age);
                    biodataViewModel.countAndSetBMI(height,weight,preRegisteredUser);
                    biodataViewModel.calculateCalories(preRegisteredUser);
                    biodataViewModel.calculateFat(preRegisteredUser,preRegisteredUser.getCalories());
                    biodataViewModel.calculateCarb(preRegisteredUser,preRegisteredUser.getCalories());
                    biodataViewModel.calculateProtein(preRegisteredUser,preRegisteredUser.getCalories());
                    startActivity(new Intent(getApplicationContext(), FoodScreeningActivity.class));
                }else {

                }
            }
        });
//        imageButtonNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
//            }
//        });
    }

    private boolean validationForm(){
        boolean valid = true;

        if (preRegisteredUser.getGender() == null){
            valid &= false;
            Toast.makeText(this, "Pilih salah satu gender", Toast.LENGTH_SHORT).show();
        }

        if(heightEditText.getText().toString().trim().length() == 0){
            valid &= false;
            heightEditText.setError("Isi bagian ini");
        }else{
            heightEditText.setError(null);
        }

        if(weightEditText.getText().toString().trim().length() == 0){
            valid &= false;
            weightEditText.setError("Isi bagian ini");
        }else{
            weightEditText.setError(null);
        }

        if(ageEditText.getText().toString().trim().length() == 0){
            valid &= false;
            ageEditText.setError("Isi bagian ini");
        }else{
            ageEditText.setError(null);
        }

        if (preRegisteredUser.getActivityLevel() == null){
            valid &= false;
        }

        return valid;
    }

//    protected void aktivitasLayoutClicked(LinearLayout layout){
//        for (LinearLayout layout1 : allLinearLayoutAktivitas){
//            if (layout1.getId() != layout.getId()){
//                layout.setBackgroundColor(Color.TRANSPARENT);
//                TextView textView = (TextView) layout.getChildAt(0);
//                textView.setTextColor(Color.BLACK);
//                textView = (TextView) layout.getChildAt(1);
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                    textView.setTextColor(Color.argb((float)0.6,(float) 0.0,(float) 0.0,(float) 0.0));
//                }
//            }
//        }
//    }

}