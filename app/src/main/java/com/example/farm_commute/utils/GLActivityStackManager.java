package com.example.farm_commute.utils;

import com.example.farm_commute.common.CommonActivity;

import java.util.ArrayList;
import java.util.List;

public class GLActivityStackManager {
    private static List<CommonActivity> mActivityList = new ArrayList<CommonActivity>();

    /**
     * Activity 추가
     *
     * @param activity
     */
    public static void add(CommonActivity activity) {
        mActivityList.add(activity);
    }

    /**
     * Activity 삭제
     *
     * @param activity
     */
    public static void remove(CommonActivity activity) {
        mActivityList.remove(activity);
    }

    /**
     * 가장 최근에 추가된 Activity정보 획득
     *
     * @return
     */
    public static CommonActivity getRecently() {
        return mActivityList.get(mActivityList.size() - 1);
    }

    /**
     * 전체 Activity 종료
     */
    public static void finishApp() {
        for (CommonActivity activity : mActivityList) {
            activity.finish();
        }
        mActivityList.clear();
////            ActivityCompat.finishAffinity(getRecently());
//        XCApplication.getInstance().onFinishApp();
////            System.runFinalizersOnExit(true);
////            System.exit(0);
    }
}
