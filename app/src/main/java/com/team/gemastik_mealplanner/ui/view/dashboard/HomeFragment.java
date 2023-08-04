package com.team.gemastik_mealplanner.ui.view.dashboard;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.team.gemastik_mealplanner.R;
import com.team.gemastik_mealplanner.ui.view.UserProfileActivity;
import com.team.gemastik_mealplanner.ui.viewmodel.HomeFragmentViewModel;
import com.team.gemastik_mealplanner.util.DummyDao;

public class HomeFragment extends Fragment {

    //vars
    HomeFragmentViewModel viewModel = new HomeFragmentViewModel();

    //widgets
    View view;
    Object[][] date = new Object[7][2];
    CardView sarapanCardView,makanSiangCardView,makanMalamCardView,snackCardView;
    CardView[] eatTime;
    ImageButton userProfileButon;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        //get widgets
        date[0][0] = (RelativeLayout)view.findViewById(R.id.compass_cardview_circleSun);
        date[0][1] = (TextView)view.findViewById(R.id.compass_textview_Sun);
        date[1][0] = (RelativeLayout)view.findViewById(R.id.compass_cardview_circleMon);
        date[1][1] = (TextView)view.findViewById(R.id.compass_textview_Mon);
        date[2][0] = (RelativeLayout)view.findViewById(R.id.compass_cardview_circleTues);
        date[2][1] = (TextView)view.findViewById(R.id.compass_textview_Tues);
        date[3][0] = (RelativeLayout)view.findViewById(R.id.compass_cardview_circleWed);
        date[3][1] = (TextView)view.findViewById(R.id.compass_textview_Wed);
        date[4][0] = (RelativeLayout)view.findViewById(R.id.compass_cardview_circleThurs);
        date[4][1] = (TextView)view.findViewById(R.id.compass_textview_Thurs);
        date[5][0] = (RelativeLayout)view.findViewById(R.id.compass_cardview_circleFri);
        date[5][1] = (TextView)view.findViewById(R.id.compass_textview_Fri);
        date[6][0] = (RelativeLayout)view.findViewById(R.id.compass_cardview_circleSat);
        date[6][1] = (TextView)view.findViewById(R.id.compass_textview_Sat);

        sarapanCardView = view.findViewById(R.id.compass_cardview_sarapan);
        makanSiangCardView = view.findViewById(R.id.compass_cardview_makansiang);
        makanMalamCardView = view.findViewById(R.id.compass_cardview_makanmalam);
        snackCardView = view.findViewById(R.id.compass_cardview_snack);
        userProfileButon = view.findViewById(R.id.dashboard_imagebutton_userprofile);

        eatTime = new CardView[]{sarapanCardView, makanSiangCardView, makanMalamCardView, snackCardView};

        //widget config
        int day = viewModel.getDayNow();
        changeSelectedDate((RelativeLayout)date[day][0], (TextView)date[day][1]);

        int eatTime = viewModel.getEatTime();
        switch (eatTime){
            case 1:
                changeCardViewBackground(sarapanCardView);
                break;
            case 2:
                changeCardViewBackground(makanSiangCardView);
                break;
            case 3:
                changeCardViewBackground(makanMalamCardView);
                break;
        }

        //widget utility
        sarapanCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomNavigationView bottomNav = (BottomNavigationView)getActivity().findViewById(R.id.dashboard_bottomnav_bottomnav1);
                bottomNav.setSelectedItemId(R.id.dashboard_bottomnav_itemCalendar);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.dashboard_frame_frame1,
                        new CalendarFragment("Sarapan")).commit();
            }
        });
        makanSiangCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomNavigationView bottomNav = (BottomNavigationView)getActivity().findViewById(R.id.dashboard_bottomnav_bottomnav1);
                bottomNav.setSelectedItemId(R.id.dashboard_bottomnav_itemCalendar);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.dashboard_frame_frame1,
                        new CalendarFragment("Makan siang")).commit();
            }
        });
        makanMalamCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomNavigationView bottomNav = (BottomNavigationView)getActivity().findViewById(R.id.dashboard_bottomnav_bottomnav1);
                bottomNav.setSelectedItemId(R.id.dashboard_bottomnav_itemCalendar);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.dashboard_frame_frame1,
                        new CalendarFragment("Makan malam")).commit();
            }
        });
        snackCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomNavigationView bottomNav = (BottomNavigationView)getActivity().findViewById(R.id.dashboard_bottomnav_bottomnav1);
                bottomNav.setSelectedItemId(R.id.dashboard_bottomnav_itemCalendar);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.dashboard_frame_frame1,
                        new CalendarFragment("Camilan")).commit();
            }
        });
        userProfileButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), UserProfileActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    void changeCardViewBackground(CardView selected){
        for (CardView cardView: eatTime) {
            if (cardView.equals(selected)){
                cardView.setCardBackgroundColor(Color.parseColor("#FF00AA13"));
                TextView textView = (TextView) cardView.getChildAt(0);
                cardView.setForeground(null);
                cardView.setRadius(60);
                textView.setTextColor(Color.WHITE);
            }else{
                cardView.setCardBackgroundColor(Color.TRANSPARENT);
                cardView.setForeground(ContextCompat.getDrawable(getContext(), R.drawable.outlinedbox_cardview));
                TextView textView = (TextView) cardView.getChildAt(0);
                textView.setTextColor(Color.BLACK);
            }
        }
    }
    void changeSelectedDate(RelativeLayout dateView, TextView dateText){
        for (int i = 0; i < 6; i++) {
            RelativeLayout tempDateObject = (RelativeLayout) date[i][0];
            TextView tempDateText = (TextView)date[i][1];
            if (tempDateObject.equals(dateView) && tempDateText.equals(dateText)){
                tempDateObject.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.greencircle_date_shape));
                tempDateText.setTextColor(Color.WHITE);
            }else{
                tempDateObject.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.greystrokecircel_date_shape));
                tempDateText.setTextColor(Color.BLACK);
            }
        }
    }
}