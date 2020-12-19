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

public interface UserManagerService {

    @POST("user/login")
    Call <User> login(@Body User user);

    @POST("user/register")
    Call <User> register(@Body User user);

    @GET("user/users")
    Call <List<User>> getUsers();

    @GET("user/{nickName}")
    Call <User> getUser(@Path("nickName") String userName);

    //De aqui para abajo arreglar  amedida que vayamos usando

    @POST("usermanager/users/{name}/update") //PUT
    Call <Void> updateUser(@Body User usr, @Path("name") String userName);

    @POST("usermanager/users/{name}/delete") //DELETE
    Call <Void> deleteUser(@Body User user);

    @GET("usermanager/users/{name}/screen")
    Call <String> getUserScreen(@Path("name") String userName);

    /*@GET("usermanager/users/{name}/objects")
    Call <List<Objeto>> getUserObjects(@Path("name") String userName);

    @POST("usermanager/users/{name}/addobject")
    Call <Void> addObjectToUser(@Path("name") String userName, String obj);
    */

}
