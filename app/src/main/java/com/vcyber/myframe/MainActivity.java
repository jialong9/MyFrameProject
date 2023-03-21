package com.vcyber.myframe;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.vcyber.myframe.base.BaseActivity;
import com.vcyber.myframe.base.BaseClassResultBean;
import com.vcyber.myframe.base.MvpActivity;
import com.vcyber.myframe.bean.LoginBean;
import com.vcyber.myframe.bean.XingTuBean;
import com.vcyber.myframe.databinding.ActivityMainBinding;
import com.vcyber.myframe.mvp.presenter.MainPresenter;
import com.vcyber.myframe.mvp.view.MainView;
import com.vcyber.myframe.ui.activity.TestDownTimeActivity;
import com.vcyber.myframe.ui.activity.TestPullPushActivity;
import com.vcyber.myframe.ui.activity.ViewpagerActivity;


import org.libpag.PAGFile;
import org.libpag.PAGView;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by zjl on 2019/5/6
 * ---- 主activity ----
 */
public class MainActivity extends MvpActivity<MainPresenter, ActivityMainBinding> implements MainView {

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    int a = 0;

    @Override
    public void initData(Bundle savedInstanceState) {
        mPresenter.getTestData();

        binding.pagview.setPath("assets://test.pag");
        binding.pagview.setRepeatCount(1);
        binding.btTestFragment.setOnClickListener(v -> {
//            binding.pagview.play();
//            if (a <= 0) {
//                a++;
//                startActivity(new Intent(MainActivity.this, ViewpagerActivity.class));
//            }
            startActivity(new Intent(this, TestDownTimeActivity.class));
        });

    }

    @Override
    public void getDataSuccess(LoginBean bean) {
        binding.tvTest.setText(bean.getData().getAddress());
    }

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


}