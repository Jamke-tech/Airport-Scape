package com.example.log_in_java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class twoOptionsToPlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_options_to_play);
    }

    public void newGameClicked(View v){
        startActivity(new Intent(twoOptionsToPlayActivity.this, NewGameActivity.class));
    }

    public void loadGameClicked(View v){
        startActivity(new Intent(twoOptionsToPlayActivity.this, LoadGameActivity.class));
    }
}