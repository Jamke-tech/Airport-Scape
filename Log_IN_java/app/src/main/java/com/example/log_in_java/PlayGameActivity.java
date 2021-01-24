package com.example.log_in_java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.log_in_java.models.Game;
import com.example.log_in_java.services.GameManagerService;
import com.unity3d.player.UnityPlayerActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PlayGameActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    private String nickname;
    private int level;
    private String suspicious;
    private String money;
    private static Retrofit retrofit;
    private GameManagerService gameAPI;
    private String mapa;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        loadPreferences();
        nickname =preferences.getString("userNickname", null);//recupero el nickname

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            suspicious = extras.getString("suspicious");
            money = extras.getString("money");
        }


    }


    public void newYorkClick (View v){

        level = 1;
        GetMapa(level);
        Intent newintent = new Intent(this, UnityPlayerActivity.class);
        newintent.putExtra("playerSuspicious",suspicious);
        newintent.putExtra("playerMoney",money);
        newintent.putExtra("playerNickname",nickname);
        newintent.putExtra("playerLevel",level);
        startActivity(newintent);

    }

    public void frankfurtClick (View v){

        level=4;
        GetMapa(level);
        Intent newintent = new Intent(this, UnityPlayerActivity.class);
        newintent.putExtra("playerSuspicious",suspicious);
        newintent.putExtra("playerMoney",money);
        newintent.putExtra("playerNickname",nickname);
        newintent.putExtra("playerLevel",level);
        startActivity(newintent);


    }

    public void moscowClick (View v){

        level=7;
        GetMapa(level);
        Intent newintent = new Intent(this, UnityPlayerActivity.class);
        newintent.putExtra("playerSuspicious",suspicious);
        newintent.putExtra("playerMoney",money);
        newintent.putExtra("playerNickname",nickname);
        newintent.putExtra("playerLevel",level);
        startActivity(newintent);


    }

    private void loadPreferences(){
        preferences = getSharedPreferences("Login credentials", Context.MODE_PRIVATE);
    }

    private String GetMapa(int level){
        gameAPI = retrofit.create(GameManagerService.class);
        Call<String> call = gameAPI.getStringMap(level);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code()==200){
                    mapa = response.body();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "No hi ha mapes" , Toast.LENGTH_LONG).show();

            }
        });
        return mapa;


    }




}