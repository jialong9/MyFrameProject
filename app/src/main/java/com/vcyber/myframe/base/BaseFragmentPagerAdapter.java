package com.vcyber.myframe.base;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * description ：
 * author : zjl
 * date : 10/14/21
 */

public class BaseFragmentPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mDataList;

    public BaseFragmentPagerAdapter(FragmentManager fm, List<Fragment> dataList) {
        super(fm);
        mDataList = dataList;
    }

    @Override
    public Fragment getItem(int i) {
        if (mDataList.get(i) instanceof Fragment) {
            return  mDataList.get(i);
        }
        return null;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
    }
}
