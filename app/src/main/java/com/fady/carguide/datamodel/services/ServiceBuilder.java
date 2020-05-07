package com.fady.carguide.datamodel.services;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ServiceBuilder {
    private static final String URL = "http://demo1286023.mockable.io/api/v1/";
    private static OkHttpClient.Builder okHttp =
            new OkHttpClient.Builder().addInterceptor(chain -> {
                Request request = chain.request()
                        .newBuilder()
                        .build();
                return chain.proceed(request);
            })
                    .readTimeout(10, TimeUnit.SECONDS)
                    .connectTimeout(10, TimeUnit.SECONDS);


    public static <T> T buildService(Class<T> serviceType) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(URL)
                .client(okHttp.build())
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        return retrofit.create(serviceType);
    }


}
