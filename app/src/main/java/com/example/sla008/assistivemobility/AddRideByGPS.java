package com.example.sla008.assistivemobility;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by lilga on 11/30/2017.
 *
 *  open fragment containg a map
 *
 *  display blip showing current loaction
 *
 *  show the user location
 *      allow user to drag to new location?
 *
 *      if time permits geofence the map
 *
 *   set the ride location
 *   set the ride time
 *   set the ride date
 *   add notes for the driver
 *
 *
 */

public class AddRideByGPS extends Fragment{
    String TAG = "AddRideFragment";

    Calendar myCalandar;

    GoogleMap googleMap;
    MapView mapView;
    View myView;
    Button selectDate, selectTime, scheduleRide;
    FragmentManager myFragmentManager;
    TextView showDate, showTime;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myFragmentManager = getFragmentManager();
        myView = inflater.inflate(R.layout.gps_add_ride_layout, container, false);


        //set up google map
        mapView = (MapView) myView.findViewById(R.id.mapViewGpsAddRide);
        mapView.onCreate(savedInstanceState);

        //buttons
        selectDate = (Button) myView.findViewById(R.id.btnSelectDate);
        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectDate();
            }
        });

        selectTime = (Button) myView.findViewById(R.id.btnSelectTime);
        selectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectTime();
            }
        });

        scheduleRide = (Button) myView.findViewById(R.id.btnCreateRide);
        scheduleRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createRide();
            }
        });

        //text view
        showDate = (TextView) myView.findViewById(R.id.tvSelectDate);
        showTime = (TextView) myView.findViewById(R.id.tvSelectTime);

        myCalandar= Calendar.getInstance();
        displayDate();
        displayTime();
        //get time
        //displayTime(Calendar.get());
        return myView;
    }

    //displays a dialogue with a calander that the user can use
    public void selectDate(){
        //show the date picker
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), date, myCalandar.get(Calendar.YEAR), myCalandar.get(Calendar.MONTH), myCalandar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public void selectTime(){
        //allow the user to select the time of the ride
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), time, myCalandar.get(Calendar.HOUR_OF_DAY), myCalandar.get(Calendar.MINUTE),false);
        timePickerDialog.show();
    }

    public void createRide(){
        //create the ride
        //save it
        //inflate viewRide Fragment, which should display the scheduled rides, including the new one
        //launch the view ride page
        myFragmentManager.beginTransaction().replace(R.id.content_frame, new ViewRideFragment())
                .commit();
    }


    //call back for the time picker, updates the text view when date is picked
    TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            myCalandar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            myCalandar.set(Calendar.MINUTE,minute);
            displayTime();
        }
    };

    //call back for the date picker, updates the text view when date is picked
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalandar.set(Calendar.YEAR, year);
            myCalandar.set(Calendar.MONTH, month);
            myCalandar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            displayDate();
        }
    };

    public void displayDate(){
        showDate.setText((myCalandar.get(Calendar.MONTH) +1) + "/" + myCalandar.get(Calendar.DAY_OF_MONTH) + "/" + myCalandar.get(Calendar.YEAR));

    }


    public void displayTime(){
        //update the time on the screen
        SimpleDateFormat date_format = new SimpleDateFormat("HH:mm");
        showTime.setText(date_format.format(myCalandar.getTime()));

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
