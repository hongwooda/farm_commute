package com.example.farm_commute.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.farm_commute.R;
import com.example.farm_commute.common.CommonActivity;
import com.example.farm_commute.common.OnSingleClickListener;
import com.example.farm_commute.custom.CustomActionBarView;
import com.example.farm_commute.custom.CustomTimePickerDialog;
import com.example.farm_commute.databinding.ActivityMainBinding;
import com.example.farm_commute.model.LoginResponse;
import com.example.farm_commute.model.QuotesResponse;
import com.example.farm_commute.model.UserDataListResponse;
import com.example.farm_commute.model.UserInfoResponse;
import com.example.farm_commute.utils.GLEnvironments;
import com.example.farm_commute.viewmodel.MainActivityViewModel;
import com.google.android.material.button.MaterialButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import javax.security.auth.login.LoginException;

public class MainActivity extends CommonActivity {


    public ActivityMainBinding mBinding;

    private MainActivityViewModel mViewModel;

    final Calendar cal = Calendar.getInstance();

    private LifecycleOwner mLifecycleOwner;

    Activity mActivity;

    int year = cal.get(Calendar.YEAR);
    int month = cal.get(Calendar.MONTH);
    int day = cal.get(Calendar.DATE);

    String Todaydata = "";

    boolean checkbox_flag = false;

    int pick_i = 0;

    private ArrayList<UserDataListResponse.Result> UserDataList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mViewModel = ViewModelProviders.of(MainActivity.this).get(MainActivityViewModel.class);


        mLifecycleOwner = this;
        mActivity = this;

        setupActionBar();

        //   mBinding.mainGoodsayingLayout.setBackgroundResource(R.drawable.goodsaying1);

        Date currentTime = Calendar.getInstance().getTime();

        String date_text = new SimpleDateFormat("MM월 dd일", Locale.getDefault()).format(currentTime);


        SimpleDateFormat simpledateformat = new SimpleDateFormat("EEEE");
        Date date = new Date(year, month, day - 1);
        String dayOfWeek = simpledateformat.format(date);

        checkbox_flag = false;


        //  Toast.makeText(getApplicationContext(), year + "년" + month + "월" + day +"일", Toast.LENGTH_SHORT).show();


        //  Log.e("date",year+"-"+(month+1)+"-"+day);

        Todaydata = year + "-" + (month + 1) + "-" + day;

        GLEnvironments.오늘날짜 = Todaydata;

        mBinding.mainDateTextview.setText(date_text + " " + dayOfWeek);

        setFunction();


        setNetwork();

    }

    private void setNetwork() {

        mViewModel.getUserInfo(GLEnvironments.사용자번호);
        mViewModel.getMutableUserInfoData().observe(mLifecycleOwner, new Observer<UserInfoResponse>() {
            @Override
            public void onChanged(UserInfoResponse userInfoResponse) {


                GLEnvironments.사용자닉네임 = userInfoResponse.getNickname();
                GLEnvironments.사용자프로필유알엘 = userInfoResponse.getProfileURL();

                mViewModel.getUserDataPickList(GLEnvironments.사용자번호, Todaydata, "");
                mViewModel.getMutableUserDataPickListData().observe(mLifecycleOwner, new Observer<UserDataListResponse>() {
                    @Override
                    public void onChanged(UserDataListResponse userDataListResponse) {


                      /*  try {

                            UserDataList.clear();
                            UserDataList.addAll(userDataListResponse.getResult());


                            String onworkTime = UserDataList.get(0).onworkTime;
                            String offworkTime = UserDataList.get(0).offworkTime;

                            onworkTime = onworkTime.replace("-", " : ");
                            offworkTime = offworkTime.replace("-", " : ");

                            mBinding.onworkTextview.setText(onworkTime);
                            mBinding.offworkTextview.setText(offworkTime);

                            if (UserDataList.get(0).holidayYn.equals("Y")) {
                                mBinding.hoildayCheckbox.setChecked(true);
                            } else if (UserDataList.get(0).holidayYn.equals("N")) {
                                mBinding.hoildayCheckbox.setChecked(false);
                            }


                        } catch (NullPointerException ex) {
                            Toast.makeText(getApplicationContext(), "입력된 날짜가 없습니다. 날짜를 입력해주세요.", Toast.LENGTH_SHORT).show();
                            mBinding.onworkTextview.setText("00 : 00");
                            mBinding.offworkTextview.setText("00 : 00");

                        }*/

                        UserDataList.clear();
                        UserDataList.addAll(userDataListResponse.getResult());


                        if (UserDataList.size() == 0) {
                            Toast.makeText(getApplicationContext(), "입력된 시간이 없습니다. 시간을 입력해주세요.", Toast.LENGTH_SHORT).show();
                            mBinding.onworkTextview.setText("00 : 00");
                            mBinding.offworkTextview.setText("00 : 00");

                        } else {


                            String onworkTime = UserDataList.get(0).onworkTime;
                            String offworkTime = UserDataList.get(0).offworkTime;

                            onworkTime = onworkTime.replace("-", " : ");
                            offworkTime = offworkTime.replace("-", " : ");

                            mBinding.onworkTextview.setText(onworkTime);
                            mBinding.offworkTextview.setText(offworkTime);

                            if (UserDataList.get(0).holidayYn.equals("Y")) {
                                mBinding.hoildayCheckbox.setChecked(true);
                            } else if (UserDataList.get(0).holidayYn.equals("N")) {
                                mBinding.hoildayCheckbox.setChecked(false);
                            }


                        }

                        checkbox_flag = true;

                        mViewModel.getQuotes(Todaydata);
                        mViewModel.getMutablegetQuotesData().observe(mLifecycleOwner, new Observer<QuotesResponse>() {
                            @Override
                            public void onChanged(QuotesResponse quotesResponse) {


                                try {
                                    Random rnd = new Random();
                                    int num = rnd.nextInt(6);

                                    switch (num) {
                                        case 0:
                                            Glide.with(mActivity).load(R.drawable.goodsaying1).into(mBinding.mainGoodsayingImageview);
                                            break;
                                        case 1:
                                            Glide.with(mActivity).load(R.drawable.goodsaying2).into(mBinding.mainGoodsayingImageview);
                                            break;
                                        case 2:
                                            Glide.with(mActivity).load(R.drawable.goodsaying3).into(mBinding.mainGoodsayingImageview);
                                            break;
                                        case 3:
                                            Glide.with(mActivity).load(R.drawable.goodsaying4).into(mBinding.mainGoodsayingImageview);
                                            break;
                                        case 4:
                                            Glide.with(mActivity).load(R.drawable.goodsaying5).into(mBinding.mainGoodsayingImageview);
                                            break;
                                        case 5:
                                            Glide.with(mActivity).load(R.drawable.goodsaying6).into(mBinding.mainGoodsayingImageview);
                                            break;
                                        case 6:
                                            Glide.with(mActivity).load(R.drawable.goodsaying7).into(mBinding.mainGoodsayingImageview);
                                            break;
                                        case 7:
                                            Glide.with(mActivity).load(R.drawable.goodsaying8).into(mBinding.mainGoodsayingImageview);
                                            break;
                                        case 8:
                                            Glide.with(mActivity).load(R.drawable.goodsaying9).into(mBinding.mainGoodsayingImageview);
                                            break;
                                        case 9:
                                            Glide.with(mActivity).load(R.drawable.goodsaying10).into(mBinding.mainGoodsayingImageview);
                                            break;

                                    }

                                    mBinding.mainGoodsayingImageview.setColorFilter(Color.parseColor("#BDBDBD"), PorterDuff.Mode.MULTIPLY);

                                    if (quotesResponse.getQuote().length() == 0) {
                                        mBinding.mainGoodsayingTextview.setText("아이디어 팜이여 영원하라!");
                                        mBinding.mainGoodsayingPersonTextview.setText("- simon -");
                                    } else {
                                        mBinding.mainGoodsayingTextview.setText(quotesResponse.getQuote());
                                        mBinding.mainGoodsayingPersonTextview.setText("- "+quotesResponse.getPerson()+" -");
                                    }


                                } catch (NullPointerException e) {
                                    //    Glide.with(mActivity).load(R.drawable.goodsaying3).into(mBinding.mainGoodsayingImageview);
                                    mBinding.mainGoodsayingImageview.setColorFilter(Color.parseColor("#BDBDBD"), PorterDuff.Mode.MULTIPLY);
                                    mBinding.mainGoodsayingTextview.setText("아이디어 팜이여 영원하라!");
                                    mBinding.mainGoodsayingPersonTextview.setText("- simon -");
                                }

                            }
                        });


                    }

                });

            }

        });

    }

    DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {

        @Override

        public void onDateSet(DatePicker view, int yearofyear, int monthOfYear, int dayOfMonth) {

            //     Toast.makeText(getApplicationContext(), year + "년" + monthOfYear + "월" + dayOfMonth +"일", Toast.LENGTH_SHORT).show();

            SimpleDateFormat simpledateformat = new SimpleDateFormat("EEEE");
            Date date = new Date(yearofyear, monthOfYear, dayOfMonth - 1);
            String dayOfWeek = simpledateformat.format(date);

            mBinding.mainDateTextview.setText((monthOfYear + 1) + "월 " + dayOfMonth + "일 " + dayOfWeek);

            mBinding.hoildayCheckbox.setChecked(false);

            year = yearofyear;
            month = monthOfYear;
            day = dayOfMonth;

            Todaydata = year + "-" + (month + 1) + "-" + day;

            mViewModel.getUserDataPickList(GLEnvironments.사용자번호, Todaydata, "");


            //     Toast.makeText(getApplicationContext(), yearofyear + "년" + monthOfYear + "월" + dayOfMonth +"일", Toast.LENGTH_SHORT).show();


        }

    };


    private void setFunction() {

        mBinding.mainDateTextview.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {


                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, AlertDialog.THEME_DEVICE_DEFAULT_DARK, listener, year, month, day);
                dialog.show();

                checkbox_flag = false;

            }
        });


        mBinding.hoildayCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if (checkbox_flag == true) {
                    if (isChecked) {  // 체크박스 안한 경우

                        Log.e("check", "checked");

                        mViewModel.setHoliday(GLEnvironments.사용자번호, Todaydata, "Y");

                        mBinding.onworkTextview.setText("8 : 30");
                        mBinding.offworkTextview.setText("18 : 00");
                    } else {
                        Log.e("uncheck", "unchecked");
                        mViewModel.setHoliday(GLEnvironments.사용자번호, Todaydata, "N");
                    }
                }

            }
        });


        mBinding.onworkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CustomTimePickerDialog dialog = new CustomTimePickerDialog(MainActivity.this, android.R.style.Theme_Holo_Light_Dialog, new CustomTimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int min) {

                        String msg = String.format("%d 시 %d 분", hour, min);

                        if (min == 0) {
                            mBinding.onworkTextview.setText(hour + " : 00");

                        } else {
                            mBinding.onworkTextview.setText(hour + " : " + min);
                        }


                        if (pick_i == 0) {
                            pick_i++;

                        } else if (pick_i == 1) {

                            if (min == 0) {
                                mViewModel.setOnwork(GLEnvironments.사용자번호, Todaydata, hour + "", "00");
                            } else {
                                mViewModel.setOnwork(GLEnvironments.사용자번호, Todaydata, hour + "", min + "");
                            }

                            Toast.makeText(MainActivity.this, msg + "출근이 입력 되었습니다.", Toast.LENGTH_SHORT).show();
                        }


                    }
                }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true);  //마지막 boolean 값은 시간을 24시간으로 보일지 아닐지

                dialog.show();

                pick_i = 0;
            }
        });

        mBinding.offworkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (mBinding.onworkTextview.getText().equals("00 : 00")) {

                    Toast.makeText(getApplicationContext(), "출근 부터 입력 해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    CustomTimePickerDialog dialog = new CustomTimePickerDialog(MainActivity.this, android.R.style.Theme_Holo_Light_Dialog, new CustomTimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int hour, int min) {

                            String msg = String.format("%d 시 %d 분", hour, min);

                            if (min == 0) {
                                mBinding.offworkTextview.setText(hour + " : 00");
                            } else {
                                mBinding.offworkTextview.setText(hour + " : " + min);
                            }


                            if (pick_i == 0) {
                                pick_i++;

                            } else if (pick_i == 1) {
                                //    Log.e("mse",msg);

                                if (min == 0) {
                                    mViewModel.setOffwork(GLEnvironments.사용자번호, Todaydata, hour + "", "00");
                                } else {
                                    mViewModel.setOffwork(GLEnvironments.사용자번호, Todaydata, hour + "", min + "");
                                }
                                Toast.makeText(MainActivity.this, msg + "퇴근이 입력 되었습니다.", Toast.LENGTH_SHORT).show();

                                showDialogUpdate();
                            }


                        }
                    }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true);  //마지막 boolean 값은 시간을 24시간으로 보일지 아닐지

                    pick_i = 0;

                    dialog.show();
                }


            }
        });

    }

    private void showDialogUpdate() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_update);
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
            }
        });


        dialog.show();
    }


    private void setupActionBar() {
        setCustomActionBarActivity(this);
        getCustomActionBar().setTitleHeader("Idea farm");
        getCustomActionBar().setLeftImage(R.drawable.ic_gnb_drawer_on);
        getCustomActionBar().setOnLeftButtonEvent(new CustomActionBarView.LeftEventListener() {
            @Override
            public void onLeftButtonEvent() {
                Intent intent = new Intent(getApplicationContext(), MyPageActivity.class);

                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
            }
        });

        getCustomActionBar().setRightImage2(R.drawable.schedule);

        getCustomActionBar().setOnRightButtonEvent2(new CustomActionBarView.RightEventListener2() {

            @Override
            public void onRightButtonEvent2() {

                Intent intent = new Intent(getApplicationContext(), WeekListActivity.class);
                startActivity(intent);

            }
        });

        getCustomActionBar().setTitleLayoutColor(Color.parseColor("#ffffff"));

        getCustomActionBar().setRightImageVisibility(false);


    }
}