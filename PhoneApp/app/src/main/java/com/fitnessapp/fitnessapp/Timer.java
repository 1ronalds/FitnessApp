package com.fitnessapp.fitnessapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Timer extends AppCompatActivity {

    public static final String EXTRA_SECONDS = "com.fitnessapp.fitnessapp.EXTRA_SECONDS";
    public static final String EXTRA_MINUTES = "com.fitnessapp.fitnessapp.EXTRA_MINUTES";
    int seconds = 0;
    int minutes = 0;

    String alarm, theme;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        SharedPreferences sharedpreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        alarm = getSharedPreferences("Settings", Context.MODE_PRIVATE).getString("alarm", null);
        theme = getSharedPreferences("Settings", Context.MODE_PRIVATE).getString("theme", null);


/*

        */


    }

    public void openExercises(View view) {
        Intent activity = new Intent(this, Exercises.class);
        startActivity(activity);
    }

    public void openIntervalTimer(View view){
        Intent activity = new Intent(this, IntervalTimerActivity.class);
        startActivity(activity);
    }
    public void openTimer(View view){
        Intent activity = new Intent(this, Timer.class);
        startActivity(activity);
    }
    public void openTimerStarted(View view) {
        if(seconds > 0 || minutes > 0 ) {
            Intent activity = new Intent(this, TimerStarted.class);
            activity.putExtra(EXTRA_MINUTES, minutes);
            activity.putExtra(EXTRA_SECONDS, seconds);
            startActivity(activity);
        }
    }


    public void addTime(View view){
        String time = "";
        String sSeconds = "";
        TextView tw = (TextView) findViewById(R.id.textView6);
        if(seconds+5 < 60){
            seconds += 5;
        } else {
            seconds = 0;
            minutes += 1;
        }
        if(seconds < 10) {
            sSeconds = "0" + Integer.toString(seconds);
        } else {
            sSeconds = Integer.toString(seconds);
        }
        time = Integer.toString(minutes) + " : " + sSeconds;
        tw.setText(time);
    }
    public void removeTime(View view){
        String time = "";
        String sSeconds = "";
        TextView tw = (TextView) findViewById(R.id.textView6);
        if(seconds-5 >= 0){
            seconds -= 5;
        } else if(seconds-5 == -5 && minutes > 0){
            seconds = 55;
            minutes -= 1;
        }

        if(seconds < 10) {
            sSeconds = "0" + Integer.toString(seconds);
        } else {
            sSeconds = Integer.toString(seconds);
        }
        time = Integer.toString(minutes) + " : " + sSeconds;
        tw.setText(time);

    }

    public void openSettings(View view){
        setContentView(R.layout.activity_settings);

        Spinner spin = findViewById(R.id.spinner);
        ArrayList<String>list = new ArrayList<>();
        list.add("Light");
        list.add("Dark");
        ArrayAdapter adapter = new ArrayAdapter(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item,list);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String value = adapterView.getItemAtPosition(i).toString();
                theme = value;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        Spinner spin2 = findViewById(R.id.spinner2);
        ArrayList<String>list2 = new ArrayList<>();
        list2.add("Default");
        list2.add("Long");
        ArrayAdapter adapter2 = new ArrayAdapter(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item,list2);
        spin2.setAdapter(adapter2);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String value = adapterView.getItemAtPosition(i).toString();
                alarm = value;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });



    }

    public void closeSettings(View view){
        setContentView(R.layout.activity_timer);
    }

}