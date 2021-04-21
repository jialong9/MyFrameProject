package com.vcyber.myframe.ui.activity;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.vcyber.myframe.R;
import com.vcyber.myframe.base.BaseAdapter;
import com.vcyber.myframe.base.BaseViewHolder;

/**
 * description ：
 * author : zjl
 * date : 2021/4/1
 */
public class VouchersAdapter extends BaseAdapter<VouchersBean> {
    private ImageView imvVouchers;

    public VouchersAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    public void setView(ImageView imvVouchers) {
        this.imvVouchers = imvVouchers;
    }

    @Override
    protected void bindData(BaseViewHolder holder, VouchersBean data, int position) {
        ImageView imv_icon = holder.getImageView(R.id.imv_icon);
        imv_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAnimation(0, -imvVouchers.getX(),
                        0, -imvVouchers.getY(), 2000, imv_icon);
            }
        });
    }

    /**
     * 如果是从现在的位置移动到别的位置，那么初始XY的值都是0，向左边，那么结束X值就是 负数，向右则是正数，上下亦是如此
     * 如果是从别的位置移动到现在的位置，那么结束XY的值都是0，其他的和上面一样
     *
     * @param fromX 初始X位置
     * @param toX   结束X位置
     * @param fromY 初始Y位置
     * @param toY   结束Y位置
     * @param time  动画时间
     * @param view  控件
     */

    private void setAnimation(float fromX, float toX, float fromY, float toY, long time, View view) {
        /**位移动画*/
        Animation mTranslateAnimation = new TranslateAnimation(fromX, toX, fromY, toY);
        mTranslateAnimation.setDuration(time);

        /** 缩放动画*/
        Animation mScaleAnimation = new ScaleAnimation(1.0f, 0.5f, 1.0f, 0.5f,// 整个屏幕就0.0到1.0的大小//缩放
                Animation.INFINITE, 0.5f,
                Animation.INFINITE, 0.5f);
        mScaleAnimation.setDuration(time);

        /** 组合动画*/
        AnimationSet mAnimationSet = new AnimationSet(false);
        mAnimationSet.addAnimation(mScaleAnimation);
        mAnimationSet.setFillAfter(true);//true 设置动画结束后，控件位置保持不动，false 则是返回初始位置
        mAnimationSet.addAnimation(mTranslateAnimation);
        view.startAnimation(mAnimationSet);
    }
}
