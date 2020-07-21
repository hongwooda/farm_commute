package com.example.farm_commute.api;


import com.example.farm_commute.model.LoginResponse;
import com.example.farm_commute.model.NothingResponse;
import com.example.farm_commute.model.QuotesResponse;
import com.example.farm_commute.model.UserDataListResponse;
import com.example.farm_commute.model.UserInfoResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface GLApiService {
    String API_URL = "http://101.101.218.210:3000/";






    //로그
    @FormUrlEncoded
    @POST("api/login")
    Call<LoginResponse> getLogin(@Field("companyID") String companyID, @Field("password") String password);

    //유저정보 조회
    @FormUrlEncoded
    @POST("api/user/getUserInfo")
    Call<UserInfoResponse> getUserInfo(@Field("companyID") String companyID);

/*
    //날짜 조회 - 전체 조회
    @FormUrlEncoded
    @POST("api/commute/getUserDateList")
    Call<UserDataListResponse> getUserAllDateList(@Field("companyID") String companyID);
*/

    //날짜 조회 - 특정 날짜 조회
    @FormUrlEncoded
    @POST("api/commute/getUserDateList")
    Call<UserDataListResponse> getUserPickDateList(@Field("companyID") String companyID,@Field("date") String date,@Field("getWeek") String getWeek);

/*
    //날짜 조회 - 주 날짜 조회
    @FormUrlEncoded
    @POST("api/commute/getUserDateList")
    Call<UserDataListResponse> getUserWeekDateList(@Field("companyID") String companyID,@Field("date") String date,@Field("getWeek") String getWeek);
*/

    //출근 날짜 입력
    @FormUrlEncoded
    @POST("api/commute/setOnwork")
    Call<NothingResponse> setOnwork(@Field("companyID") String companyID, @Field("date") String date, @Field("hour") String hour, @Field("minute") String minute);


    //퇴근 날짜 입력
    @FormUrlEncoded
    @POST("api/commute/setOffwork")
    Call<NothingResponse> setOffwork(@Field("companyID") String companyID, @Field("date") String date, @Field("hour") String hour, @Field("minute") String minute);


    //휴가 입력
    @FormUrlEncoded
    @POST("api/commute/setHoliday")
    Call<NothingResponse> setHoliday(@Field("companyID") String companyID, @Field("date") String date, @Field("holiday_yn") String holiday_yn);


    //명언 출
    @FormUrlEncoded
    @POST("api/etc/getQuotes")
    Call<QuotesResponse> getQuotes(@Field("date") String date);

    //명언 출
    @FormUrlEncoded
    @POST("api/etc/QnA")
    Call<NothingResponse> setQnA(@Field("companyID") String companyID,@Field("contents") String contents);


}