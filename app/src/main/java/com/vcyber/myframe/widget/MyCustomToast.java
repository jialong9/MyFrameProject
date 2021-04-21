package com.vcyber.myframe.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.vcyber.myframe.R;


/**
 * description ：自定义样式的toast
 * author : zjl
 * date : 2021/3/23
 */
public class MyCustomToast {
    private static Toast mToast;
    private volatile static String msg = "";
    private final static Handler mHandler = new Handler();
    private final static Runnable r = new Runnable() {
        public void run() {
            msg = "";
            mToast.cancel();
            mToast = null;
        }
    };

    /**
     * 展示Toast
     *
     * @param mContext 上下文
     * @param text     Toast内容
     * @param duration 0代表Toast.LENGTH_SHORT  1代表Toast.LENGTH_LONG
     */
    @SuppressLint("InflateParams")
    public static void showToast(Context mContext, String text, int duration) {
        if (msg.equals(text)) {//防止多次点击
            return;
        }
        msg = text;
        View view = LayoutInflater.from(mContext).inflate(R.layout.custom_toast, null);
        TextView textView = view.findViewById(R.id.textView);
        textView.setText(text);
        mHandler.removeCallbacks(r);
        mToast = new Toast(mContext);
        mToast.setDuration(duration);
        mToast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 300);
        mToast.setView(view);
        mHandler.postDelayed(r, 2500);
        mToast.show();
    }
}
