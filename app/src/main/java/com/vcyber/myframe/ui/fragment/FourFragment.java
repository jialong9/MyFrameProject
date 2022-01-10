package com.vcyber.myframe.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vcyber.myframe.R;
import com.vcyber.myframe.base.BaseFragment;

/**
 * description ：
 * author : zjl
 * date : 1/10/22
 */
public class FourFragment extends BaseFragment {
    @Override
    public int getLayoutId() {
        return R.layout.fragment_general;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("FourFragment----","onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("FourFragment----","onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("FourFragment----","onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("FourFragment----","onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("FourFragment----","onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("FourFragment----","onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("FourFragment----","onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("FourFragment----","onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("FourFragment----","onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("FourFragment----","onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("FourFragment----","onDetach");
    }
}