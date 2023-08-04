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

public class VerificationFragment extends Fragment {

    //widgets
    View verificationView;
    TextInputEditText otp1, otp2, otp3, otp4;
    AppCompatButton verifyButton;
    AppCompatTextView resendTextView;

    //application
    AuthentificationActivity parentClass;
    ForgetPasswordFragment forgetPasswordFragment;
    FragmentActivity fragmentActivity;

    public VerificationFragment() {}

    public VerificationFragment(AuthentificationActivity parentClass, ForgetPasswordFragment forgetPasswordFragment){
        this.parentClass = parentClass;
        this.forgetPasswordFragment = forgetPasswordFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // get widgets
        verificationView = inflater.inflate(R.layout.fragment_verification, container, false);
        otp1 = verificationView.findViewById(R.id.verify_textinputedittext_otp1);
        otp2 = verificationView.findViewById(R.id.verify_textinputedittext_otp2);
        otp3 = verificationView.findViewById(R.id.verify_textinputedittext_otp3);
        otp4 = verificationView.findViewById(R.id.verify_textinputedittext_otp4);
        verifyButton = verificationView.findViewById(R.id.verify_button_verify);
        resendTextView = verificationView.findViewById(R.id.verify_appcompattextview_resend);

        parentClass.getImageView1().setImageResource(R.drawable.auth_image_3);

        parentClass.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentActivity.getSupportFragmentManager().beginTransaction().replace(R.id.auth_frame_1, forgetPasswordFragment).commit();
            }
        });

        return verificationView;
    }
    @Override
    public void onAttach(@NonNull Activity activity) {
        fragmentActivity = (FragmentActivity) activity;
        super.onAttach(activity);
    }
}