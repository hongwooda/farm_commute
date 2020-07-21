package com.example.farm_commute.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

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
import com.example.farm_commute.databinding.ActivityMypageCommuteHistoryBinding;
import com.example.farm_commute.databinding.ActivityMypageQnaBinding;
import com.example.farm_commute.model.UserDataListResponse;
import com.example.farm_commute.utils.GLEnvironments;
import com.example.farm_commute.viewmodel.MyPageCommuteHistoryActivityViewModel;
import com.example.farm_commute.viewmodel.MyPageQnaActivityViewModel;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

/**
 * 마이페이지 액티비티는 메인 -> 햄버거 클릭시 접근합니다.
 * 로그인이 되어있지 않다면 (사용자 번호가 없다면)
 * 화면 접근이 불가능 해야 합니다.
 */
public class MyPageQnaActivity extends CommonActivity {
    private ActivityMypageQnaBinding mBinding;

    private MyPageQnaActivityViewModel mViewModel;
    private LifecycleOwner mLifecycleOwner;

    CommuteListAdapter commuteListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_mypage_qna);
        mViewModel = ViewModelProviders.of(MyPageQnaActivity.this).get(MyPageQnaActivityViewModel.class);
        mLifecycleOwner = this;

        setNetwork();

        setupActionBar();
    }

    private void setNetwork() {


    }

    private void setupActionBar() {
        setCustomActionBarActivity(this);
        getCustomActionBar().setTitleHeader("문의 사항");
        getCustomActionBar().setLeftImage(R.drawable.ic_gnb_close);

        getCustomActionBar().setOnLeftButtonEvent(new CustomActionBarView.LeftEventListener() {
            @Override
            public void onLeftButtonEvent() {
                finish();
            }
        });

        getCustomActionBar().setRightImage2(R.drawable.send_icon);

        getCustomActionBar().setOnRightButtonEvent2(new CustomActionBarView.RightEventListener2() {

            @Override
            public void onRightButtonEvent2() {

        //통신

                mViewModel.setQna(GLEnvironments.사용자번호,mBinding.activityQnaWriteEdittext.getText().toString());


                showDialogUpdate();

            }
        });
        getCustomActionBar().setRightImageVisibility(false);
    }


    private void showDialogUpdate() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_qna);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.inset_dialog);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        Window window = dialog.getWindow();
        window.setAttributes(layoutParams);

        MaterialButton materialButton = dialog.findViewById(R.id.close_button);
        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();

                finish();
            }
        });


        dialog.show();
    }

}
