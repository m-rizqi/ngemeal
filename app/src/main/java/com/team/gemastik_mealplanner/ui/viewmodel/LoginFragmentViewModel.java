package com.team.gemastik_mealplanner.ui.viewmodel;

import android.content.Context;

import com.team.gemastik_mealplanner.data.api.ApiService;

import java.util.HashMap;

public class LoginFragmentViewModel {

    public HashMap<String, Object> loginUser(String email, String password){
        return ApiService.getInstance().loginUser(email, password);
    }

}
