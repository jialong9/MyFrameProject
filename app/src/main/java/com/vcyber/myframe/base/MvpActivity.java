package com.vcyber.myframe.base;

import android.os.Bundle;
import android.support.annotation.Nullable;


/**
 * Create by zjl on 2019/5/6
 * ---- mvp底层基类 ----
 */
public abstract class MvpActivity<P extends BasePresenter<?>> extends BaseActivity {

    public P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter = initPresenter();
        super.onCreate(savedInstanceState);
    }

    protected abstract P initPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
