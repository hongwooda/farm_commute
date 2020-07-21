package com.example.farm_commute.utils;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public  class GLLog {

    public static GLLog d (AppCompatActivity activity, String msg) {

        if (GLEnvironments.isLogYn == true) {
            Log.d("GL-LOG DEBUG MSG : " + activity.getLocalClassName() + " ", msg);
        }

        return new GLLog();
    }

    public static GLLog d (String msg) {

        if (GLEnvironments.isLogYn == true) {
            Log.d("GL-LOG DEBUG MSG : ", msg);
        }

        return new GLLog();
    }

    public static GLLog i (AppCompatActivity activity, String msg) {

        if (GLEnvironments.isLogYn == true) {
            Log.i("GL-LOG INFO MSG : " + activity.getLocalClassName() + " ", msg);
        }

        return new GLLog();
    }

    public static GLLog i (String msg) {

        if (GLEnvironments.isLogYn == true) {
            Log.i("GL-LOG INFO MSG : ", msg);
        }

        return new GLLog();
    }

    public static GLLog e (AppCompatActivity activity, String msg) {

        if (GLEnvironments.isLogYn == true) {
            Log.e("GL-LOG ERROR MSG : " + activity.getLocalClassName() + " ", msg);
        }

        return new GLLog();
    }

    public static GLLog e (String msg) {

        if (GLEnvironments.isLogYn == true) {
            Log.e("GL-LOG ERROR MSG : ", msg);
        }

        return new GLLog();
    }
}
