package com.vcyber.myframe.base;

import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.vcyber.myframe.broadcastreceiver.NetWordChangeReceiver;
import com.vcyber.myframe.databinding.ActivityMainBinding;
import com.vcyber.myframe.utils.ActivityManagerUtil;
import com.vcyber.myframe.widget.MyCustomToast;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Create by zjl on 2019/5/6
 * ---- 底层基类 ----
 */
public abstract class BaseActivity extends AppCompatActivity {

    public Unbinder mUnbinder;
    public Context mContext;
    private NetWordChangeReceiver mNetWordChangeReceiver;
    private ActivityMainBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());
        mUnbinder = ButterKnife.bind(this);
        setNoStatusBar(true);
        initData(savedInstanceState);
        ActivityManagerUtil.getActivityStackInfos();
        mContext = this;

        mNetWordChangeReceiver = new NetWordChangeReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mNetWordChangeReceiver, filter);
    }

    public abstract int getLayoutId();

    public abstract void initData(Bundle savedInstanceState);

    /**
     * 设置沉浸式状态栏
     */
    public void setNoStatusBar(boolean isChangeColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && isChangeColor) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        unregisterReceiver(mNetWordChangeReceiver);
    }

    public void showToast(String message) {
        MyCustomToast.showToast(this, message, Toast.LENGTH_LONG);
    }
}
