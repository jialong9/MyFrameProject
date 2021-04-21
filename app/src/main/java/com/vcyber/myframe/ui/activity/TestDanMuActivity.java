package com.vcyber.myframe.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;

import com.vcyber.myframe.R;
import com.vcyber.myframe.base.BaseActivity;
import com.vcyber.myframe.bean.DanMuBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.ui.widget.DanmakuView;

/**
 * description ：
 * author : zjl
 * date : 2021/3/31
 */
public class TestDanMuActivity extends BaseActivity {
    @BindView(R.id.danmaku_view)
    DanmakuView danmakuView;

    private boolean showDanmaku;//是否循环展示弹幕

    private DanmakuContext danmakuContext;//弹幕的Context使用对象

    List<DanMuBean> totalDanMuList = new ArrayList<>();//用于记录弹幕的内容列表

    private BaseDanmakuParser parser = new BaseDanmakuParser() {
        @Override
        protected IDanmakus parse() {
            return new Danmakus();
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_danmu;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        danmakuView.enableDanmakuDrawingCache(true);
        danmakuView.setCallback(new DrawHandler.Callback() {
            @Override
            public void prepared() {
                showDanmaku = true;
                danmakuView.start();

                //初始化弹幕列表，可以替换成自己的数据集
                for (int index = 0; index < 50; index++) {
                    DanMuBean danMuBean = new DanMuBean();
                    Spanned cs = Html.fromHtml("<font color='#FFFFFF'>恭喜尾号</font>" +
                            "<font color='#65D877'>" + (int) ((Math.random() * 10000)) + "</font>" +
                            "<font color='#FFFFFF'>的玩家成功兑换了</font>" +
                            "<font color='#D1BA49'>30元</font>" +
                            "<font color='#FFFFFF'>" + operator() + "</font>");
                    danMuBean.setContent(cs);
                    danMuBean.setShowBorder(false);
                    totalDanMuList.add(danMuBean);
                }
                //启动线程展示弹幕
                generateSomeDanmaku();
            }

            @Override
            public void updateTimer(DanmakuTimer timer) {

            }

            @Override
            public void danmakuShown(BaseDanmaku danmaku) {

            }

            @Override
            public void drawingFinished() {

            }
        });
        danmakuContext = DanmakuContext.create();
        danmakuView.prepare(parser, danmakuContext);

        // 设置最大显示行数
        HashMap<Integer, Integer> maxLinesPair = new HashMap<Integer, Integer>();
        maxLinesPair.put(BaseDanmaku.TYPE_SCROLL_RL, 3); // 滚动弹幕最大显示5行

        // 设置是否禁止重叠
        HashMap<Integer, Boolean> overlappingEnablePair = new HashMap<Integer, Boolean>();
        overlappingEnablePair.put(BaseDanmaku.TYPE_SCROLL_RL, true);
        overlappingEnablePair.put(BaseDanmaku.TYPE_FIX_TOP, true);

        danmakuContext.setDanmakuStyle(IDisplayer.DANMAKU_STYLE_STROKEN, 3)
                .setDuplicateMergingEnabled(false)
                .setScrollSpeedFactor(1.2f)
                .setScaleTextSize(1.2f)
                .setDuplicateMergingEnabled(false) //设置不合并相同内容弹幕
                .preventOverlapping(overlappingEnablePair)
                .setMaximumLines(maxLinesPair);
        // 获得DanmakuManager单例
//        DanmakuManager dm = DanmakuManager.getInstance();
//
//        dm.init(TestDanMuActivity.this, container);
//        // 设置一个FrameLayout为弹幕容器
//        dm.setDanmakuContainer(container);
//        dm.setMaxDanmakuSize(10);
//        dm.getConfig().setMaxScrollLine(10);
//
//        CountDownTimer countDownTimer = new CountDownTimer(60 * 60 * 1000, 2000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                Danmaku danmaku = new Danmaku();
//                danmaku.text = Html.fromHtml("<font color='#FFFFFF'>恭喜尾号</font>" +
//                        "<font color='#65D877'>" + (int) ((Math.random() * 10000)) + "</font>" +
//                        "<font color='#FFFFFF'>的玩家成功兑换了</font>" +
//                        "<font color='#D1BA49'>30元</font>" +
//                        "<font color='#FFFFFF'>" + operator() + "</font>");
//                dm.send(danmaku);
//            }
//
//            @Override
//            public void onFinish() {
//            }
//        };
//        countDownTimer.start();
    }

    /**
     * 向弹幕View中添加一条弹幕
     *
     * @param content    弹幕的具体内容
     * @param withBorder 弹幕是否有边框
     */
    private void addDanmaku(CharSequence content, boolean withBorder) {
        if (danmakuView == null) return;
        BaseDanmaku danmaku = danmakuContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL);
        danmaku.text = content;
        danmaku.padding = 5;
        danmaku.textSize = sp2px(12);
        danmaku.textColor = Color.WHITE;
        danmaku.setTime(danmakuView.getCurrentTime());
        if (withBorder) {
            danmaku.borderColor = Color.GREEN;
        }
        danmakuView.addDanmaku(danmaku);
    }

    /**
     * 循环展示弹幕，展示列表的弹幕
     */
    private void generateSomeDanmaku() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (showDanmaku) {
                    for (int index = 0; index < totalDanMuList.size(); index++) {
                        int time = new Random().nextInt(1000);
                        DanMuBean danMuBean = totalDanMuList.get(index);
                        if (danMuBean != null) {
                            addDanmaku(danMuBean.getContent(), danMuBean.isShowBorder());
                            try {
                                Thread.sleep(time);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }).start();
    }

    /**
     * sp转px的方法。
     */
    public int sp2px(float spValue) {
        final float fontScale = getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (danmakuView != null && danmakuView.isPrepared()) {
            danmakuView.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (danmakuView != null && danmakuView.isPrepared() && danmakuView.isPaused()) {
            danmakuView.resume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showDanmaku = false;
        if (danmakuView != null) {
            danmakuView.release();
            danmakuView = null;
        }
    }

    private String operator() {
        int min = 1;
        int max = 3;
        Random random = new Random();
        int num = random.nextInt(max) % (max - min + 1) + min;
        if (num == 1) {
            return "移动话费";
        } else if (num == 2) {
            return "联通话费";
        } else {
            return "电信话费";
        }
    }
}
