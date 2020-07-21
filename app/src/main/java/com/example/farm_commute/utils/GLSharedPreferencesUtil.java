package com.example.farm_commute.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 안드로이드 Preferences 유틸
 */
public class GLSharedPreferencesUtil {
    public static void putSharedPreference(Context context, String key, List<String> values) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        JSONArray a = new JSONArray();
        for (int i = 0; i < values.size(); i++) {
            a.put(values.get(i));
        }
        if (!values.isEmpty()) {
            editor.putString(key, a.toString());
        } else {
            editor.putString(key, null);
        }
        editor.commit();
    }

    public static List<String> getListSharedPreference(Context context, String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String json = prefs.getString(key, null);
        List<String> urls = new ArrayList<String>();
        if (json != null) {
            try {
                JSONArray a = new JSONArray(json);
                for (int i = 0; i < a.length(); i++) {
                    String url = a.optString(i);
                    urls.add(url);
                }
            } catch (JSONException e) {
                GLLog.e(e.toString());
            }
        }
        return urls;
    }

    /**
     * String형태의 preference 저장
     *
     * @param context
     * @param key
     * @param value
     */
    public static void putSharedPreference(Context context, String key, String value) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        if (value == null) value = "";

        editor.putString(key, value);
        editor.commit();
    }

    /**
     * boolean형태의 preference 저장
     *
     * @param context
     * @param key
     * @param value
     */
    public static void putSharedPreference(Context context, String key, boolean value) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * int형태의 preference 저장
     *
     * @param context
     * @param key
     * @param value
     */
    public static void putSharedPreference(Context context, String key, int value) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * long형태의 preference 저장
     *
     * @param context
     * @param key
     * @param value
     */
    public static void putSharedPreference(Context context, String key, long value) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putLong(key, value);
        editor.commit();
    }

    private final static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * Date형태의 preference 저장
     *
     * @param context
     * @param key
     * @param value
     */
    public static void putSharedPreference(Context context, String key, Date value) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

        editor.putString(key, sdf.format(value));
        editor.commit();
    }

    /**
     * String형태의 preference 정보 획득
     *
     * @param context
     * @param key
     * @return
     */
    public static String getSharedPreference(Context context, String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(key, null);
    }

    /**
     * String형태의 preference 정보 획득
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static String getSharedPreference(Context context, String key, String defValue) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(key, defValue);
    }

    /**
     * Boolean형태의 preference 정보 획득
     *
     * @param context
     * @param key
     * @param defaultValue 해당 값이 없을 때 리턴되는 기본값
     * @return
     */
    public static boolean getBooleanSharedPreference(Context context, String key, boolean defaultValue) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

        return prefs.getBoolean(key, defaultValue);
    }

    /**
     * int형태의 preference 정보 획득
     *
     * @param context
     * @param key
     * @return
     */
    public static int getIntSharedPreference(Context context, String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

        return prefs.getInt(key, 0);
    }

    /**
     * int형태의 preference 정보 획득
     *
     * @param context
     * @param key
     * @param defValue 해당 값이 없을 때 리턴되는 기본값
     * @return
     */
    public static int getIntSharedPreference(Context context, String key, int defValue) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

        return prefs.getInt(key, defValue);
    }

    /**
     * long형태의 preference 정보 획득
     *
     * @param context
     * @param key
     * @return
     */
    public static long getLongSharedPreference(Context context, String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

        return prefs.getLong(key, 0);
    }

    /**
     * boolean형태의 preference 정보 획득
     *
     * @param context
     * @param key
     * @return
     */
    public static Date getDateSharedPreference(Context context, String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

        String value = prefs.getString(key, null);
        try {
            return value == null ? null : new SimpleDateFormat(DATE_FORMAT).parse(value);
        } catch (ParseException e) {
            GLLog.e(e.toString());
            return null;
        }
    }

    /**
     * preference정보에 해당 key의 값이 존재하는 지 여부 획득
     *
     * @param context
     * @param key
     * @return
     */
    public static boolean contains(Context context, String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

        return prefs.contains(key);
    }
}
