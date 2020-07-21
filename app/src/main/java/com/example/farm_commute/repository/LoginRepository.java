package com.example.farm_commute.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.farm_commute.api.GLApiService;
import com.example.farm_commute.model.LoginResponse;
import com.example.farm_commute.utils.GLEnvironments;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginRepository {

    private static final String TAG = "LoginRepository";

    private GLApiService glApiService;

    public LoginRepository() {
        Retrofit retrofit = GLEnvironments.getRetrofit();
        glApiService = retrofit.create(GLApiService.class);
    }

    private MutableLiveData<LoginResponse> mutableLoginData = new MutableLiveData<>();

    public MutableLiveData<LoginResponse> getMutableLoginData(String companyID, String password) {
        glApiService.getLogin(companyID, password).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.e(TAG, "HTTP status code: " + response.code()+"///"+response.body()+"///"+response.message());
                if (response.isSuccessful()) {
                    mutableLoginData.setValue(response.body());

                } else {
                    mutableLoginData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e(TAG, "onFailure", t);
                mutableLoginData.setValue(null);
            }
        });
        return mutableLoginData;
    }
}
