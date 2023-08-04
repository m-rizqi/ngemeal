package com.team.gemastik_mealplanner.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;

import com.google.gson.JsonParser;
import com.team.gemastik_mealplanner.data.model.Food;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class DummyDao {

    private static Food food1,food2,food3,food4,food5,food6,food7,food8,food9;
    private static ArrayList<Food> allFood = new ArrayList<>();
    private static ArrayList<Food> breakFast = new ArrayList<>();
    private static ArrayList<Food> lunch = new ArrayList<>();
    private static ArrayList<Food> dinner = new ArrayList<>();
    private static ArrayList<Food> snack = new ArrayList<>();
    private static ArrayList<Food> likedFood = new ArrayList<>();
    private static ArrayList<Food> historyFood = new ArrayList<>();

    private static DummyDao instance;

    private DummyDao(){
        {
            food1 = new Food(1,"Sereal Gandum\ndengan Susu",
                    "https://cdn.hellosehat.com/wp-content/uploads/2017/12/makan-gandum-utuh.jpg",
                    340,
                    72,
                    2,
                    13);

        }
        {
            food2= new Food(2,"Jus Alpukat"
            ,"https://i0.wp.com/resepkoki.id/wp-content/uploads/2021/02/Resep-Jus-Alpukat.jpg?fit=500%2C667&ssl=1"
            ,278
            ,17
            ,29
            ,4);
        }
        {
            food3 = new Food(3,"Nasi Putih"
            ,"https://cdnt.orami.co.id/unsafe/cdn-cas.orami.co.id/parenting/images/kalori-nasi-putih.width-800.jpegquality-80.jpg"
            ,204
            ,44
            ,1
            ,4);
        }
        {
            food4 = new Food(4,"Ayam Goreng Kalasan"
            ,"https://selerasa.com/wp-content/uploads/2015/12/images_daging_ayam-goreng.jpg"
            ,360
            ,14
            ,10
            ,22);
        }
        {
            food5 = new Food(5,"Es Teh"
            ,"https://nilaigizi.com/assets/images/produk/produk_1578041377.jpg"
            ,90
            ,23
            ,0
            ,0);
        }
        {
            food6 = new Food(6,"Nasi Putih","https://cdnt.orami.co.id/unsafe/cdn-cas.orami.co.id/parenting/images/kalori-nasi-putih.width-800.jpegquality-80.jpg"
            ,204
            ,44
            ,1
            ,4);
        }
        {
            food7 = new Food(7,"Steak Daging Sapi"
            ,"https://akcdn.detik.net.id/community/media/visual/2019/03/29/1bd885b1-ac28-46e5-81a4-5d200cf7290f_43.jpeg?w=480"
            ,252
            ,0
            ,15
            ,27);
        }
        {
            food8 = new Food(8,"Buah Pisang"
            ,"https://asset-a.grid.id//crop/0x0:0x0/700x465/photo/bobofoto/original/22342_pisang.jpg"
            ,105
            ,62
            ,1
            ,1);
        }
        {
            food9 = new Food(9,"Yogurt Chimory"
            ,"https://www.static-src.com/wcsstore/Indraprastha/images/catalog/full/MTA-3170200/cimory_cimory-yogurt-drink-blueberry--250-ml-_full04.jpg"
            ,50
            ,10
            ,0
            ,2);
        }
        breakFast.add(food1);breakFast.add(food2);
        lunch.add(food3);lunch.add(food4);lunch.add(food5);
        dinner.add(food6);dinner.add(food7);
        snack.add(food8);snack.add(food9);
        allFood.add(food1);allFood.add(food2);allFood.add(food3);allFood.add(food4);
        allFood.add(food5);allFood.add(food6);allFood.add(food7);allFood.add(food8);
        allFood.add(food9);
    }

    public static DummyDao getInstance(){
        if (instance == null){
            instance = new DummyDao();
        }
        return instance;
    }

    public void foodisEaten(Food food){
        food.setHistory(true);
    }

    public ArrayList<Food> getHistoryFood(){
        ArrayList<Food> hFood = new ArrayList<>();
        for (Food food:
                allFood) {
            if (food.isHistory() == true){
                hFood.add(food);
            }
        }
        return hFood;
    }

    public void addLikedFood(Food food){
        food.setLiked(true);
//        this.likedFood.add(food);
    }

    public void removeLikedFood(Food food){
        food.setLiked(false);
//        this.likedFood.remove(food);
    }

    public ArrayList<Food> getLikedFood(){
        ArrayList<Food> lFood = new ArrayList<>();
        for (Food food:
             allFood) {
            if (food.isLiked()){
                lFood.add(food);
            }
        }
        return lFood;
//        return this.likedFood;
    }

    public void setIsLiked(ArrayList<Food> foods){
        for (Food food:
             foods) {
            for (Food lFood:
                 this.likedFood) {
                if (lFood.getId() == food.getId()){
                    food.setLiked(true);
                    break;
                }
            }
        }
    }

    public ArrayList<Food> getBreakFast() {
//        setIsLiked(this.breakFast);
        return this.breakFast;
    }

    public void setBreakFast(ArrayList<Food> breakFast) {
        this.breakFast = breakFast;
    }

    public ArrayList<Food> getLunch() {
//        setIsLiked(this.lunch);
        return this.lunch;
    }

    public void setLunch(ArrayList<Food> lunch) {
        this.lunch = lunch;
    }

    public ArrayList<Food> getDinner() {
//        setIsLiked(this.dinner);
        return this.dinner;
    }

    public void setDinner(ArrayList<Food> dinner) {
        this.dinner = dinner;
    }

    public ArrayList<Food> getSnack() {
//        setIsLiked(this.snack);
        return this.snack;
    }

    public void setSnack(ArrayList<Food> snack) {
        this.snack = snack;
    }

    public Food getFood(int id){
        Food food = null;
        for (Food f:
             allFood) {
            if(f.getId() == id){
                food = f;
                break;
            }
        }
        return food;
    }
    
}
