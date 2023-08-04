package com.team.gemastik_mealplanner.ui.view.auth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.team.gemastik_mealplanner.R;
import com.team.gemastik_mealplanner.ui.view.OnBoardingActivity;
import com.team.gemastik_mealplanner.ui.view.dashboard.DashboardActivity;
import com.team.gemastik_mealplanner.ui.view.screening.NutritionTargetActivity;
import com.team.gemastik_mealplanner.ui.viewmodel.LoginFragmentViewModel;

import java.util.HashMap;
import java.util.Set;

public class LogInFragment extends Fragment {

   //widget
    View LoginView;
    TextInputEditText emailInputEditText, passwordInputEditText;
    TextInputLayout emailInputLayout, passwordInputLayout;
    AppCompatTextView forgetPasswordTextView, signUpTextView;
    AppCompatButton loginButton;
    ImageButton facebookButton, googleButton;

    //Aplication
    AuthentificationActivity parentClass;
    FragmentActivity fragmentActivity;
    LoginFragmentViewModel viewModel = new LoginFragmentViewModel();
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    public LogInFragment(){}

    public LogInFragment(AuthentificationActivity parentClass){
        this.parentClass = parentClass;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LoginView = inflater.inflate(R.layout.fragment_log_in, container, false);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        // get widgets
        emailInputEditText = LoginView.findViewById(R.id.login_edittext_email);
        passwordInputEditText = LoginView.findViewById(R.id.login_edittext_password);
        emailInputLayout = LoginView.findViewById(R.id.login_inputlayout_1);
        passwordInputLayout = LoginView.findViewById(R.id.login_inputlayout_2);
        forgetPasswordTextView = LoginView.findViewById(R.id.login_button_textforgetpassword);
        signUpTextView = LoginView.findViewById(R.id.login_appcompattextview_signup);
        loginButton = LoginView.findViewById(R.id.login_button_login);
        facebookButton = LoginView.findViewById(R.id.login_imagebutton_facebook);
        googleButton = LoginView.findViewById(R.id.login_imagebutton_google);

        //widget utility
        forgetPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentActivity.getSupportFragmentManager().beginTransaction().replace(R.id.auth_frame_1, new ForgetPasswordFragment(parentClass,LogInFragment.this)).commit();
            }
        });

        parentClass.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), OnBoardingActivity.class));
            }
        });

        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentActivity.getSupportFragmentManager().beginTransaction().replace(R.id.auth_frame_1, new SignupFragment(parentClass,LogInFragment.this)).commit();
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                HashMap<String, Object> loginUser = viewModel.loginUser(emailInputEditText.getText().toString(), passwordInputEditText.getText().toString());
//                boolean valid = (boolean) loginUser.get("valid");
//                if (valid) {
                if (!emailInputEditText.getText().toString().isEmpty() && !passwordInputEditText.getText().toString().isEmpty()){
                    emailInputLayout.setErrorEnabled(false);
                    passwordInputLayout.setErrorEnabled(false);
//                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
//                    SharedPreferences.Editor editor = preferences.edit();
//                    editor.putBoolean("isLogin",true);
////                    String token = (String) loginUser.get("token");
////                    editor.putString("token",token);
////                    int id = (int) loginUser.get("id");
//                    editor.putInt("id",id);
//                    editor.apply();
//                    startActivity(new Intent(getContext(), DashboardActivity.class));
                    firebaseLogin(emailInputEditText.getText().toString(), passwordInputEditText.getText().toString(), getActivity());
                }else{
                    emailInputLayout.setErrorEnabled(true);
                    emailInputLayout.setError(" ");
                    passwordInputLayout.setErrorEnabled(true);
                    passwordInputLayout.setError(" ");
                    Toast.makeText(getContext(), "Email atau Password salah", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return  LoginView;
    }

    public void firebaseLogin(String email, String password, Activity activity){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putBoolean("isLogin",true);
                            editor.putString("id",task.getResult().getUser().getUid());
                            editor.apply();
                            startActivity(new Intent(getContext(), DashboardActivity.class));
                        }else{
                            Toast.makeText(activity, String.format("Sign In Failed : %s", task.getException().getMessage()), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        fragmentActivity = (FragmentActivity) activity;
        super.onAttach(activity);
    }
}