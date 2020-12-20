package com.example.log_in_java;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.log_in_java.models.User;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadPreferences();
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

    private void loadPreferences(){
        preferences = getSharedPreferences("Login credentials", Context.MODE_PRIVATE);
    }

    private void closeSession(){
        preferences.edit().clear().apply();
        goLoginActivity();

    }

    private void goLoginActivity(){
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);

    }

    //Menu para salir o cerrar sesion
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.general_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.action_exit){
            finish();
            System.exit(0);
        } else if (id==R.id.action_logout){
            closeSession();
        }
        return super.onOptionsItemSelected(item);
    }
}



