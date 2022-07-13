package com.fitnessapp.fitnessapp;

import static java.lang.Thread.*;

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

import java.util.TimerTask;

public class TimerStarted extends AppCompatActivity {
    CountDownTimer CDT = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_started);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
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
                ToneGenerator toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
                toneGen1.startTone(ToneGenerator.TONE_CDMA_MED_PBX_L,150);
                startActivity(activity);
            }

        }.start();

    }
    public void openTimer(View view){
        Intent activity = new Intent(this, Timer.class);
        startActivity(activity);
        CDT.cancel();
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
}