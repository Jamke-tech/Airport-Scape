package com.example.log_in_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.log_in_java.Adapter.MyAdapter;
import com.example.log_in_java.models.Objects;
import com.example.log_in_java.models.User;
import com.example.log_in_java.services.ObjectManagerService;
import com.example.log_in_java.services.UserManagerService;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyObjectsActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    private static Retrofit retrofit;
    private String nickname;
    private ObjectManagerService objectsAPI;
    private List<Objects> objectsList;
    private List<Objects> bagsList;

    private RecyclerView objectsRecycler;
    private RecyclerView bagsRecycler;
    private MyAdapter mobjectsAdapter;
    private MyAdapter mbagsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_objects);
        loadPreferences();
        nickname =preferences.getString("userNickname", null);//recupero el nickname
        objectsRecycler= this.findViewById(R.id.objectRecycler);
        bagsRecycler= this.findViewById(R.id.bagsRecycler);
        startRetrofit();
        getObjectFromDataBase(nickname);

        //Inizializo vectores
        objectsList= new ArrayList<Objects>();
        bagsList= new ArrayList<Objects>();

        //Recyclerview
        objectsRecycler.setHasFixedSize(true);
        bagsRecycler.setHasFixedSize(true);
        objectsRecycler.setLayoutManager(new LinearLayoutManager(this));
        bagsRecycler.setLayoutManager(new LinearLayoutManager(this));
        // use a linear layout manager


        //Hem d'inizialitzar els vectors


        mobjectsAdapter = new MyAdapter(this,objectsList);
        mbagsAdapter = new MyAdapter(this,bagsList);
        objectsRecycler.setAdapter(mobjectsAdapter);
        bagsRecycler.setAdapter(mbagsAdapter);




    }

    private void getObjectFromDataBase(String nickname) {
        //pedimos los objetos de la base de Datos i los ponemos en los dos vectores
        objectsAPI = retrofit.create(ObjectManagerService.class);
        Call<List<Objects>> call = objectsAPI.listBuyedObjects(nickname);
        call.enqueue(new Callback<List<Objects>>() {
            @Override
            public void onResponse(Call<List<Objects>> call, Response<List<Objects>> response) {
                //Si nos responde con un 200 OK sacamos los objetos i las mochilas por separado
                if(response.code()==200){
                    //tenemos objetos en el responsebody, recoremos el body para ir sacando los objetos i poniendolos en las listas

                    for (Objects object : response.body()){

                        if(object.isBag()){
                            bagsList.add(object);
                        }
                        else {
                            objectsList.add(object);
                        }
                    }


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
            public void onFailure(Call<List<Objects>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();


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