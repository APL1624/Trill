package com.apl.trill.api;



import com.apl.trill.constants.HttpConstans;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/4/3.
 */

public class Api {
    private static ApiService apiService;
    public static ApiService getApiService(){
        if (apiService == null){
            initApiService(HttpConstans.HOST_NAME);
        }
        return apiService;
    }

    private static void initApiService(String host) {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(host)
                .build();
        apiService = retrofit.create(ApiService.class);
    }
}
