package com.ifkbhit.imagefinder.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static RetrofitClient client = null;

    public static RetrofitClient getInstance() {
        return client == null? (client = new RetrofitClient()): client;
    }

    private static String BASE_URL = "http://api.themoviedb.org/3/";

    private Retrofit retrofit;

    private RetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public MoviesInterface getMovieApi(){
        return retrofit.create(MoviesInterface.class);
    }


}
