package com.team.gemastik_mealplanner.ui.view.dashboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.team.gemastik_mealplanner.R;
import com.team.gemastik_mealplanner.ui.view.SaranCalendarFragment;
import com.team.gemastik_mealplanner.ui.view.TargetCalendarFragment;
import com.team.gemastik_mealplanner.ui.viewmodel.HomeFragmentViewModel;

public class CalendarFragment extends Fragment {

    //vars
    String mealTime;

    //widgets
    ImageButton backButton;
    TextView mealTimeTextView;
    MaterialButton saranButton, targetButton;
    FrameLayout frameLayout;


    public CalendarFragment() {
        HomeFragmentViewModel homeFragmentViewModel = new HomeFragmentViewModel();
        int eatTime = homeFragmentViewModel.getEatTime();
        switch (eatTime){
            case 1:
                mealTime = "Sarapan";
                break;
            case 2:
                mealTime = "Makan siang";
                break;
            case 3:
                mealTime = "Makan malam";
                break;
        }
    }

    public CalendarFragment(String mealTime){
        this.mealTime = mealTime;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_calendar, container, false);

        //get Widgets
        backButton = view.findViewById(R.id.calendar_button_back);
        mealTimeTextView = view.findViewById(R.id.calendar_textview_1);
        saranButton = view.findViewById(R.id.calendar_button_saran);
        targetButton = view.findViewById(R.id.calendar_button_target);
        frameLayout = view.findViewById(R.id.calendar_frame_1);

        //config
        saranButton.setChecked(true);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.calendar_frame_1, new SaranCalendarFragment(mealTime)).commit();

        //widget utility
        mealTimeTextView.setText(mealTime);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.dashboard_frame_frame1,
                        new HomeFragment()).commit();
            }
        });
        saranButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saranButton.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.app_green_dark));
                targetButton.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.app_green));
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.calendar_frame_1, new SaranCalendarFragment(mealTime)).commit();
            }
        });

        targetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saranButton.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.app_green));
                targetButton.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.app_green_dark));
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.calendar_frame_1, new TargetCalendarFragment()).commit();
            }
        });

        return view;
    }

}