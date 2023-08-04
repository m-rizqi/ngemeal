package com.team.gemastik_mealplanner.data.model;

import java.util.ArrayList;
import java.util.Arrays;

public class DietType {

    private String dietName;
    private ArrayList<String> userAllergy;
    public static ArrayList<String> anything = new ArrayList<String>();
    public static ArrayList<String> paleo = new ArrayList<String>(
            Arrays.asList(
                    "Susu","Kentang","Krim","Keju","Yogurt","Bubuk Whey"
            )
    );
    public static ArrayList<String> vegan = new ArrayList<String>(
            Arrays.asList(
                    "Telur","Kerang","Susu","Ikan","Daging Merah","Daging Sapi","Salmon","Ayam","Krim","Keju","Yogurt", "Bubuk Whey"
            )
    );
    public static ArrayList<String> vegetarian = new ArrayList<String>(
            Arrays.asList(
                    "Telur", "Kerang", "Kacang Pohon","Ikan", "Gluten", "Kedelai", "Kacang","Daging Merah", "Daging Sapi"
                    )
    );
    public static ArrayList<String> ketogenik= new ArrayList<String>(
            Arrays.asList(
                    "Roti","Nasi","Oatmeal","Gula","Kentang", "Jagung"
            )
    );
    public static ArrayList<String> mediterania = new ArrayList<String>(
            Arrays.asList(
                    "Daging Merah", "Daging Sapi","Pasta","Gula","Krim"
            )
    );

    public DietType(){
        this.dietName = "anything";
        this.userAllergy =anything;
    }

    public void setDietName(String name){
        this.dietName = name;
    }

    public String getDietName(){return this.dietName;}

    public void setUserAllergy(ArrayList<String> list){
        this.userAllergy = list;
    }

    public ArrayList<String> getUserAllergy(){return this.userAllergy;}

}
