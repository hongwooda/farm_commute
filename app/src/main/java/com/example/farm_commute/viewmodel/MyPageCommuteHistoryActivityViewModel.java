package com.example.farm_commute.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.farm_commute.model.LoginResponse;
import com.example.farm_commute.model.UserDataListResponse;
import com.example.farm_commute.repository.LoginRepository;
import com.example.farm_commute.repository.UserDataPickListRepository;

import lombok.Getter;

/**
 * @version 1.0.0, class generate
 */
public class MyPageCommuteHistoryActivityViewModel extends ViewModel {

    private UserDataPickListRepository UserDataPickListRepository;
    @Getter
    private MutableLiveData<UserDataListResponse> mutableUserDataPickListData;

    public void getUserDataPickList(String companyID,String date,String getWeek) {
        if (UserDataPickListRepository == null) UserDataPickListRepository = new UserDataPickListRepository();

        mutableUserDataPickListData = UserDataPickListRepository.getMutableUserDataPickListData(companyID,date,getWeek);
    }
}