package com.example.sla008.assistivemobility;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by lilga on 11/30/2017.
 *
 * this contains the contact page
 *
 * the first button contains a phone number, and switches the phone to call
 *  with the transit number
 *
 *  the second button contains an emai, and switches the phone to call with the transit number
 */

public class ContactFragment extends Fragment implements View.OnClickListener{
    String TAG = "ContactFragment";
    
    View myView;
    Button call, email;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.contact_layout, container, false);

        //set up the two buttons
        call = (Button) myView.findViewById(R.id.btnCall);
        call.setOnClickListener(this);

        email = (Button) myView.findViewById(R.id.btnEmail);
        email.setOnClickListener(this);

        return myView;
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.btnCall:
                //switch to phone call with transit number
                //Log.d(TAG, "onClick: Call");
                Intent phoneCall = new Intent(Intent.ACTION_DIAL);
                phoneCall.setData(Uri.parse("tel:" + getString(R.string.paratransit_number)));
                startActivity(phoneCall);
                break;

            case R.id.btnEmail:
                //switch to email with transit as recepiant
                //Log.d(TAG, "onClick: Email");
                Intent email = new Intent(Intent.ACTION_SENDTO);
                email.setData(Uri.parse("mailto::" + getString(R.string.paratransit_email)));
                startActivity(email);
                break;
        }
    }
}
