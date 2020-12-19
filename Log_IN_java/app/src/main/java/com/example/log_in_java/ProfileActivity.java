package com.example.log_in_java;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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

    private Context context;
    private static Retrofit retrofit;
    private static String retrofitIpAddress;
    private UserManagerService usersAPI;
    User user = new User();
    ImageView imageView;
    TextView textUsername;
    TextView textName;
    TextView textSurname;
    TextView textMail;
    TextView textMoney;

    //-------------------------------API Methods------------------------------------------//
    public void profileData(String usrname) {
        //Method getUser() from API
        Call<User> call = usersAPI.getUser(usrname);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    //We "fill" the logged User instance
                    User loggedUsr = User.getInstance();
                    loggedUsr.setPassword(response.body().getPassword());
                    loggedUsr.setName(response.body().getName());
                    loggedUsr.setSurname(response.body().getSurname());
                    loggedUsr.setUserName(response.body().getUserName());
                    loggedUsr.setMail(response.body().getMail());
                    loggedUsr.setMoney(response.body().getMoney());

                    String userName = "UserName : " + user.getUserName();
                    String name = "Name : " + user.getName();
                    String surname = "Surname : " + user.getSurname();
                    String mail = "Mail : " + user.getMail();
                    String money = "Money : " + user.getMoney();

                    textUsername.setText(userName);
                    textName.setText(name);
                    textSurname.setText(surname);
                    textMail.setText(mail);
                    textMoney.setText(money);

                    /*textUsername.setText(User.getInstance().getUserName());
                    textName.setText(String.valueOf(User.getInstance().getName()));
                    textSurname.setText(String.valueOf(User.getInstance().getSurname()));
                    textMail.setText(String.valueOf(User.getInstance().getMail()));
                    textMoney.setText(String.valueOf(User.getInstance().getMoney()));*/


                } else
                    Toast.makeText(getApplicationContext(), "Error getting User statistics: " + response.code() + "\nInternal Server Error", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }




    /*@Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Initialize and fill all the Dashboard fields
        imageView = (ImageView) getView().findViewById(R.id.imageProfile);
        textUsername = (TextView) getView().findViewById(R.id.textUsername);
        textName = (TextView) getView().findViewById(R.id.textName);
        textSurname = (TextView) getView().findViewById(R.id.textSurname);
        textMail = (TextView) getView().findViewById(R.id.textMail);
        textMoney = (TextView) getView().findViewById(R.id.textMoney);

        startRetrofit();
        usersAPI = retrofit.create(UserManagerService.class);

        profileData(User.getInstance().getUserName());

    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        imageView = this.findViewById(R.id.imageProfile);
        textUsername = this.findViewById(R.id.textUsername);
        textName = this.findViewById(R.id.textName);
        textSurname = this.findViewById(R.id.textSurname);
        textMail = this.findViewById(R.id.textMail);
        textMoney = this.findViewById(R.id.textMoney);

        startRetrofit();
        //usersAPI = retrofit.create(UserManagerService.class);

        //profileData(User.getInstance().getUserName());
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
}