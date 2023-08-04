package com.team.gemastik_mealplanner.ui.viewmodel;

import android.widget.RadioButton;

import com.team.gemastik_mealplanner.data.model.PreRegisteredUser;

public class BiodataViewModel {

    public double countAndSetBMI(int height,int weight, PreRegisteredUser preRegisteredUser){

        double heightInM = ((double)height) / 100;
        double dWeight = (double)weight;
        double bmi = dWeight / (Math.pow(heightInM, 2));
        preRegisteredUser.setBmi(Math.round(bmi));
        return bmi;
    }

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

    public void calculateCarb(PreRegisteredUser preRegisteredUser, int calories){
        int carbLower = 0;
        int carbUpper = 0;

        double temp = 0.6 * calories;
        carbLower = (int) Math.round(temp / 4);
        preRegisteredUser.setCarbLower(carbLower);

        temp = 0.7 * calories;
        carbUpper = (int) Math.round(temp / 4);
        preRegisteredUser.setCarbUpper(carbUpper);

    }

    public void calculateFat(PreRegisteredUser preRegisteredUser, int calories){
        int fatLower = 0;
        int fatUpper = 0;

        double temp = 0.2 * calories;
        fatLower = (int) Math.round(temp / 9);
        preRegisteredUser.setFatLower(fatLower);

        temp = 0.3 * calories;
        fatUpper = (int) Math.round(temp / 9);
        preRegisteredUser.setFatUpper(fatUpper);

    }

    public void calculateProtein(PreRegisteredUser preRegisteredUser, int calories){
        int proteinLower = 0;
        int proteinUpper = 0;

        double temp = 0.1 * calories;
        proteinLower = (int) Math.round(temp / 4);
        preRegisteredUser.setProteinLower(proteinLower);

        temp = 0.15 * calories;
        proteinUpper = (int) Math.round(temp / 4);
        preRegisteredUser.setProteinUpper(proteinUpper);

    }

}
