package com.vcyber.myframe.mvp.view;

import com.vcyber.myframe.base.BaseView;
import com.vcyber.myframe.bean.LoginBean;

/**
 * Create by zjl on 2019/5/6
 * ---- 主activity的网络请求回调接口类 ----
 */
public interface MainView extends BaseView {

    void getDataSuccess(LoginBean bean);
}
