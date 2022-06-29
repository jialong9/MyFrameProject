package com.vcyber.myframe.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.viewbinding.ViewBinding;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


/**
 * Create by zjl on 2019/5/6
 * ---- mvp底层基类 ----
 */
public abstract class MvpActivity<P extends BasePresenter<?>, VB extends ViewBinding> extends BaseActivity {

    public P mPresenter;
    public VB binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter = initPresenter();
        super.onCreate(savedInstanceState);
        Type type = getClass().getGenericSuperclass();
        try {
            if (type instanceof ParameterizedType) {
                Class<VB> classObj = (Class<VB>) ((ParameterizedType) type).getActualTypeArguments()[1];
                Method method = classObj.getMethod("inflate", LayoutInflater.class);
                binding = (VB) method.invoke(null, getLayoutInflater());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        setContentView(binding.getRoot());
        initData(savedInstanceState);
    }

    protected abstract P initPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
