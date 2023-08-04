package com.team.gemastik_mealplanner.ui.viewmodel;

import android.content.Context;

import com.team.gemastik_mealplanner.R;
import com.team.gemastik_mealplanner.data.model.Food;
import com.team.gemastik_mealplanner.ui.view.dashboard.CalendarFragment;
import com.team.gemastik_mealplanner.util.DummyDao;

import java.util.ArrayList;
import java.util.Arrays;

public class CalendarFragmentViewModel {

    DummyDao dummyDao = DummyDao.getInstance();

    public ArrayList<Food> getBreakfast(){
        return dummyDao.getBreakFast();
    }
    public ArrayList<Food> getLunch(){
        return dummyDao.getLunch();
    }
    public ArrayList<Food> getDinner(){
        return dummyDao.getDinner();
    }
    public ArrayList<Food> getSnack(){
        return dummyDao.getSnack();
    }

}
