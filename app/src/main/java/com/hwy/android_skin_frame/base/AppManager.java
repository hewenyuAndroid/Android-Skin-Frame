package com.hwy.android_skin_frame.base;

import android.app.Activity;

import java.util.Stack;

/**
 * 作者: hewenyu
 * 日期: 2019/2/19 14:42
 * 说明:
 */
public class AppManager {

    private volatile static AppManager mInstance = null;

    private Stack<Activity> mActivitys;

    private AppManager() {
        mActivitys = new Stack<>();
    }

    public static AppManager getInstance() {
        if (mInstance == null) {
            synchronized (AppManager.class) {
                if (mInstance == null) {
                    mInstance = new AppManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 新增一个Activity
     *
     * @param activity
     */
    public void attachActivity(Activity activity) {
        if (activity != null) {
            mActivitys.add(activity);
        }
    }

    /**
     * 移除一个Activity对象
     *
     * @param activity
     */
    public void detachActivity(Activity activity) {
        if (activity != null && mActivitys.contains(activity)) {
            mActivitys.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束栈顶的Activity
     */
    public void detachLastActivity() {
        if (mActivitys.isEmpty()) {
            return;
        }
        detachActivity(mActivitys.lastElement());
    }

    /**
     * 根据 Class 对象结束单个 Activity
     *
     * @param activityClass
     */
    public void detachActivity(Class<?> activityClass) {
        for (int i = mActivitys.size() - 1; i >= 0; i--) {
            Activity activity = mActivitys.get(i);
            if (activity.getClass().getCanonicalName()
                    .equals(activityClass.getCanonicalName())) {
                detachActivity(activity);
                break;
            }
        }
    }

    /**
     * 根据 Class 对象结束一类 Activity
     *
     * @param activityClass
     */
    public void detachActivitys(Class<?> activityClass) {
        for (int i = mActivitys.size() - 1; i >= 0; i--) {
            Activity activity = mActivitys.get(i);
            if (activity.getClass().getCanonicalName()
                    .equals(activityClass.getCanonicalName())) {
                detachActivity(activity);
            }
        }
    }

    /**
     * 移除所有的Activity
     */
    public void detachAllActivity() {
        for (int i = mActivitys.size() - 1; i >= 0; i--) {
            Activity activity = mActivitys.get(i);
            detachActivity(activity);
        }
    }

    /**
     * 获取栈顶的Activity
     *
     * @return
     */
    public Activity getLastActivity() {
        return mActivitys.lastElement();
    }

    /**
     * 获取指定类型的Activity
     *
     * @param activityClass
     * @return
     */
    public Activity getActivity(Class<?> activityClass) {
        for (Activity activity : mActivitys) {
            if (activity.getClass().getName().equals(activityClass.getName())) {
                return activity;
            }
        }
        return null;
    }

    /**
     * 获取Activity栈的大小
     *
     * @return
     */
    public int getSize() {
        return mActivitys.size();
    }

}
