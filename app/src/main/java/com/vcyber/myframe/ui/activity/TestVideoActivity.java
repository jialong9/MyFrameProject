package com.vcyber.myframe.ui.activity;

import android.os.Bundle;
import android.widget.Button;

import com.vcyber.myframe.R;
import com.vcyber.myframe.base.BaseActivity;
import com.vcyber.myframe.widget.JzvdCustom;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.Jzvd;

/**
 * description ：
 * author : zjl
 * date : 9/10/21
 */
public class TestVideoActivity extends BaseActivity{

    private JzvdCustom jz_video;

    @Override
    public int getLayoutId() {
        return R.layout.activity_video;
    }

    int num = 0;

    @Override
    public void initData(Bundle savedInstanceState) {
        List<String> list = new ArrayList<>();
        list.add("http://vcr-static.huixuanjiasu.com/vcr_game/mp4/8476d86d6a77d49bb7aaafac1d63e8e7.mp4");
        list.add("http://vcr-static.huixuanjiasu.com/vcr_game/mp4/773105cb51f78c433193090981d3479a.mp4");
        list.add("http://vcr-static.huixuanjiasu.com/vcr_game/mp4/8ee7d017b678863db40880c2fd9a7973.mp4");
        list.add("http://vcr-static.huixuanjiasu.com/vcr_game/mp4/fdf2867ab2a878dc85d17b24a02989f2.mp4");
        list.add("http://vcr-static.huixuanjiasu.com/vcr_game/mp4/9421d5ca525fbe8b7a6188b21a467daf.mp4");

        jz_video = findViewById(R.id.jz_video);

        Button bt_next = findViewById(R.id.bt_next);
        bt_next.setOnClickListener(v -> {
            jz_video.setUp(list.get(num), "");
            jz_video.startVideoAfterPreloading();
            num++;
        });

        Button bt_play = findViewById(R.id.bt_play);
        bt_play.setOnClickListener(v -> {
            jz_video.setUp(list.get(num), "");
            jz_video.startVideoAfterPreloading();
            num++;
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (jz_video != null && jz_video.state != Jzvd.STATE_IDLE) {
            jz_video.mediaInterface.pause();
            jz_video.onStatePause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (jz_video != null && jz_video.state != Jzvd.STATE_IDLE) {
            jz_video.mediaInterface.start();
            jz_video.onStatePlaying();
        }
    }
}