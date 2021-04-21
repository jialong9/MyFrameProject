package com.vcyber.myframe;


import com.vcyber.myframe.bean.LoginBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Create by zjl on 2019/5/6
 * ---- 网络请求和其他配置的api ----
 */
public interface ApiStore {
    interface Url {
        String BASE_URL = "http://202.99.107.207/";
    }

    interface Config {
        String ContentType = "application/json";
    }


    /**
     * 帐号验证码登录
     */
    @POST("api/account/userloginbypwd")
    Observable<LoginBean> userLoginByCode(@Body RequestBody body);
}
