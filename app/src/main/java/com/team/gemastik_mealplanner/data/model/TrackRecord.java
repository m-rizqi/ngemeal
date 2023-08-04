package com.team.gemastik_mealplanner.data.model;

public class TrackRecord {

    private static TrackRecord instance;

    private int todayEnergy, todayCarb,todayFat, todayProtein;

    public static void setInstance(TrackRecord instance) {
        TrackRecord.instance = instance;
    }

    public int getTodayEnergy() {
        return todayEnergy;
    }

    public void setTodayEnergy(int todayEnergy) {
        this.todayEnergy = todayEnergy;
    }

    public int getTodayCarb() {
        return todayCarb;
    }

    public void setTodayCarb(int todayCarb) {
        this.todayCarb = todayCarb;
    }

    public int getTodayFat() {
        return todayFat;
    }

    public void setTodayFat(int todayFat) {
        this.todayFat = todayFat;
    }

    public int getTodayProtein() {
        return todayProtein;
    }

    public void setTodayProtein(int todayProtein) {
        this.todayProtein = todayProtein;
    }

    private TrackRecord(){
    }

    public static TrackRecord getInstance(){
        if (instance==null){
            instance = new TrackRecord();
        }
        return instance;
    }

}
