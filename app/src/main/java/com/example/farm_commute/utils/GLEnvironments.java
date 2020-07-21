package com.example.farm_commute.utils;

import android.util.Log;

import com.example.farm_commute.api.GLApiService;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GLEnvironments {

    public static String 사용자번호 = "";
    public static String 사용자닉네임 = "";
    public static String 사용자프로필유알엘 = "";
    public static String 오늘날짜 = "";

    public static final int FINISH_APP = 444;

    public static boolean isFinishJoinYn = false;
    public static boolean isCommentTagged = false;
    public static String taggedCommentNo = null;
    public static String taggedCommentUserName = null;

    public static class NETWORK {
        public final static int CONNECTION_TIMEOUT = 10;
        public final static int READ_TIMEOUT = 300;
    }

    public static Boolean isLogYn = true;

    public static Retrofit getRetrofit() {


     //   final String cookie = GLSharedPreferencesUtil.getSharedPreference(GLActivityStackManager.getRecently(), GLEnvironments.로그인유저쿠키키);


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(GLEnvironments.NETWORK.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(GLEnvironments.NETWORK.READ_TIMEOUT, TimeUnit.SECONDS)
                .build();

        return new Retrofit.Builder()
                .baseUrl(GLApiService.API_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

/*
        OkHttpClient defaultHttpClient = new OkHttpClient.Builder()
                .addInterceptor(
                        new Interceptor() {
                            @Override
                            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                                Request request = chain.request().newBuilder().build();
                                      //  .addHeader("Cookie", ck).build();
                                return chain.proceed(request);
                            }
                        }).build();



         return new Retrofit.Builder()
                .baseUrl(GLApiService.API_URL)
                .client(defaultHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();*/

      /*  if (GLUtils.isEmpty(cookie)) {


        } else {

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(GLEnvironments.NETWORK.CONNECTION_TIMEOUT, TimeUnit.MINUTES)
                    .readTimeout(GLEnvironments.NETWORK.READ_TIMEOUT, TimeUnit.SECONDS)
                    .addInterceptor(
                            new Interceptor() {
                                @Override
                                public okhttp3.Response intercept(Chain chain) throws IOException {
                                    Request request = chain.request().newBuilder()
                                            .addHeader("Cookie", cookie).build();
                                    return chain.proceed(request);
                                }
                            })
                    .build();

            return new Retrofit.Builder()
                    .baseUrl(GLApiService.API_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }*/

    }
}