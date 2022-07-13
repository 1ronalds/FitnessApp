package com.fitnessapp.fitnessapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class IntervalTimerActivity extends AppCompatActivity {
    public static final String EXTRA_SECONDS_W = "com.fitnessapp.fitnessapp.EXTRA_SECONDS_W";
    public static final String EXTRA_MINUTES_W = "com.fitnessapp.fitnessapp.EXTRA_MINUTES_W";
    public static final String EXTRA_SECONDS_R = "com.fitnessapp.fitnessapp.EXTRA_SECONDS_R";
    public static final String EXTRA_MINUTES_R = "com.fitnessapp.fitnessapp.EXTRA_MINUTES_R";
    public static final String EXTRA_SETS = "com.fitnessapp.fitnessapp.EXTRA_SETS";
    int minutesWork;
    int secondsWork;
    int minutesRest;
    int secondsRest;
    int setsCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_interval_timer);
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
    public void addWorkTime(View view){
        String time = "";
        String sSeconds = "";
        TextView tw = (TextView) findViewById(R.id.textView7);
        if(secondsWork+5 < 60){
            secondsWork += 5;
        } else {
            secondsWork = 0;
            minutesWork += 1;
        }
        if(secondsWork < 10) {
            sSeconds = "0" + Integer.toString(secondsWork);
        } else {
            sSeconds = Integer.toString(secondsWork);
        }
        time = Integer.toString(minutesWork) + " : " + sSeconds;
        tw.setText(time);
    }
    public void removeWorkTime(View view){
        String time = "";
        String sSeconds = "";
        TextView tw = (TextView) findViewById(R.id.textView7);
        if(secondsWork-5 >= 0){
            secondsWork -= 5;
        } else if(secondsWork-5 == -5 && minutesWork > 0){
            secondsWork = 55;
            minutesWork -= 1;
        }

        if(secondsWork < 10) {
            sSeconds = "0" + Integer.toString(secondsWork);
        } else {
            sSeconds = Integer.toString(secondsWork);
        }
        time = Integer.toString(minutesWork) + " : " + sSeconds;
        tw.setText(time);
    }
    public void addRestTime(View view){
        String time = "";
        String sSeconds = "";
        TextView tw = (TextView) findViewById(R.id.textView10);
        if(secondsRest+5 < 60){
            secondsRest += 5;
        } else {
            secondsRest = 0;
            minutesRest += 1;
        }
        if(secondsRest < 10) {
            sSeconds = "0" + Integer.toString(secondsRest);
        } else {
            sSeconds = Integer.toString(secondsRest);
        }
        time = Integer.toString(minutesRest) + " : " + sSeconds;
        tw.setText(time);
    }
    public void removeRestTime(View view){
        String time = "";
        String sSeconds = "";
        TextView tw = (TextView) findViewById(R.id.textView10);
        if(secondsRest-5 >= 0){
            secondsRest -= 5;
        } else if(secondsRest-5 == -5 && minutesRest > 0){
            secondsRest = 55;
            minutesRest -= 1;
        }

        if(secondsRest < 10) {
            sSeconds = "0" + Integer.toString(secondsRest);
        } else {
            sSeconds = Integer.toString(secondsRest);
        }
        time = Integer.toString(minutesRest) + " : " + sSeconds;
        tw.setText(time);
    }

    public void addSets(View view){
        TextView tw = (TextView) findViewById(R.id.textView4);
        setsCount += 1;
        tw.setText(Integer.toString(setsCount));
    }

    public void subtractSets(View view){
        TextView tw = (TextView) findViewById(R.id.textView4);
        if (setsCount <=1) setsCount=0;
        else setsCount -= 1;
        tw.setText(Integer.toString(setsCount));
    }

    public void runIntervalTimer(View view){
        Intent activity = new Intent(this, IntervalTimerActionActivity.class);
        activity.putExtra(EXTRA_MINUTES_W, minutesWork);
        activity.putExtra(EXTRA_SECONDS_W, secondsWork);
        activity.putExtra(EXTRA_MINUTES_R, minutesRest);
        activity.putExtra(EXTRA_SECONDS_R, secondsRest);
        activity.putExtra(EXTRA_SETS, setsCount);
        startActivity(activity);
    }

}