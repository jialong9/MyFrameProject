package com.vcyber.myframe.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.vcyber.myframe.R;
import com.vcyber.myframe.base.BaseActivity;
import com.vcyber.myframe.widget.easybarrage.Barrage;
import com.vcyber.myframe.widget.easybarrage.BarrageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * description ：
 * author : zjl
 * date : 2021/4/1
 */
public class DanMu2Activity extends BaseActivity {
    @BindView(R.id.barrageView)
    BarrageView barrageView;

    @BindView(R.id.rv_vouchers)
    RecyclerView rvVouchers;

    @BindView(R.id.imv_vouchers)
    ImageView imvVouchers;

    @BindView(R.id.imv_get)
    ImageView imv_get;

    @BindView(R.id.bt_animation)
    Button bt_animation;

    private List<Barrage> mBarrages = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_danmu2;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        barrageView.setBarrages(mBarrages);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 200; i++) {
                            Spanned cs = Html.fromHtml("<font color='#FFFFFF'>恭喜尾号</font>" +
                                    "<font color='#65D877'>" + ((int) (Math.random() * 8998) + 1000 + 1) + "</font>" +
                                    "<font color='#FFFFFF'>的玩家成功兑换了</font>" +
                                    "<font color='#D1BA49'>30元</font>" +
                                    "<font color='#FFFFFF'>" + operator() + "</font>");
                            barrageView.addBarrage(new Barrage(cs));
                        }
                        barrageView.setMaxBarrageSize(3);
                    }
                });
            }
        }, 200);

        rvVouchers.setLayoutManager(new GridLayoutManager(this, 2));
        VouchersAdapter adapter = new VouchersAdapter(this, R.layout.item_vouchers);
        rvVouchers.setAdapter(adapter);
        List<VouchersBean> beans = new ArrayList<>();
        for (int i = 0; i <= 6; i++) {
            VouchersBean bean = new VouchersBean();
            bean.setSign(true);
            beans.add(bean);
        }
        adapter.setData(beans);
        adapter.setView(imvVouchers);
    }

    @OnClick({R.id.bt_animation})
    public void setOnClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.bt_animation) {
            imv_get.setVisibility(View.VISIBLE);
            int[] location = new int[2];
            imvVouchers.getLocationInWindow(location);
            int x = location[0];
            int y = location[1];

            int[] location2 = new int[2];
            imv_get.getLocationInWindow(location2);
            int x2 = location2[0];
            int y2 = location2[1];
            float toX = x2 > x ? x2 - x : x - x2;
            setAnimation(0, toX,
                    0, -y, 2000, imv_get);
        }
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
        Animation mScaleAnimation = new ScaleAnimation(1.0f, 0.15f, 1.0f, 0.15f,// 整个屏幕就0.0到1.0的大小//缩放
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


    private String operator() {
        int min = 1;
        int max = 3;
        Random random = new Random();
        int num = random.nextInt(max) % (max - min + 1) + min;
        if (num == 1) {
            return "移动话费";
        } else if (num == 2) {
            return "联通话费";
        } else {
            return "电信话费";
        }
    }
}
