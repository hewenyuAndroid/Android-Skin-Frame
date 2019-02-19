package com.hwy.skin.attr;

import android.view.View;

import java.util.List;

/**
 * 作者: hewenyu
 * 日期: 2019/2/18 19:36
 * 说明: 用于封装View及其对应的换肤属性
 */
public class SkinAttrHolder {

    private View mView;

    /**
     * 当前控件需要换肤的属性列表
     */
    private List<SkinAttr> mSkinAttr;

    public SkinAttrHolder(View view, List<SkinAttr> skinAttrs) {
        this.mView = view;
        this.mSkinAttr = skinAttrs;
    }

    public void skin() {
        for (SkinAttr attr : mSkinAttr) {
            attr.skin(mView);
        }
    }

    public View getView() {
        return mView;
    }

}
