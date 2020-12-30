package com.example.log_in_java;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.log_in_java.models.User;
import com.example.log_in_java.services.UserManagerService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileActivity extends AppCompatActivity {

    //Hola Gerard esto es una prueba
    private SharedPreferences preferences;
    private String nickname;
    private Context context;
    private static Retrofit retrofit;
    private static String retrofitIpAddress;
    private UserManagerService usersAPI;
    private ImageView imageView;
    private TextView textUsername;
    private TextView textName;
    private TextView textSurname;
    private TextView textMail;
    private TextView textMoney;

    //-------------------------------API Methods------------------------------------------//
    public void profileData(String usrname) {

        //Method getUser() from API
        usersAPI = retrofit.create(UserManagerService.class);
        Call<User> call = usersAPI.getUser(usrname);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == 200) {
                    //We "fill" the logged User instance
                    User loggedUsr = User.getInstance();
                    loggedUsr.setPassword(response.body().getPassword());
                    loggedUsr.setName(response.body().getName());
                    loggedUsr.setSurname(response.body().getSurname());
                    loggedUsr.setUserName(response.body().getUserName());
                    loggedUsr.setMail(response.body().getMail());
                    loggedUsr.setMoney(response.body().getMoney());

                    String userName = "UserName: " + loggedUsr.getUserName();
                    String name = "Name: " + loggedUsr.getName();
                    String surname = "Surname: " + loggedUsr.getSurname();
                    String mail = "Mail: " + loggedUsr.getMail();
                    String money = "Money: " + loggedUsr.getMoney();

                    textUsername.setText(userName);
                    textName.setText(name);
                    textSurname.setText(surname);
                    textMail.setText(mail);
                    textMoney.setText(money);



                } else
                    Toast.makeText(getApplicationContext(), "Error getting User statistics: " + response.code() + "\nInternal Server Error", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loadPreferences();
        nickname =preferences.getString("userNickname", null);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        imageView = this.findViewById(R.id.imageProfile);
        textUsername = this.findViewById(R.id.textUsername);
        textName = this.findViewById(R.id.textName);
        textSurname = this.findViewById(R.id.textSurname);
        textMail = this.findViewById(R.id.textMail);
        textMoney = this.findViewById(R.id.textMoney);
        context = this.getApplicationContext();

        startRetrofit();


        //profileData(User.getInstance().getUserName());
        profileData(nickname);
    }


    private void loadPreferences(){
        preferences = getSharedPreferences("Login credentials", Context.MODE_PRIVATE);
    }

    private static void startRetrofit(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //Attaching Interceptor to a client
        OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/gameDSA/") //Local host on windows 10.0.2.2 and ip our machine 147.83.7.203
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public void onChangePasswordClicked(View view){
        changePasswordDialog();
    }

    private void changePasswordDialog(){
        final Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialog_changepassword);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        Button cancel= dialog.findViewById(R.id.buttonCancel);
        Button accept =dialog.findViewById(R.id.buttonAccept);
        TextView currentPass = dialog.findViewById(R.id.currentPasswordText);
        TextView newPass = dialog.findViewById(R.id.newPasswordText);
        TextView newPassRe = dialog.findViewById(R.id.repeatPasswordText);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){dialog.dismiss();}
        });

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check if the password is correct and new password and retype password is same
                if(newPass.getText().toString().equals(newPassRe.getText().toString()) &&!newPass.getText().toString().contains(" ")){
                    //New pass and old pass is same
                    if(currentPass.getText().toString().equals(User.getInstance().getPassword())){
                        User.getInstance().setPassword( newPass.getText().toString());
                        dialog.dismiss();
                    }else{
                        //Incorrect Current Password NOTIFY
                        Toast.makeText(getApplicationContext(), "Incorrect current password: ", Toast.LENGTH_LONG).show();

                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Please type new Password without spaces ", Toast.LENGTH_LONG).show();

                }
            }
        });
        dialog.show();
    }

}