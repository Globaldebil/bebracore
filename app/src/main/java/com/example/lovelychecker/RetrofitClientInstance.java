package com.example.lovelychecker;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

private static Retrofit retrofit = null;
    private static final String BASE_URL = "https://yourserver.com/"; // ЗАМЕНИТЬ НА URL СЕРВЕРА

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
