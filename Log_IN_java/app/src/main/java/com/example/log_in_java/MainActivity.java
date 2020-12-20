package com.example.log_in_java;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

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
        //poner la url de la tienda web
        Uri uri = Uri.parse("shop.html");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void myObjectsButtonClicked(View v){
        startActivity(new Intent(MainActivity.this, MyObjectsActivity.class));
    }

}



