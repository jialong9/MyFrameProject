package com.vcyber.myframe;


import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.vcyber.myframe.base.MvpActivity;
import com.vcyber.myframe.bean.LoginBean;
import com.vcyber.myframe.mvp.presenter.MainPresenter;
import com.vcyber.myframe.mvp.view.MainView;
import com.vcyber.myframe.ui.activity.TestJsonActivity;
import com.vcyber.myframe.ui.activity.TestNetWordActivity;
import com.vcyber.myframe.ui.activity.TestVideoActivity;
import com.vcyber.myframe.ui.activity.ViewpagerActivity;
import com.vcyber.myframe.widget.LineProgressView;
import com.vcyber.myframe.widget.LuckPan;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Create by zjl on 2019/5/6
 * ---- 主activity ----
 */
public class MainActivity extends MvpActivity<MainPresenter> implements MainView {

    @BindView(R.id.tv_test)
    TextView tv_test;

    @BindView(R.id.tv_testPull)
    TextView tv_testPull;

    @BindView(R.id.progress)
    LineProgressView progress;

    @BindView(R.id.luckPan)
    LuckPan luckPan;


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mPresenter.getTestData();
    }

    @Override
    public void getDataSuccess(LoginBean bean) {
        tv_test.setText(bean.getData().getAddress());
    }

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter(this);
    }

    @OnClick(R.id.tv_test)
    public void setTv_test() {
        Intent intent = new Intent(this, TestVideoActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.tv_testPull)
    public void setTv_testPull() {
        Intent intent = new Intent(this, TestJsonActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.bt_test_fragment)
    public void setBtTestFragment() {
        Intent intent = new Intent(this, ViewpagerActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }
}