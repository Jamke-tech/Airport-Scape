package com.example.log_in_java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.example.log_in_java.services.UserManagerService;
import com.example.log_in_java.models.*;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    //--------Atributes------------//

    Button loginBtn;
    Button registerBtn;
    private EditText idLogin;
    private EditText passwordLogin;
    private UserManagerService usersAPI;
    private Boolean authenticated;


    //---------------API Methods----------------------------//
    public void login(User user){

        Call<User> call = usersAPI.login(user);
        call.enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == 200){
                    //Fill the user instance
                    User loggedUser = User.getInstance();
                    loggedUser.setUserName(response.body().getUserName());
                    loggedUser.setPassword(response.body().getPassword());

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish(); //-----Response activity (close session - delete SHAREDPREFERENCES)
                    Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_LONG).show();
                }
                else{

                    if(response.code() == 404)
                        Toast.makeText(getApplicationContext(), "Authentication error: " + response.code() + "\nInvalid username or password" , Toast.LENGTH_LONG).show();
                    else if(response.code() == 401)
                        Toast.makeText(getApplicationContext(),"Authentication error:"+response.code() + "\nNot authorized", Toast.LENGTH_LONG).show();
                    else if(response.code() ==503)
                        Toast.makeText(getApplicationContext(),"Authentication error:"+response.code() + "\nDatabase down", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getApplicationContext(), "Authentication error: " + response.code() + "\nInternal Server Error", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });


    }

    //------------------Activity Methods-----------------//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        idLogin = findViewById(R.id.textViewID);
        passwordLogin = findViewById(R.id.editTextPassword);
        loginBtn = findViewById(R.id.buttonLogin);
        registerBtn = findViewById(R.id.buttonRegister);



        //Temporal
        authenticated = false;
        Retrofit retrofitinstance = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/gameDSA/") //Later on we will put the server's IP address, meanwhile in localhost
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        usersAPI = retrofitinstance.create(UserManagerService.class);

        loginBtn.setOnClickListener((v) -> {

            if (idLogin.getText().toString().equals("")|| passwordLogin.getText().toString().equals("")){
                Toast.makeText(getApplicationContext(), "Error: you must fill User Name and Password fields", Toast.LENGTH_LONG).show();
            }
            else{
                login(new User(idLogin.getText().toString(),passwordLogin.getText().toString()));
                savePreferences();
            }

        });

        //registerBtn =(Button) findViewById(R.id.buttonRegister);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));

            }
        });


    }

    private void savePreferences(){
        SharedPreferences preferences= getSharedPreferences("Login credentials", Context.MODE_PRIVATE);
        String nickname = idLogin.getText().toString();
        String password = passwordLogin.getText().toString();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("userNickname", nickname);
        editor.putString("userPassword", password);
        editor.apply();


    }

}