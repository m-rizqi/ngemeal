package com.team.gemastik_mealplanner.ui.view.auth;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textfield.TextInputEditText;
import com.team.gemastik_mealplanner.R;

public class ForgetPasswordFragment extends Fragment {

    //widgets
    View forgetPasswordView;
    TextInputEditText emailTextInputEditText;
    AppCompatTextView loginTextView;
    AppCompatButton sendButton;

    //Aplication
    AuthentificationActivity parentClass;
    LogInFragment logInFragment;
    FragmentActivity fragmentActivity;

    public ForgetPasswordFragment(){}

    public ForgetPasswordFragment(AuthentificationActivity parentClass, LogInFragment logInFragment){
        this.parentClass = parentClass;
        this.logInFragment = logInFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //gets widgets
        forgetPasswordView = inflater.inflate(R.layout.fragment_forget_password, container, false);
        emailTextInputEditText = forgetPasswordView.findViewById(R.id.forgetpassword_edittext_email);
        loginTextView = forgetPasswordView.findViewById(R.id.forgetpassword_appcompattextview_login);
        sendButton = forgetPasswordView.findViewById(R.id.forgetpassword_button_send);

        parentClass.getImageView1().setImageResource(R.drawable.auth_image_2);

        //widget utility
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentActivity.getSupportFragmentManager().beginTransaction().replace(R.id.auth_frame_1, new VerificationFragment(parentClass,ForgetPasswordFragment.this)).commit();
            }
        });

        parentClass.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentActivity.getSupportFragmentManager().beginTransaction().replace(R.id.auth_frame_1, logInFragment).commit();
            }
        });

        return forgetPasswordView;
    }
    @Override
    public void onAttach(@NonNull Activity activity) {
        fragmentActivity = (FragmentActivity) activity;
        super.onAttach(activity);
    }
}