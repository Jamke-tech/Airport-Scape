package com.example.log_in_java.services;

import com.example.log_in_java.models.*;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface GameManagerService {


    @POST("game/save")
    Call<Game> saveGame (@Body Game game);

    @GET("game/{name}")
    Call<Game> getSavedGame (@Path("name") String username);

    @GET("game/getIdMap/{name}")
    Call<Game> getIdMap (@Path("name") String username);

    @GET("game/getStringMap/{id}")
    Call<Map> getStringMap (@Path("id") int id);

    @POST("game/win/")
    Call<Game> updateMoney (@Body Game game);

}
