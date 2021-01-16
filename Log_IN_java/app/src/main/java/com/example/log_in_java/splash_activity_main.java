package com.example.log_in_java;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.graphics.PorterDuff;


public class splash_activity_main extends AppCompatActivity {

    private final int SPLASH_DURATION = 3500;
    private SharedPreferences preferences;
    private ProgressBar loginbar;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_main);
        loadPreferences();
        loginbar=(ProgressBar) findViewById(R.id.loadingBar);
        loginbar.getIndeterminateDrawable().setColorFilter(Color.RED,PorterDuff.Mode.SRC_IN);
        loginbar.onVisibilityAggregated(true);
        
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkCredentials();

            };
        }, SPLASH_DURATION);
    }

    private void loadPreferences(){
        preferences = getSharedPreferences("Login credentials", Context.MODE_PRIVATE);
    }

    private void checkCredentials(){

        String nickname =preferences.getString("userNickname", null);
        String password = preferences.getString("userPassword", null);
        if (nickname != null && password != null){
            goMainActivity();
        }
        else{
            goLoginActivity();
        }
    }

    private void goMainActivity(){
        Intent intent = new Intent(splash_activity_main.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    private void goLoginActivity(){
        Intent intent = new Intent(splash_activity_main.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }


}