package com.example.farm_commute.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;


import com.example.farm_commute.common.CommonActivity;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class GLUtils {

    public static final int TYPE_PREVIEW = 520;
    public static final int TYPE_DETAIL = 521;

    public static final int TYPE_EVENT = 522;
    public static final int TYPE_POST = 523;

    public static boolean isEmpty(String str) {
        return str == null || str.replaceAll("\\s", "").length() == 0;
    }

    public static void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

    public static float convertPixelsToDp(float px, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return dp;
    }

    public static int convertDpToPixels(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, GLActivityStackManager.getRecently().getApplicationContext().getResources().getDisplayMetrics());
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static void showKeyboard(CommonActivity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    public static void closeKeyboard(CommonActivity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    /**
     * 3자리 단위마다 콤마를 추가해 주는 함수.
     *
     * @param number 콤마를 추가하고 싶은 값.
     * @return 3자리 마다 콤마를 추가한 결과 값.
     */
    public static String addComma(int number) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        return decimalFormat.format(number);
    }

    public static int toInt(String value) {
        if (value.equals("")) {
            return 0;
        } else {
            value = value.replaceAll("\\.", "");
            return Integer.parseInt(value);
        }
    }



    public static String getRefinedTime(String date, int positionType) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.KOREA);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

            Date currentDate = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
            Date writtenDate = simpleDateFormat.parse(date);

            long timeDiff = (currentDate.getTime() - writtenDate.getTime()) / 1000;

            long hourDiff = timeDiff / 3600;

            timeDiff = timeDiff % 3600;
            long minDiff = timeDiff / 60;
            long secDiff = timeDiff % 60;

            if (hourDiff > 24 || positionType != TYPE_PREVIEW) {
                return new SimpleDateFormat("yyyy.MM.dd").format(writtenDate);
            } else if (hourDiff > 0) {
                return hourDiff + "시간 전";
            } else if (minDiff > 0) {
                return minDiff + "분 전";
            } else if (secDiff >= 0) {
                return secDiff + "초 전";
            } else {
                return "방금 전";
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isAvailablePeriod(String date) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.KOREA);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

            Date currentDate = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
            Date endDate = simpleDateFormat.parse(date);

            return currentDate.compareTo(endDate) != 1;

        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }
}
