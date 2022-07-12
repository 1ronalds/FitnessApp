package com.fitnessapp.fitnessapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Timer extends AppCompatActivity {


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
        Intent activity = new Intent(this, TimerStarted.class);
        startActivity(activity);
    }
}