package com.team.gemastik_mealplanner.data.model;

public class PreRegisteredUser extends User{

    private static PreRegisteredUser instance;

    private PreRegisteredUser(String name, String email, String password) {
        super(name, email, password);
    }

    public static PreRegisteredUser getInstance(String name, String email, String password){
        if (instance == null){
            instance = new PreRegisteredUser(name,email,password);
        }
        return instance;
    }

    public static PreRegisteredUser getInstance(){
        return getInstance("","","");
    }

}
