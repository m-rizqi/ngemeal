package com.team.gemastik_mealplanner.data.model;

import java.io.Serializable;

public class Food implements Serializable {

    private int id;
    private String name;
    private String image;
    private int calories;
    private int carb;
    private int fat;
    private int protein;
    private boolean liked;
    private boolean history;


    public Food(int id, String name, String image, int calories, int carb, int fat, int protein) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.calories = calories;
        this.carb = carb;
        this.fat = fat;
        this.protein = protein;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getCarb() {
        return carb;
    }

    public void setCarb(int carb) {
        this.carb = carb;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public boolean isHistory() {
        return history;
    }

    public void setHistory(boolean history) {
        this.history = history;
    }
    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", calories=" + calories +
                ", carb=" + carb +
                ", fat=" + fat +
                ", protein=" + protein +
                ", liked=" + liked +
                '}';
    }
}
