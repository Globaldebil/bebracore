package com.example.lovelychecker;

import com.example.lovelychecker.tovar.Product;

import java.util.List;

import javax.xml.transform.Result;

import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Header;
import retrofit2.Call;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface interfaceAPI {

    @POST("/login")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

    @POST("/signupf")
    Call<LoginResponse> signUp(@Body SignupRequest signupRequest);

    @POST("/accountVerification/{confirmToken}")
    Call<Post> confirm(@Path(value = "confirmToken") String confirmToken);

    @GET
    Call<LoginResponse> finishOAuth2(@Url String url, @Header("Cookie") String jsessionId);

    @GET("/product/smartphones")
    Call<List<Product>> getProducts(@Query(value = "text") String text, @Query("brands") List<String> brands,
                                    @Query("rams") List<String> rams, @Query("sort") String sort);

    @GET("login/oauth2/{service}")
    Call<Void> oauth2(@Path(value="service") String service);
//    @GET("/somewhere")
//    Call<Post> something(String something);
}
