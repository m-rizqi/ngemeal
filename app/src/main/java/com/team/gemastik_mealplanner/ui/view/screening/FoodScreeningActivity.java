package com.team.gemastik_mealplanner.ui.view.screening;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.textfield.TextInputLayout;
import com.team.gemastik_mealplanner.R;
import com.team.gemastik_mealplanner.data.model.DietType;
import com.team.gemastik_mealplanner.data.model.PreRegisteredUser;
import com.team.gemastik_mealplanner.ui.viewmodel.FoodScreeningViewModel;

import java.util.ArrayList;
import java.util.Arrays;

public class FoodScreeningActivity extends AppCompatActivity {

    //vars
    ArrayList<String> batasHarianList = new ArrayList<String>(Arrays.asList(new String[]{
            "Tidak ada batas",
            "Rp 15.000 - Rp 25.000",
            "Rp 26.000 - Rp 35.000",
            "Rp 36.000 - Rp 45.000",
            "Rp 46.000 - Rp 55.000",
            "Rp 56.000 - Rp 60.000",
            "Rp 60.000 ke atas",
    }));
    ArrayList<String> allIngredients = new ArrayList<>(Arrays.asList(
       new String[]{
               "Telur", "Kerang", "Kacang Pohon", "Susu", "Ikan", "Gluten", "Kedelai", "Kacang", "Daging Merah", "Daging Sapi", "Pasta", "Salmon", "Ayam", "Roti", "Nasi", "Oatmeal", "Gula", "Kentang", "Jagung", "Sayuran", "Asparagus", "Alpukat", "Kacang Almond", "Krim", "Keju", "Yogurt", "Bubuk Whey"
       }
    ));
    ArrayAdapter<String> batasHarianArrayAdapter;
    FoodScreeningViewModel foodScreeningViewModel = new FoodScreeningViewModel();
    PreRegisteredUser preRegisteredUser;

    //widgets
    TextInputLayout batasHarianInputLayout;
    AutoCompleteTextView batasHarianAutoCompleteTextview;
    AppCompatButton nextButton;
    ImageButton backButton;
    RadioGroup foodTypeRadioGrup;
    RadioButton apasajaRadioButton,paleoRadioButton,vegetarianRadioButton,veganRadioButton,ketoRadioButton,mediteranRadioButton;
//    CheckBox telurCheckBox,kerangCheckBox,kacangPohonCheckBox,susuCheckBox,ikanCheckBox,glutenCheckBox,kedelaiCheckBox,kacangCheckBox,
//    dagingMerahCheckBox,dagingSapiCheckBox,pastaCheckBox,salmonCheckBox,ayamCheckBox,rotiCheckBox,nasiCheckBox,oatmealCheckBox,gulaCheckBox,
//    kentangCheckBox,jagungCheckBox,sayuranCheckBox,asparagusCheckBox,alpukatCheckbox,kacangAlmondCheckBox,krimCheckBox,kejuCheckBox,yogurtCheckBox,
//    bubukWheyCheckBox;
    ArrayList<CheckBox> checkBoxesIngredients = new ArrayList<>();
    RadioButton[] foodTypeRadioButton;
    GridLayout avoidListGridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_screening);

        preRegisteredUser = PreRegisteredUser.getInstance();

        //getAllWidgets
        batasHarianInputLayout = findViewById(R.id.foodscreening_inputlayout_1);
        batasHarianAutoCompleteTextview = findViewById(R.id.foodscreening_autocompletetextview_batasharian);
        batasHarianArrayAdapter = new ArrayAdapter<>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,batasHarianList);
        batasHarianAutoCompleteTextview.setAdapter(batasHarianArrayAdapter);
        batasHarianAutoCompleteTextview.setDropDownBackgroundResource(R.color.white);
        avoidListGridLayout = findViewById(R.id.foodscreening_gridlayout_1);

        foodTypeRadioGrup = findViewById(R.id.foodscreening_radiogrup_foodtype);
        apasajaRadioButton = findViewById(R.id.foodscreening_radiobutton_apasaja);
        paleoRadioButton = findViewById(R.id.foodscreening_radiobutton_paleo);
        vegetarianRadioButton = findViewById(R.id.foodscreening_radiobutton_vegetarian);
        veganRadioButton = findViewById(R.id.foodscreening_radiobutton_vegan);
        ketoRadioButton = findViewById(R.id.foodscreening_radiobutton_ketogenik);
        mediteranRadioButton = findViewById(R.id.foodscreening_radiobutton_mediterania);

        //config
        for (String name : allIngredients){
            addCheckbox(name);
        }

//        telurCheckBox = findViewById(R.id.foodscreening_checkbox_telur);
//        kerangCheckBox = findViewById(R.id.foodscreening_checkbox_kerang);
//        kacangPohonCheckBox = findViewById(R.id.foodscreening_checkbox_kacangpohon);
//        susuCheckBox = findViewById(R.id.foodscreening_checkbox_susu);
//        ikanCheckBox = findViewById(R.id.foodscreening_checkbox_ikan);
//        glutenCheckBox = findViewById(R.id.foodscreening_checkbox_gluten);
//        kedelaiCheckBox = findViewById(R.id.foodscreening_checkbox_kedelai);
//        kacangCheckBox = findViewById(R.id.foodscreening_checkbox_kacang);
//        dagingMerahCheckBox = findViewById(R.id.foodscreening_checkbox_dagingmerah);
//        dagingSapiCheckBox = findViewById(R.id.foodscreening_checkbox_dagingsapi);
//        pastaCheckBox = findViewById(R.id.foodscreening_checkbox_pasta);
//        salmonCheckBox = findViewById(R.id.foodscreening_checkbox_salmon);
//        ayamCheckBox = findViewById(R.id.foodscreening_checkbox_ayam);
//        rotiCheckBox = findViewById(R.id.foodscreening_checkbox_roti);
//        nasiCheckBox = findViewById(R.id.foodscreening_checkbox_nasi);
//        oatmealCheckBox = findViewById(R.id.foodscreening_checkbox_oatmeal);
//        gulaCheckBox = findViewById(R.id.foodscreening_checkbox_gula);
//        kentangCheckBox = findViewById(R.id.foodscreening_checkbox_kentang);
//        jagungCheckBox = findViewById(R.id.foodscreening_checkbox_jagung);
//        alpukatCheckbox = findViewById(R.id.foodscreening_checkbox_alpukat);
//        kacangAlmondCheckBox = findViewById(R.id.foodscreening_checkbox_kacangalmond);
//        krimCheckBox = findViewById(R.id.foodscreening_checkbox_krim);
//        kejuCheckBox = findViewById(R.id.foodscreening_checkbox_keju);
//        yogurtCheckBox = findViewById(R.id.foodscreening_checkbox_yogurt);
//        bubukWheyCheckBox = findViewById(R.id.foodscreening_checkbox_bubukwhey);
//        sayuranCheckBox = findViewById(R.id.foodscreening_checkbox_sayuran);
//        asparagusCheckBox = findViewById(R.id.foodscreening_checkbox_asparagus);

//        checkBoxesIngredients = new CheckBox[]{
//                telurCheckBox,kerangCheckBox,kacangPohonCheckBox,susuCheckBox,ikanCheckBox,glutenCheckBox,kedelaiCheckBox,kerangCheckBox,kacangCheckBox,
//                dagingMerahCheckBox,dagingSapiCheckBox,pastaCheckBox,salmonCheckBox,ayamCheckBox,rotiCheckBox,nasiCheckBox,oatmealCheckBox,gulaCheckBox,
//                kentangCheckBox,jagungCheckBox,sayuranCheckBox,asparagusCheckBox,alpukatCheckbox,kacangAlmondCheckBox,krimCheckBox,kejuCheckBox,yogurtCheckBox,
//                bubukWheyCheckBox
//        };

        foodTypeRadioButton = new RadioButton[]{
                apasajaRadioButton,paleoRadioButton,vegetarianRadioButton,veganRadioButton,ketoRadioButton,mediteranRadioButton
        };

        nextButton = findViewById(R.id.foodscreening_imagebutton_nextarrow);
        backButton = findViewById(R.id.foodscreening_button_back);

        preRegisteredUser.setDietType(new DietType());

        //widget utility
        for (RadioButton radioButton : foodTypeRadioButton){
            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    foodTypeRadioButtonChecked(radioButton);
                }
            });
        }

//        for (CheckBox checkBox : checkBoxesIngredients){
//            checkBox.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    alergiCheckBoxChecked(checkBox);
//                }
//            });
//        }

        for (CheckBox checkBox : checkBoxesIngredients){
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alergiCheckBoxChecked(checkBox);
                }
            });
        }

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),NutritionTargetActivity.class));
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        batasHarianAutoCompleteTextview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                foodScreeningViewModel.setPriceLimit(position,preRegisteredUser);
            }
        });

    }

    public void foodTypeRadioButtonChecked(RadioButton rButton){
        CheckBox[] checkBoxes = new CheckBox[checkBoxesIngredients.size()];
        for (int i = 0; i < checkBoxes.length; i++){
            checkBoxes[i] = checkBoxesIngredients.get(i);
        }
        for (RadioButton radioButton :foodTypeRadioButton){
            if (radioButton.getId() == rButton.getId()){
                radioButton.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.outlinedbox_appgreen));
                radioButton.setTextColor(Color.WHITE);
                foodScreeningViewModel.foodTypeSelected(String.valueOf(radioButton.getText()), checkBoxes ,preRegisteredUser);
                for (CheckBox checkBox:checkBoxesIngredients){
                    alergiCheckBoxChecked(checkBox);
                }
            }else{
                radioButton.setBackgroundColor(Color.TRANSPARENT);
                radioButton.setTextColor(Color.BLACK);
            }
            foodTypeRadioGrup.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.outlinedbox_inputlayout));
        }
        }

    @SuppressLint("Range")
    public void alergiCheckBoxChecked(CheckBox checkBox){
        if (checkBox.isChecked()){
            checkBox.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.outlinedbox_red));
            checkBox.setTextColor(Color.parseColor("#E50914"));
            checkBox.setPaintFlags(checkBox.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            foodScreeningViewModel.addAvoid(String.valueOf(checkBox.getText()),preRegisteredUser);
        }else{
            checkBox.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.outlinedbox_inputlayout));
            checkBox.setTextColor(Color.BLACK);
            checkBox.setPaintFlags(checkBox.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            foodScreeningViewModel.removeAvoid(String.valueOf(checkBox.getText()),preRegisteredUser);
        }
    }

    public void addCheckbox(String name){
        CheckBox checkBox = (CheckBox) LayoutInflater.from(getApplicationContext()).inflate(R.layout.avoidfood_checkbox_itemlist, null);
        @SuppressLint({"NewApi", "LocalSuppress"})
        GridLayout.LayoutParams param= new GridLayout.LayoutParams(GridLayout.spec(
                GridLayout.UNDEFINED),
                GridLayout.spec(GridLayout.UNDEFINED,GridLayout.FILL,1f));
        checkBox.setLayoutParams(param);
        checkBox.setText(name);
        checkBox.setHeight(100);
        avoidListGridLayout.addView(checkBox);
        checkBoxesIngredients.add(checkBox);
    }
}