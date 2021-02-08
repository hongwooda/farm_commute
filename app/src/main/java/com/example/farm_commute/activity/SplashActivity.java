package com.example.farm_commute.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.farm_commute.R;
import com.example.farm_commute.common.CommonActivity;
import com.example.farm_commute.common.GlobalApplication;
import com.example.farm_commute.databinding.ActivitySplashBinding;

/**
 * 스플래시 액티비티는, 메니페스트에서 최초 런치 화면으로 설정이 되어야 합니다.
 * Preference 변수를 조회하여,
 * 카카오톡 로그인 토큰이 있을 경우 카톡 로그인을,
 * 페북 로그인 토큰이 있을 경우 페북 로그인을, 마지막으로
 * 아무것도 없을 경우 로그인을 시키지 않고 액티비티를 종료합니다.
 */

public class SplashActivity extends CommonActivity {
    private ActivitySplashBinding mBinding;
    // private SplashActivityViewModel mViewModel;
    String companyID="";
    String password="";

    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash);

        //    mViewModel = ViewModelProviders.of(this).get(SplashActivityViewModel.class);

        Glide.with(this).load(R.drawable.logo2).into(mBinding.imageViewActSplash);

        Animation anima = AnimationUtils.loadAnimation(this, R.anim.alpha);

        mBinding.imageViewActSplash.startAnimation(anima);


        //저장된 값을 불러오기 위해 같은 네임파일을 찾음.
        SharedPreferences sf = getSharedPreferences("Login", MODE_PRIVATE);
        //text라는 key에 저장된 값이 있는지 확인. 아무값도 들어있지 않으면 ""를 반환
        companyID = sf.getString("companyID", "");
        password = sf.getString("password", "");

        runSplashDelay();

    }

    private void runSplashDelay() {
        Handler hd = new Handler();

        hd.postDelayed(new Runnable() {
            @Override
            public void run() {


                Intent intent = new Intent(getApplication(), LoginActivity.class);
                intent.putExtra("companyID", companyID);
                intent.putExtra("password", password);
                startActivity(intent);

                finish();

                overridePendingTransition(R.anim.anim_fade_in, R.anim.anim_fade_out);
            }
        }, 3000);
    }

}
