package com.vcyber.myframe.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;

import com.vcyber.myframe.R;
import com.vcyber.myframe.base.BaseActivity;
import com.vcyber.myframe.base.BaseFragment;
import com.vcyber.myframe.base.BaseFragmentPagerAdapter;
import com.vcyber.myframe.ui.fragment.FirstFragment;
import com.vcyber.myframe.ui.fragment.FourFragment;
import com.vcyber.myframe.ui.fragment.SecondFragment;
import com.vcyber.myframe.ui.fragment.ThirdFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * description ：
 * author : zjl
 * date : 1/10/22
 */
public class ViewpagerActivity extends BaseActivity {
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;

    @Override
    public int getLayoutId() {
        return R.layout.activity_viewpager;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        List<Fragment> list = new ArrayList<>();
        list.add(new FirstFragment());
        list.add(new SecondFragment());
        list.add(new ThirdFragment());
        list.add(new FourFragment());
        BaseFragmentPagerAdapter adapter = new BaseFragmentPagerAdapter(getSupportFragmentManager(), list);
        viewpager.setAdapter(adapter);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_1) {
                    viewpager.setCurrentItem(0);
                } else if (checkedId == R.id.rb_2) {
                    viewpager.setCurrentItem(1);
                } else if (checkedId == R.id.rb_3) {
                    viewpager.setCurrentItem(2);
                } else if (checkedId == R.id.rb_4) {
                    viewpager.setCurrentItem(3);
                }
            }
        });
    }
}