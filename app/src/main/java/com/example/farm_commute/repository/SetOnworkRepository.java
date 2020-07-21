package com.example.farm_commute.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.farm_commute.api.GLApiService;
import com.example.farm_commute.model.NothingResponse;
import com.example.farm_commute.utils.GLEnvironments;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SetOnworkRepository {

    private static final String TAG = "SetOnworkRepository";

    private GLApiService glApiService;

    public SetOnworkRepository() {
        Retrofit retrofit = GLEnvironments.getRetrofit();
        glApiService = retrofit.create(GLApiService.class);
    }

    private MutableLiveData<NothingResponse> mutableData = new MutableLiveData<>();

    public MutableLiveData<NothingResponse> getMutablesetOnworkData(String companyID,String date,String hour,String minute) {
        glApiService.setOnwork(companyID,date,hour,minute).enqueue(new Callback<NothingResponse>() {
            @Override
            public void onResponse(Call<NothingResponse> call, Response<NothingResponse> response) {
                Log.e(TAG, "HTTP status code: " + response.code()+"///"+response.body()+"///"+response.message());
                if (response.isSuccessful()) {
                    mutableData.setValue(response.body());

                } else {
                    mutableData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<NothingResponse> call, Throwable t) {
                Log.e(TAG, "onFailure", t);
                mutableData.setValue(null);
            }
        });
        return mutableData;
    }
}
