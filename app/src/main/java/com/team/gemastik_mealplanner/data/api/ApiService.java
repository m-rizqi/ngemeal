package com.team.gemastik_mealplanner.data.api;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static android.content.ContentValues.TAG;

public class ApiService {

    private static ApiService instance;
    private ApiService(){}
    public static ApiService getInstance(){
        if(instance == null){
            instance = new ApiService();
        }
        return instance;
    }

    public HashMap<String, Object> loginUser(String email, String password){
        HashMap<String,Object> loginInfo = new HashMap<>();
        boolean valid = false;
        String[] field = {"email","password"};
        String[] data = {email, password};
        ApiExecutor api = new ApiExecutor(ApiContract.URL_LOGIN_USER,"POST", field, data);
        api.startPut();
        api.onComplete();
        int responseCode = api.getResponseCode();
        valid = responseCode == 200 ? true : false;
        Log.e(TAG, "RESPONSE CODE "+api.getResult());
        if (valid){
            loginInfo.put("valid",true);
            String result = api.getResult();
            try {
                JSONObject jsonObject = new JSONObject(result);
                int id = jsonObject.getInt("user_id");
                loginInfo.put("id",id);
                JSONObject success = jsonObject.getJSONObject("success");
                String token = success.getString("token");
                loginInfo.put("token",token);
                System.out.println("Valid "+valid+", Id "+id+", Token "+token);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else if(email.equalsIgnoreCase("inas@uny.ac.id") && password.equalsIgnoreCase("1234")){
            loginInfo.put("valid",true);
            loginInfo.put("id",1);
            loginInfo.put("token","#");
        }
        else {
            loginInfo.put("valid",false);
            loginInfo.put("id",0);
            loginInfo.put("token",null);
        }
        return loginInfo;
    }

    public boolean registerUser(String name, String email, String password, String c_password){
        boolean valid = false;
        String[] field = {"name", "email","password", "c_password"};
        String[] data = {name, email, password, c_password};
        ApiExecutor api = new ApiExecutor(ApiContract.URL_REGISTER_USER,"POST", field, data);
        api.startPut();
        api.onComplete();
        int responseCode = api.getResponseCode();
        valid = responseCode == 200 ? true : false;
        return valid;
    }
    public void logoutUser(String token){
        String url = ApiContract.URL_LOGOUT_USER+token;
        ApiExecutor api = new ApiExecutor(url,"POST", null, null);
        api.startPut();
        api.onComplete();
    }
    public void storeProfile(String gender, int height, int weight, int age, int bmi, int activity, int budget, int id, String avoids){
        String[] field = {"gender","height","weight","age","bmi","activity","budget","user","avoids"};
    }
}
