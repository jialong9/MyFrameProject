package com.vcyber.myframe.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.vcyber.myframe.widget.MyCustomToast;


/**
 * Create by zjl on 2019/5/8
 * ---- 底层基类 ----
 */
public abstract class BaseFragment extends Fragment {

    public Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != mContext) {
            mContext = null;
        }
    }

    public abstract int getLayoutId();

    public abstract void initData(Bundle savedInstanceState);

    public void showToast(String message) {
        MyCustomToast.showToast(getActivity(), message, Toast.LENGTH_LONG);
    }
}
