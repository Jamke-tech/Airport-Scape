package com.example.log_in_java;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import com.example.log_in_java.models.User;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void profileButtonClicked(View v){
        startActivity(new Intent(MainActivity.this, ProfileActivity.class));
    }

    public void playButtonClicked(View v){
        startActivity(new Intent(MainActivity.this, twoOptionsToPlayActivity.class));
    }

    public void shopButtonClicked(View v){

    }

    public void myObjectsButtonClicked(View v){
        startActivity(new Intent(MainActivity.this, MyObjectsActivity.class));
    }

}



