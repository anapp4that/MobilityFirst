package com.example.sla008.assistivemobility;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/**
 * Created by lilga on 11/30/2017.
 *
 *
 * check if user is loged in, if so launch the main activity with their acocunt
 *
 */

public class LoginActivity extends AppCompatActivity{
    String TAG = "LoginActivity";



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        findViewById(R.id.btnLogin).setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: login");
                login();
            }
        }));

        findViewById(R.id.btnSignUp).setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: signUp");
                signUp();
            }
        }));
    }

    public void login(){
        //log-in logic

        //launch the main activty
        Intent login = new Intent(this, MainActivity.class);
        //clear the back stack
        login.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(login);
        finish();
    }

    public void signUp(){
        //Sign-up logic

        //launch the sign up activity
        Intent login = new Intent(this, SignUpActivity.class);
        startActivity(login);


    }
}
