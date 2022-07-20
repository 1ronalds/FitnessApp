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
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
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
import java.util.ArrayList;


public class Exercises extends AppCompatActivity {
    String alarm, theme;
    TextView textView2 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_exercises);

        SharedPreferences sharedpreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        alarm = getSharedPreferences("Settings", Context.MODE_PRIVATE).getString("alarm", null);
        theme = getSharedPreferences("Settings", Context.MODE_PRIVATE).getString("theme", null);
        if(theme.equals("Dark")) {
            RelativeLayout rl = (RelativeLayout) findViewById(R.id.rl);
            rl.setBackgroundColor(Color.parseColor("#000000"));
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

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, "https://pastebin.com/raw/Ehy7BwQT",
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
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
        getWindow().setWindowAnimations(0);
    }

    public void showContents(JSONObject response) throws JSONException {
        LinearLayout ll = (LinearLayout) findViewById(R.id.ll);

        JSONArray exercises = response.getJSONArray("exercises");
        for (int i = 0; i < exercises.length(); i++){
            JSONObject exercise = exercises.getJSONObject(i);
            String title = exercise.getString("title");
            String description = exercise.getString("text");
            String img = exercise.getString("src");
            ImageView imgv = new ImageView(this);
            TextView titlev = new TextView(this);
            titlev.setTypeface(null, Typeface.BOLD);
            titlev.setTextSize(0, 50);
            TextView descriptionv = new TextView(this);
            descriptionv.setTypeface(null, Typeface.NORMAL);
            descriptionv.setTextSize(0, 50);
            if(theme.equals("Light")){
                titlev.setTextColor(Color.BLACK);
                descriptionv.setTextColor(Color.BLACK);
            } else {
                titlev.setTextColor(Color.WHITE);
                descriptionv.setTextColor(Color.WHITE);
            }
            Glide.with(this).load(img).into(imgv);
            ll.addView(imgv);
            titlev.setText(title);
            ll.addView(titlev);
            descriptionv.setText(description + "\n\n\n\n\n");
            ll.addView(descriptionv);
        }
    }

    public void openIntervalTimer(View view) {
        Intent activity = new Intent(this, IntervalTimerActivity.class);
        activity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(activity);

    }

    public void openTimer(View view) {
        Intent activity = new Intent(this, Timer.class);
        activity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(activity);
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
        Intent activity = new Intent(this, Exercises.class);
        activity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(activity);


    }
}