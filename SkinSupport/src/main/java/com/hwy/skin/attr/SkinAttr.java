package com.hwy.skin.attr;

import android.view.View;

/**
 * 作者: hewenyu
 * 日期: 2019/2/18 19:35
 * 说明:
 */
public class SkinAttr {

    /**
     * 资源属性的名称
     */
    private String mResName;

    private ISkinType mSkinType;

    public SkinAttr(String resName, ISkinType skinType) {
        this.mResName = resName;
        this.mSkinType = skinType;
    }

    public void skin(View view) {
        mSkinType.skin(view, mResName);
    }

}
