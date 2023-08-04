package com.team.gemastik_mealplanner.ui.view.auth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.team.gemastik_mealplanner.R;
import com.team.gemastik_mealplanner.data.model.PreRegisteredUser;
import com.team.gemastik_mealplanner.ui.view.screening.BiodataActivity;

public class SignupFragment extends Fragment {

    //vars
    PreRegisteredUser preRegisteredUser ;

    //widgets
    View signupView;
    TextInputEditText fullnameTextInputEditText,emailTextInputEditText,passwordTextInputEditText;
    TextInputLayout fullnameTextInputLayout, emailTextInputLayout, passwordTextInputLayout;
    AppCompatButton signupButton;
    ImageButton facebookButton,googleButton;

    //Aplication
    AuthentificationActivity parentClass;
    LogInFragment logInFragment;
    FragmentActivity fragmentActivity;

    public SignupFragment(){}

    public SignupFragment(AuthentificationActivity parentClass, LogInFragment logInFragment){
        this.parentClass = parentClass;
        this.logInFragment = logInFragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        preRegisteredUser = PreRegisteredUser.getInstance();

        //gets widgets
        signupView = inflater.inflate(R.layout.fragment_signup, container, false);
        fullnameTextInputEditText = signupView.findViewById(R.id.signup_edittext_fullname);
        emailTextInputEditText = signupView.findViewById(R.id.signup_edittext_email);
        passwordTextInputEditText = signupView.findViewById(R.id.signup_edittext_password);
        signupButton = signupView.findViewById(R.id.signup_button_signup);
        facebookButton = signupView.findViewById(R.id.signup_imagebutton_facebook);
        googleButton = signupView.findViewById(R.id.signup_imagebutton_google);
        fullnameTextInputLayout = signupView.findViewById(R.id.signup_inputlayout_1);
        emailTextInputLayout = signupView.findViewById(R.id.signup_inputlayout_2);
        passwordTextInputLayout = signupView.findViewById(R.id.signup_inputlayout_3);

        parentClass.getImageView1().setImageResource(R.drawable.auth_image_4);

        //widget utility
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = fullnameTextInputEditText.getText().toString();
                String email = emailTextInputEditText.getText().toString();
                String password = passwordTextInputEditText.getText().toString();
                if (name.trim().length() == 0 || email.trim().length() == 0 || password.trim().length() == 0){
                    if (name.trim().length() == 0){
                        fullnameTextInputLayout.setErrorEnabled(true);
                        fullnameTextInputLayout.setError("Nama tidak boleh kosong !");
                    }else{
                        fullnameTextInputLayout.setErrorEnabled(false);
                        fullnameTextInputLayout.setError(null);
                    }
                    if (email.trim().length() == 0){
                        emailTextInputLayout.setErrorEnabled(true);
                        emailTextInputLayout.setError("Email tidak boleh kosong !");
                    }else {
                        emailTextInputLayout.setErrorEnabled(false);
                        emailTextInputLayout.setError(null);
                    }
                    if (password.trim().length() == 0){
                        passwordTextInputLayout.setErrorEnabled(true);
                        passwordTextInputLayout.setError("Password tidak boleh kosong !");
                    }else{
                        passwordTextInputLayout.setErrorEnabled(false);
                        passwordTextInputLayout.setError(null);
                    }
                }else{

                    fullnameTextInputLayout.setErrorEnabled(false);
                    fullnameTextInputLayout.setError(null);
                    emailTextInputLayout.setErrorEnabled(false);
                    emailTextInputLayout.setError(null);
                    passwordTextInputLayout.setErrorEnabled(false);
                    passwordTextInputLayout.setError(null);

                    preRegisteredUser.setName(name);
                    preRegisteredUser.setEmail(email);
                    preRegisteredUser.setPassword(password);
                    startActivity(new Intent(getContext(),BiodataActivity.class));
                }
            }
        });

        parentClass.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentActivity.getSupportFragmentManager().beginTransaction().replace(R.id.auth_frame_1, logInFragment).commit();
            }
        });

        return signupView;
    }
    @Override
    public void onAttach(@NonNull Activity activity) {
        fragmentActivity = (FragmentActivity) activity;
        super.onAttach(activity);
    }
}