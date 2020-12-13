package com.example.log_in_java;


import android.content.Intent;
import android.os.Bundle;
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

        Call<User> call = usersAPI.register(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.code() == 200){
                    //Fill the user instance
                    User loggedUser = User.getInstance();
                    loggedUser.setUserName(user.getUserName());
                    loggedUser.setPassword(user.getPassword());

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish(); //-----Response activity (close session - delete SHAREDPREFERENCES)

                    Toast.makeText(getApplicationContext(), "Register successful", Toast.LENGTH_LONG).show();
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



        usersAPI = retrofit.create(UserManagerService.class);


        registerButton.setOnClickListener((v )->{

            if(nameRegister.getText().toString().equals("")||surnameRegister.getText().toString().equals("")||nicknameRegister.getText().toString().equals("")|| mailRegister.getText().toString().equals("")|| passwordRegister.getText().toString().equals("") || repeatPasswordRegister.getText().toString().equals("")){
                Toast.makeText(getApplicationContext(), "Error: you must fill all the fields", Toast.LENGTH_LONG).show();
            }
            else{
                register(new User(0,nicknameRegister.getText().toString(),passwordRegister.getText().toString(),nameRegister.getText().toString(),surnameRegister.getText().toString(),0,mailRegister.getText().toString()));
            }

        } );


    }
    private static void startRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/gameDSA/") //Later on we will put the server's IP address, meanwhile in localhost
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
