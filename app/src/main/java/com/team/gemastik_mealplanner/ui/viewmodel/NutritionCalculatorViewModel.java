package com.team.gemastik_mealplanner.ui.viewmodel;

import com.team.gemastik_mealplanner.data.model.PreRegisteredUser;

import java.util.HashMap;

public class NutritionCalculatorViewModel {

    public void countAndSetBMI(int height,int weight, PreRegisteredUser preRegisteredUser){
        double heightInM = ((double)height) / 100;
        double dWeight = (double)weight;
        double bmi = dWeight / (Math.pow(heightInM, 2));
        preRegisteredUser.setBmi(bmi);
    }
    public int calculateCalories(String gender,int height, int weight, int age,String activityLevel,String targetWeight){
        int calories = 0;
        double AMB = calculateAMB(gender,height,weight,age);
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

        switch (targetWeight){
            case "turun":
                calories -= 300;
                break;
            case "naik":
                calories += 300;
                break;
        }

        return calories;
    }

    public double calculateAMB(String gender,int height, int weight, int age){
        double AMB = 0;
        switch (gender){
            case "male":
                AMB = 66 + (13.7 * weight) + (5 * height) - (6.8 * age);
                break;
            case "female":
                AMB = 655 + (9.6 * weight) + (1.8 * height) - (4.7 * age);
                break;
        }
        return AMB;
    }

    public int[] calculateCarb(int calories){
        int carbLower = 0;
        int carbUpper = 0;

        double temp = 0.6 * calories;
        carbLower = (int) Math.round(temp / 4);

        temp = 0.7 * calories;
        carbUpper = (int) Math.round(temp / 4);

        return  new int[]{carbLower,carbUpper};
    }

    public int[] calculateFat(int calories){
        int fatLower = 0;
        int fatUpper = 0;

        double temp = 0.2 * calories;
        fatLower = (int) Math.round(temp / 9);

        temp = 0.3 * calories;
        fatUpper = (int) Math.round(temp / 9);

        return new int[]{fatLower,fatUpper};

    }

    public int[] calculateProtein(int calories){
        int proteinLower = 0;
        int proteinUpper = 0;

        double temp = 0.1 * calories;
        proteinLower = (int) Math.round(temp / 4);

        temp = 0.15 * calories;
        proteinUpper = (int) Math.round(temp / 4);

        return new int[]{proteinLower,proteinUpper};

    }
}
