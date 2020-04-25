package com.bw.movie.utils;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WorkUtil {
    private static WorkUtil incract;
    private final Retrofit mRetrofit;

    public static WorkUtil getIncract() {
        if (incract==null) {
            if (incract==null) {
                incract =new WorkUtil();
            }
        }
        return incract;
    }
    private WorkUtil(){
//        创建日志拦截器
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
//        添加日志拦截器
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
//        okHttp Retrofit
        mRetrofit = new Retrofit.Builder()
//                添加Gson工厂类
                .addConverterFactory(GsonConverterFactory.create())
//                添加RXjava工厂类
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                添加baseUrl
                .baseUrl(Api.BASE_URL)
//                把OkHttpClient添加到Retrofit里
                .client(client)
                .build();
    }
    public <T> T create(final Class<T> service) {
        return mRetrofit.create(service);
    }
    }
