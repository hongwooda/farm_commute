package com.example.farm_commute.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.farm_commute.api.GLApiService;
import com.example.farm_commute.model.UserInfoResponse;
import com.example.farm_commute.utils.GLEnvironments;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserInfoRepository {

    private static final String TAG = "UserInfoRepository";

    private GLApiService glApiService;

    public UserInfoRepository() {
        Retrofit retrofit = GLEnvironments.getRetrofit();
        glApiService = retrofit.create(GLApiService.class);
    }

    private MutableLiveData<UserInfoResponse> mutableData = new MutableLiveData<>();

    public MutableLiveData<UserInfoResponse> getMutableUserInfoData(String companyID) {
        glApiService.getUserInfo(companyID).enqueue(new Callback<UserInfoResponse>() {
            @Override
            public void onResponse(Call<UserInfoResponse> call, Response<UserInfoResponse> response) {
                Log.e(TAG, "HTTP status code: " + response.code()+"///"+response.body()+"///"+response.message());
                if (response.isSuccessful()) {
                    mutableData.setValue(response.body());

                } else {
                    mutableData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<UserInfoResponse> call, Throwable t) {
                Log.e(TAG, "onFailure", t);
                mutableData.setValue(null);
            }
        });
        return mutableData;
    }
}
