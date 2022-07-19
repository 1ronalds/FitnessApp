package com.fitnessapp.fitnessapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.ToneGenerator;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class IntervalTimerActionActivity extends AppCompatActivity {
    private CountDownTimer CDT;
    public Button pauseStartTimer;
    public Button restartTimer;
    public boolean isTimerRunning = false;
    public long residualTime;
    public boolean isWorkTime = true;
    public boolean isRestTime = false;
    public int setsCount=1;

    private SoundPool soundpool;
    private int tone;

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
        if(theme.equals("Dark")){
            RelativeLayout rl = (RelativeLayout) findViewById(R.id.rl);
            rl.setBackgroundColor(Color.parseColor("#000000"));
            TextView tw = (TextView) findViewById(R.id.textView);
            tw.setBackgroundColor(Color.parseColor("#000000"));
            tw.setTextColor(Color.parseColor("#FFFFFF"));
            TextView tw2 = (TextView) findViewById(R.id.textView3);
            tw2.setBackgroundColor(Color.parseColor("#000000"));
            tw2.setTextColor(Color.parseColor("#FFFFFF"));
            TextView tw3 = (TextView) findViewById(R.id.textViewSets);
            tw3.setBackgroundColor(Color.parseColor("#000000"));
            tw3.setTextColor(Color.parseColor("#FFFFFF"));
            TextView btn1 = (TextView) findViewById(R.id.timerBtn);
            btn1.setBackgroundTintList(ColorStateList.valueOf((Color.parseColor("#000000"))));
            btn1.setTextColor(Color.parseColor("#FFFFFF"));
            TextView btn2 = (TextView) findViewById(R.id.intervalTimerBtn);
            btn2.setBackgroundTintList(ColorStateList.valueOf((Color.parseColor("#000000"))));
            btn2.setTextColor(Color.parseColor("#FFFFFF"));
            TextView btn3 = (TextView) findViewById(R.id.exercisesBtn);
            btn3.setBackgroundTintList(ColorStateList.valueOf((Color.parseColor("#000000"))));
            btn3.setTextColor(Color.parseColor("#FFFFFF"));
            ImageButton btn4 = (ImageButton) findViewById(R.id.settingsButton);
            btn4.setColorFilter(Color.parseColor("#FFFFFF"));
            btn4.setBackgroundTintList(ColorStateList.valueOf((Color.parseColor("#000000"))));

        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioatribbutes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            soundpool = new SoundPool.Builder()
                    .setMaxStreams(1)
                    .setAudioAttributes(audioatribbutes)
                    .build();
        }
        else {
            soundpool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        }
        chooseSound(alarm);

        TextView tw = findViewById(R.id.textView);
        TextView tw2 = (TextView) findViewById(R.id.textView3);
        TextView tw3 = (TextView) findViewById(R.id.textViewSets);
        pauseStartTimer = findViewById(R.id.button6);
        restartTimer = findViewById(R.id.button5);

        Intent intent = getIntent();
        final int sets = intent.getIntExtra("sets", 0);
        final int workSec = intent.getIntExtra("wSec", 0);
        final int restSec = intent.getIntExtra("rSec", 0);

        int min = (int) (workSec / 1000) / 60;
        int sec = (int) (workSec / 1000) % 60;
        String visual = String.format("%02d:%02d", min, sec);
        tw.setText(visual);
        residualTime=workSec;
        timer(restSec, workSec, sets, tw, tw2, tw3);

        restartTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTimer(restSec, workSec, sets, tw, tw2, tw3);
            }
        });

        pauseStartTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              pauseStartTimer(restSec, workSec, sets, tw, tw2, tw3);
            }
        });
        getWindow().setWindowAnimations(0);
    }

    public void timer(int restSec, int workSec, int sets, TextView tw, TextView tw2, TextView tw3){
        if (residualTime>0 && isWorkTime == true && setsCount<=sets) {
            isTimerRunning = true;
            CDT = new CountDownTimer(residualTime, 1000) {
                @Override
                public void onTick(long l) {
                    long wSec = l;
                    int min = (int) (wSec / 1000) / 60;
                    int sec = (int) (wSec / 1000) % 60;
                    String visual = String.format("%02d:%02d", min, sec);
                    tw.setText(visual);
                    tw2.setText("Work");
                    tw3.setText("Set " + setsCount + "/" + sets);
                    residualTime -= 1000;
                }

                @Override
                public void onFinish() {
                    soundpool.play(tone, 1, 1, 0, 0, 1);
                    isWorkTime = false;
                    isRestTime = true;
                    if(setsCount==sets){
                        isTimerRunning = false;
                        tw.setText("Well done!");
                        tw2.setText("Rest");
                    }
                    else {
                        isWorkTime = false;
                        isRestTime = true;
                        residualTime = restSec;
                        timer(restSec, workSec, sets, tw, tw2, tw3);
                    }
                }
            }.start();
        }
        else if (residualTime>0 && isRestTime==true && setsCount<sets){
            isTimerRunning = true;
            CDT = new CountDownTimer(residualTime, 1000) {
                @Override
                public void onTick(long l) {
                    long rSec = l;
                    int min = (int) (rSec / 1000) / 60;
                    int sec = (int) (rSec / 1000) % 60;
                    String visual = String.format("%02d:%02d", min, sec);
                    tw.setText(visual);
                    tw2.setText("Rest");
                    tw3.setText("Set " + setsCount + "/" + sets);
                    residualTime -= 1000;
                }

                @Override
                public void onFinish() {
                    soundpool.play(tone, 1, 1, 0, 0, 1);
                    isWorkTime = true;
                    isRestTime = false;
                    if(setsCount>=sets){
                        isTimerRunning = false;
                        tw.setText("Well done!");
                        tw2.setText("Rest");
                    }
                    else {
                        setsCount+=1;
                        residualTime = workSec;
                        isWorkTime = true;
                        isRestTime = false;
                        timer(restSec, workSec, sets, tw, tw2, tw3);
                     }
                }
            }.start();
        }
    }

    public void resetTimer(int restSec, int workSec, int sets, TextView tw, TextView tw2, TextView tw3){
        CDT.cancel();
        isWorkTime = true;
        isRestTime = false;
        residualTime = workSec;
        setsCount = 1;
        timer(restSec, workSec, sets, tw, tw2, tw3);
    }

    public void pauseStartTimer(int restSec, int workSec, int sets, TextView tw, TextView tw2, TextView tw3){
        if(isTimerRunning == true){
            CDT.cancel();
            isTimerRunning = false;
            pauseStartTimer.setText("▶");
        }
        else {
            isTimerRunning = true;
            pauseStartTimer.setText("II");
           timer(restSec, workSec, sets, tw, tw2, tw3);
        }
    }
    public void openIntervalTimer(View view){
        CDT.cancel();
        Intent activity = new Intent(this, IntervalTimerActivity.class);
        activity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(activity);
    }

    public void openTimer(View view){
        CDT.cancel();
        Intent activity = new Intent(this, Timer.class);
        activity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(activity);
    }

    public void openExercises(View view){CDT.cancel();
        CDT.cancel();
        Intent activity = new Intent(this, Exercises.class);
        activity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(activity);
    }
    public void opeSettings(View view){
        CDT.cancel();
        Intent activity = new Intent(this, Settings.class);
        activity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(activity);
    }

    public void chooseSound(String alarm) {
        switch (alarm){
            case "Beep":{
                tone = soundpool.load(this, R.raw.beep4, 1);
                break;
            }
            case "Alarm long":{
                tone = soundpool.load(this, R.raw.alarm_long, 1);
                break;
            }
            case "Cat":{
                tone = soundpool.load(this, R.raw.cat_meow, 1);
                break;
            }
            case "Gong":{
                tone = soundpool.load(this, R.raw.gong, 1);
                break;
            }
            default:{
                tone = soundpool.load(this, R.raw.tissman__cool_tone, 1);
                break;
            }
        }
    }

}