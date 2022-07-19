package com.fitnessapp.fitnessapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;

public class Timer extends AppCompatActivity {

    public static final String EXTRA_SECONDS = "com.fitnessapp.fitnessapp.EXTRA_SECONDS";
    public static final String EXTRA_MINUTES = "com.fitnessapp.fitnessapp.EXTRA_MINUTES";
    int seconds = 0;
    int minutes = 0;

    String alarm, theme;

    TextView textView2 = null;

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

        //Applies background to Timer
        if(theme.equals("Dark")){
            ConstraintLayout cl = (ConstraintLayout) findViewById(R.id.rl);
            cl.setBackgroundColor(Color.parseColor("#000000"));
            TextView tw = (TextView) findViewById(R.id.textView6);
            tw.setBackgroundColor(Color.parseColor("#000000"));
            tw.setTextColor(Color.parseColor("#FFFFFF"));
            Button btn1 = (Button) findViewById(R.id.timerBtn);
            btn1.setBackgroundTintList(ColorStateList.valueOf((Color.parseColor("#000000"))));
            btn1.setTextColor(Color.parseColor("#FFFFFF"));
            Button btn2 = (Button) findViewById(R.id.intervalTimerBtn);
            btn2.setBackgroundTintList(ColorStateList.valueOf((Color.parseColor("#000000"))));
            btn2.setTextColor(Color.parseColor("#FFFFFF"));
            Button btn3 = (Button) findViewById(R.id.exercisesBtn);
            btn3.setBackgroundTintList(ColorStateList.valueOf((Color.parseColor("#000000"))));
            btn3.setTextColor(Color.parseColor("#FFFFFF"));
            ImageButton btn4 = (ImageButton) findViewById(R.id.settingsButton);
            btn4.setColorFilter(Color.parseColor("#FFFFFF"));
            btn4.setBackgroundTintList(ColorStateList.valueOf((Color.parseColor("#000000"))));
        }



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
        else if(seconds<=0 && minutes<=0) {
            TextView tw = (TextView) findViewById(R.id.textView6);
            tw.setTextColor(getResources().getColor(R.color.red));
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
        if(theme.equals("Light")){
            tw.setTextColor(getResources().getColor(R.color.black));
        } else {
            tw.setTextColor(getResources().getColor(R.color.white));
        }
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

        //Sets theme for settings
        if(theme.equals("Dark")){
            TextView tw8 = (TextView) findViewById(R.id.textView8);
            tw8.setTextColor(Color.parseColor("#FFFFFF"));
            ConstraintLayout cl = (ConstraintLayout) findViewById(R.id.rl);
            cl.setBackgroundColor(Color.parseColor("#000000"));
            TextView tw11 = (TextView) findViewById(R.id.textView11);
            tw11.setTextColor(Color.parseColor("#FFFFFF"));
            TextView tw12= (TextView) findViewById(R.id.textView12);
            tw12.setTextColor(Color.parseColor("#FFFFFF"));
            Spinner spinner1 = (Spinner) findViewById(R.id.spinner);
            spinner1.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
            Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
            spinner2.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
            ImageButton closeBtn = (ImageButton) findViewById(R.id.closeButton);
            closeBtn.setColorFilter(Color.parseColor("#FFFFFF"));
            closeBtn.setBackgroundTintList(ColorStateList.valueOf((Color.parseColor("#000000"))));
        }

        SharedPreferences sharedpreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
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
                if(theme.equals("Dark")){
                    ((TextView) adapterView.getChildAt(0)).setTextColor(Color.parseColor("#FFFFFF"));
                    textView2.setTextColor(Color.parseColor("#FFFFFF"));
                    TextView tw8 = (TextView) findViewById(R.id.textView8);
                    tw8.setTextColor(Color.parseColor("#FFFFFF"));
                    ConstraintLayout cl = (ConstraintLayout) findViewById(R.id.rl);
                    cl.setBackgroundColor(Color.parseColor("#000000"));
                    TextView tw11 = (TextView) findViewById(R.id.textView11);
                    tw11.setTextColor(Color.parseColor("#FFFFFF"));
                    TextView tw12= (TextView) findViewById(R.id.textView12);
                    tw12.setTextColor(Color.parseColor("#FFFFFF"));
                    Spinner spinner1 = (Spinner) findViewById(R.id.spinner);
                    spinner1.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
                    Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
                    spinner2.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
                    ImageButton closeBtn = (ImageButton) findViewById(R.id.closeButton);
                    closeBtn.setColorFilter(Color.parseColor("#FFFFFF"));
                    closeBtn.setBackgroundTintList(ColorStateList.valueOf((Color.parseColor("#000000"))));
                } else {
                    TextView tw8 = (TextView) findViewById(R.id.textView8);
                    textView2.setTextColor(Color.parseColor("#000000"));
                    tw8.setTextColor(Color.parseColor("#000000"));
                    ConstraintLayout cl = (ConstraintLayout) findViewById(R.id.rl);
                    cl.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    TextView tw11 = (TextView) findViewById(R.id.textView11);
                    tw11.setTextColor(Color.parseColor("#000000"));
                    TextView tw12= (TextView) findViewById(R.id.textView12);
                    tw12.setTextColor(Color.parseColor("#000000"));
                    Spinner spinner1 = (Spinner) findViewById(R.id.spinner);
                    spinner1.getBackground().setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
                    Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
                    spinner2.getBackground().setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
                    ImageButton closeBtn = (ImageButton) findViewById(R.id.closeButton);
                    closeBtn.setColorFilter(Color.parseColor("#000000"));
                    closeBtn.setBackgroundTintList(ColorStateList.valueOf((Color.parseColor("#FFFFFF"))));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        Spinner spin2 = findViewById(R.id.spinner2);
        ArrayList<String>list2 = new ArrayList<>();
        list2.add("Beep");
        list2.add("Alarm long");
        list2.add("Cat");
        list2.add("Gong");
        list2.add("Cool tone");
        ArrayAdapter adapter2 = new ArrayAdapter(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item,list2);
        spin2.setAdapter(adapter2);
        int spinnerPosition2 = adapter2.getPosition(alarm);
        spin2.setSelection(spinnerPosition2);

        spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                textView2 = ((TextView) adapterView.getChildAt(0));
                String value = adapterView.getItemAtPosition(i).toString();
                alarm = value;
                int spinnerPosition = adapter.getPosition(alarm);
                spin2.setSelection(spinnerPosition);
                editor.putString("alarm", alarm);
                editor.commit();

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }

    public void closeSettings(View view){
        setContentView(R.layout.activity_timer);
        //Applies background to Timer
        if(theme.equals("Dark")){
            ConstraintLayout cl = (ConstraintLayout) findViewById(R.id.rl);
            cl.setBackgroundColor(Color.parseColor("#000000"));
            TextView tw = (TextView) findViewById(R.id.textView6);
            tw.setBackgroundColor(Color.parseColor("#000000"));
            tw.setTextColor(Color.parseColor("#FFFFFF"));
            Button btn1 = (Button) findViewById(R.id.timerBtn);
            btn1.setBackgroundTintList(ColorStateList.valueOf((Color.parseColor("#000000"))));
            btn1.setTextColor(Color.parseColor("#FFFFFF"));
            Button btn2 = (Button) findViewById(R.id.intervalTimerBtn);
            btn2.setBackgroundTintList(ColorStateList.valueOf((Color.parseColor("#000000"))));
            btn2.setTextColor(Color.parseColor("#FFFFFF"));
            Button btn3 = (Button) findViewById(R.id.exercisesBtn);
            btn3.setBackgroundTintList(ColorStateList.valueOf((Color.parseColor("#000000"))));
            btn3.setTextColor(Color.parseColor("#FFFFFF"));
            ImageButton btn4 = (ImageButton) findViewById(R.id.settingsButton);
            btn4.setColorFilter(Color.parseColor("#FFFFFF"));
            btn4.setBackgroundTintList(ColorStateList.valueOf((Color.parseColor("#000000"))));
        }
        minutes = 0;
        seconds = 0;

    }
}