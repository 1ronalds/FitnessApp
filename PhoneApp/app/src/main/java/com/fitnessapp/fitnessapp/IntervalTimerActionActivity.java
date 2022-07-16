package com.fitnessapp.fitnessapp;

import static java.lang.Integer.getInteger;
import static java.lang.Integer.toBinaryString;
import static java.lang.Integer.valueOf;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class IntervalTimerActionActivity extends AppCompatActivity {
    private CountDownTimer CDT;
    private CountDownTimer CDT2;
    public Button pauseStartTimer;
    public Button restartTimer;
    public boolean isTimerRunning;
    public int s;
    public int ss;
    public long wSec;
    public long rSec;
    public long wwSec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interval_timer_action);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        SharedPreferences sharedpreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        String alarm = getSharedPreferences("Settings", Context.MODE_PRIVATE).getString("alarm", null);
        String theme = getSharedPreferences("Settings", Context.MODE_PRIVATE).getString("theme", null);

        TextView tw = findViewById(R.id.textView);

        Intent intent = getIntent();
        final int sets = intent.getIntExtra("sets", 0);
        final int workSec = intent.getIntExtra("wSec", 0);
        final int restSec = intent.getIntExtra("rSec", 0);

        final int secWork = valueOf(workSec);
        final int secRest = valueOf(restSec);
        wSec = secWork;
        rSec = secRest;
        s = valueOf(sets);
        ss = s;

        TextView tw2 = (TextView) findViewById(R.id.textView3);
        TextView tw3 = (TextView) findViewById(R.id.textViewSets);
        pauseStartTimer = findViewById(R.id.button6);
        restartTimer = findViewById(R.id.button5);

        CDT = new CountDownTimer(wSec, 1000) {
            @Override
            public void onTick(long l) {
                isTimerRunning = true;
                wwSec = l;
                int min = (int) (wwSec / 1000) / 60;
                int sec = (int) (wwSec / 1000) % 60;
                String visual = String.format("%02d:%02d", min, sec);
                tw.setText(visual);
                tw2.setText("Work");
                tw3.setText("Set " + (ss + 1 - s));
            }

            public void onFinish() {
                ToneGenerator toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
                toneGen1.startTone(ToneGenerator.TONE_CDMA_MED_PBX_L, 150);
                if ((s-1)>0) {
                    CDT2 = new CountDownTimer(rSec, 1000) {
                        public void onTick(long l) {
                            isTimerRunning = true;
                            long rSec = l;
                            int min = (int) (rSec / 1000) / 60;
                            int sec = (int) (rSec / 1000) % 60;
                            String visual = String.format("%02d:%02d", min, sec);
                            tw.setText(visual);
                            tw2.setText("Rest");
                        }
                        public void onFinish() {
                            ToneGenerator toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
                            toneGen1.startTone(ToneGenerator.TONE_CDMA_MED_PBX_L, 150);
                            tw2.setText("Rest");
                            s--;
                            if (s > 0) CDT.start();
                            else {
                                tw.setText("Well done!");
                                tw2.setText("Rest");
                            }
                        }
                    }.start();
                }
                else{
                    tw.setText("Well done!");
                    tw2.setText("Rest");
                }
            }
        }.start();

        pauseStartTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isTimerRunning==true){
                    CDT.cancel();
                    isTimerRunning = false;
                    pauseStartTimer.setText("▶");
                }
                else if(tw2.getText()=="Work"){
                    pauseStartTimer.setText("II");
                    isTimerRunning = true;
                    CDT.start();
                }
                else {
                    pauseStartTimer.setText("II");
                    isTimerRunning = true;
                    CDT2.start();
                }
            }
        });
    }


    public void openIntervalTimer(View view){
        CDT.cancel();
        CDT2.cancel();
        Intent activity = new Intent(this, IntervalTimerActivity.class);
        startActivity(activity);
    }

    public void openTimer(View view){
        CDT.cancel();
        CDT2.cancel();
        Intent activity = new Intent(this, Timer.class);
        startActivity(activity);
    }

    public void resetTimer(View view){
        CDT.cancel();
        CDT2.cancel();
        Intent activity = new Intent(this, IntervalTimerActivity.class);
        startActivity(activity);
    }

    public void openExercises(View view){
        CDT.cancel();
        CDT2.cancel();
        Intent activity = new Intent(this, Exercises.class);
        startActivity(activity);
    }
    public void opeSettings(View view){
        CDT.cancel();
        CDT2.cancel();
    }

}