package com.fitnessapp.fitnessapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Timer extends AppCompatActivity {

    public static final String EXTRA_SECONDS = "com.fitnessapp.fitnessapp.EXTRA_SECONDS";
    public static final String EXTRA_MINUTES = "com.fitnessapp.fitnessapp.EXTRA_MINUTES";
    int seconds = 0;
    int minutes = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
    public void openExercises(View view) {
        Intent activity = new Intent(this, Exercises.class);
        startActivity(activity);
    }

    public void openSettings(View view) {
        Intent activity = new Intent(this, SettingsActivity.class);
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

}