package com.fitnessapp.fitnessapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class TimerStarted extends AppCompatActivity {
    CountDownTimer CDT = null;
    private SoundPool soundpool;
    private int tone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_timer_started);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        SharedPreferences sharedpreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        String alarm = getSharedPreferences("Settings", Context.MODE_PRIVATE).getString("alarm", null);
        String theme = getSharedPreferences("Settings", Context.MODE_PRIVATE).getString("theme", null);
        if(theme.equals("Dark")){
            ConstraintLayout cl = (ConstraintLayout) findViewById(R.id.rl);
            cl.setBackgroundColor(Color.parseColor("#000000"));
            TextView tw = (TextView) findViewById(R.id.textView6);
            tw.setBackgroundColor(Color.parseColor("#000000"));
            tw.setTextColor(Color.parseColor("#FFFFFF"));
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

        Intent intent = getIntent();
        final int minutes = intent.getIntExtra(Timer.EXTRA_MINUTES, 0); //0 is mandatory, default value
        final int seconds = intent.getIntExtra(Timer.EXTRA_SECONDS, 0);
        TextView tw = (TextView) findViewById(R.id.textView6);
        String sSeconds = "";
        String time = "";
        if(seconds < 10) {
            sSeconds = "0" + Integer.toString(seconds);
        } else {
            sSeconds = Integer.toString(seconds);
        }
        time = Integer.toString(minutes) + " : " + sSeconds;
        Intent back = new Intent(this, Timer.class);
        int total = minutes * 60 + seconds;
            CDT = new CountDownTimer(total * 1000, 1000) {
            int sseconds = seconds+1;
            int mminutes = minutes;
            String ssSeconds;
            Intent activity = back;
            @Override
            public void onTick(long l){
                if (sseconds - 1 >= 0) {
                    sseconds -= 1;
                } else if (sseconds - 1 == -1 && mminutes > 0) {
                    mminutes -= 1;
                    sseconds = 59;
                }
                if (sseconds < 10) {
                    ssSeconds = "0" + Integer.toString(sseconds);
                } else {
                    ssSeconds = Integer.toString(sseconds);
                }
                String time = Integer.toString(mminutes) + " : " + ssSeconds ;
                tw.setText(time);
            }
            @Override
            public void onFinish(){
                tw.setText("0 : 00");
                soundpool.play(tone, 1, 1, 0, 0, 1);
                activity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(activity);
            }

        }.start();
        getWindow().setWindowAnimations(0);
    }

    @Override
    public void onBackPressed() {
        CDT.cancel();
        super.onBackPressed();
    }

    public void openTimer(View view){
        CDT.cancel();
        Intent activity = new Intent(this, Timer.class);
        activity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(activity);
    }
    public void pause(View view){
        CDT.cancel();
        Button pause = (Button) findViewById(R.id.buttonPause);
        Button play = (Button) findViewById(R.id.playButton);
        pause.setVisibility(View.INVISIBLE);
        play.setVisibility(View.VISIBLE);
    }
    public void restart(View view){
        Button pause = (Button) findViewById(R.id.buttonPause);
        Button play = (Button) findViewById(R.id.playButton);
        pause.setVisibility(View.VISIBLE);
        play.setVisibility(View.INVISIBLE);
        CDT.start();
    }

    public void chooseSound(String alarm) {
        switch (alarm) {
            case "Beep": {
                tone = soundpool.load(this, R.raw.beep4, 1);
                break;
            }
            case "Alarm long": {
                tone = soundpool.load(this, R.raw.alarm_long, 1);
                break;
            }
            case "Cat": {
                tone = soundpool.load(this, R.raw.cat_meow, 1);
                break;
            }
            case "Gong": {
                tone = soundpool.load(this, R.raw.gong, 1);
                break;
            }
            default: {
                tone = soundpool.load(this, R.raw.tissman__cool_tone, 1);
                break;
            }
        }
    }
}