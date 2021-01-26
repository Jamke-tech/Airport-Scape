package com.example.log_in_java;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.log_in_java.models.Game;
import com.example.log_in_java.models.Map;
import com.example.log_in_java.models.User;
import com.example.log_in_java.services.GameManagerService;
import com.example.log_in_java.services.UserManagerService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyUnity extends AppCompatActivity {

    private static Retrofit retrofit;
    private GameManagerService gameAPI;


    public static String GetMap(int level){

        MyMaps mapas= MyMaps.getInstance();
        String mapaLevel = mapas.getMap(level);
        Log.d("Mapa retuned",mapaLevel);

        return mapaLevel;
    }
    public static int GetLevel(){
        int level = MyGame.getInstance().getLevel();
        return level;

    }
    public static int GetSuspicious(){
        int susp = MyGame.getInstance().getSuspicious();
        return susp;

    }
    public static String GetName(){
        String name = MyGame.getInstance().getNameUser();
        return name;

    }
    public static int GetMoney(){
        int money = MyGame.getInstance().getMoney();
        return money;

    }

    public void FinalGame(int level, boolean win, int playerSuspicious, int playerMoney, String nickname, String gameName)//Tiene que poner el dinero en el usuario  y poner la partida en win ( volver a main activity con una toast que pongo money win)
    {
        Log.d("Dinero Ganado",String.valueOf(playerMoney));
        Game game = new Game(0, gameName, level, win, nickname, playerSuspicious);
        saveeGame(game);
    }
    public static void GameOver()//Cuando perdemos en unity entramos aqui tendremos que ir a main activity
    {
        Log.d("Fin Partida","Game OVER");
    }

    public static void SaveGame( int level, boolean win, int playerSuspicious, int playerMoney)
    {
        MyGame game = MyGame.getInstance();
        String namePartida = game.getNamePartida();


    }

    public void saveeGame(final Game game){

        gameAPI = retrofit.create(GameManagerService.class);
        Call<Game> call = gameAPI.saveGame(game);
        call.enqueue(new Callback<Game>() {
            @Override
            public void onResponse(Call<Game> call, Response<Game> response) {
                if (response.code() == 200){
                    Game savedGame = Game.getInstance();
                    savedGame.setId(response.body().getId());
                    savedGame.setName(response.body().getName());
                    savedGame.setIdMap(response.body().getIdMap());
                    savedGame.setWin(response.body().isWin());
                    savedGame.setSuspicious(response.body().getSuspicious());
                    savedGame.setUserName(response.body().getUserName());
                    savedGame.setMoney(response.body().getMoney());

                    Toast.makeText(getApplicationContext(), "Game saved", Toast.LENGTH_LONG).show();
                }
                else{
                    if(response.code() == 400)
                        Toast.makeText(getApplicationContext(), "Error data" , Toast.LENGTH_LONG).show();
                    else if(response.code() == 503)
                        Toast.makeText(getApplicationContext(),"BBDD down", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Game> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }




}
