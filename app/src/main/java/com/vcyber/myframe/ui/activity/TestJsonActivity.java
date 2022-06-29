package com.vcyber.myframe.ui.activity;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vcyber.myframe.R;
import com.vcyber.myframe.base.MvpActivity;
import com.vcyber.myframe.bean.TestJsonBean;
import com.vcyber.myframe.bean.TestJsonVcrDataBean;
import com.vcyber.myframe.bean.XingTuBean;
import com.vcyber.myframe.databinding.ActivityTestJsonBinding;
import com.vcyber.myframe.mvp.presenter.TestJsonPresenter;
import com.vcyber.myframe.mvp.view.TestJsonView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * description ：
 * author : zjl
 * date : 7/27/21
 */
public class TestJsonActivity extends MvpActivity<TestJsonPresenter, ActivityTestJsonBinding> implements TestJsonView {
    @Override
    public int getLayoutId() {
        return R.layout.activity_test_json;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
//        authorsList = new ArrayList<>();
//        VideoView videoView = findViewById(R.id.video);
//        videoView.setVideoPath("http://vcr-static.huixuanjiasu.com/vcr_game/mp4/49eff9cc518b6229e446887106165caf.mp4");
//        videoView.start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                readTextFromSDcard();
//            }
//        }).start();
//        for (int i = 1; i <= 96; i++) {
//        for (int i = 61; i <= 74; i++) {
//            mPresenter.getXingTuData(i);
//        }
//        mPresenter.getXingTuData(1);
    }

    //将传入的is一行一行解析读取出来出来
    private void readTextFromSDcard() {
        InputStreamReader inputStreamReader;
        try {
            inputStreamReader = new InputStreamReader(getAssets().open("100.json"), "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(
                    inputStreamReader);
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            inputStreamReader.close();
            bufferedReader.close();
            String resultString = stringBuilder.toString();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Gson gson = new Gson();
                    List<TestJsonBean> list = gson.fromJson(resultString, new TypeToken<List<TestJsonBean>>() {
                    }.getType());
                    List<TestJsonVcrDataBean> stringList = new ArrayList<>();
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getStatus().equals("COMPLETE")) {
                            String str = list.get(i).getResponse().getBody().getText();

                            TestJsonVcrDataBean bean = gson.fromJson(str, TestJsonVcrDataBean.class);
                            stringList.add(bean);
                        }
                    }
                    String newJson = gson.toJson(stringList);
                    System.out.println(newJson);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected TestJsonPresenter initPresenter() {
        return new TestJsonPresenter(this);
    }

    private List<XingTuBean.XingTuData.Authors> authorsList;
    private int num = 1;

    @Override
    public void getXingTuDataSuccess(List<XingTuBean.XingTuData.Authors> bean) {
        authorsList.addAll(bean);
        num++;
//        if (num ==97){
        if (num == 15) {
//            Comparator<XingTuBean.XingTuData.Authors> comparator = new Comparator<XingTuBean.XingTuData.Authors>() {
//                @Override
//                public int compare(XingTuBean.XingTuData.Authors o1, XingTuBean.XingTuData.Authors o2) {
//                    int num = Integer.parseInt(o1.getFollower()) - Integer.parseInt(o2.getFollower());
//                    if (num > 0) {
//                        return -1;
//                    } else {
//                        return 1;
//                    }
//                }
//            };
//            Collections.sort(authorsList, comparator);
            String newJson = new Gson().toJson(authorsList);
            int size = authorsList.size();
            System.out.println(newJson);
        }
    }
}