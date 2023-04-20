package com.example.lovelychecker;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

private static Retrofit retrofit = null;
    public static final String BASE_URL = "http://10.131.60.181:8080";
    public static String JSESSION_ID;

    public static String USER_ID = "6435cd043572c97e7243c5c9";
    public static String ACCESS_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2NDM1Y2QwNDM1NzJjOTdlNzI0M2M1YzkiLCJleHAiOjMzNjM4NjU2NTd9.AY8Yq444j6M63Egd8p2EIxwuPDpBcXbNqyQpjRLAwFY";
    public static interfaceAPI getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(interfaceAPI.class);
    }
}
