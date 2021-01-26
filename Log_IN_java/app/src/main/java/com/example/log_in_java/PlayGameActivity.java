package com.example.log_in_java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.log_in_java.models.Game;
import com.example.log_in_java.models.Objects;
import com.example.log_in_java.models.User;
import com.example.log_in_java.services.GameManagerService;
import com.unity3d.player.UnityPlayerActivity;

import java.util.ArrayList;
import java.util.List;

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
    private EditText gameName;
    private String namePartida;
    private static Retrofit retrofit;
    private GameManagerService gameAPI;
    String stringMap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        loadPreferences();
        nickname =preferences.getString("userNickname", null);//recupero el nickname
        gameName = this.findViewById(R.id.gameNameEditText);
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            suspicious = extras.getString("suspicious");
            money = extras.getString("money");
            System.out.println(money);
            System.out.println(suspicious);
        }
        startRetrofit();
        //GetMapa(1);
    }


    public void newYorkClick (View v){

        level = 4;
        //GetMapa(level);
        if (gameName.getText().toString() != null)
        {
            Intent newintent = new Intent(this, UnityPlayerActivity.class);
            saveGameValues(level,suspicious,money,gameName.getText().toString());
            MyMaps mapas = MyMaps.getInstance();
            mapas.setMaps();
            startActivity(newintent);
        }
        else{
            Toast.makeText(getApplicationContext(), "Error: Fill the Game Name" , Toast.LENGTH_LONG).show();
        }

        /*newintent.putExtra("playerSuspicious",suspicious);
        newintent.putExtra("playerMoney",money);
        newintent.putExtra("playerNickname",nickname);
        String levelstr = String.valueOf(level);
        newintent.putExtra("playerLevel",levelstr);
        Toast.makeText(this,levelstr,Toast.LENGTH_LONG).show();*/
    }

    public void frankfurtClick (View v){

        level=1;
        //GetMapa(level);

        if (gameName.getText().toString() != null)
        {
            Intent newintent = new Intent(this, UnityPlayerActivity.class);
            saveGameValues(level,suspicious,money,gameName.getText().toString());
            MyMaps mapas = MyMaps.getInstance();
            mapas.setMaps();
            startActivity(newintent);
        }
        else{
            Toast.makeText(getApplicationContext(), "Error: Fill the Game Name" , Toast.LENGTH_LONG).show();
        }
        /*newintent.putExtra("playerSuspicious",suspicious);
        newintent.putExtra("playerMoney",money);
        newintent.putExtra("playerNickname",nickname);
        String levelstr = String.valueOf(level);
        newintent.putExtra("playerLevel",levelstr);
        Toast.makeText(this,levelstr,Toast.LENGTH_LONG).show();*/


    }

    public void moscowClick (View v){

        level=7;
        if (gameName.getText().toString() != null)
        {
            Intent newintent = new Intent(this, UnityPlayerActivity.class);
            saveGameValues(level,suspicious,money,gameName.getText().toString());
            MyMaps mapas = MyMaps.getInstance();
            mapas.setMaps();
            startActivity(newintent);
        }
        else{
            Toast.makeText(getApplicationContext(), "Error: Fill the Game Name" , Toast.LENGTH_LONG).show();
        }

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

    public void saveGame(final Game game) {

        gameAPI = retrofit.create(GameManagerService.class);
        Call<Game> call = gameAPI.saveGame(game);
        call.enqueue(new Callback<Game>() {
            @Override
            public void onResponse(Call<Game> call, Response<Game> response) {
                if (response.code() == 200) {
                    Game savedGame = Game.getInstance();
                    savedGame.setId(response.body().getId());
                    savedGame.setName(response.body().getName());
                    savedGame.setIdMap(response.body().getIdMap());
                    savedGame.setWin(response.body().isWin());
                    savedGame.setSuspicious(response.body().getSuspicious());
                    savedGame.setUserName(response.body().getUserName());
                    savedGame.setMoney(response.body().getMoney());

                    Toast.makeText(getApplicationContext(), "Game saved", Toast.LENGTH_LONG).show();
                } else {
                    if (response.code() == 400)
                        Toast.makeText(getApplicationContext(), "Error data", Toast.LENGTH_LONG).show();
                    else if (response.code() == 503)
                        Toast.makeText(getApplicationContext(), "BBDD down", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Game> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }


}