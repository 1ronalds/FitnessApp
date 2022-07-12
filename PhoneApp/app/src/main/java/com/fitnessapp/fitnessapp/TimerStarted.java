package com.fitnessapp.fitnessapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TimerStarted extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_started);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        Intent intent = getIntent();
        int minutes = intent.getIntExtra(Timer.EXTRA_MINUTES, 0); //0 is mandatory, default value
        int seconds = intent.getIntExtra(Timer.EXTRA_SECONDS, 0);
        TextView tw = (TextView) findViewById(R.id.textView6);
        String sSeconds = "";
        String time = "";
        if(seconds < 10) {
            sSeconds = "0" + Integer.toString(seconds);
        } else {
            sSeconds = Integer.toString(seconds);
        }
        time = Integer.toString(minutes) + " : " + sSeconds;
        tw.setText(time);
    }
    public void openTimer(View view){
        Intent activity = new Intent(this, Timer.class);
        startActivity(activity);
    }
}