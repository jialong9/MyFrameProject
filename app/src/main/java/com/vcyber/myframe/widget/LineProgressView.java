package com.vcyber.myframe.widget;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.vcyber.myframe.R;
import com.vcyber.myframe.utils.UIUtil;

/**
 * description ：
 * author : zjl
 * date : 2020-05-21
 */
public class LineProgressView extends View {
    private Paint bgPaint;
    private Paint progressPaint;
    private int bgColor;//进度条背景颜色
    private int progressColor;//进度条颜色
    private int barSize;//进度条高度
    private int progress;//进度

    public LineProgressView(Context context) {
        super(context);
    }

    public LineProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LineProgressView);
        bgColor = typedArray.getColor(R.styleable.LineProgressView_progressBgColor, Color.parseColor("#999999"));
        progressColor = typedArray.getColor(R.styleable.LineProgressView_progressColor, Color.parseColor("#999999"));
        barSize = typedArray.getDimensionPixelSize(R.styleable.LineProgressView_progressWidth, UIUtil.dip2px(context, 0));
        typedArray.recycle();
        init();
    }

    public LineProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        bgPaint = new Paint();
        bgPaint.setAntiAlias(true);
        bgPaint.setStrokeCap(Paint.Cap.ROUND);
        bgPaint.setStrokeWidth(barSize);
        bgPaint.setColor(bgColor);

        progressPaint = new Paint();
        progressPaint.setAntiAlias(true);
        progressPaint.setStrokeCap(Paint.Cap.ROUND);
        progressPaint.setColor(progressColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(barSize / 2, barSize / 2, getWidth() - barSize / 2, barSize / 2, bgPaint);
        if (progress > 0) {
            canvas.drawLine(barSize / 2, barSize / 2, progress * (getWidth() - barSize / 2) / 100, barSize / 2, progressPaint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(widthSpecSize, barSize);
    }

    public void setProgressColor(int progressColor) {
        this.progressColor = progressColor;
    }

    public void setProgress(int progress) {
//        this.progress = progress;
        progressPaint.setStrokeWidth(barSize);
        ValueAnimator animator = new ValueAnimator();
        animator.setDuration(1000);
        animator.setObjectValues(progress);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                LineProgressView.this.progress = (int)value;
                invalidate();
            }
        });
    }

    //view移除时
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
