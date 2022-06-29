package com.vcyber.myframe.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.vcyber.myframe.R;

/**
 * description ：
 * author : zjl
 * date : 9/29/21
 */
public class JigsawView extends View {
    private Paint paint;
    private Path path;
    private Bitmap bitmap;
    private int measuredWidth;

    public JigsawView(Context context) {
        super(context);
    }

    public JigsawView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public JigsawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        init();
    }

    private void init(int widthSpecSize) {
        paint = new Paint();
        paint.setColor(Color.parseColor("#ffffff"));
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(2);
        path = new Path();
        bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.icon_cover), widthSpecSize, widthSpecSize, true);
    }

    private boolean isMeasure;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        measuredWidth = widthSpecSize / 2;
        if (!isMeasure && measuredWidth > 0) {
            isMeasure = true;
            init(widthSpecSize);
        }
        setMeasuredDimension(widthSpecSize, widthSpecSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(bitmap, 0, 0, paint);
        path.moveTo(100, 100);
        path.lineTo(measuredWidth, 0);
        path.lineTo(0, measuredWidth * 5 / 16);
        path.close();
//        paint.setAlpha(153);
//        ColorMatrix cm = new ColorMatrix();
//        cm.setSaturation(0f); // 设置饱和度:0为纯黑白，饱和度为0；1为饱和度为100，即原图；
//        ColorMatrixColorFilter grayColorFilter = new ColorMatrixColorFilter(cm);
//        paint.setColorFilter(grayColorFilter);
        canvas.drawPath(path, paint);
    }
}