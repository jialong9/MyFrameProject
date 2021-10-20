package com.vcyber.myframe.mvp.presenter;

import com.vcyber.myframe.base.BasePresenter;
import com.vcyber.myframe.bean.XingTuBean;
import com.vcyber.myframe.mvp.model.MainModel;
import com.vcyber.myframe.mvp.model.TestJsonModel;
import com.vcyber.myframe.mvp.view.MainView;
import com.vcyber.myframe.mvp.view.TestJsonView;
import com.vcyber.myframe.retorfit.ApiCallback;
import com.vcyber.myframe.utils.log.LogUtil;

/**
 * description ：
 * author : zjl
 * date : 9/6/21
 */
public class TestJsonPresenter extends BasePresenter<TestJsonView> {
    private TestJsonModel model;

    public TestJsonPresenter(TestJsonView view) {
        super.attachView(view);
        model = new TestJsonModel();
    }

    int num;

    public void getXingTuData(int page) {
        addDisposable(model.getXingTuData(page), new ApiCallback<XingTuBean>() {
            @Override
            public void onSuccess(XingTuBean bean) {
                if (bean.getCode() == 0) {
                    LogUtil.e("成功=" + page);
                    num++;
                    LogUtil.e("数量=" + num);
                    mvpView.getXingTuDataSuccess(bean.getData().getAuthors());
                } else {
                    getXingTuData(page);
                }
            }

            @Override
            public void onFailure(int code, String msg) {

            }
        });
    }
}