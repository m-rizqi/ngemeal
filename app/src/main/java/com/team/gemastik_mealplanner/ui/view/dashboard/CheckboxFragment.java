package com.team.gemastik_mealplanner.ui.view.dashboard;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.team.gemastik_mealplanner.R;
import com.team.gemastik_mealplanner.data.model.Food;
import com.team.gemastik_mealplanner.ui.view.TrackRecordActivity;
import com.team.gemastik_mealplanner.ui.viewmodel.adapter.FragmentCheckBoxAdapter;
import com.team.gemastik_mealplanner.util.DummyDao;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckboxFragment extends Fragment {

    //widgets
    View view;
    RecyclerView historyRecyclerView;
    MaterialButton lihatAktivitasButton;

    //vars
    public ArrayList<Food> foods1 = new ArrayList<Food>(
//            Arrays.asList(
//                    new Food("Nasi Goreng", R.drawable.nasi_goreng,500,450,39,40),
//                    new Food("Steak Daging Sapi", R.drawable.stea_daging_sapi,280,265,56,34),
//                    new Food("Sate Ayam", R.drawable.sate_ayam,225,214,46,23),
//                    new Food("Ayam Bakar", R.drawable.ayam_bakar,226,218,56,23)
//            )
    );
    public ArrayList<String> eatTimes = new ArrayList<>(
            Arrays.asList(
                    "Sarapan", "Makan Malam", "Makan Malam", "Makan Siang"
            )
    );
    FragmentCheckBoxAdapter checkBoxAdapter;

    public CheckboxFragment() {
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
        view =  inflater.inflate(R.layout.fragment_checkbox, container, false);

        //extract widgets
        historyRecyclerView = view.findViewById(R.id.fragmentcheckbox_recyclerview_makanan);
        lihatAktivitasButton = view.findViewById(R.id.fragmentcheckbox_button_lihatkativitas);

        //widget config
        DummyDao dummyDao = DummyDao.getInstance();
        checkBoxAdapter = new FragmentCheckBoxAdapter(dummyDao.getHistoryFood(), eatTimes);
        historyRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        historyRecyclerView.setAdapter(checkBoxAdapter);

        //widget utility
        lihatAktivitasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), TrackRecordActivity.class));
            }
        });

        return view;
    }
}