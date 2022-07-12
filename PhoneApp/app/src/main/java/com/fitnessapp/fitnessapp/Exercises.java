package com.fitnessapp.fitnessapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Exercises extends AppCompatActivity {
//i.setImageBitmap(bitmap);
    Bitmap dlImage(String url){
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream((InputStream)new URL(url).getContent());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ConstraintLayout exerciseList = (ConstraintLayout) findViewById(R.id.linearl);
        //ImageView img = new ImageView(Exercises.this);
        //img.setImageBitmap(dlImage("https://filesamples.com/samples/image/bmp/sample_640%C3%97426.bmp"));
        //ConstraintLayout layout = new ConstraintLayout(this);
        //exerciseList.addView(layout);
        //ScrollView scrollView = (ScrollView) findViewById(R.id.scrv);
        //LinearLayout linearLayout = new LinearLayout(this);
        //scrollView.addView(linearLayout);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_exercises);
    }



    public void openIntervalTimer(View view){
        Intent activity = new Intent(this, IntervalTimerActivity.class);
        startActivity(activity);

    }
    public void openTimer(View view){
        Intent activity = new Intent(this, Timer.class);
        startActivity(activity);
    }
}