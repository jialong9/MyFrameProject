package com.vcyber.myframe.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.vcyber.myframe.R;

import java.util.Timer;

import cn.jzvd.JZUtils;
import cn.jzvd.JzvdStd;

/**
 * description ：
 * author : zjl
 * date : 9/10/21
 */
public class JzvdCustom extends JzvdStd {
    public JzvdCustom(Context context) {
        super(context);
    }

    public JzvdCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onAutoCompletion() {
        state = STATE_PREPARED;
        startVideoAfterPreloading();
        onStatePlaying();
    }

    @Override
    public int getLayoutId() {
        return R.layout.custom_jzvd;
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.start) {
            if (jzDataSource == null || jzDataSource.urlsMap.isEmpty() || jzDataSource.getCurrentUrl() == null) {
                Toast.makeText(getContext(), getResources().getString(R.string.no_url), Toast.LENGTH_SHORT).show();
                return;
            }
            if (state == STATE_NORMAL) {
                if (!jzDataSource.getCurrentUrl().toString().startsWith("file") && !
                        jzDataSource.getCurrentUrl().toString().startsWith("/") &&
                        !JZUtils.isWifiConnected(getContext()) && !WIFI_TIP_DIALOG_SHOWED) {//这个可以放到std中
                    showWifiDialog();
                    return;
                }
                startVideo();
            } else if (state == STATE_PLAYING) {
                mediaInterface.pause();
                onStatePause();
            } else if (state == STATE_PAUSE) {
                mediaInterface.start();
                onStatePlaying();
            } else if (state == STATE_AUTO_COMPLETE) {
                startVideo();
            }
            updateStartImage();
        }
    }

    //隐藏关闭按钮时候减少时间
    @Override
    public void startDismissControlViewTimer() {
        cancelDismissControlViewTimer();
        DISMISS_CONTROL_VIEW_TIMER = new Timer();
        mDismissControlViewTimerTask = new DismissControlViewTimerTask();
        DISMISS_CONTROL_VIEW_TIMER.schedule(mDismissControlViewTimerTask, 1000);
    }


    //父类进度条 没必要执行 所以重写了
    @Override
    public void startProgressTimer() {

    }

    //移除开始/暂停按钮之外所有控件
    @Override
    public void onClickUiToggle() {
        updateStartImage();
    }

    //移除开始/暂停按钮之外所有控件
    @Override
    public void setAllControlsVisiblity(int topCon, int bottomCon, int startBtn, int loadingPro, int thumbImg, int bottomPro, int retryLayout) {
        topContainer.setVisibility(GONE);
        bottomContainer.setVisibility(GONE);
        startButton.setVisibility(startBtn);
        loadingProgressBar.setVisibility(loadingPro);
        thumbImageView.setVisibility(GONE);
        bottomProgressBar.setVisibility(GONE);
        mRetryLayout.setVisibility(GONE);
    }
}