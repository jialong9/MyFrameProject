package com.vcyber.myframe.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Create by zjl on 2019/5/8
 * ---- mvp底层基类 ----
 */
public abstract class MvpFragment<P extends BasePresenter> extends BaseFragment {
    public P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter = initPresenter();
        super.onCreate(savedInstanceState);
    }

    protected abstract P initPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
