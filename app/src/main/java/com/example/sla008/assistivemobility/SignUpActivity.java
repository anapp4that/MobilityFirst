package com.example.sla008.assistivemobility;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/**
 * Created by lilga on 11/30/2017.
 */

public class SignUpActivity extends AppCompatActivity{
    String TAG = "LoginActivity";



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);

        findViewById(R.id.btnSubmit).setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        }));
    }

    private void submit(){
        //switch to the main activity
        Intent submit = new Intent(this, MainActivity.class);
        //clear the back stack
        submit.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(submit);
        finish();
    }
}
