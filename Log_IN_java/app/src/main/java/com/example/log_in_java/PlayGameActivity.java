package com.example.log_in_java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.log_in_java.models.Game;
import com.example.log_in_java.models.Map;
import com.example.log_in_java.models.User;
import com.example.log_in_java.services.GameManagerService;
import com.unity3d.player.UnityPlayerActivity;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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
        newintent.putExtra("playerSuspicious",suspicious);
        newintent.putExtra("playerMoney",money);
        newintent.putExtra("playerNickname",nickname);
        newintent.putExtra("playerLevel",level);
        startActivity(newintent);

    }

    public void frankfurtClick (View v){

        level=1;
        GetMapa(level);

        //Intent newintent = new Intent(this, UnityPlayerActivity.class);
        /*newintent.putExtra("playerSuspicious",suspicious);
        newintent.putExtra("playerMoney",money);
        newintent.putExtra("playerNickname",nickname);
        newintent.putExtra("playerLevel",level);*/
        //startActivity(newintent);


    }

    public void moscowClick (View v){

        level=7;
        //GetMapa(level);
        Intent newintent = new Intent(this, UnityPlayerActivity.class);
        newintent.putExtra("playerSuspicious",suspicious);
        newintent.putExtra("playerMoney",money);
        newintent.putExtra("playerNickname",nickname);
        newintent.putExtra("playerLevel",level);
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

    private String GetMapa(int level){

        gameAPI = retrofit.create(GameManagerService.class);
        Call<Map> call = gameAPI.getStringMap(level);
        call.enqueue(new Callback<Map>() {
            @Override
            public void onResponse(Call<Map> call, Response<Map> response) {
                if (response.isSuccessful()){
                    Map mapa = Map.getInstance();
                    mapa.setStringMap(response.body().getStringMap());
                    mapa.setName(response.body().getName());
                    mapa.setId(response.body().getId());

                    stringMap = mapa.getStringMap();
                    System.out.println(stringMap);
                    Log.d("Prueba", stringMap);
                }

            }

            @Override
            public void onFailure(Call<Map> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "No hi ha mapes" , Toast.LENGTH_LONG).show();

            }
        });
        return stringMap;


    }

    public void FinalGame(int money)//Tiene que poner el dinero en el usuario  y poner la partida en win ( volver a main activity con una toast que pongo money win)
    {

    }
    public void GameOver()//Cuando perdemos en unity entramos aqui tendremos que ir a main activity
    {

    }

    public void SaveGame( int level, int playerTime,boolean win,int playerSuspicious){

    }




}