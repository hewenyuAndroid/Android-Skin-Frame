package com.hwy.skin.attr;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hwy.skin.SkinManager;
import com.hwy.skin.SkinResource;

/**
 * 作者: hewenyu
 * 日期: 2019/2/19 12:48
 * 说明: 通用的皮肤属性解析,自定义属性解析时可以继承该类,也可以实现 ISkinType 接口
 */
public class SkinType implements ISkinType {

    private ExchangeAttr mExchangeAttr;

    @Override
    public void skin(View view, String resName) {
        if (mExchangeAttr == null) {
            return;
        }
        switch (mExchangeAttr) {
            case TEXT_COLOR:    // 设置文本颜色
                setTextColor(view, resName);
                break;
            case BACKGROUND:    // 设置背景颜色
                setBackground(view, resName);
                break;
            case SRC:   // 设置图片资源
                setSrc(view, resName);
                break;
        }
    }

    @Override
    public boolean isSkinType(String attrName) {
        ExchangeAttr[] exchangeAttrs = ExchangeAttr.values();
        for (ExchangeAttr exchangeAttr : exchangeAttrs) {
            if (exchangeAttr.mAttr.equals(attrName)) {
                mExchangeAttr = exchangeAttr;
                return true;
            }
        }
        return false;
    }

    protected SkinResource getSkinResource() {
        return SkinManager.getInstance().getSkinResource();
    }

    enum ExchangeAttr {
        TEXT_COLOR("textColor"),
        BACKGROUND("background"),
        SRC("src");

        private String mAttr;

        ExchangeAttr(String attr) {
            this.mAttr = attr;
        }

    }

    // region ------------ 设置属性 ------------

    /**
     * 设置图片资源
     *
     * @param view
     * @param resName
     */
    protected void setSrc(View view, String resName) {
        Drawable drawable = getSkinResource().getDrawableByName(resName);
        if (drawable != null && view instanceof ImageView) {
            ((ImageView) view).setImageDrawable(drawable);
        }
    }

    /**
     * 设置背景
     *
     * @param view
     * @param resName
     */
    protected void setBackground(View view, String resName) {
        Drawable drawable = getSkinResource().getDrawableByName(resName);
        if (drawable != null) {
            view.setBackgroundDrawable(drawable);
            return;
        }
        ColorStateList color = getSkinResource().getColorByName(resName);
        if (color != null) {
            view.setBackgroundColor(color.getDefaultColor());
        }
    }

    /**
     * 设置文本颜色
     *
     * @param view
     * @param resName
     */
    protected void setTextColor(View view, String resName) {
        ColorStateList color = getSkinResource().getColorByName(resName);
        if (color != null && view instanceof TextView) {
            ((TextView) view).setTextColor(color);
        }
    }

    // endregion ------------------------------------
}
