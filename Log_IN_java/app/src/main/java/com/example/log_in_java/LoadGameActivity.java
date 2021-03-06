package com.example.log_in_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.log_in_java.Adapter.MyAdapter;
import com.example.log_in_java.Adapter.MyAdapter3;
import com.example.log_in_java.models.Game;

import com.example.log_in_java.models.Objects;
import com.example.log_in_java.models.User;
import com.example.log_in_java.services.GameManagerService;
import com.example.log_in_java.services.ObjectManagerService;
import com.example.log_in_java.services.UserManagerService;
import com.unity3d.player.UnityPlayerActivity;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Callback;
import retrofit2.Response;


public class LoadGameActivity extends AppCompatActivity {

    private String nickname;
    private SharedPreferences preferences;
    private static Retrofit retrofit;
    private GameManagerService gameAPI;
    private List<Game> gameList= new ArrayList<Game>();
    private RecyclerView gamesRecycler;
    private MyAdapter3 gamesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_game);

        loadPreferences();
        nickname =preferences.getString("userNickname", null);//recupero el nickname
        gamesRecycler= this.findViewById(R.id.recyclerViewListGame);
        startRetrofit();
        //getSavedGame(nickname);
        getListSavedGames(nickname);
    }


    private void getListSavedGames(String nickname){
        gameAPI = retrofit.create(GameManagerService.class);
        Call<List<Game>> call = gameAPI.getListSavedGames(nickname);
        call.enqueue(new Callback<List<Game>>() {
            @Override
            public void onResponse(Call<List<Game>> call, Response<List<Game>> response) {
                if (response.isSuccessful()){
                    gameList = response.body();


                    gamesRecycler.setHasFixedSize(true);
                    gamesRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    gamesAdapter = new MyAdapter3(getApplicationContext(),gameList);
                    gamesRecycler.setAdapter(gamesAdapter);
                }
                else
                {
                    if(response.code() == 401)
                        Toast.makeText(getApplicationContext(), "Error data" , Toast.LENGTH_LONG).show();
                    else if(response.code() == 503)
                        Toast.makeText(getApplicationContext(),"BBDD down", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<List<Game>> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }

    public void ResumeGameClicked(View v){

        Intent newintent = new Intent(this, UnityPlayerActivity.class);
        startActivity(newintent);

    }


    private void getSavedGame(String nickname){

        gameAPI = retrofit.create(GameManagerService.class);
        Call<Game> call = gameAPI.getSavedGame(nickname);
        call.enqueue(new Callback<Game>() {
            @Override
            public void onResponse(Call<Game> call, Response<Game> response) {
                if (response.code()==200){
                    //We "fill" the logged User instance
                    Game savedGame = Game.getInstance();
                    savedGame.setId(response.body().getId());
                    savedGame.setName(response.body().getName());
                    savedGame.setIdMap(response.body().getIdMap());
                    savedGame.setWin(response.body().isWin());
                    savedGame.setSuspicious(response.body().getSuspicious());

                }
            }

            @Override
            public void onFailure(Call<Game> call, Throwable t) {

            }
        });


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
}