package com.vcyber.myframe.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.vcyber.myframe.R;

/**
 * description ：
 * author : zjl
 * date : 2021/3/30
 */
public class RotaView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private SurfaceHolder mHolder;
    private Canvas mCanvas;

    private Thread mDrawThread;//用于绘制的线程
    private boolean isRunning;//作为子线程运行的控制开关

    //背景图
    private Bitmap mBcgBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_cover);

    //盘块的奖项
    private final String[] mAwardsName ={"1","2","3","4","5","6"};
    //盘块的奖项图片id
    private final int[] mAwardsImgs = new int[]{R.mipmap.icon_company_logo, R.mipmap.icon_company_logo, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.icon_glide_default, R.mipmap.icon_glide_default};
    //盘块的奖项图片
    private Bitmap[] mImgsBitmap;
    //盘块的数量
    private final int mItemCount = 6;
    //判断是否点击了停止按钮的标志
    private boolean isShouldEnd;
    //转盘的中心位置
    private int mCenter;
    //直接以padding值为准（或者取left、right、top、bottom中设置的最小的）
    private int mPadding;

    //整个盘块的范围
    private RectF mRange = new RectF();
    //整个盘快的直径
    private int mDia;
    //绘制盘块、文本的画笔
    private Paint mArcPaint, mTextPaint;
    //盘块滚动的速度(即转盘每隔mSpeed设置的角度重绘一次,但绘制的时间间隔不变)
    private double mSpeed;
    //起始角度(设置为float而非int，因为转盘存在某些逻辑会使得mStartAngle带有小数，如果为int会失去精度对指定奖项时的计算产生影响)
    private volatile float mStartAngle = 0;//可能会存在于两个线程，同时更新

    private final int platePartColor1 = 0xFFFFC300, platePartColor2 = 0xFFF17E01;

    public RotaView(Context context) {
        this(context, null);
    }

    public RotaView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mHolder = getHolder();
        mHolder.addCallback(this);
        //可获得焦点
        setFocusable(true);
        setFocusableInTouchMode(true);
        //设置常量
        setKeepScreenOn(true);
    }

    //强制将转盘设置为正方形,并设置一些相关参数
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = Math.min(getMeasuredWidth(), getMeasuredHeight());

        mPadding = getPaddingLeft();
        //半径
        mDia = width - mPadding * 2;
        //中心点
        mCenter = width / 2;

        setMeasuredDimension(width, width);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        init();

        isRunning = true;
        mDrawThread = new Thread(this);
        mDrawThread.start();
    }

    private void init() {
        //初始化绘制盘快的画笔
        mArcPaint = new Paint();
        mArcPaint.setAntiAlias(true);
        mArcPaint.setDither(true);

        //初始化文本画笔
        mTextPaint = new Paint();
        mTextPaint.setColor(0xffffffff);
        mTextPaint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 20, getResources().getDisplayMetrics()));//设置文字大小

        //初始化盘快绘制的范围（mRadius已经减去了mPadding）
        mRange = new RectF(mPadding, mPadding, mDia + mPadding, mDia + mPadding);

        //初始化图片
        mImgsBitmap = new Bitmap[mItemCount];
        for (int i = 0; i < mItemCount; i++)
            mImgsBitmap[i] = BitmapFactory.decodeResource(getResources(), mAwardsImgs[i]);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isRunning = false;
    }

    @Override
    public void run() {
        //不断进行绘制
        while (isRunning) {
            long start = System.currentTimeMillis();
            draw();
            long end = System.currentTimeMillis();

            if (end - start < 100) {
                try {
                    Thread.sleep(100 - (end - start));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void draw() {
        /**
         * try-catch与判空的原因:
         * 当SurfaceView在主界面时，如果点击home或者back键，都会使得Surface销毁，
         * 但是在销毁之后，有可能已经进入该方法执行相应的逻辑了，因此需要对mCanvas进行判空，
         * 另外，由于Surface被销毁，但是线程却不是那么容易被关闭，继续执行draw something的操作，
         * 此时就有可能会抛出某些异常
         */
        try {
            //首先拿到Canvas用于绘制
            mCanvas = mHolder.lockCanvas();
            if (mCanvas != null) {

                //绘制背景
                mCanvas.drawColor(0xffffffff);
                mCanvas.drawBitmap(mBcgBitmap, null, new Rect(mPadding / 2, mPadding / 2, getMeasuredWidth() - mPadding / 2, getMeasuredWidth() - mPadding / 2), null);

                //绘制盘块
                float tmpAngle = mStartAngle;
                float sweepAngle = 360 / mItemCount;
                for (int i = 0; i < mItemCount; i++) {
                    //1、绘制盘块
                    mArcPaint.setColor((i % 2 == 0 ? platePartColor1 : platePartColor2));
                    mCanvas.drawArc(mRange, tmpAngle, sweepAngle, true, mArcPaint);

                    //2、绘制盘块上的文本(弧形的)
                    Path path = new Path();
                    path.addArc(mRange, tmpAngle, sweepAngle);
                    //垂直偏移量取半径的1/6
                    int vOffset = mDia / 8;
                    //利用水平偏移量使文字水平居中
                    /**
                     * 圆的周长/盘块数量=每个盘块弧的长度
                     * 之后再/2,即取一半
                     * 最后减去文字的长度的一半(减文字的长度之前需要注意文字长度的值的一半小于等于上一步所求的值)
                     */
                    int hOffset = (int) (mDia * Math.PI / mItemCount / 2 - mTextPaint.measureText(mAwardsName[i]) / 2);
                    mCanvas.drawTextOnPath(mAwardsName[i], path, hOffset, vOffset, mTextPaint);

                    //3、绘制盘块图标
                    //设置图片的宽度为半径的1/8
                    int imgWidth = mDia / 8;
                    //求得弧度值（即图片所示的α）
                    float angle = (float) ((tmpAngle + sweepAngle / 2) * Math.PI / 180);
                    //求得图标中心点的坐标(而非图标左上角的坐标)
                    int x = (int) (mCenter + mDia / 2 / 2 * Math.cos(angle));//mDia / 2 / 2->自定义去半径的一半
                    int y = (int) (mCenter + mDia / 2 / 2 * Math.sin(angle));
                    //确定图标的位置
                    Rect rect = new Rect(x - imgWidth / 2, y - imgWidth / 2, x + imgWidth / 2, y + imgWidth / 2);
                    mCanvas.drawBitmap(mImgsBitmap[i], null, rect, null);

                    tmpAngle += sweepAngle;
//                    if(tmpAngle==360) tmpAngle=0;
                }

                //mSpeed设置为10角度，即转盘每隔10角度重绘一次
                mStartAngle += mSpeed;

                //如果点击了停止按钮，使得转盘缓缓停止
                if (isShouldEnd)
                    mSpeed -= 1;
                if (mSpeed <= 0) {
                    mSpeed = 0;
                    isShouldEnd = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (mCanvas != null)
                mHolder.unlockCanvasAndPost(mCanvas);
        }
    }

    /**
     * 启动转盘
     */
    public void startDial() {
        mSpeed = 10;
        isShouldEnd = false;
    }

    /**
     * 启动转盘，指定停止时的奖项
     */
    public void startDial(int index) {
        int angle = 360 / mItemCount;
        //计算指定index的中奖角度范围
        int from = 270 - (index + 1) * angle;
        int end = from + angle;

        //设置停下来需要旋转的角度范围(每次都停在指定奖项所在的范围内，而不是每次都停在指定奖项的同一点)
        int targetFrom = 1 * 360 + from;//1*360+from中的2表示点击停止后再转一圈再停止
        int targetEnd = targetFrom + 60;
        //为了实现上述所说的停在奖项对应区间的任意点
        //且停止时所对应的奖项是靠mSpeed的值决定的
        //所以需要使得mSpeed处于[targetFrom,targetEnd]所对应的值的区间(即[v1,v2])

        /**
         * mSpeed->0时停止转动且要考虑点击停止按钮时因惯性每次-1
         *
         * 设mSpeed.v1对应targetFrom
         * 则有 (v1+0)*(v1+1)/2=targetFrom=>v1=(-1+Math.sqrt(1+8*targetFrom))/2(除去了负值的)
         */
        float v1 = (float) ((-1 + Math.sqrt(1 + 8 * targetFrom)) / 2);
        float v2 = (float) ((-1 + Math.sqrt(1 + 8 * targetEnd)) / 2);

        mSpeed = v1 + Math.random() * (v2 - v1);

        isShouldEnd = false;
    }

    /**
     * 停止转盘
     */
    public void stopDial() {
        isShouldEnd = true;
        mStartAngle = 0;
    }

    /**
     * 判断是转盘是否正在转
     */
    public boolean isRotating() {
        return mSpeed != 0;
    }

    public boolean isShouldEndFlag() {
        return isShouldEnd;
    }
}
