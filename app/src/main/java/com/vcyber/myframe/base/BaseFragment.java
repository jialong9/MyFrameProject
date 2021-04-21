package com.vcyber.myframe.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.vcyber.myframe.widget.MyCustomToast;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Create by zjl on 2019/5/8
 * ---- 底层基类 ----
 */
public abstract class BaseFragment extends Fragment {

    public Unbinder mUnbinder;
    public Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(getLayoutId(), container, false);
        mUnbinder = ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != mContext) {
            mContext = null;
        }
        mUnbinder.unbind();
    }

    public abstract int getLayoutId();

    public abstract void initData(Bundle savedInstanceState);

    public void showToast(String message) {
        MyCustomToast.showToast(getActivity(), message, Toast.LENGTH_LONG);
    }
}
