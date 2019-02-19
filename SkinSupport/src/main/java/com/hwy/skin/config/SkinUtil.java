package com.hwy.skin.config;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * 作者: hewenyu
 * 日期: 2019/2/18 15:17
 * 说明:
 */
public class SkinUtil {

    private static volatile SkinUtil mInstance;

    private Context mContext;

    private SkinUtil(Context context) {
        mContext = context.getApplicationContext();
    }

    public static SkinUtil getInstance(Context context) {
        if (mInstance == null) {
            synchronized (SkinUtil.class) {
                if (mInstance == null) {
                    mInstance = new SkinUtil(context);
                }
            }
        }
        return mInstance;
    }

    /**
     * 缓存皮肤路径
     *
     * @param skinPath
     */
    public void updateSkinPath(String skinPath) {
        mContext.getSharedPreferences(SkinConfig.SKIN_INFO_NAME, Context.MODE_PRIVATE)
                .edit()
                .putString(SkinConfig.SKIN_PATH_NAME, skinPath)
                .commit();

    }

    /**
     * 清除缓存的皮肤路径
     */
    public void clearSkinPath() {
        updateSkinPath("");
    }

    /**
     * 获取皮肤路径
     *
     * @return
     */
    public String getSkinPath() {
        return mContext.getSharedPreferences(SkinConfig.SKIN_INFO_NAME, Context.MODE_PRIVATE)
                .getString(SkinConfig.SKIN_PATH_NAME, "");

    }

    /**
     * 获取目标文件的包名
     *
     * @param skinPath
     * @return
     */
    public String getPackageName(String skinPath) {
        return mContext.getPackageManager().getPackageArchiveInfo(skinPath, PackageManager.GET_ACTIVITIES).packageName;
    }

    /**
     * 获取当前安装APP的APK文件路径
     *
     * @return
     */
    public String getCurrApkPath() {
        return mContext.getPackageResourcePath();
    }

}
