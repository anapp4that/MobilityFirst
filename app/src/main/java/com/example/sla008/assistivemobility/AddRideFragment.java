package com.example.sla008.assistivemobility;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.MapView;

/**
 * Created by lilga on 11/30/2017.
 *
 *
 * contain a google map that shows the user location on the map when the fragment is started
 *
 * allows user to set ride by location they are currenlty at
 *
 * allows the user to set a ride by street address
 *
 *
 */

public class AddRideFragment extends Fragment{
    String TAG = "AddRideFragment";
    MapView mapView;
    View myView;
    Button useGps,enterAddress;
    FragmentManager myFragmentManager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myFragmentManager = getFragmentManager();

        myView = inflater.inflate(R.layout.add_ride_layout, container, false);

        //set up google map
        mapView = (MapView) myView.findViewById(R.id.mapViewScheduleRide);
        mapView.onCreate(savedInstanceState);
        centerMap();


        //buttons
        useGps = (Button) myView.findViewById(R.id.btnUseGps);
        useGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                useGps();
            }
        });

        enterAddress = (Button) myView.findViewById(R.id.btnEnterAddress);
        enterAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterAddress();
            }
        });


        return myView;
    }

    public void centerMap(){

    }


    public void useGps(){
        myFragmentManager.beginTransaction().replace(R.id.content_frame,new AddRideByGPS()).commit();
    }

    public void enterAddress(){
        myFragmentManager.beginTransaction().replace(R.id.content_frame,new AddRideByAddress()).commit();

    }

    @Override
    public void onResume(){
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory(){
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
