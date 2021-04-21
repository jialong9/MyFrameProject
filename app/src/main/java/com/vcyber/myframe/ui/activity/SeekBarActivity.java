package com.vcyber.myframe.ui.activity;

import android.os.Bundle;

import com.vcyber.myframe.R;
import com.vcyber.myframe.base.BasePresenter;
import com.vcyber.myframe.base.MvpActivity;

/**
 * description ：
 * author : zjl
 * date : 2020-03-16
 */
public class SeekBarActivity extends MvpActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_seek_bar;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }
}
