package com.example.log_in_java;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.log_in_java.models.User;
import com.example.log_in_java.services.UserManagerService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;


public class RegisterActivity extends AppCompatActivity {

    private static Retrofit retrofit;
    private int idRegister;
    private EditText passwordRegister;
    private EditText repeatPasswordRegister;
    private EditText nameRegister;
    private EditText surnameRegister;
    private EditText nicknameRegister;
    private EditText mailRegister;
    private UserManagerService usersAPI;
    private Boolean authenticated;
    Button registerButton;


    //-----------------API Methods------------------//
    public void register(final User user){
        usersAPI = retrofit.create(UserManagerService.class);
        usersAPI.register(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.code() == 200){
                    //Fill the user instance
                    User loggedUser = User.getInstance();
                    loggedUser.setUserName(user.getUserName());
                    loggedUser.setPassword(user.getPassword());

                    Toast.makeText(getApplicationContext(), "Register successful", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish(); //-----Response activity (close session - delete SHAREDPREFERENCES)
                }
                else{
                    if(response.code() == 400)
                        Toast.makeText(getApplicationContext(), "Register error: " + response.code() + "\nNickname used" , Toast.LENGTH_LONG).show();
                    else if(response.code() == 503)
                        Toast.makeText(getApplicationContext(), "Register error: " + response.code() + "\nDatabase down" , Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getApplicationContext(), "Register error: " + response.code() + "\nInternal Server Error", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }

    //-------------------Activity Methods-----------//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        nameRegister = findViewById(R.id.editTextName);
        surnameRegister = findViewById(R.id.editTextSurname);
        nicknameRegister = findViewById(R.id.editTextNickname);
        mailRegister = findViewById(R.id.editTextEmail);
        passwordRegister = findViewById(R.id.editTextTextPassword);
        repeatPasswordRegister = findViewById(R.id.editTextTextPassword2);

        startRetrofit();
        //authenticated = false;


    }

    public void registerButtonClicked (View v){
        if(nameRegister.getText().toString().equals("")||surnameRegister.getText().toString().equals("")||nicknameRegister.getText().toString().equals("")|| mailRegister.getText().toString().equals("")|| passwordRegister.getText().toString().equals("") || repeatPasswordRegister.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Error: you must fill all the fields", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "Accesing to the BBDD", Toast.LENGTH_LONG).show();
            User user = new User(0,nicknameRegister.getText().toString(),passwordRegister.getText().toString(),nameRegister.getText().toString(),surnameRegister.getText().toString(),0,mailRegister.getText().toString());
            register(user);
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

}
