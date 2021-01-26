package com.example.log_in_java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.log_in_java.models.User;
import com.example.log_in_java.services.GameManagerService;
import com.unity3d.player.UnityPlayerActivity;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlayGameActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    private String nickname;
    private int level;
    private String suspicious;
    private String money;
    private static Retrofit retrofit;
    private GameManagerService gameAPI;
    String stringMap;



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
        startRetrofit();


    }


    public void newYorkClick (View v){

        level = 4;
        //GetMapa(level);
        Intent newintent = new Intent(this, UnityPlayerActivity.class);
        saveGameValues(level,suspicious,money,namePartida);
        MyMaps mapas = MyMaps.getInstance();
        mapas.setMaps();

        /*newintent.putExtra("playerSuspicious",suspicious);
        newintent.putExtra("playerMoney",money);
        newintent.putExtra("playerNickname",nickname);
        String levelstr = String.valueOf(level);
        newintent.putExtra("playerLevel",levelstr);
        Toast.makeText(this,levelstr,Toast.LENGTH_LONG).show();*/


        startActivity(newintent);

    }

    public void frankfurtClick (View v){

        level=1;
        //GetMapa(level);

        Intent newintent = new Intent(this, UnityPlayerActivity.class);
        saveGameValues(level,suspicious,money,namePartida);
        MyMaps mapas = MyMaps.getInstance();
        mapas.setMaps();
        /*newintent.putExtra("playerSuspicious",suspicious);
        newintent.putExtra("playerMoney",money);
        newintent.putExtra("playerNickname",nickname);
        String levelstr = String.valueOf(level);
        newintent.putExtra("playerLevel",levelstr);
        Toast.makeText(this,levelstr,Toast.LENGTH_LONG).show();*/


        startActivity(newintent);


    }

    public void moscowClick (View v){

        level=7;
        Intent newintent = new Intent(this, UnityPlayerActivity.class);
        saveGameValues(level,suspicious,money,namePartida);
        MyMaps mapas = MyMaps.getInstance();
        mapas.setMaps();

        /*newintent.putExtra("playerSuspicious",suspicious);
        newintent.putExtra("playerMoney",money);
        newintent.putExtra("playerNickname",nickname);
        String levelstr = String.valueOf(level);
        newintent.putExtra("playerLevel",levelstr);
        Toast.makeText(this,levelstr,Toast.LENGTH_LONG).show();*/


        startActivity(newintent);


    }
    private static void startRetrofit(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //Attaching Interceptor to a client
        OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://eetacdsa0.upc.es:8080/gameDSA/") //Local host on windows 10.0.2.2 and ip our machine 147.83.7.203
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }
    private void loadPreferences(){
        preferences = getSharedPreferences("Login credentials", Context.MODE_PRIVATE);
    }
    private void saveGameValues(int level, String suspicious, String money,String namePartida){

        MyGame startedGame = MyGame.getInstance();
        startedGame.setLevel(level);
        startedGame.setMoney(Integer.parseInt(money));
        startedGame.setNameUser(nickname);
        startedGame.setSuspicious(Integer.parseInt(suspicious));
        startedGame.setNamePartida(namePartida);
    }






}