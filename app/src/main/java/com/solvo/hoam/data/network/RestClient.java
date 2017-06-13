package com.solvo.hoam.data.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {
    private RestService restService;

    public RestClient() {
        restService = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(RestService.HOAM_API)
                .build()
                .create(RestService.class);
    }

    public RestService getRestService() {
        return restService;
    }
}