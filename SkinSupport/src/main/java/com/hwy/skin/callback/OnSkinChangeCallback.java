package com.hwy.skin.callback;

import android.view.View;

import com.hwy.skin.SkinResource;

/**
 * 作者: hewenyu
 * 日期: 2019/2/19 10:00
 * 说明: 换肤回调,用于扩展自定义View的属性
 */
public interface OnSkinChangeCallback {

    /**
     * 换肤的回调,用于扩展自定义View的换肤功能
     *
     * @param view
     * @param skinResource
     */
    void onSkinChange(View view, SkinResource skinResource);

}
