package com.example.farm_commute.common;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LifecycleOwner;

import com.example.farm_commute.custom.CustomActionBarView;
import com.example.farm_commute.utils.GLLog;


public class CommonActivity extends AppCompatActivity implements LifecycleOwner {

    private CustomActionBarView mCustomActionBarView = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GLLog.i(this, "**********     START ACTIVITY     **********");
    //    GLActivityStackManager.add(this);


        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GLLog.i(this, "**********     FINISH ACTIVITY     **********");
    //    GLActivityStackManager.remove(this);
    }


    public void setCustomActionBarActivity(Activity activity) {
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setElevation(0);

        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT);
        getSupportActionBar().setCustomView(getCustomActionBar(), layoutParams);

        Toolbar toolbar = (Toolbar) getCustomActionBar().getParent();
        toolbar.setContentInsetsAbsolute(0, 0);
        toolbar.getContentInsetEnd();
        toolbar.setPadding(0, 0, 0, 0);
    }

    public CustomActionBarView getCustomActionBar() {
        if (mCustomActionBarView == null) {
            mCustomActionBarView = new CustomActionBarView(this);
        }
        return mCustomActionBarView;
    }

}