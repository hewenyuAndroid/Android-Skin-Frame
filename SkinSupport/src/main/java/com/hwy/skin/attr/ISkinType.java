package com.hwy.skin.attr;

import android.view.View;

/**
 * 作者: hewenyu
 * 日期: 2019/2/19 11:59
 * 说明: 换肤属性接口,用于扩展通用的自定义属性,参考SkinType
 */
public interface ISkinType {

    /**
     * 换肤
     *
     * @param view
     * @param resName View的属性值ID对应的名称
     */
    void skin(View view, String resName);

    /**
     * 判断是否是需要换肤的属性
     *
     * @param attrName View的属性的名称
     * @return
     */
    boolean isSkinType(String attrName);

}
