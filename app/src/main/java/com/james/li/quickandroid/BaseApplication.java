package com.james.li.quickandroid;

import android.app.Activity;
import android.app.Application;

import java.util.ArrayList;
import java.util.List;

/**
 * 主要实现完全退出app的内容
 * Created by lsy-android on 10/16/16 in zsl-tech.
 */

public class BaseApplication extends Application {

    private List<Activity> activities = new ArrayList<Activity>();

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        for (Activity activity : activities) {
            activity.finish();
        }

        // 释放其他资源的方法，需要自己编写
        //onDestroy();
        System.exit(0);
    }


}
