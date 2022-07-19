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
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;

public class IntervalTimerActivity extends AppCompatActivity {
    int minutesWork;
    int secondsWork;
    int minutesRest;
    int secondsRest;
    int setsCount;

    String alarm, theme;
    TextView textView2 = null;
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
        if(theme.equals("Dark")) {
            RelativeLayout rl = (RelativeLayout) findViewById(R.id.rl);
            rl.setBackgroundColor(Color.parseColor("#000000"));
            TextView tw = (TextView) findViewById(R.id.textView2);
            tw.setBackgroundColor(Color.parseColor("#000000"));
            tw.setTextColor(Color.parseColor("#FFFFFF"));
            TextView tw2 = (TextView) findViewById(R.id.textView4);
            tw2.setBackgroundColor(Color.parseColor("#000000"));
            tw2.setTextColor(Color.parseColor("#FFFFFF"));
            TextView tw3 = (TextView) findViewById(R.id.textView5);
            tw3.setBackgroundColor(Color.parseColor("#000000"));
            tw3.setTextColor(Color.parseColor("#FFFFFF"));
            TextView tw4 = (TextView) findViewById(R.id.textView7);
            tw4.setBackgroundColor(Color.parseColor("#000000"));
            tw4.setTextColor(Color.parseColor("#FFFFFF"));
            TextView tw5 = (TextView) findViewById(R.id.textView9);
            tw5.setBackgroundColor(Color.parseColor("#000000"));
            tw5.setTextColor(Color.parseColor("#FFFFFF"));
            TextView tw6 = (TextView) findViewById(R.id.textView10);
            tw6.setBackgroundColor(Color.parseColor("#000000"));
            tw6.setTextColor(Color.parseColor("#FFFFFF"));
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

    public void openIntervalTimer(View view){
        Intent activity = new Intent(this, IntervalTimerActivity.class);
        startActivity(activity);
    }
    public void openTimer(View view){
        Intent activity = new Intent(this, Timer.class);
        startActivity(activity);
    }

    public void openExercises(View view){
        Intent activity = new Intent(this, Exercises.class);
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
        if(theme.equals("Light")){
            tw.setTextColor(getResources().getColor(R.color.black));
        } else {
            tw.setTextColor(getResources().getColor(R.color.white));
        }
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

        if(theme.equals("Light")){
            tw.setTextColor(getResources().getColor(R.color.black));
        } else {
            tw.setTextColor(getResources().getColor(R.color.white));
        }
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

        if(theme.equals("Light")){
            tw.setTextColor(getResources().getColor(R.color.black));
        } else {
            tw.setTextColor(getResources().getColor(R.color.white));
        }
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
        if (workSeconds!=0 && restSeconds!=0 && sets!=0){
            Intent intent = new Intent(this, IntervalTimerActionActivity.class);
            intent.putExtra("sets", sets);
            intent.putExtra("wSec", workSeconds);
            intent.putExtra("rSec", restSeconds);
            startActivity(intent);
        }
        else if (sets == 0) {
            TextView tw = (TextView) findViewById(R.id.textView4);
            tw.setTextColor(getResources().getColor(R.color.red));
        }
        else if (workSeconds == 0) {
            TextView tw2 = (TextView) findViewById(R.id.textView7);
            tw2.setTextColor(getResources().getColor(R.color.red));
        }
        else if (restSeconds == 0) {
            TextView tw3 = (TextView) findViewById(R.id.textView10);
            tw3.setTextColor(getResources().getColor(R.color.red));
        }
    }

    public void openSettings(View view){
        setContentView(R.layout.activity_settings);
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
                textView2 = ((TextView) adapterView.getChildAt(0));
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
        setContentView(R.layout.activity_interval_timer);
        minutesWork = 0;
        secondsWork = 0;
        minutesRest = 0;
        secondsRest = 0;
        setsCount = 0;
        if(theme.equals("Dark")) {
            RelativeLayout rl = (RelativeLayout) findViewById(R.id.rl);
            rl.setBackgroundColor(Color.parseColor("#000000"));
            TextView tw = (TextView) findViewById(R.id.textView2);
            tw.setBackgroundColor(Color.parseColor("#000000"));
            tw.setTextColor(Color.parseColor("#FFFFFF"));
            TextView tw2 = (TextView) findViewById(R.id.textView4);
            tw2.setBackgroundColor(Color.parseColor("#000000"));
            tw2.setTextColor(Color.parseColor("#FFFFFF"));
            TextView tw3 = (TextView) findViewById(R.id.textView5);
            tw3.setBackgroundColor(Color.parseColor("#000000"));
            tw3.setTextColor(Color.parseColor("#FFFFFF"));
            TextView tw4 = (TextView) findViewById(R.id.textView7);
            tw4.setBackgroundColor(Color.parseColor("#000000"));
            tw4.setTextColor(Color.parseColor("#FFFFFF"));
            TextView tw5 = (TextView) findViewById(R.id.textView9);
            tw5.setBackgroundColor(Color.parseColor("#000000"));
            tw5.setTextColor(Color.parseColor("#FFFFFF"));
            TextView tw6 = (TextView) findViewById(R.id.textView10);
            tw6.setBackgroundColor(Color.parseColor("#000000"));
            tw6.setTextColor(Color.parseColor("#FFFFFF"));
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
}