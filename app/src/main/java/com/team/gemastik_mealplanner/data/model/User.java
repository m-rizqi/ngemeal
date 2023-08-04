 package com.team.gemastik_mealplanner.data.model;

 import java.util.List;

 public class User {

    private String id;
    private String name;
    private String email;
    private String password;
    private String gender;
    private int height;
    private int weight;
    private int age;
    private double bmi;
    private String activityLevel;
    private DietType dietType;
    private List<Long> priceLimit;
    private int calories;
    private int carbLower;
    private int carbUpper;
    private int proteinLower;
    private int proteinUpper;
    private int fatLower;
    private int fatUpper;

    public User(){

    };

     public User(String name, String email, String password) {
         this.name = name;
         this.email = email;
         this.password = password;
     }

     public String getId() {
         return id;
     }

     public void setId(String id) {
         this.id = id;
     }

     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     public String getEmail() {
         return email;
     }

     public void setEmail(String email) {
         this.email = email;
     }

     public String getPassword() {
         return password;
     }

     public void setPassword(String password) {
         this.password = password;
     }

     public String getGender() {
         return gender;
     }

     public void setGender(String gender) {
         this.gender = gender;
     }

     public int getHeight() {
         return height;
     }

     public void setHeight(int height) {
         this.height = height;
     }

     public int getWeight() {
         return weight;
     }

     public void setWeight(int weight) {
         this.weight = weight;
     }

     public int getAge() {
         return age;
     }

     public void setAge(int age) {
         this.age = age;
     }

     public double getBmi() {
         return bmi;
     }

     public void setBmi(double bmi) {
         this.bmi = bmi;
     }

     public String getActivityLevel() {
         return activityLevel;
     }

     public void setActivityLevel(String activityLevel) {
         this.activityLevel = activityLevel;
     }

     public DietType getDietType() {
         return dietType;
     }

     public void setDietType(DietType dietType) {
         this.dietType = dietType;
     }

     public List<Long> getPriceLimit() {
         return priceLimit;
     }

     public void setPriceLimit(List<Long> price_limit) {
         this.priceLimit = price_limit;
     }

     public int getCalories() {
         return calories;
     }

     public void setCalories(int calories) {
         this.calories = calories;
     }

     public int getCarbLower() {
         return carbLower;
     }

     public void setCarbLower(int carbLower) {
         this.carbLower = carbLower;
     }

     public int getCarbUpper() {
         return carbUpper;
     }

     public void setCarbUpper(int carbUpper) {
         this.carbUpper = carbUpper;
     }

     public int getProteinLower() {
         return proteinLower;
     }

     public void setProteinLower(int proteinLower) {
         this.proteinLower = proteinLower;
     }

     public int getProteinUpper() {
         return proteinUpper;
     }

     public void setProteinUpper(int proteinUpper) {
         this.proteinUpper = proteinUpper;
     }

     public int getFatLower() {
         return fatLower;
     }

     public void setFatLower(int fatLower) {
         this.fatLower = fatLower;
     }

     public int getFatUpper() {
         return fatUpper;
     }

     public void setFatUpper(int fatUpper) {
         this.fatUpper = fatUpper;
     }
 }
