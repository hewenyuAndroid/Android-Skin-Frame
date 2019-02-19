package com.hwy.android_skin_frame.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hwy.android_skin_frame.R;

/**
 * 作者: hewenyu
 * 日期: 2019/2/19 15:29
 * 说明:
 */
public class MyView extends View {

    private String mText = "自定义文本控件";

    private Paint mPaint;

    private int mCustomColor = Color.RED;

    private int mCustomSize = 12;

    private Rect mTextRect;

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyView);
        mCustomColor = array.getColor(R.styleable.MyView_customColor, mCustomColor);
        mCustomSize = array.getDimensionPixelSize(R.styleable.MyView_customSize, mCustomSize);
        array.recycle();

        mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(mCustomSize);
        mPaint.setColor(mCustomColor);
        mPaint.setTextAlign(Paint.Align.CENTER);

        mTextRect = new Rect();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        int cx = getMeasuredWidth() / 2;
        int baseLine = getMeasuredHeight() / 2 + getBaseLine();
        canvas.drawText(mText, cx, baseLine, mPaint);
    }

    private int getBaseLine() {
        Paint.FontMetrics metrics = mPaint.getFontMetrics();
        return (int) (Math.abs(metrics.ascent - metrics.descent) / 2);
    }

    private void calculateTextBody() {
        mPaint.getTextBounds(mText, 0, mText.length(), mTextRect);
    }

    public void setColor(int color) {
        mCustomColor = color;
        mPaint.setColor(color);
        invalidate();
    }

}
