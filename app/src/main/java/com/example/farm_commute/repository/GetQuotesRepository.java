package com.example.farm_commute.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.farm_commute.api.GLApiService;
import com.example.farm_commute.model.QuotesResponse;
import com.example.farm_commute.utils.GLEnvironments;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GetQuotesRepository {

    private static final String TAG = "GetQuotesRepository";

    private GLApiService glApiService;

    public GetQuotesRepository() {
        Retrofit retrofit = GLEnvironments.getRetrofit();
        glApiService = retrofit.create(GLApiService.class);
    }

    private MutableLiveData<QuotesResponse> mutableLoginData = new MutableLiveData<>();

    public MutableLiveData<QuotesResponse> getMutableQuotesData(String date) {
        glApiService.getQuotes(date).enqueue(new Callback<QuotesResponse>() {
            @Override
            public void onResponse(Call<QuotesResponse> call, Response<QuotesResponse> response) {
                Log.e(TAG, "HTTP status code: " + response.code()+"///"+response.body()+"///"+response.message());
                if (response.isSuccessful()) {
                    mutableLoginData.setValue(response.body());

                } else {
                    mutableLoginData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<QuotesResponse> call, Throwable t) {
                Log.e(TAG, "onFailure", t);
                mutableLoginData.setValue(null);
            }
        });
        return mutableLoginData;
    }
}
