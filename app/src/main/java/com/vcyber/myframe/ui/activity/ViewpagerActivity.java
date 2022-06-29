package com.vcyber.myframe.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.RadioGroup;

import com.vcyber.myframe.R;
import com.vcyber.myframe.base.BaseFragmentPagerAdapter;
import com.vcyber.myframe.base.BasePresenter;
import com.vcyber.myframe.base.MvpActivity;
import com.vcyber.myframe.databinding.ActivityViewpagerBinding;
import com.vcyber.myframe.ui.fragment.FirstFragment;
import com.vcyber.myframe.ui.fragment.FourFragment;
import com.vcyber.myframe.ui.fragment.SecondFragment;
import com.vcyber.myframe.ui.fragment.ThirdFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * description ：
 * author : zjl
 * date : 1/10/22
 */
public class ViewpagerActivity extends MvpActivity<BasePresenter<?>, ActivityViewpagerBinding> {
    @Override
    public int getLayoutId() {
        return R.layout.activity_viewpager;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        binding.pagview.setPath("assets://test.pag");
        binding.pagview.play();
        List<Fragment> list = new ArrayList<>();
        list.add(new FirstFragment());
        list.add(new SecondFragment());
        list.add(new ThirdFragment());
        list.add(new FourFragment());
        BaseFragmentPagerAdapter adapter = new BaseFragmentPagerAdapter(getSupportFragmentManager(), list);
        binding.viewpager.setAdapter(adapter);
        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_1) {
                    binding.viewpager.setCurrentItem(0);
                } else if (checkedId == R.id.rb_2) {
                    binding.viewpager.setCurrentItem(1);
                } else if (checkedId == R.id.rb_3) {
                    binding.viewpager.setCurrentItem(2);
                } else if (checkedId == R.id.rb_4) {
                    binding.viewpager.setCurrentItem(3);
                }
            }
        });
    }

    @Override
    protected BasePresenter<?> initPresenter() {
        return null;
    }
}