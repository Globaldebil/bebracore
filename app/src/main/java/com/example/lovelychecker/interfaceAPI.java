package com.example.lovelychecker;

import javax.xml.transform.Result;

import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Header;
import retrofit2.Call;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface interfaceAPI {

    @POST("/login")
    Call<LoginFragment.Result> loginUser(@Body LoginRequest loginRequest);

    @POST("/signupf")
    Call<Post> signUp(@Body SignupRequest signupRequest);

    @POST("/confirm") // потом поменять
    Call<Post> confirm(@Body String confirmToken);

//    @GET("/somewhere")
//    Call<Post> something(String something);
}
