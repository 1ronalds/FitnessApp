package com.fitnessapp.fitnessapp;

import static java.lang.Integer.getInteger;
import static java.lang.Integer.valueOf;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    private boolean isTimerRunning;
    public int s;
    public int ss;

    /*private Button buttonStartPause;
    private Button buttonReset;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interval_timer_action);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        TextView tw = findViewById(R.id.textView);
        /*buttonStartPause = findViewById(R.id.button6);
        buttonReset = findViewById(R.id.button5);*/

        Intent intent = getIntent();
        final int sets = intent.getIntExtra("sets", 0);
        final int workSec = intent.getIntExtra("wSec", 0);
        final int restSec = intent.getIntExtra("rSec", 0);

        final int secWork = valueOf(workSec);
        final int secRest = valueOf(restSec);
        s = (int) valueOf(sets);
        ss = (int) valueOf(sets);


        int wSec = (int) secWork;
        int rSec = (int) secRest;
        int sec = wSec + rSec;
        TextView tw2 = (TextView) findViewById(R.id.textView3);
        TextView tw3 = (TextView) findViewById(R.id.textViewSets);


        CDT = new CountDownTimer(wSec, 1000) {
            public void onTick(long l) {
                long wSec = l;
                int min = (int) (wSec / 1000) / 60;
                int sec = (int) (wSec / 1000) % 60;
                String visual = String.format("%02d:%02d", min, sec);
                tw.setText(visual);
                tw2.setText("Work");
                tw3.setText("Set " + (ss + 1 - s));
            }

            public void onFinish() {
                ToneGenerator toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
                toneGen1.startTone(ToneGenerator.TONE_CDMA_MED_PBX_L, 150);
                CDT2 = new CountDownTimer(rSec, 1000) {

                    public void onTick(long l) {
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
                        runOnUiThread(new Runnable() {
                            public void run() {
                                s--;
                            }
                        });
                        if (s > 0) CDT.start();
                        else {
                            tw.setText("Well done!");
                            tw2.setText("Rest");
                        }
                    }
                }.start();
            }
        }.start();
    }

    public void openExercises(View view) {
        CDT.cancel();
        Intent activity = new Intent(this, Exercises.class);
        startActivity(activity);
    }

    public void openSettings(View view) {
        CDT.cancel();
        Intent activity = new Intent(this, SettingsActivity.class);
        startActivity(activity);
    }

    public void openIntervalTimer(View view){
        CDT.cancel();
        Intent activity = new Intent(this, IntervalTimerActivity.class);
        startActivity(activity);
    }

    public void openTimer(View view){
        CDT.cancel();
        Intent activity = new Intent(this, Timer.class);
        startActivity(activity);
    }

}