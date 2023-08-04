package com.team.gemastik_mealplanner.ui.viewmodel;

import android.content.Context;

import com.team.gemastik_mealplanner.data.api.ApiService;
import com.team.gemastik_mealplanner.data.model.PreRegisteredUser;
import com.team.gemastik_mealplanner.util.ModelToStringConverter;

import java.util.HashMap;

public class NutritionTargetViewModel {

    public int calculateCalories(PreRegisteredUser preRegisteredUser){
        int calories = 0;
        String gender = preRegisteredUser.getGender();
        double AMB = calculateAMB(gender,preRegisteredUser);
        String activityLevel = preRegisteredUser.getActivityLevel();
        double indexActivity = 0;
        switch (activityLevel){
            case "nothing":
                indexActivity = 1.3;
                break;
            case "medium":
                indexActivity = gender.equals("male") ? 1.76 : 1.7;
                break;
            case "active":
                indexActivity = gender.equals("male") ? 2.1 : 2;
                break;
        }
        calories = (int) Math.round((indexActivity * AMB));
        preRegisteredUser.setCalories(calories);

        return calories;
    }

    public double calculateAMB(String gender,PreRegisteredUser preRegisteredUser){
        double AMB = 0;
        switch (gender){
            case "male":
                AMB = 66 + (13.7 * preRegisteredUser.getWeight()) + (5 * preRegisteredUser.getHeight()) - (6.8 * preRegisteredUser.getAge());
                break;
            case "female":
                AMB = 655 + (9.6 * preRegisteredUser.getWeight()) + (1.8 * preRegisteredUser.getHeight()) - (4.7 * preRegisteredUser.getAge());
                break;
        }
        return AMB;
    }

    public double getCarbFraction(PreRegisteredUser preRegisteredUser, int calories){
        int carbLower = 0;
        int carbUpper = 0;

        double temp = 0.6 * calories;
        carbLower = (int) Math.round(temp / 4);
        preRegisteredUser.setCarbLower(carbLower);

        temp = 0.7 * calories;
        carbUpper = (int) Math.round(temp / 4);
        preRegisteredUser.setCarbUpper(carbUpper);

        return  0.7;
    }

    public double getFatFraction(PreRegisteredUser preRegisteredUser, int calories){
        int fatLower = 0;
        int fatUpper = 0;

        double temp = 0.2 * calories;
        fatLower = (int) Math.round(temp / 9);
        preRegisteredUser.setFatLower(fatLower);

        temp = 0.3 * calories;
        fatUpper = (int) Math.round(temp / 9);
        preRegisteredUser.setFatUpper(fatUpper);

        return  0.2;
    }

    public double getProteinFraction(PreRegisteredUser preRegisteredUser, int calories){
        int proteinLower = 0;
        int proteinUpper = 0;

        double temp = 0.1 * calories;
        proteinLower = (int) Math.round(temp / 4);
        preRegisteredUser.setProteinLower(proteinLower);

        temp = 0.15 * calories;
        proteinUpper = (int) Math.round(temp / 4);
        preRegisteredUser.setProteinUpper(proteinUpper);

        return  0.1;
    }

    public boolean registerUser(String name, String email, String password, String c_password){
        return ApiService.getInstance().registerUser(name,email,password,c_password);
    }

    public HashMap<String, Object> loginUser(String email, String password){
        return ApiService.getInstance().loginUser(email,password);
    }

}
