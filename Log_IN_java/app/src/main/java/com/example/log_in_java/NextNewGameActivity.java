package com.example.log_in_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.log_in_java.Adapter.MyAdapter2;
import com.example.log_in_java.models.Objects;
import com.example.log_in_java.services.ObjectManagerService;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NextNewGameActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    private static Retrofit retrofit;
    private ObjectManagerService objectsAPI;

    private RecyclerView bagsRecycler;
    private TextView suspiciousTextView;
    private MyAdapter2 mobjectsAdapter;
    private ProgressBar progressBar;
    private TextView dineroTextView;
    private TextView spacesTextView;

    private Context context;

    private String nickname;
    private List<Objects> objectsList= new ArrayList<Objects>();
    private List<Objects> bagsList= new ArrayList<Objects>();
    private List<Objects> selectedBagsList= new ArrayList<Objects>();

    private int spaceAvailable;
    private int extraMoney = 0;
    private String suspicious;
    private Objects chosenBag;

    private Boolean drugs = false;
    private Boolean gun = false;
    private Boolean money = false;
    private Boolean snake = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_new_game);

        bagsRecycler = this.findViewById(R.id.recyclerView);
        suspiciousTextView = this.findViewById(R.id.suspiciousTextView);
        progressBar = this.findViewById(R.id.progressBarSuspicious2);
        dineroTextView = this.findViewById(R.id.dineroTextview);
        spacesTextView = this.findViewById(R.id.spacesTextView);

        loadPreferences();
        nickname = preferences.getString("userNickname",null);

        startRetrofit();

        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            suspicious = extras.getString("suspicious");
            suspiciousTextView.setText(String.valueOf(suspicious));
            progressBar.setProgress(Integer.parseInt(suspicious));
        }

        getObjectFromDataBase(nickname);
        context = getApplicationContext();

    }

    private void getObjectFromDataBase(String nickname) {
        //pedimos los objetos de la base de Datos y los ponemos en los dos vectores
        objectsAPI = retrofit.create(ObjectManagerService.class);
        Call<List<Objects>> call = objectsAPI.listBuyedObjects(nickname);
        call.enqueue(new Callback<List<Objects>>() {

            @Override
            public void onResponse(Call<List<Objects>> call, Response<List<Objects>> response) {
                //Si nos responde con un 200 OK sacamos los objetos y las mochilas por separado
                if(response.code()==200){
                    //tenemos objetos en el responsebody, recoremos el body para ir sacando los objetos y poniendolos en las listas

                    for (Objects object : response.body()){

                        if(object.isBag()){
                            bagsList.add(object);
                        }
                        else {
                            objectsList.add(object);
                        }
                    }
                    Toast.makeText(getApplicationContext(), "Imagen: " + objectsList.get(0).getUrlImage() , Toast.LENGTH_LONG).show();
                    if(objectsList.size()==0){
                        Objects object = new Objects(0,"YOU DO NOT HAVE ANY OBJECT",0,"Go to the shop if you want to spend the money",0,false, "imagen/sad.png", false);
                        objectsList.add(object);
                    }
                    else if(bagsList.size()==0){
                        Objects object = new Objects(0,"YOU DO NOT HAVE ANY BAG",0,"Go to the shop if you want to spend the money",0,true, "imagen/sad.png", false);
                        bagsList.add(object);
                    }

                    bagsRecycler.setHasFixedSize(true);
                    bagsRecycler.setLayoutManager(new GridLayoutManager(getApplicationContext(),3 ));
                    mobjectsAdapter = new MyAdapter2(getApplicationContext(),bagsList);

                    bagsRecycler.setAdapter(mobjectsAdapter);


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

    public void chooseButtonClicked(View v){
        selectedBagsList = mobjectsAdapter.getSelectedObjectsList();
        spaceAvailable = selectedBagsList.get(0).getAttribute();
        spacesTextView.setText(String.valueOf(spaceAvailable));
        suspiciousTextView.setText(String.valueOf(suspicious));
        progressBar.setProgress(Integer.parseInt(suspicious));
        extraMoney = 0;
        dineroTextView.setText(String.valueOf(extraMoney));
        drugs = false;
        gun = false;
        money = false;
        snake = false;
    }

    public void drugsImageViewClicked(View v){
        if(!drugs&&spaceAvailable>=2){
            drugs = true;
            spaceAvailable = spaceAvailable - 2;
            extraMoney = extraMoney + 400;
            dineroTextView.setText(String.valueOf(extraMoney));
            spacesTextView.setText(String.valueOf(spaceAvailable));
            if(Integer.parseInt(suspiciousTextView.getText().toString())+10>=100){
                suspiciousTextView.setText(String.valueOf(100));
                progressBar.setProgress(100);
            }
            else{
                suspiciousTextView.setText(String.valueOf(Integer.parseInt(suspiciousTextView.getText().toString())+10));
                progressBar.setProgress(Integer.parseInt(suspiciousTextView.getText().toString()));
            }

        }
        else{
            if(spaceAvailable<2){
                Toast.makeText(context,"There's not enough space in your bag",Toast.LENGTH_LONG).show();
            }
            if(drugs){
                drugs = false;
                spaceAvailable = spaceAvailable + 2;
                extraMoney = extraMoney - 400;
                dineroTextView.setText(String.valueOf(extraMoney));
                spacesTextView.setText(String.valueOf(spaceAvailable));
                suspiciousTextView.setText(String.valueOf(Integer.parseInt(suspiciousTextView.getText().toString())-10));
                progressBar.setProgress(Integer.parseInt(suspiciousTextView.getText().toString()));
            }
        }
    }

    public void gunImageViewClicked(View v){
        if(!gun&&spaceAvailable>=1){
            gun = true;
            spaceAvailable = spaceAvailable - 1;
            extraMoney = extraMoney + 150;
            dineroTextView.setText(String.valueOf(extraMoney));
            spacesTextView.setText(String.valueOf(spaceAvailable));
            if(Integer.parseInt(suspiciousTextView.getText().toString())+10>=100){
                suspiciousTextView.setText(String.valueOf(100));
                progressBar.setProgress(100);
            }
            else{
                suspiciousTextView.setText(String.valueOf(Integer.parseInt(suspiciousTextView.getText().toString())+5));
                progressBar.setProgress(Integer.parseInt(suspiciousTextView.getText().toString()));
            }

        }
        else{
            if(spaceAvailable<1){
                Toast.makeText(context,"There's not enough space in your bag",Toast.LENGTH_LONG).show();
            }
            if(gun){
                gun = false;
                spaceAvailable = spaceAvailable + 1;
                extraMoney = extraMoney - 150;
                dineroTextView.setText(String.valueOf(extraMoney));
                spacesTextView.setText(String.valueOf(spaceAvailable));
                suspiciousTextView.setText(String.valueOf(Integer.parseInt(suspiciousTextView.getText().toString())-5));
                progressBar.setProgress(Integer.parseInt(suspiciousTextView.getText().toString()));
            }
        }
    }

    public void moneyImageViewClicked(View v){
        if(!money&&spaceAvailable>=3){
            money = true;
            spaceAvailable = spaceAvailable - 3;
            extraMoney = extraMoney + 700;
            dineroTextView.setText(String.valueOf(extraMoney));
            spacesTextView.setText(String.valueOf(spaceAvailable));
            if(Integer.parseInt(suspiciousTextView.getText().toString())+10>=100){
                suspiciousTextView.setText(String.valueOf(100));
                progressBar.setProgress(100);
            }
            else{
                suspiciousTextView.setText(String.valueOf(Integer.parseInt(suspiciousTextView.getText().toString())+15));
                progressBar.setProgress(Integer.parseInt(suspiciousTextView.getText().toString()));
            }

        }
        else{
            if(spaceAvailable<3){
                Toast.makeText(context,"There's not enough space in your bag",Toast.LENGTH_LONG).show();
            }
            if(money){
                money = false;
                spaceAvailable = spaceAvailable + 3;
                extraMoney = extraMoney - 700;
                dineroTextView.setText(String.valueOf(extraMoney));
                spacesTextView.setText(String.valueOf(spaceAvailable));
                suspiciousTextView.setText(String.valueOf(Integer.parseInt(suspiciousTextView.getText().toString())-15));
                progressBar.setProgress(Integer.parseInt(suspiciousTextView.getText().toString()));
            }
        }
    }

    public void snakeImageViewClicked(View v){
        if(!snake&&spaceAvailable>=3){
            snake = true;
            spaceAvailable = spaceAvailable - 3;
            extraMoney = extraMoney + 850;
            dineroTextView.setText(String.valueOf(extraMoney));
            spacesTextView.setText(String.valueOf(spaceAvailable));
            if(Integer.parseInt(suspiciousTextView.getText().toString())+10>=100){
                suspiciousTextView.setText(String.valueOf(100));
                progressBar.setProgress(100);
            }
            else{
                suspiciousTextView.setText(String.valueOf(Integer.parseInt(suspiciousTextView.getText().toString())+15));
                progressBar.setProgress(Integer.parseInt(suspiciousTextView.getText().toString()));
            }

        }
        else{
            if(spaceAvailable<3){
                Toast.makeText(context,"There's not enough space in your bag",Toast.LENGTH_LONG).show();
            }
            if(snake){
                snake = false;
                spaceAvailable = spaceAvailable + 3;
                extraMoney = extraMoney - 850;
                dineroTextView.setText(String.valueOf(extraMoney));
                spacesTextView.setText(String.valueOf(spaceAvailable));
                suspiciousTextView.setText(String.valueOf(Integer.parseInt(suspiciousTextView.getText().toString())-15));
                progressBar.setProgress(Integer.parseInt(suspiciousTextView.getText().toString()));
            }
        }
    }

    public void playButtonClicked(View v){

        Toast.makeText(context,"Starting game as: " + nickname,Toast.LENGTH_LONG).show();





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