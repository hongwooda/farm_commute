package com.example.farm_commute.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.farm_commute.R;
import com.example.farm_commute.common.CommonActivity;
import com.example.farm_commute.common.OnSingleClickListener;
import com.example.farm_commute.databinding.ActivityLoginBinding;
import com.example.farm_commute.databinding.ActivitySplashBinding;
import com.example.farm_commute.model.LoginResponse;
import com.example.farm_commute.utils.GLEnvironments;
import com.example.farm_commute.viewmodel.LoginActivityViewModel;
import com.example.farm_commute.viewmodel.MainActivityViewModel;

import java.util.Iterator;


public class LoginActivity extends CommonActivity {
    private ActivityLoginBinding mBinding;
    private LoginActivityViewModel mViewModel;
    private LifecycleOwner mLifecycleOwner;

    String companyID;
    String password;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        mViewModel = ViewModelProviders.of(LoginActivity.this).get(LoginActivityViewModel.class);

        mLifecycleOwner = this;

        Intent intent = getIntent(); /*데이터 수신*/

        companyID = intent.getExtras().getString("companyID"); /*String형*/
        password = intent.getExtras().getString("password");


        Log.e("companyID : ",companyID);
        Log.e("password : ",password);


        if(companyID.length() != 0)
        {
            mViewModel.LoginRequest(companyID, password);
            mViewModel.getMutableLoginData().observe(mLifecycleOwner, new Observer<LoginResponse>() {
                @Override
                public void onChanged(LoginResponse getLoginResponse) {


                    //   Log.e("result : ",getLoginResponse.getResult());
                    //   getLoginResponse.getResult();


                    try {

                        if (getLoginResponse.getResult().equals("true")) {

                            startActivity(new Intent(getApplication(), MainActivity.class)); //로딩이 끝난 후, ChoiceFunction 이동
                            finish();
                            overridePendingTransition(R.anim.anim_fade_in, R.anim.anim_fade_out);

                            Toast.makeText(getApplicationContext(), password + "님 로그인 되었습니다.", Toast.LENGTH_SHORT).show();

                            GLEnvironments.사용자번호 = companyID;



                        } else if (getLoginResponse.getResult().equals("false")) {
                            Toast.makeText(getApplicationContext(), "계정/비밀번호를 다시 확인해주세요.", Toast.LENGTH_SHORT).show();
                        }

                    } catch (NullPointerException ex) {
                        Toast.makeText(getApplicationContext(), "통신 오류 입니다.", Toast.LENGTH_SHORT).show();
                    }


                }

            });
        }


        setNetwork();

    }

    private void setNetwork() {

        mBinding.loginButton.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {


                //    mViewModel.LoginRequest(mBinding.loginCompanyIDEditText.getText().toString(), mBinding.loginPasswordEditText.getText().toString());
                //   mViewModel.LoginRequest("2200144", "woody");

                mViewModel.LoginRequest(mBinding.loginCompanyIDEditText.getText().toString(), mBinding.loginPasswordEditText.getText().toString());
                mViewModel.getMutableLoginData().observe(mLifecycleOwner, new Observer<LoginResponse>() {
                    @Override
                    public void onChanged(LoginResponse getLoginResponse) {


                        //   Log.e("result : ",getLoginResponse.getResult());
                        //   getLoginResponse.getResult();


                        try {

                            if (getLoginResponse.getResult().equals("true")) {

                                startActivity(new Intent(getApplication(), MainActivity.class)); //로딩이 끝난 후, ChoiceFunction 이동
                                finish();
                                overridePendingTransition(R.anim.anim_fade_in, R.anim.anim_fade_out);

                                Toast.makeText(getApplicationContext(), mBinding.loginCompanyIDEditText.getText().toString() + "님 로그인 되었습니다.", Toast.LENGTH_SHORT).show();

                                GLEnvironments.사용자번호 = mBinding.loginCompanyIDEditText.getText().toString();

                                // Activity가 종료되기 전에 저장한다.
                                //SharedPreferences를 sFile이름, 기본모드로 설정
                                SharedPreferences sharedPreferences = getSharedPreferences("Login",MODE_PRIVATE);

                                //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("companyID",mBinding.loginCompanyIDEditText.getText().toString()); // key, value를 이용하여 저장하는 형태
                                editor.putString("password",mBinding.loginPasswordEditText.getText().toString()); // key, value를 이용하여 저장하는 형태
                                //최종 커밋
                                editor.commit();



                            } else if (getLoginResponse.getResult().equals("false")) {
                                Toast.makeText(getApplicationContext(), "계정/비밀번호를 다시 확인해주세요.", Toast.LENGTH_SHORT).show();
                            }

                        } catch (NullPointerException ex) {
                            Toast.makeText(getApplicationContext(), "통신 오류 입니다.", Toast.LENGTH_SHORT).show();
                        }


                    }

                });
            }
        });


    }

}
