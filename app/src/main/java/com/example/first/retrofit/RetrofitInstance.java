package com.example.first.retrofit;

import com.example.first.service.DummyJsonService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofit = null;
    private static String URL = "https://dummyjson.com/";


    public static DummyJsonService getService() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        DummyJsonService service = retrofit.create(DummyJsonService.class);
        return service;

    }

}
