package com.hwy.android_skin_frame.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.hwy.skin.SkinManager;
import com.hwy.skin.SkinResource;
import com.hwy.skin.attr.SkinAttr;
import com.hwy.skin.attr.SkinAttrHolder;
import com.hwy.skin.callback.OnSkinChangeCallback;
import com.hwy.skin.support.SkinAttrSupport;
import com.hwy.skin.support.SkinCompat;

import java.util.List;

/**
 * 作者: hewenyu
 * 日期: 2019/2/19 16:13
 * 说明:
 */
public abstract class SkinActivity extends AppCompatActivity implements OnSkinChangeCallback {

    protected SkinCompat mSkinCompat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        LayoutInflaterCompat.setFactory2(layoutInflater, this);
        mSkinCompat = new SkinCompat(this, getWindow());
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        // 拦截View的创建
        View view = mSkinCompat.createView(parent, name, context, attrs);
        // 解析View声明的属性
        if (view != null) {
            // 获取当前View可以换肤的属性列表
            List<SkinAttr> skinAttrs = SkinAttrSupport.getSkinAttr(context, attrs);
            // 将获取到的属性和对应的 View 封装
            SkinAttrHolder skinAttrHolder = new SkinAttrHolder(view, skinAttrs);
            // 统一交给 SkinManager 管理
            mSkinCompat.managerSkinView(this, skinAttrHolder);
            // 判断是否需要换肤
            SkinManager.getInstance().checkSkin(this, view, skinAttrHolder);
        }
        return view;
    }

    /**
     * 扩展继承自当前Activity页面的换肤
     *
     * @param view
     * @param skinResource
     */
    @Override
    public void onSkinChange(View view, SkinResource skinResource) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSkinCompat.release(this);
    }

}
