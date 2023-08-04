package com.team.gemastik_mealplanner.ui.viewmodel;

import android.widget.CheckBox;

import com.team.gemastik_mealplanner.data.model.DietType;
import com.team.gemastik_mealplanner.data.model.PreRegisteredUser;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class FoodScreeningViewModel {

    public void setPriceLimit(int position, PreRegisteredUser preRegisteredUser){
        List<Long> priceLimit = new ArrayList<>();
        switch (position){
            case 0:
                priceLimit.add(0L);
                priceLimit.add(0L);
                break;
            case 1:
                priceLimit.add(15000L);
                priceLimit.add(25000L);
                break;
            case 2:
                priceLimit.add(26000L);
                priceLimit.add(35000L);
                break;
            case 3:
                priceLimit.add(36000L);
                priceLimit.add(45000L);
                break;
            case 4:
                priceLimit.add(46000L);
                priceLimit.add(55000L);
                break;
            case 5:
                priceLimit.add(56000L);
                priceLimit.add(60000L);
                break;
            case 6:
                priceLimit.add(60000L);
                priceLimit.add(100000L);
                break;
        }
        preRegisteredUser.setPriceLimit(priceLimit);
    }

    public void foodTypeSelected(String dietName,CheckBox[] checkBoxes,PreRegisteredUser preRegisteredUser){
        switch(dietName){
            case "Apa saja":
                preRegisteredUser.getDietType().setDietName("anything");
                preRegisteredUser.getDietType().setUserAllergy(DietType.anything);
                break;
            case "Paleo":
                preRegisteredUser.getDietType().setDietName("paleo");
                preRegisteredUser.getDietType().setUserAllergy(DietType.paleo);
                break;
            case "Vegetarian":
                preRegisteredUser.getDietType().setDietName("vegetarian");
                preRegisteredUser.getDietType().setUserAllergy(DietType.vegetarian);
                break;
            case "Vegan":
                preRegisteredUser.getDietType().setDietName("vegan");
                preRegisteredUser.getDietType().setUserAllergy(DietType.vegan);
                break;
            case "Ketogenik":
                preRegisteredUser.getDietType().setDietName("ketogenik");
                preRegisteredUser.getDietType().setUserAllergy(DietType.ketogenik);
                break;
            case "Mediterania":
                preRegisteredUser.getDietType().setDietName("mediterania");
                preRegisteredUser.getDietType().setUserAllergy(DietType.mediterania);
                break;
        }

        for (CheckBox checkboxes:
             checkBoxes) {
            if (preRegisteredUser.getDietType().getUserAllergy().contains(String.valueOf(checkboxes.getText()))){
                checkboxes.setChecked(true);
            }else{
                checkboxes.setChecked(false);
            }
        }

    }

    public void addAvoid(String ingredient,PreRegisteredUser preRegisteredUser){
        preRegisteredUser.getDietType().getUserAllergy().add(ingredient);
    }
    public void removeAvoid(String ingredient,PreRegisteredUser preRegisteredUser){
        preRegisteredUser.getDietType().getUserAllergy().remove(ingredient);
    }

}
