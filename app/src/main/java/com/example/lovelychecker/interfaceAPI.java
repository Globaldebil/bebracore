package com.example.lovelychecker;

import retrofit2.http.POST;
import retrofit2.http.Header;
import retrofit2.Call;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface interfaceAPI {

    @GET("api/endpoint") // заменить на конечный пункт сервера
    Call<Void> loginUser(@Query("email") String email, @Query("password") String password);

}
