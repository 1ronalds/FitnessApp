package com.fitnessapp.fitnessapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class IntervalTimerActionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interval_timer_action);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        Intent intent = getIntent();
        int workMin = intent.getIntExtra(IntervalTimerActivity.EXTRA_MINUTES_W, 0); //0 is mandatory, default value
        int workSec = intent.getIntExtra(IntervalTimerActivity.EXTRA_SECONDS_W, 0);
        int restMin = intent.getIntExtra(IntervalTimerActivity.EXTRA_MINUTES_R, 0);
        int restSec = intent.getIntExtra(IntervalTimerActivity.EXTRA_SECONDS_R, 0);
        int sets = intent.getIntExtra(IntervalTimerActivity.EXTRA_SETS, 0);
        TextView tw = (TextView) findViewById(R.id.textView);
        String workTime = Integer.toString(workMin)  + ":" + Integer.toString(workSec);
        tw.setText(workTime);
        TextView tw2 = (TextView) findViewById(R.id.textView3);
        tw2.setText("Rest");
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

}