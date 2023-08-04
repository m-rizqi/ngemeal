package com.team.gemastik_mealplanner.ui.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.team.gemastik_mealplanner.R;
import com.team.gemastik_mealplanner.ui.viewmodel.adapter.SaranCalendarAdapter;
import com.team.gemastik_mealplanner.ui.viewmodel.CalendarFragmentViewModel;

public class SaranCalendarFragment extends Fragment {

    //widgets
    View view;
    RecyclerView makananRecyclerView;
    ImageButton refreshButton;

    //vars
    SaranCalendarAdapter saranCalendarAdapter;
    String eatTime;
    CalendarFragmentViewModel calendarFragmentViewModel;

    public SaranCalendarFragment(String eatTime){
        this.eatTime = eatTime;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_saran_calendar, container, false);

        calendarFragmentViewModel  = new CalendarFragmentViewModel();

        //extract widgets
        refreshButton = view.findViewById(R.id.sarancalendar_imagebutton_refresh);
        makananRecyclerView = view.findViewById(R.id.sarancalendar_recyclerview_makanan);

        //config
        makananRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        switch (eatTime){
            case "Sarapan":
                saranCalendarAdapter = new SaranCalendarAdapter(calendarFragmentViewModel.getBreakfast());
                break;
            case "Makan siang":
                saranCalendarAdapter = new SaranCalendarAdapter(calendarFragmentViewModel.getLunch());
                break;
            case "Makan malam":
                saranCalendarAdapter = new SaranCalendarAdapter(calendarFragmentViewModel.getDinner());
                break;
            case "Camilan":
                saranCalendarAdapter = new SaranCalendarAdapter(calendarFragmentViewModel.getSnack());
                break;
        }
        makananRecyclerView.setAdapter(saranCalendarAdapter);

        return view;
    }
}