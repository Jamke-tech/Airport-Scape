package com.example.log_in_java;

import android.util.Log;

import com.example.log_in_java.models.Map;
import com.example.log_in_java.services.GameManagerService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyMaps {

    private static MyMaps mapsinstance;

    //Attributes
    HashMap<Integer,String> Maps;
    private static Retrofit retrofit;
    private static GameManagerService gameAPI;
    //------------Singleton--------------//

    public MyMaps(){
        Maps = new HashMap<Integer, String>();
    }

    public static synchronized MyMaps getInstance(){
        if(mapsinstance == null) {
            mapsinstance = new MyMaps();
        }
        return mapsinstance;
    }


    public void setMaps() {

        for(int i=1;i<10;i++){

            GetMaps(i);
        }
        Log.d("Debug","Maps Created");


    }
    public String getMap(int level){
        return Maps.get(level);
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


    public  void GetMaps(int level){
        startRetrofit();

        gameAPI = retrofit.create(GameManagerService.class);
        Call<Map> call = gameAPI.getStringMap(level);
        call.enqueue(new Callback<Map>() {
            @Override
            public void onResponse(Call<Map> call, Response<Map> response) {
                if (response.isSuccessful()){
                    Map mapa = Map.getInstance();
                    mapa.setStringMap(response.body().getStringMap());

                    String stringMap = mapa.getStringMap();
                    System.out.println(stringMap);
                    Log.d("Prueba", stringMap);
                    Maps.put(level,stringMap);
                }
            }

            @Override
            public void onFailure(Call<Map> call, Throwable t) {
                Log.d( "Error Mapa" ,"No hi ha mapes");
            }
        });
    }
}
