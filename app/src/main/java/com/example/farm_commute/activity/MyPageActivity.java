package com.example.farm_commute.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.farm_commute.R;
import com.example.farm_commute.common.CommonActivity;
import com.example.farm_commute.common.OnSingleClickListener;
import com.example.farm_commute.custom.CustomActionBarView;
import com.example.farm_commute.databinding.ActivityMypageBinding;
import com.example.farm_commute.utils.GLEnvironments;
import com.example.farm_commute.utils.GLUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 마이페이지 액티비티는 메인 -> 햄버거 클릭시 접근합니다.
 * 로그인이 되어있지 않다면 (사용자 번호가 없다면)
 * 화면 접근이 불가능 해야 합니다.
 */
public class MyPageActivity extends CommonActivity {
    private ActivityMypageBinding mBinding;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_mypage);


        setCustomActionBarActivity(this);
        getCustomActionBar().setLeftImage(R.drawable.ic_gnb_close);

        getCustomActionBar().setOnLeftButtonEvent(new CustomActionBarView.LeftEventListener() {
            @Override
            public void onLeftButtonEvent() {
                finish();
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
            }
        });




        mBinding.edittextNickname.setText(GLEnvironments.사용자닉네임);


        if(GLEnvironments.사용자프로필유알엘.equals(""))
        {
            Glide.with(this).load("https://www.pngitem.com/pimgs/m/146-1468479_my-profile-icon-blank-profile-picture-circle-hd.png").apply(new RequestOptions().circleCrop()).into(mBinding.imageviewMypageProfile);
        }else
        {
            Glide.with(this).load(GLEnvironments.사용자프로필유알엘).apply(new RequestOptions().circleCrop()).into(mBinding.imageviewMypageProfile);
        }



        mBinding.viewMyHistory.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MyPageCommuteHistoryActivity.class);
                startActivity(intent);
            }
        });

        mBinding.viewMyQnA.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyPageQnaActivity.class);
                startActivity(intent);
            }
        });


        mBinding.viewMyLogout.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {

                //logout

                // Activity가 종료되기 전에 저장한다.
                //SharedPreferences를 sFile이름, 기본모드로 설정
                SharedPreferences sharedPreferences = getSharedPreferences("Login",MODE_PRIVATE);

                //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("companyID",""); // key, value를 이용하여 저장하는 형태
                editor.putString("password",""); // key, value를 이용하여 저장하는 형태
                //최종 커밋
                editor.commit();

                Toast.makeText(getApplicationContext(), "정상적으로 로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();

                finishAffinity();
                System.runFinalization();
                System.exit(0);
            }
        });

 /*       Glide.with(this).load(GLEnvironments.사용자프로필유알엘).apply(RequestOptions.circleCropTransform()).into(mBinding.imageviewMypageProfile);
        mBinding.edittextNickname.setText(GLEnvironments.사용자닉네임);
        mFirstNickName = GLEnvironments.사용자닉네임;

        setEditingStatusName(false);*/
/*

        //내가쓴레시피
        mBinding.viewMyReceipe.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                Intent intent = new Intent(MyPageActivity.this, MyPostActivity.class);
                intent.putExtra("postType", "recipe");
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
            }
        });
*/

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
    }


}
