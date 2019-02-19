package com.hwy.android_skin_frame;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.hwy.android_skin_frame.base.AppManager;
import com.hwy.skin.SkinManager;

/**
 * 作者: hewenyu
 * 日期: 2019/2/19 14:41
 * 说明:
 */
public class SkinApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SkinManager.getInstance().init(this);

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                AppManager.getInstance().attachActivity(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                AppManager.getInstance().detachActivity(activity);
            }
        });

    }
}
