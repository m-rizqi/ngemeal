package com.team.gemastik_mealplanner.ui.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.team.gemastik_mealplanner.R;

public class MainActivity extends AppCompatActivity {
    int count = 0;

    TextView textView1, textView2;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.id1);
        textView2 = findViewById(R.id.id2);

        button = findViewById(R.id.button1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                textView1.setText(String.valueOf(count));
            }
        });

    }
}