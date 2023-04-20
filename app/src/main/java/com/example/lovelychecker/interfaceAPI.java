package com.example.lovelychecker;

import com.example.lovelychecker.tovar.Magazins;
import com.example.lovelychecker.tovar.Product;
import com.example.lovelychecker.tovar.Review;
import com.example.lovelychecker.tovar.ReviewRequest;
import com.example.lovelychecker.tovar.ReviewResponse;

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

    @GET("/product/smartphone/{id}")
    Call<Product> getProduct(@Path("id") String id);

    @POST("/product/smartphone/{id}/review")
    Call<Void> saveReview(@Path("id") String id, @Body ReviewRequest reviewRequest, @Header("Authorization") String accessToken);

    @POST("/product/smartphone/{id}/review/{reviewId}/like")
    Call<Void> like(@Path("id") String id, @Path("reviewId") String reviewId, @Header("Authorization") String token);

    @POST("/product/smartphone/{id}/review/{reviewId}/dislike")
    Call<Void> dislike(@Path("id") String id, @Path("reviewId") String reviewId, @Header("Authorization") String token);

    @GET("/product/smartphone/{id}/reviews")
    Call<List<ReviewResponse>> getReviews(@Path("id") String id);

    @GET("/product/smartphone/{id}/resources")
    Call<List<Magazins>> getResources(@Path("id") String id);
    @GET("login/oauth2/{service}")
    Call<Void> oauth2(@Path(value="service") String service);
//    @GET("/somewhere")
//    Call<Post> something(String something);
}
