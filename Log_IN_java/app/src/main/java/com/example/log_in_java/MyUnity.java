package com.example.log_in_java;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.log_in_java.models.Map;
import com.example.log_in_java.services.GameManagerService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyUnity extends AppCompatActivity {

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

    public static void FinalGame(int level, boolean win, int playerSuspicious, int playerMoney)//Tiene que poner el dinero en el usuario  y poner la partida en win ( volver a main activity con una toast que pongo money win)
    {
        Log.d("Dinero Ganado",String.valueOf(playerMoney));

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




}
