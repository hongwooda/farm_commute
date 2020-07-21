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

public class SetQnaRepository {

    private static final String TAG = "GetQuotesRepository";

    private GLApiService glApiService;

    public SetQnaRepository() {
        Retrofit retrofit = GLEnvironments.getRetrofit();
        glApiService = retrofit.create(GLApiService.class);
    }

    private MutableLiveData<NothingResponse> mutableLoginData = new MutableLiveData<>();

    public MutableLiveData<NothingResponse> getMutableQnaData(String companyID,String contents) {
        glApiService.setQnA(companyID,contents).enqueue(new Callback<NothingResponse>() {
            @Override
            public void onResponse(Call<NothingResponse> call, Response<NothingResponse> response) {
                Log.e(TAG, "HTTP status code: " + response.code()+"///"+response.body()+"///"+response.message());
                if (response.isSuccessful()) {
                    mutableLoginData.setValue(response.body());

                } else {
                    mutableLoginData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<NothingResponse> call, Throwable t) {
                Log.e(TAG, "onFailure", t);
                mutableLoginData.setValue(null);
            }
        });
        return mutableLoginData;
    }
}
