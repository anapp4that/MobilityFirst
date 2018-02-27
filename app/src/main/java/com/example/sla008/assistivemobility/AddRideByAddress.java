package com.example.sla008.assistivemobility;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;


import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by lilga on 11/30/2017.
 *
 */

public class AddRideByAddress extends Fragment {
    String TAG = "AddRideFragment";
    View myView;
    TextView showDate, showTime;
    Button selectDate,selectTime,scheduleRide;
    public static Calendar myCalander;
    FragmentManager myFragmentManager;
    SimpleDateFormat date_format = new SimpleDateFormat("HH:mm");

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.address_add_ride_layout, container, false);
        myFragmentManager = getFragmentManager();
        myCalander = Calendar.getInstance();

        //text views
        showDate = (TextView) myView.findViewById(R.id.tvSelectDate);
        showTime = (TextView) myView.findViewById(R.id.tvSelectTime);

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

        // todo have it dispaly the currrent date in the date field upon page opening
        displayDate();
        displayTime();

        return myView;
    }

    //call back for the time picker, updates the text view when time is picked
    TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            myCalander.set(Calendar.HOUR_OF_DAY, hourOfDay);
            myCalander.set(Calendar.MINUTE,minute);
            displayTime();
        }
    };

    //call back for the date picker, updates the date view when time is picked
   DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
       @Override
       public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
           myCalander.set(Calendar.YEAR, year);
           myCalander.set(Calendar.MONTH, month);
           myCalander.set(Calendar.DAY_OF_MONTH, dayOfMonth);
           displayDate();
       }
   };


    //date picker fragment class returns calendar view
    public void selectDate(){
        //show the date picker
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), date, myCalander.get(Calendar.YEAR), myCalander.get(Calendar.MONTH), myCalander.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();

    }

    public void selectTime(){
        //allow the user to select the time of the ride
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), time, myCalander.get(Calendar.HOUR_OF_DAY), myCalander.get(Calendar.MINUTE),false);
        timePickerDialog.show();
    }

    public void createRide(){

        //assemble the data collected on the screen


        //launch the view ride page
        myFragmentManager.beginTransaction().replace(R.id.content_frame,new ViewRideFragment()).commit();
    }

    //display date
    public void displayDate(){
        showDate.setText((myCalander.get(Calendar.MONTH) +1) + "/" + myCalander.get(Calendar.DAY_OF_MONTH) + "/" + myCalander.get(Calendar.YEAR));
    }

    //display time
    public void displayTime(){
        //update the time on the screen
        showTime.setText(date_format.format(myCalander.getTime()));
    }

}



