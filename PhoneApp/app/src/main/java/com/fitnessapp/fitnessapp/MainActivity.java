package com.fitnessapp.fitnessapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    // Works well on screen Pixel XL

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            public void run(){}
        },3000);
        SharedPreferences sharedpreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        String alarm = getSharedPreferences("Settings", Context.MODE_PRIVATE).getString("alarm", null);
        String theme = getSharedPreferences("Settings", Context.MODE_PRIVATE).getString("theme", null);
        if(alarm == null && theme == null){
            editor.putString("alarm", "default");
            editor.putString("theme", "light");
            editor.commit();
        }
        setContentView(R.layout.activity_main);
        Intent activity = new Intent(this, Timer.class);
        activity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(activity);
        getWindow().setWindowAnimations(0);
    }
}