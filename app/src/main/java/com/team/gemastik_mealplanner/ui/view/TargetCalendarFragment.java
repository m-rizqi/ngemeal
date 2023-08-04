package com.team.gemastik_mealplanner.ui.view;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.team.gemastik_mealplanner.R;

import java.util.ArrayList;

public class TargetCalendarFragment extends Fragment {

    //widgets
    View view;
    PieChart nutritionPie;
    TextView amountKalTextView;
    TextView carbTextView;
    TextView fatTextView;
    TextView proteinTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_target_calendar, container, false);

        //extract widgets
        nutritionPie = view.findViewById(R.id.targetcalendar_piechart_nutritionpie);
        setupPieChart();
        loadPieChartData(0.4f,0.4f,0.3f);
        amountKalTextView = view.findViewById(R.id.targetcalendar_textview_amountkal);
        carbTextView = view.findViewById(R.id.targetcalendar_textview_amountofcarb);
        fatTextView = view.findViewById(R.id.targetcalendar_textview_amountoffat);
        proteinTextView = view.findViewById(R.id.targetcalendar_textview_amountofprotein);

        //config
        amountKalTextView.setText(String.valueOf(1962));
        carbTextView.setText(String.valueOf(40) + " g");
        fatTextView.setText(String.valueOf(40) + " g");
        proteinTextView.setText(String.valueOf(30) + " g");

        return view;
    }

    private void setupPieChart(){
        nutritionPie.setDrawHoleEnabled(true);
        nutritionPie.setUsePercentValues(false);
        nutritionPie.setDrawEntryLabels(false);
        nutritionPie.getDescription().setEnabled(false);
        nutritionPie.setHoleRadius(80f);
        nutritionPie.animateXY(1000,1000);
        nutritionPie.setTouchEnabled(false);

        Legend legend = nutritionPie.getLegend();
        legend.setEnabled(false);
        legend.setDrawInside(false);
    }

    private void loadPieChartData(float carb, float fat, float protein){
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(carb,""));
        entries.add(new PieEntry(fat,""));
        entries.add(new PieEntry(protein,""));

        ArrayList<Integer> colors = new ArrayList<>();

        colors.add(Color.parseColor("#059255"));
        colors.add(Color.parseColor("#c2282d"));
        colors.add(Color.parseColor("#633379"));

        PieDataSet dataSet = new PieDataSet(entries,"Nutrition Calculate");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(false);

        nutritionPie.setData(data);
        nutritionPie.invalidate();

    }

}