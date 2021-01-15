package com.example.log_in_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.DirectAction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;
import android.content.Intent;

import com.example.log_in_java.Adapter.*;
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

public class NewGameActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    private static Retrofit retrofit;
    private ObjectManagerService objectsAPI;
    private List<Objects> objectsList= new ArrayList<Objects>();
    private List<Objects> selectedObjectsList= new ArrayList<Objects>();
    private List<Objects> bagsList= new ArrayList<Objects>();
    private RecyclerView objectsRecycler;
    private MyAdapter2 mobjectsAdapter;
    private ProgressBar progressBar;
    private String nickname;
    private ImageView imageVieww;
    private Context context;
    private TextView suspiciousNum;
    private Button selectObjectButton;
    int atributo;
    int sospechoso = 100;
    Boolean clickeado = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
        selectObjectButton =this.findViewById(R.id.selectObjectsButton);
        imageVieww = (ImageView) this.findViewById(R.id.imageChar);
        objectsRecycler= this.findViewById(R.id.myObjects);
        suspiciousNum = (TextView) this.findViewById(R.id.suspiciousNum);
        progressBar = (ProgressBar) this.findViewById(R.id.progressBarSuspicious);
        context = this.getApplicationContext();
        loadPreferences();
        nickname =preferences.getString("userNickname", null);//recupero el nickname
        startRetrofit();

        getObjectFromDataBase(nickname);



        //ESTO NO VA BIEN, LO PROBARÉ CON OTRO METODO
        /*objectsRecycler.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            datos = getIntent().getExtras();
                            String datosObtenidos = datos.getString("Suspicious deacreament");
                            int datosInt = datos.getInt("Suspicious deacreament");

                            suspiciousNum.setText(datosObtenidos);
                            progressBar.setProgress(100-datosInt);
                        }
                    });*/


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
                        Objects object = new Objects(0,"YOU DO NOT HAVE ANY OBJECT",0,"Go to the shop if you want to spend the money",0,false, "imagen/sad.png");
                        objectsList.add(object);
                    }
                    else if(bagsList.size()==0){
                        Objects object = new Objects(0,"YOU DO NOT HAVE ANY BAG",0,"Go to the shop if you want to spend the money",0,true, "imagen/sad.png");
                        bagsList.add(object);
                    }



                    objectsRecycler.setHasFixedSize(true);
                    objectsRecycler.setLayoutManager(new GridLayoutManager(getApplicationContext(),3 ));
                    mobjectsAdapter = new MyAdapter2(getApplicationContext(),objectsList);
                    objectsRecycler.setAdapter(mobjectsAdapter);


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

    public void nextButtonClicked (View v){
        Intent intent = new Intent(getApplicationContext(), NextNewGameActivity.class);
        intent.putExtra("suspicious",suspiciousNum.getText().toString());
        startActivity(intent);
    }




    public void selectObjectsButtonClicked(View v){


        selectedObjectsList = mobjectsAdapter.getSelectedObjectsList();
        for (int i=0; i < selectedObjectsList.size(); i++){

            if (selectedObjectsList.size() == 0){
                atributo = 0;
            }
            atributo = atributo + selectedObjectsList.get(i).getAttribute();

        }
        int suspicious = sospechoso - atributo;
        progressBar.setProgress(suspicious);
        suspiciousNum.setText(String.valueOf(suspicious));
        sospechoso = 100;
        atributo = 0;

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