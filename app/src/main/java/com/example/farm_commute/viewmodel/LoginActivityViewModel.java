package com.example.farm_commute.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.farm_commute.model.LoginResponse;
import com.example.farm_commute.repository.LoginRepository;

import lombok.Getter;

/**
 * @version 1.0.0, class generate
 */
public class LoginActivityViewModel extends ViewModel {

    private LoginRepository loginRepository;


    @Getter
    private MutableLiveData<LoginResponse> mutableLoginData;



    public void LoginRequest(String companyID,String password) {
        if (loginRepository == null) loginRepository = new LoginRepository();

       mutableLoginData = loginRepository.getMutableLoginData(companyID,password);
    }
}