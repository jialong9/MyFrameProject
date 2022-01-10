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
public class ThirdFragment extends BaseFragment {
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
        Log.e("ThirdFragment----","onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("ThirdFragment----","onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("ThirdFragment----","onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("ThirdFragment----","onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("ThirdFragment----","onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("ThirdFragment----","onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("ThirdFragment----","onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("ThirdFragment----","onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("ThirdFragment----","onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("ThirdFragment----","onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("ThirdFragment----","onDetach");
    }
}