package com.vcyber.myframe;


import com.vcyber.myframe.bean.LoginBean;
import com.vcyber.myframe.bean.XingTuBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Create by zjl on 2019/5/6
 * ---- 网络请求和其他配置的api ----
 */
public interface ApiStore {
    interface Url {
//        String BASE_URL = "http://202.99.107.207/";
        String BASE_URL = "https://www.xingtu.cn/";
    }
    interface Config {
        String ContentType = "application/json";
    }


    /**
     * 帐号验证码登录
     */
    @POST("api/account/userloginbypwd")
    Observable<LoginBean> userLoginByCode(@Body RequestBody body);

    //剧情搞笑 97 2866
    //生活 26 445
    //时尚 6  258
    //母婴亲子 55 508
    //测评 15 200
    //旅行 27 269
    //汽车 31 227
    //运动健身 60 329
    //萌宠 11 348
    //游戏 23 603
    //家居家装 75 78。。
    //音乐 41 449。。
    //舞蹈 46 243。。
    //二次元 25 471。。
    //科技数码 64 152。。
    //教育培训 68 283。。
    //才艺技能 79 308。。
    //影视娱乐 85 377。。
    //艺术文化 87 278。。
    //财经投资 91 100。。
    //房产 139 18。。
    //情感 100 342。。
    //三农 95 81。。
    //园艺 102 4。。
    //随拍 130 610。。
    //图文控 137 5 。。
//    @GET("v/api/demand/author_list/?limit=30&tag=97&need_detail=true&platform_source=1&order_by=score&disable_replace_keyword=false&marketing_target=1&task_category=1&fans_min=1000000&price_min=10000&is_filter=true")
    @GET("v/api/demand/author_list/?limit=30&need_detail=true&platform_source=1&order_by=score&disable_replace_keyword=false&marketing_target=3&task_category=1&is_filter=false")
    Observable<XingTuBean> getXingTuData(@Query("page") int page);

}
