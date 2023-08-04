package com.team.gemastik_mealplanner.util;

import com.team.gemastik_mealplanner.data.model.DietType;
import com.team.gemastik_mealplanner.data.model.User;

import java.util.ArrayList;
import java.util.HashMap;

public class ModelToStringConverter {

    public static HashMap<String,String> userToString(User user){
        HashMap<String,String> userString = new HashMap<>();

        userString.put("id", String.valueOf(user.getId()));
        userString.put("name",user.getName());
        userString.put("email", user.getEmail());
        userString.put("password",user.getPassword());
        userString.put("gender",user.getGender());
        userString.put("height",String.valueOf(user.getHeight()));
        userString.put("weight",String.valueOf(user.getWeight()));
        userString.put("age",String.valueOf(user.getAge()));
        userString.put("bmi",String.valueOf(user.getBmi()));
        userString.put("activityLevel",user.getActivityLevel());

        DietType dietType = user.getDietType();
        userString.put("dietType",dietType.getDietName());
        ArrayList<String> userList = dietType.getUserAllergy();
        String s = "["+userList.get(0);
        for (int i = 1; i <userList.size() ; i++) {
            s += ","+userList.get(i);
        }
        s+= "]";
        userString.put("allergy",s);

        s = "["+user.getPriceLimit().get(0)+","+user.getPriceLimit().get(1)+"]";

        userString.put("priceLimit",s);
        userString.put("calories",String.valueOf(user.getCalories()));
        userString.put("carbLower",String.valueOf(user.getCarbLower()));
        userString.put("carbUpper",String.valueOf(user.getCarbUpper()));
        userString.put("proteinLower",String.valueOf(user.getProteinLower()));
        userString.put("proteinUpper",String.valueOf(user.getProteinUpper()));
        userString.put("fatLower",String.valueOf(user.getFatLower()));
        userString.put("fatUpper",String.valueOf(user.getFatUpper()));

        return userString;
    }

}
