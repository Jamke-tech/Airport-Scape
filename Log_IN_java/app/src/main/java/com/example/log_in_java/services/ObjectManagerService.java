package com.example.log_in_java.services;

import com.example.log_in_java.models.Objects;
import com.example.log_in_java.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ObjectManagerService {

    @GET("object/getList/{nickName}")
    Call<List<Objects>> listBuyedObjects(@Path("nickName") String userName);


}
