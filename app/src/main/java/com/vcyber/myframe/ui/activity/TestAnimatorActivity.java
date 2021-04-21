package com.vcyber.myframe.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vcyber.myframe.R;
import com.vcyber.myframe.base.BasePresenter;
import com.vcyber.myframe.base.MvpActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Create by zjl on 2019/10/8
 * ---- ----
 */
public class TestAnimatorActivity extends MvpActivity {
    public final static int ANIMATOR_TIME = 300;

    @BindView(R.id.bt_carControl)
    Button btCarControl;

    @BindView(R.id.ll_view)
    LinearLayout llView;


    @BindView(R.id.imv_carView)
    ImageView imvCarView;

    @BindView(R.id.bt_closeView)
    Button bt_closeView;

    @BindView(R.id.ll_view2)
    LinearLayout llView2;

    @BindView(R.id.ll_carSetting)
    LinearLayout llCarSetting;

    @BindView(R.id.linearLayout1)
    LinearLayout linearLayout1;

    @BindView(R.id.linearLayout2)
    LinearLayout linearLayout2;

    @BindView(R.id.tv_carSetting)
    TextView tvCarSetting;

    @BindView(R.id.tv_carSetting2)
    TextView tvCarSetting2;

    @BindView(R.id.tv_carSetting3)
    TextView tvCarSetting3;

    @BindView(R.id.bt_carIndividuation)
    Button btCarIndividuation;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_test_animator;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    ObjectAnimator objectAnimator;
    ObjectAnimator objectAnimator1;
    ObjectAnimator objectAnimator2;
    ObjectAnimator objectAnimator3;
    ObjectAnimator objectAnimator4;
    ObjectAnimator objectAnimator5;
    ObjectAnimator objectAnimator6;
    ObjectAnimator objectAnimator7;

    ObjectAnimator objectAnimator11;
    ObjectAnimator objectAnimator12;
    ObjectAnimator objectAnimator13;
    ObjectAnimator objectAnimator14;
    ObjectAnimator objectAnimator15;

    @OnClick(R.id.bt_carControl)
    public void setBtTestMove() {
//        objectAnimator = ObjectAnimator.ofFloat(llView, "translationX", 0f, -llView.getWidth())
//                .setDuration(ANIMATOR_TIME);
//        objectAnimator.setInterpolator(new LinearInterpolator());
//        objectAnimator.start();

        objectAnimator1 = ObjectAnimator.ofFloat(llView, "translationY", 0f, -llView.getHeight() / 3)
                .setDuration(ANIMATOR_TIME);
        objectAnimator1.setInterpolator(new LinearInterpolator());
        objectAnimator1.start();

        objectAnimator2 = ObjectAnimator.ofFloat(imvCarView, "translationX", 0f, -imvCarView.getWidth())
                .setDuration(ANIMATOR_TIME);
        objectAnimator2.setInterpolator(new LinearInterpolator());
        objectAnimator2.start();

        objectAnimator3 = ObjectAnimator.ofFloat(imvCarView, "scaleX", 1f, 1.5f)
                .setDuration(ANIMATOR_TIME);
        objectAnimator3.setInterpolator(new LinearInterpolator());
        objectAnimator3.start();

        objectAnimator4 = ObjectAnimator.ofFloat(imvCarView, "scaleY", 1f, 1.5f)
                .setDuration(ANIMATOR_TIME);
        objectAnimator4.setInterpolator(new LinearInterpolator());
        objectAnimator4.start();

        float viewX = bt_closeView.getX();
        float viewX2 = getWindowManager().getDefaultDisplay().getWidth();
        objectAnimator5 = ObjectAnimator.ofFloat(bt_closeView, "translationX", 0f, -((viewX - viewX2) + bt_closeView.getWidth()))
                .setDuration(ANIMATOR_TIME);
        objectAnimator5.setInterpolator(new LinearInterpolator());
        objectAnimator5.start();

        objectAnimator6 = ObjectAnimator.ofFloat(bt_closeView, "scaleX", 0f, 1f).setDuration(ANIMATOR_TIME);
        objectAnimator6.setInterpolator(new LinearInterpolator());
        objectAnimator6.start();

        objectAnimator7 = ObjectAnimator.ofFloat(bt_closeView, "scaleY", 0f, 1f).setDuration(ANIMATOR_TIME);
        objectAnimator7.setInterpolator(new LinearInterpolator());
        objectAnimator7.start();

        PropertyValuesHolder bottomLayout1 = PropertyValuesHolder.ofFloat("translationX", 0f, -llView.getWidth());
        PropertyValuesHolder bottomLayout2 = PropertyValuesHolder.ofFloat("translationY", 0f, -llView.getHeight() / 3);
        objectAnimator = ObjectAnimator.ofPropertyValuesHolder(llView, bottomLayout1, bottomLayout2).setDuration(ANIMATOR_TIME);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.start();
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.e("TestAnimatroActivity", animation.getCurrentPlayTime() + "===getCurrentPlayTime");
                Log.e("TestAnimatroActivity", animation.getDuration() + "getDuration");
                Log.e("TestAnimatroActivity", animation.getAnimatedValue() + "getAnimatedValue");
            }
        });

    }

    @OnClick(R.id.bt_closeView)
    public void setBt_closeView() {
        objectAnimator.reverse();
        objectAnimator1.reverse();
        objectAnimator2.reverse();
        objectAnimator3.reverse();
        objectAnimator4.reverse();
        objectAnimator5.reverse();
        objectAnimator6.reverse();
        objectAnimator7.reverse();
    }

    @OnClick(R.id.ll_carSetting)
    public void setBtCarSetting() {
        llView2.setVisibility(View.VISIBLE);
        PropertyValuesHolder holder1 = PropertyValuesHolder.ofFloat("translationX", llView2.getWidth(), 0f);
        PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat("translationY", llView2.getHeight(), 0f);
        objectAnimator11 = ObjectAnimator.ofPropertyValuesHolder(llView2, holder1, holder2).setDuration(ANIMATOR_TIME);
        objectAnimator11.setInterpolator(new LinearInterpolator());
        objectAnimator11.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                tvCarSetting.setVisibility(View.VISIBLE);
                tvCarSetting2.setVisibility(View.INVISIBLE);
                tvCarSetting3.setVisibility(View.VISIBLE);
            }
        });
        objectAnimator11.start();

        PropertyValuesHolder bottomLayout6 = PropertyValuesHolder.ofFloat("translationY", 0f, llView2.getHeight());
        objectAnimator12 = ObjectAnimator.ofPropertyValuesHolder(llView, bottomLayout6).setDuration(ANIMATOR_TIME);
        objectAnimator12.setInterpolator(new LinearInterpolator());
        objectAnimator12.start();

        PropertyValuesHolder bottomLayout1 = PropertyValuesHolder.ofFloat("translationX", 0f, -linearLayout1.getWidth());
        PropertyValuesHolder bottomLayout2 = PropertyValuesHolder.ofFloat("translationY", 0f, 0f);
        objectAnimator13 = ObjectAnimator.ofPropertyValuesHolder(linearLayout1, bottomLayout1, bottomLayout2).setDuration(ANIMATOR_TIME);
        objectAnimator13.setInterpolator(new LinearInterpolator());
        objectAnimator13.start();

        PropertyValuesHolder bottomLayout3 = PropertyValuesHolder.ofFloat("translationX", 0f, -linearLayout2.getWidth());
        PropertyValuesHolder bottomLayout4 = PropertyValuesHolder.ofFloat("translationY", 0f, 0f);
        objectAnimator14 = ObjectAnimator.ofPropertyValuesHolder(linearLayout2, bottomLayout3, bottomLayout4).setDuration(ANIMATOR_TIME);
        objectAnimator14.setInterpolator(new LinearInterpolator());
        objectAnimator14.start();


        tvCarSetting.setVisibility(View.INVISIBLE);
        tvCarSetting2.setVisibility(View.VISIBLE);
        tvCarSetting3.setVisibility(View.INVISIBLE);
        PropertyValuesHolder bottomTextView1 = PropertyValuesHolder.ofFloat("translationX", 0f, -10f);
        PropertyValuesHolder bottomTextView2 = PropertyValuesHolder.ofFloat("translationY", 0f, -llView2.getHeight() / 2);
        objectAnimator15 = ObjectAnimator.ofPropertyValuesHolder(tvCarSetting2, bottomTextView1, bottomTextView2).setDuration(ANIMATOR_TIME);
        objectAnimator15.setInterpolator(new LinearInterpolator());
        objectAnimator15.start();

    }

    @OnClick(R.id.bt_carIndividuation)
    public void setBtCarIndividuation() {
        tvCarSetting.setVisibility(View.VISIBLE);
        tvCarSetting2.setVisibility(View.VISIBLE);
        tvCarSetting3.setVisibility(View.INVISIBLE);
        objectAnimator11.reverse();
        objectAnimator12.reverse();
        objectAnimator13.reverse();
        objectAnimator14.reverse();
        objectAnimator15.reverse();
    }
}
