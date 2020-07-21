package com.example.farm_commute.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.farm_commute.model.LoginResponse;
import com.example.farm_commute.model.NothingResponse;
import com.example.farm_commute.model.QuotesResponse;
import com.example.farm_commute.model.UserDataListResponse;
import com.example.farm_commute.model.UserInfoResponse;
import com.example.farm_commute.repository.GetQuotesRepository;
import com.example.farm_commute.repository.SetHolidayRepository;
import com.example.farm_commute.repository.SetOffworkRepository;
import com.example.farm_commute.repository.SetOnworkRepository;
import com.example.farm_commute.repository.UserDataPickListRepository;
import com.example.farm_commute.repository.UserInfoRepository;

import lombok.Getter;

/**
 * @version 1.0.0, class generate
 */
public class MainActivityViewModel extends ViewModel {


    private UserInfoRepository UserInfoRepository;
    @Getter
    private MutableLiveData<UserInfoResponse> mutableUserInfoData;

    public void getUserInfo(String companyID) {
        if (UserInfoRepository == null) UserInfoRepository = new UserInfoRepository();

        mutableUserInfoData = UserInfoRepository.getMutableUserInfoData(companyID);
    }



    private UserDataPickListRepository UserDataPickListRepository;
    @Getter
    private MutableLiveData<UserDataListResponse> mutableUserDataPickListData;

    public void getUserDataPickList(String companyID,String date,String getWeek) {
        if (UserDataPickListRepository == null) UserDataPickListRepository = new UserDataPickListRepository();

        mutableUserDataPickListData = UserDataPickListRepository.getMutableUserDataPickListData(companyID,date,getWeek);
    }

    private SetOnworkRepository SetOnworkRepository;
    @Getter
    private MutableLiveData<NothingResponse> mutablesetOnworkData;

    public void setOnwork(String companyID,String date,String hour,String minute) {
        if (SetOnworkRepository == null) SetOnworkRepository = new SetOnworkRepository();

        mutablesetOnworkData = SetOnworkRepository.getMutablesetOnworkData(companyID,date,hour,minute);
    }

    private SetOffworkRepository SetOffworkRepository;
    @Getter
    private MutableLiveData<NothingResponse> mutablesetOffworkData;

    public void setOffwork(String companyID,String date,String hour,String minute) {
        if (SetOffworkRepository == null) SetOffworkRepository = new SetOffworkRepository();

        mutablesetOffworkData = SetOffworkRepository.getMutablesetOffworkData(companyID,date,hour,minute);
    }

    private SetHolidayRepository SetHolidayRepository;
    @Getter
    private MutableLiveData<NothingResponse> mutablesetHolidayData;

    public void setHoliday(String companyID,String date,String holiday_yn) {
        if (SetHolidayRepository == null) SetHolidayRepository = new SetHolidayRepository();

        mutablesetHolidayData = SetHolidayRepository.getMutablesetHolidayData(companyID,date,holiday_yn);
    }


    private GetQuotesRepository GetQuotesRepository;
    @Getter
    private MutableLiveData<QuotesResponse> mutablegetQuotesData;

    public void getQuotes(String date) {
        if (GetQuotesRepository == null) GetQuotesRepository = new GetQuotesRepository();

        mutablegetQuotesData = GetQuotesRepository.getMutableQuotesData(date);
    }
}