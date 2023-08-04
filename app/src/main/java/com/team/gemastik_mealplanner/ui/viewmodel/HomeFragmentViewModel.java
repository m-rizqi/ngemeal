package com.team.gemastik_mealplanner.ui.viewmodel;

import com.team.gemastik_mealplanner.data.model.Food;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class HomeFragmentViewModel {

    public int getDayNow(){
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        dayOfWeek--;
        return dayOfWeek;
    }

    public int getEatTime(){
        Calendar calendar = GregorianCalendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int eatTime = 0;
        if (hour >= 4 && hour < 12){
            eatTime = 1;
        }else if(hour >= 12 && hour < 17){
            eatTime = 2;
        }else {
            eatTime = 3;
        }
        return eatTime;
    }

}
