package com.team.gemastik_mealplanner.ui.view.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.team.gemastik_mealplanner.R;

public class AuthentificationActivity extends AppCompatActivity {

    //widgets
    private ImageButton backButton;
    private ImageView imageView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification);

        //get widgets
        backButton = findViewById(R.id.auth_button_back);
        imageView1 = findViewById(R.id.auth_image_image1);

        getSupportFragmentManager().beginTransaction().replace(R.id.auth_frame_1, new LogInFragment(this)).commit();
    }
    public void setBackButton(ImageButton backButton) {
        this.backButton = backButton;
    }
    public ImageButton getBackButton() {
        return backButton;
    }
    public ImageView getImageView1() {
        return imageView1;
    }

    public void setImageView1(ImageView imageView1) {
        this.imageView1 = imageView1;
    }

}
