package com.example.farm_commute.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.farm_commute.model.NothingResponse;
import com.example.farm_commute.model.UserDataListResponse;
import com.example.farm_commute.repository.SetQnaRepository;

import lombok.Getter;

/**
 * @version 1.0.0, class generate
 */
public class MyPageQnaActivityViewModel extends ViewModel {

    private SetQnaRepository SetQnaRepository;
    @Getter
    private MutableLiveData<NothingResponse> mutableQnaListData;

    public void setQna(String companyID,String contents) {
        if (SetQnaRepository == null) SetQnaRepository = new SetQnaRepository();

        mutableQnaListData = SetQnaRepository.getMutableQnaData(companyID,contents);
    }
}