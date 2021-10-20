package com.vcyber.myframe.mvp.view;

import com.vcyber.myframe.base.BaseView;
import com.vcyber.myframe.bean.XingTuBean;

import java.util.List;

/**
 * description ：
 * author : zjl
 * date : 9/6/21
 */
public interface TestJsonView extends BaseView {
    void getXingTuDataSuccess(List<XingTuBean.XingTuData.Authors> bean);
}
