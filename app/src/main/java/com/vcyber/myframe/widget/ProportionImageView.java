package com.vcyber.myframe.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.vcyber.myframe.R;

/**
 * description ：可以设置宽高比和圆角的ImageView
 * author : zjl
 * date : 2021-03-16
 */
public class ProportionImageView extends android.support.v7.widget.AppCompatImageView {

    private int proportionWidth;//宽比
    private int proportionHeight;//高比
    private RectF mRectF;
    private Path mPath;
    //利用clip剪切的四个角半径，八个数据分别代表左上角（x轴半径，y轴半径），右上角（**），右下角（**），左下角（**）
    private final float[] rids = new float[8];
    private PaintFlagsDrawFilter paintFlagsDrawFilter;

    public ProportionImageView(Context context) {
        super(context);
    }

    public ProportionImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProportionImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ProportionImageView, defStyleAttr, 0);
        proportionWidth = ta.getInteger(R.styleable.ProportionImageView_proportion_width, 1);
        proportionHeight = ta.getInteger(R.styleable.ProportionImageView_proportion_height, 1);
        //此处圆角半径为四个角的半径
        float mRadiusLeftTop = ta.getDimension(R.styleable.ProportionImageView_radius_left_top, 0);
        float mRadiusRightTop = ta.getDimension(R.styleable.ProportionImageView_radius_right_top, 0);
        float mRadiusLeftBottom = ta.getDimension(R.styleable.ProportionImageView_radius_left_bottom, 0);
        float mRadiusRightBottom = ta.getDimension(R.styleable.ProportionImageView_radius_right_bottom, 0);
        rids[0] = mRadiusLeftTop;
        rids[1] = mRadiusLeftTop;
        rids[2] = mRadiusRightTop;
        rids[3] = mRadiusRightTop;
        rids[4] = mRadiusRightBottom;
        rids[5] = mRadiusRightBottom;
        rids[6] = mRadiusLeftBottom;
        rids[7] = mRadiusLeftBottom;
        ta.recycle();
        mRectF = new RectF();
        mPath = new Path();
        paintFlagsDrawFilter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
        setLayerType(View.LAYER_TYPE_HARDWARE, null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecSize = widthSpecSize * proportionHeight / proportionWidth;
        setMeasuredDimension(widthSpecSize, heightSpecSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPath.reset();
        mPath.addRoundRect(mRectF, rids, Path.Direction.CW);
        canvas.setDrawFilter(paintFlagsDrawFilter);
        canvas.save();
        canvas.clipPath(mPath);
        super.onDraw(canvas);
        canvas.restore();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldWidth, int oldHeight) {
        super.onSizeChanged(w, h, oldWidth, oldHeight);
        mRectF = new RectF(0, 0, w, h);
    }
}
