package com.fitnessapp.fitnessapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class Exercises extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_exercises);



        SharedPreferences sharedpreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        String alarm = getSharedPreferences("Settings", Context.MODE_PRIVATE).getString("alarm", null);
        String theme = getSharedPreferences("Settings", Context.MODE_PRIVATE).getString("theme", null);

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, "https://pastebin.com/raw/Ehy7BwQT",
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.i("rsp", response.toString());
                            showContents(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();            }
        }
        );
        queue.add(request);
    }

    public void showContents(JSONObject response) throws JSONException {
        LinearLayout ll = (LinearLayout) findViewById(R.id.ll);

        JSONArray exercises = response.getJSONArray("exercises");
        Log.i("rsp", response.toString());
        for (int i = 0; i < exercises.length(); i++){
            JSONObject exercise = exercises.getJSONObject(i);
            String title = exercise.getString("title");
            String description = exercise.getString("text");
            String img = exercise.getString("src");
            ImageView imgv = new ImageView(this);
            TextView titlev = new TextView(this);
            TextView descriptionv = new TextView(this);
            Glide.with(this).load(img).into(imgv);
            ll.addView(imgv);
            titlev.setText(title);
            ll.addView(titlev);
            descriptionv.setText(description);
            ll.addView(descriptionv);
        }
    }

    public void openIntervalTimer(View view) {
        Intent activity = new Intent(this, IntervalTimerActivity.class);
        startActivity(activity);

    }

    public void openTimer(View view) {
        Intent activity = new Intent(this, Timer.class);
        startActivity(activity);
    }
}