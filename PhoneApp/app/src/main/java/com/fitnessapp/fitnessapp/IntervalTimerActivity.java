package com.fitnessapp.fitnessapp;

import static java.lang.Integer.valueOf;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class IntervalTimerActivity extends AppCompatActivity {
    int minutesWork;
    int secondsWork;
    int minutesRest;
    int secondsRest;
    int setsCount;

    String alarm, theme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        SharedPreferences sharedpreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        alarm = getSharedPreferences("Settings", Context.MODE_PRIVATE).getString("alarm", null);
        theme = getSharedPreferences("Settings", Context.MODE_PRIVATE).getString("theme", null);
        Log.i("CCC", alarm);
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

    public void openSettings(View view){
        setContentView(R.layout.activity_settings);
        SharedPreferences sharedpreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        Log.i("alarm", alarm);
        Log.i("theme", theme);
        Spinner spin = findViewById(R.id.spinner);
        ArrayList<String> list = new ArrayList<>();
        list.add("Light");
        list.add("Dark");
        ArrayAdapter adapter = new ArrayAdapter(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item,list);
        spin.setAdapter(adapter);
        int spinnerPosition = adapter.getPosition(theme);
        spin.setSelection(spinnerPosition);


        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String value = adapterView.getItemAtPosition(i).toString();
                theme = value;
                int spinnerPosition = adapter.getPosition(theme);
                spin.setSelection(spinnerPosition);
                editor.putString("theme", theme);
                editor.commit();
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
        int spinnerPosition2 = adapter2.getPosition(alarm);
        spin2.setSelection(spinnerPosition2);

        spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String value = adapterView.getItemAtPosition(i).toString();
                alarm = value;
                int spinnerPosition = adapter.getPosition(alarm);
                spin.setSelection(spinnerPosition);
                editor.putString("alarm", alarm);
                editor.commit();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }

    public void closeSettings(View view){
        setContentView(R.layout.activity_interval_timer);
    }

}