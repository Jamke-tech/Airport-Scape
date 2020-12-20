package com.example.log_in_java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;


public class splash_activity_main extends AppCompatActivity {

    private final int SPLASH_DURATION = 3500;
    private ProgressBar loginbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splash_activity_main.this, LoginActivity.class);
                startActivity(intent);
                finish();

            };
        }, SPLASH_DURATION);
    }

    private void loadPreferences(){
        SharedPreferences preferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        String nickname =preferences.getString("nickname", "Does not exist this information");
        String password = preferences.getString("password", "Does not exist this information");
    }



}