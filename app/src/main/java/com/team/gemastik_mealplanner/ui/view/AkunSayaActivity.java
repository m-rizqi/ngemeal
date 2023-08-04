package com.team.gemastik_mealplanner.ui.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.team.gemastik_mealplanner.R;

public class AkunSayaActivity extends AppCompatActivity {

    //widget
    ImageButton cancelButton, saveButton;
    CircularImageView userPhotoProfile;
    AppCompatTextView tambahFotoCompatTextView;
    TextInputLayout namaInputLayout, emailInputLayout, passwordInputLayout;
    TextInputEditText namaEditText, emailEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akun_saya);

        //extract widget
        cancelButton = findViewById(R.id.akunsaya_button_cancel);
        saveButton = findViewById(R.id.akunsaya_button_save);
        userPhotoProfile = findViewById(R.id.akunsaya_circleimage_userphoto);
        tambahFotoCompatTextView = findViewById(R.id.akunsaya_apptextview_tambahfoto);
        namaInputLayout = findViewById(R.id.akunsaya_inputlayout_nama);
        namaEditText = findViewById(R.id.akunsaya_edittext_nama);
        emailInputLayout = findViewById(R.id.akunsaya_inputlayout_email);
        emailEditText = findViewById(R.id.akunsaya_edittext_email);
        passwordInputLayout = findViewById(R.id.akunsaya_inputlayout_password);
        passwordEditText = findViewById(R.id.akunsaya_edittext_password);

        //widget config
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("name", "");
        namaEditText.setText(name);
        String email = preferences.getString("email", "");
        emailEditText.setText(email);
        String password = preferences.getString("password", "");
        passwordEditText.setText(password);

        //utility
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("name",namaEditText.getText().toString());
                editor.putString("email",emailEditText.getText().toString());
                editor.putString("password",passwordEditText.getText().toString());
                finish();
            }
        });
    }
}