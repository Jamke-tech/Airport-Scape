package com.example.log_in_java;

import android.util.Log;
import android.widget.Toast;

import com.example.log_in_java.models.Map;
import com.example.log_in_java.services.GameManagerService;
import com.unity3d.player.UnityPlayerActivity;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyUnity extends UnityPlayerActivity {

    private static Retrofit retrofit;
    private GameManagerService gameAPI;
    String stringMap;

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

    public String GetMap(int level){
        startRetrofit();

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
