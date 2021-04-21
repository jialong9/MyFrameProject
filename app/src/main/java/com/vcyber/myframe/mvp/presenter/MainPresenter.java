package com.vcyber.myframe.mvp.presenter;

import com.vcyber.myframe.base.BasePresenter;
import com.vcyber.myframe.bean.LoginBean;
import com.vcyber.myframe.mvp.model.MainModel;
import com.vcyber.myframe.mvp.view.MainView;
import com.vcyber.myframe.retorfit.ApiCallback;
import com.vcyber.myframe.utils.log.LogUtil;

/**
 * Create by zjl on 2019/5/6
 * ---- 主activity的presenter ----
 */
public class MainPresenter extends BasePresenter<MainView> {

    private MainModel mMainModel;

    public MainPresenter(MainView view) {
        super.attachView(view);
        LogUtil.i("MainPresenter", "----------已经创建");
        mMainModel = new MainModel();
    }

    /**
     * 这里要做的事情是发送一个网络请求
     */
    public void getTestData() {
        addDisposable(mMainModel.getDataFromNet("18910059105", "123456"), new ApiCallback<LoginBean>() {
            @Override
            public void onSuccess(LoginBean bean) {
                if (null != bean) {
                    mvpView.getDataSuccess(bean);
                }
            }

            @Override
            public void onFailure(int code, String msg) {

            }
        });
    }
}
