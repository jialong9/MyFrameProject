package com.vcyber.myframe.retorfit;


import android.net.ParseException;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.vcyber.myframe.base.BaseResultBean;
import com.vcyber.myframe.utils.log.LogUtil;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;


/**
 * Create by zjl on 2019/5/6
 * ---- 网络请求的结果回调 ----
 */
public abstract class ApiCallback<M extends BaseResultBean> extends DisposableObserver<M> {

    //对应HTTP的状态码
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;


    public abstract void onSuccess(M model);

    public abstract void onFailure(int code, String msg);


    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof SocketTimeoutException) {
            onFailure(0, "请求超时");
        } else if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            int code = httpException.code();
            switch (httpException.code()) {
                case UNAUTHORIZED:
                case FORBIDDEN:
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    onFailure(code, "网络错误");//均视为网络错误
                    break;
            }
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {//均视为解析错误
            onFailure(0, "未知错误");
        } else if (e instanceof UnknownHostException) {
            onFailure(0, "网络连接失败");
        } else if (e instanceof ConnectException) {
            onFailure(0, "网络连接失败");
        } else {
            onFailure(0, "网络错误");
        }
    }


    @Override
    public void onNext(M bean) {
        /*
         * 这次打印是bean类中定义的字段
         * 在HttpLoggingInterceptorM中273行还会有一次打印，打印所有后台返回字段
         */
        LogUtil.json(new Gson().toJson(bean));
        onSuccess(bean);
    }

    @Override
    public void onComplete() {

    }
}
