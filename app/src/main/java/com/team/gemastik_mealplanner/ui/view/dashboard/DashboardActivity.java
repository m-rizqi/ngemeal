package com.team.gemastik_mealplanner.ui.view.dashboard;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.MenuItem;

import com.team.gemastik_mealplanner.R;

public class DashboardActivity extends AppCompatActivity {

    //widgets
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //get all widgets
        bottomNavigationView = findViewById(R.id.dashboard_bottomnav_bottomnav1);
        getSupportFragmentManager().beginTransaction().replace(R.id.dashboard_frame_frame1,new HomeFragment()).commit();
        bottomNavigationView.setSelectedItemId(R.id.dashboard_bottomnav_itemHome);

        //widget utility
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.dashboard_bottomnav_itemlike:
                        fragment = new LikeFragment();
                        break;
                    case R.id.dashboard_bottomnav_itemCalendar:
                        fragment = new CalendarFragment();
                        break;
                    case R.id.dashboard_bottomnav_itemHome:
                        fragment = new HomeFragment();
                        break;
//                    case R.id.dashboard_bottomnav_itemCamera:
//                        fragment = new CameraFragment();
//                        break;
                    case R.id.dashboard_bottomnav_itemCheckbox:
                        fragment = new CheckboxFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.dashboard_frame_frame1,fragment).commit();

                return true;
            }
        });

    }
    public BottomNavigationView getBottomNavigationView() {
        return bottomNavigationView;
    }
}