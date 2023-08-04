package com.team.gemastik_mealplanner.ui.view.dashboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.team.gemastik_mealplanner.R;
import com.team.gemastik_mealplanner.data.model.Food;
import com.team.gemastik_mealplanner.ui.viewmodel.adapter.FragmentLikeAdapter;
import com.team.gemastik_mealplanner.ui.viewmodel.adapter.SaranCalendarAdapter;
import com.team.gemastik_mealplanner.util.DummyDao;

import java.util.ArrayList;
import java.util.Arrays;

public class LikeFragment extends Fragment {

    //Widgets
    View view;
    RecyclerView makananRecyclerView;

    //vars
    public ArrayList<Food> foods1 = new ArrayList<Food>(
//            Arrays.asList(
//                    new Food("Nasi Goreng", R.drawable.nasi_goreng,500,450,39,40),
//                    new Food("Steak Daging Sapi", R.drawable.stea_daging_sapi,280,265,56,34),
//                    new Food("Sate Ayam", R.drawable.sate_ayam,225,214,46,23),
//                    new Food("Ayam Bakar", R.drawable.ayam_bakar,226,218,56,23)
//            )
    );
    FragmentLikeAdapter fragmentLikeAdapter;


    public LikeFragment() {
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
        view =  inflater.inflate(R.layout.fragment_like, container, false);

        //extract widgets
        makananRecyclerView = view.findViewById(R.id.fragmentlike_recyclerview_makanan);

        //config
        DummyDao dummyDao = DummyDao.getInstance();
        makananRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        fragmentLikeAdapter = new FragmentLikeAdapter(dummyDao.getLikedFood());
        makananRecyclerView.setAdapter(fragmentLikeAdapter);

        return view;
    }
}