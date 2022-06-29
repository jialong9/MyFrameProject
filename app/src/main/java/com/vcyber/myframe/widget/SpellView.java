package com.vcyber.myframe.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.vcyber.myframe.R;

/**
 * description ：
 * author : zjl
 * date : 10/26/21
 */
public class SpellView extends View {
    private Paint paint;
    private Path path;
    private int mFuelIcon;
    private Bitmap bitmap;

    public SpellView(Context context) {
        super(context);
    }

    public SpellView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SpellView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        path = new Path();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        mFuelIcon = R.mipmap.icon_glide_default;
        bitmap = BitmapFactory.decodeResource(context.getResources(), mFuelIcon);
//        View inflate = LayoutInflater.from(context).inflate(R.layout.view_spell, null);
//        addView(inflate);
    }

    private float sX = 0, sY = 0, eX, eY;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            sX = event.getX();
            sY = event.getY();
            path.moveTo(event.getX(), event.getY());
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            path.lineTo(event.getX(), event.getY());
            invalidate();
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            eX = event.getX();
            eY = event.getY();
            if (eX - sX > 5 || eY - sY > 5) {//忽略很小的点触发的路径检查
                checkPath();
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        canvas.drawPath(path, paint);
    }

    private void checkPath() {
        if (eX > sX) {//手势往右
            if (eY > sY) {//手势右下
                if ((eX - sX) / 3 > eY - sY) {//右横
                    Log.e("方向==", "右横");
                } else if ((eY - sY) / 3 > eX - sX) {//右下竖
                    Log.e("方向==", "右下竖");
                } else {//右捺
                    Log.e("方向==", "右捺");
                }
            } else {//手势右上
                if ((eX - sX) / 3 > sY - eY) {//右横
                    Log.e("方向==", "右横");
                } else if ((sY - eY) / 3 > eX - sX) {
                    Log.e("方向==", "右上竖");
                } else {//右撇
                    Log.e("方向==", "右撇");
                }
            }
        } else {//手势往左
            if (eY > sY) {//手势左下
                if ((sX - eX) / 3 > eY - sY) {//左横
                    Log.e("方向==", "左横");
                } else if ((eY - sY) / 3 > sX - eX) {//左下竖
                    Log.e("方向==", "左下竖");
                } else {//左撇
                    Log.e("方向==", "左撇");
                }
            } else {//手势左上
                if ((sX - eX) / 3 > sY - eY) {//左横
                    Log.e("方向==", "左横");
                } else if ((sY - eY) / 3 > sX - eX) {//左上竖
                    Log.e("方向==", "左上竖");
                } else {//左捺
                    Log.e("方向==", "左捺");
                }
            }
        }
    }

    public void reset(){
        path.reset();
        invalidate();
    }
}