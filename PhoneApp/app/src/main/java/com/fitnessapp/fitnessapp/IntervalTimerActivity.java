package com.fitnessapp.fitnessapp;

import static java.lang.Integer.valueOf;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class IntervalTimerActivity extends AppCompatActivity {
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

        SharedPreferences sharedpreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        String alarm = getSharedPreferences("Settings", Context.MODE_PRIVATE).getString("alarm", null);
        String theme = getSharedPreferences("Settings", Context.MODE_PRIVATE).getString("theme", null);

        setContentView(R.layout.activity_interval_timer);
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
            sSeconds = "0" + secondsWork;
        } else {
            sSeconds = Integer.toString(secondsWork);
        }
        time = minutesWork + " : " + sSeconds;
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
            sSeconds = "0" + secondsWork;
        } else {
            sSeconds = Integer.toString(secondsWork);
        }
        time = minutesWork + " : " + sSeconds;
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
            sSeconds = "0" + secondsRest;
        } else {
            sSeconds = Integer.toString(secondsRest);
        }
        time = minutesRest + " : " + sSeconds;
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
            sSeconds = "0" + secondsRest;
        } else {
            sSeconds = Integer.toString(secondsRest);
        }
        time = minutesRest + " : " + sSeconds;
        tw.setText(time);
    }

    public void addSets(View view){
        TextView tw = (TextView) findViewById(R.id.textView4);
        setsCount += 1;
        tw.setText(String.format("%d", setsCount));
    }

    public void subtractSets(View view){
        TextView tw = (TextView) findViewById(R.id.textView4);
        if (setsCount <=1) setsCount=0;
        else setsCount -= 1;
        tw.setText(String.format("%d", setsCount));
    }

    public void runIntervalTimer(View view){
        int workSeconds = (secondsWork + minutesWork * 60) * 1000;
        int restSeconds = (secondsRest +minutesRest * 60) * 1000;
        int sets = setsCount;
        Intent intent = new Intent(this, IntervalTimerActionActivity.class);
        intent.putExtra("sets", sets);
        intent.putExtra("wSec", workSeconds);
        intent.putExtra("rSec", restSeconds);
        startActivity(intent);
    }

}