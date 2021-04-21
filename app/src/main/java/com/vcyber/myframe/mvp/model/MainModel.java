package com.vcyber.myframe.mvp.model;

import com.google.gson.Gson;
import com.vcyber.myframe.ApiStore;
import com.vcyber.myframe.base.BaseModel;
import com.vcyber.myframe.bean.LoginBean;
import com.vcyber.myframe.retorfit.AppClient;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Create by zjl on 2019/5/6
 * ---- 主activity的model ----
 */
public class MainModel implements BaseModel {

    /**
     * 测试的网络请求 这是一个登陆的接口
     *
     * @param loginName 用户名
     * @param pwd       密码
     * @return 返回Observable对象
     */
    public Observable<LoginBean> getDataFromNet(String loginName, String pwd) {
        Map<String, String> map = new HashMap<>();
        map.put("loginname", loginName);
        map.put("pwd", pwd);
        RequestBody body = RequestBody.create(MediaType.parse(ApiStore.Config.ContentType), new Gson().toJson(map));
        return AppClient.retrofit().create(ApiStore.class).userLoginByCode(body);
    }
}
