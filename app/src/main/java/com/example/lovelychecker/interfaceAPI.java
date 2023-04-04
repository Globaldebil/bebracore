package com.example.lovelychecker;

import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Header;
import retrofit2.Call;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface interfaceAPI {

    @POST("/login")
    Call<Post> loginUser(@Body LoginRequest loginRequest);

    @POST("/signupf")
    Call<Post> signUp(@Query("username") String username, @Query("email") String email, @Query("password") String password);

    @GET("/somewhere")
    Call<Post> something(String something);
}
