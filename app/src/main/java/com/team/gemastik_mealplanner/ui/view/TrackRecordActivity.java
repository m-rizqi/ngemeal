package com.team.gemastik_mealplanner.ui.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.google.android.material.slider.Slider;
import com.team.gemastik_mealplanner.R;
import com.team.gemastik_mealplanner.data.model.TrackRecord;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class TrackRecordActivity extends AppCompatActivity {
    //vars
    ArrayList<BarEntry> entries = new ArrayList<>();

    //widgets
    BarChart calorieBarChart;
    ImageButton backButton;
    TextView todayCalorieTextView, targetCalorieTextView,
        calorieTexView, carbTextView, fatTextView, proteinTextView;
    Slider calorieSlider, carbSlider, fatSlider, proteinSlider;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_record);

//        extract widgets
        calorieBarChart = findViewById(R.id.trackrecord_barchart_calorie);
        backButton = findViewById(R.id.trackrecord_imagebutton_back);
        todayCalorieTextView = findViewById(R.id.trackrecord_textview_todaycalorie);
        targetCalorieTextView = findViewById(R.id.trackrecord_textview_targetcalorie);
        calorieSlider = findViewById(R.id.trackrecord_slider_calorie);
        calorieTexView = findViewById(R.id.trackrecord_textview_calorieslider);
        carbSlider = findViewById(R.id.trackrecord_slider_carb);
        carbTextView = findViewById(R.id.trackrecord_textview_carbslider);
        fatSlider = findViewById(R.id.trackrecord_slider_fat);
        fatTextView = findViewById(R.id.trackrecord_textview_fatslider);
        proteinSlider = findViewById(R.id.trackrecord_slider_protein);
        proteinTextView = findViewById(R.id.trackrecord_textview_proteinslider);

//        widget config
        setCalorieBarChart();

        TrackRecord trackRecord = TrackRecord.getInstance();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int achievedEnergy = trackRecord.getTodayEnergy();
        int achievedCarb = trackRecord.getTodayCarb();
        int achievedFat = trackRecord.getTodayFat();
        int achievedProtein = trackRecord.getTodayProtein();

        int calorieTarget = (int) preferences.getFloat("calorie",0);
        int carbTarget = (int) preferences.getFloat("carb",0);
        int fatTarget = (int) preferences.getFloat("fat",0);
        int proteinTarget = (int) preferences.getFloat("protein",0);

        if (achievedEnergy <= calorieTarget){
            todayCalorieTextView.setText(String.valueOf(achievedEnergy));
            targetCalorieTextView.setText("/"+calorieTarget+" kkal");
            calorieSlider.setValueTo(calorieTarget);
            calorieSlider.setValue(achievedEnergy);
            calorieTexView.setText(String.format("%d / %d kkal",achievedEnergy, calorieTarget));
        }

        if (achievedCarb <= carbTarget){
            carbSlider.setValueTo(carbTarget);
            carbSlider.setValue(achievedCarb);
            carbTextView.setText(String.format("%d / %d g",achievedCarb, carbTarget));
        }

        if (achievedFat <= fatTarget){
            fatSlider.setValueTo(fatTarget);
            fatSlider.setValue(achievedFat);
            fatTextView.setText(String.format("%d / %d g",achievedFat, fatTarget));
        }

        if (achievedProtein <= proteinTarget){
            proteinSlider.setValueTo(proteinTarget);
            proteinSlider.setValue(achievedProtein);
            proteinTextView.setText(String.format("%d / %d g",achievedProtein,proteinTarget));
        }

//        widget utility
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    private void setCalorieBarChart(){

        entries.add(new BarEntry(0, new float[]{1700, 262}));
        entries.add(new BarEntry(1, new float[]{1900, 62}));
        entries.add(new BarEntry(2, new float[]{1400, 400}));
        entries.add(new BarEntry(3, new float[]{1970, 530}));
        entries.add(new BarEntry(4, new float[]{2870, 30}));
        entries.add(new BarEntry(5, new float[]{1700, 400}));
        entries.add(new BarEntry(6, new float[]{1550, 340}));

        BarDataSet barDataSet = new BarDataSet(entries, "");
        barDataSet.setColors(new int[]{Color.parseColor("#00AA13"),Color.parseColor("#CCEED0")});
//        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(10);
        barDataSet.setDrawIcons(false);
        barDataSet.setStackLabels(new String[]{"Capaian","Target"});
        barDataSet.setDrawValues(false);

        final ArrayList<String> xAxisLabel = new ArrayList<>();
        xAxisLabel.add("Min");
        xAxisLabel.add("Sen");
        xAxisLabel.add("Sel");
        xAxisLabel.add("Rab");
        xAxisLabel.add("Kam");
        xAxisLabel.add("Jum");
        xAxisLabel.add("Sab");

        BarData barData = new BarData(barDataSet);

        XAxis xAxis = calorieBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisLabel));
        xAxis.setTextSize(9f);

        calorieBarChart.setMaxVisibleValueCount(3000);
        calorieBarChart.setFitBars(true);
        calorieBarChart.setData(barData);
        calorieBarChart.animateY(2000);
        calorieBarChart.invalidate();
        calorieBarChart.setDrawValueAboveBar(false);
        calorieBarChart.getAxisRight().setEnabled(false);
        calorieBarChart.setTouchEnabled(true);
        calorieBarChart.setPinchZoom(false);
        calorieBarChart.setDoubleTapToZoomEnabled(false);
        calorieBarChart.getDescription().setEnabled(false);

    }
}