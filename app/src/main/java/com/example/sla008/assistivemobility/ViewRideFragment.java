package com.example.sla008.assistivemobility;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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
 *
 * contains a button to add a ride, located at the bottom of the page
 *
 *
 * contains a list of rides displayed by time
 */

public class ViewRideFragment extends Fragment implements View.OnClickListener {
    String TAG = "ViewRideFragment";
    View myView;
    Button addRide;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.view_rides_layout, container, false);
        addRide = (Button) myView.findViewById(R.id.btnAddRide);
        addRide.setOnClickListener(this);
        return myView;
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.btnAddRide:
                Log.d(TAG, "onClick: AddRide");
                //switch to the add ride fragment
                FragmentTransaction t = this.getFragmentManager().beginTransaction();
                t.replace(R.id.content_frame,new AddRideFragment());
                t.commit();
                break;

        }
    }
}
