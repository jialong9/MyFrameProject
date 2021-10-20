package com.vcyber.myframe.mvp.model;

import com.vcyber.myframe.ApiStore;
import com.vcyber.myframe.base.BaseModel;
import com.vcyber.myframe.bean.XingTuBean;
import com.vcyber.myframe.retorfit.AppClient;

import io.reactivex.Observable;

/**
 * description ：
 * author : zjl
 * date : 9/6/21
 */
public class TestJsonModel implements BaseModel {
    public Observable<XingTuBean> getXingTuData(int page){
        return AppClient.retrofit().create(ApiStore.class).getXingTuData(page);
    }
} 