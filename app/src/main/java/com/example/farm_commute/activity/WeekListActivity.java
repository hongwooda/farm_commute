package com.example.farm_commute.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.farm_commute.R;
import com.example.farm_commute.adapter.CommuteListAdapter;
import com.example.farm_commute.common.CommonActivity;
import com.example.farm_commute.custom.CustomActionBarView;
import com.example.farm_commute.databinding.ActivityWeeklistBinding;
import com.example.farm_commute.model.UserDataListResponse;
import com.example.farm_commute.utils.GLEnvironments;
import com.example.farm_commute.viewmodel.MyPageCommuteHistoryActivityViewModel;

import java.util.ArrayList;

/**
 * 마이페이지 액티비티는 메인 -> 햄버거 클릭시 접근합니다.
 * 로그인이 되어있지 않다면 (사용자 번호가 없다면)
 * 화면 접근이 불가능 해야 합니다.
 */
public class WeekListActivity extends CommonActivity {
    private ActivityWeeklistBinding mBinding;

    private MyPageCommuteHistoryActivityViewModel mViewModel;
    private LifecycleOwner mLifecycleOwner;
    private ArrayList<UserDataListResponse.Result> UserDataList = new ArrayList<>();

    CommuteListAdapter commuteListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_weeklist);
        mViewModel = ViewModelProviders.of(WeekListActivity.this).get(MyPageCommuteHistoryActivityViewModel.class);
        mLifecycleOwner = this;

        setNetwork();

        setupActionBar();
    }

    private void setNetwork() {

        mViewModel.getUserDataPickList(GLEnvironments.사용자번호, GLEnvironments.오늘날짜,"true");
        mViewModel.getMutableUserDataPickListData().observe(mLifecycleOwner, new Observer<UserDataListResponse>() {
            @Override
            public void onChanged(UserDataListResponse userDataListResponse) {



                try {
                    UserDataList.clear();
                    UserDataList.addAll(userDataListResponse.getResult());


                    mBinding.remainTimeTextview.setText(userDataListResponse.getRemaining());

                    setupRecyclerView();
                }catch (NullPointerException e)
                {

                }

            }

        });

    }

    private void setupActionBar() {
        setCustomActionBarActivity(this);
        getCustomActionBar().setTitleHeader("이번 주 히스토리");
        getCustomActionBar().setLeftImage(R.drawable.ic_gnb_close);

        getCustomActionBar().setOnLeftButtonEvent(new CustomActionBarView.LeftEventListener() {
            @Override
            public void onLeftButtonEvent() {
                finish();
            }
        });

    }

    private void setupRecyclerView() {

            commuteListAdapter = new CommuteListAdapter(this, UserDataList);
            mBinding.recyclerViewHistoryList.setLayoutManager(new LinearLayoutManager(this));
            mBinding.recyclerViewHistoryList.setHasFixedSize(true);
            mBinding.recyclerViewHistoryList.setAdapter(commuteListAdapter);

    }



}
