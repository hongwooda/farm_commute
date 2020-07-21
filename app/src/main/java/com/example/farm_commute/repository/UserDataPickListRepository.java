package com.example.farm_commute.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.farm_commute.api.GLApiService;
import com.example.farm_commute.model.UserDataListResponse;
import com.example.farm_commute.utils.GLEnvironments;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserDataPickListRepository {

    private static final String TAG = "UserDataPickListRepository";

    private GLApiService glApiService;

    public UserDataPickListRepository() {
        Retrofit retrofit = GLEnvironments.getRetrofit();
        glApiService = retrofit.create(GLApiService.class);
    }

    private MutableLiveData<UserDataListResponse> mutableData = new MutableLiveData<>();

    public MutableLiveData<UserDataListResponse> getMutableUserDataPickListData(String companyID,String date,String getWeek) {
        glApiService.getUserPickDateList(companyID,date,getWeek).enqueue(new Callback<UserDataListResponse>() {
            @Override
            public void onResponse(Call<UserDataListResponse> call, Response<UserDataListResponse> response) {
                Log.e(TAG, "HTTP status code: " + response.code()+"///"+response.body()+"///"+response.message());
                if (response.isSuccessful()) {
                    mutableData.setValue(response.body());

                } else {
                    mutableData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<UserDataListResponse> call, Throwable t) {
                Log.e(TAG, "onFailure", t);
                mutableData.setValue(null);
            }
        });
        return mutableData;
    }
}
