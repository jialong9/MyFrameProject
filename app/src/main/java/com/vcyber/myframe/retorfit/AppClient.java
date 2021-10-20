package com.vcyber.myframe.retorfit;

import android.text.TextUtils;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.vcyber.myframe.BuildConfig;
import com.vcyber.myframe.utils.UIUtil;

import java.io.IOException;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Create by zjl on 2019/5/6
 * ---- 发送网络请求 ----
 */
public class AppClient {
    private static Retrofit mRetrofit;

    /**
     * 服务端域名或ip
     */
    private static String API_SERVER_URL;
    /**
     * 连接超时时间,单位  秒
     */
    private static final int CONN_TIMEOUT = 10;
    /**
     * 读取超时时间,单位  秒
     */
    private static final int READ_TIMEOUT = 90;
    /**
     * 设置写的超时时间,单位  秒
     */
    private static final int WRITE_TIMEOUT = 90;

    public static void setApiServerUrl(String apiServerUrl) {
        API_SERVER_URL = apiServerUrl;
    }


    public static Retrofit retrofit() {
        if (mRetrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            if (BuildConfig.DEBUG) {
                // Log信息拦截器
                HttpLoggingInterceptorM loggingInterceptor = new HttpLoggingInterceptorM();
                loggingInterceptor.setLevel(HttpLoggingInterceptorM.Level.BODY);
                //设置 Debug Log 模式
                builder.addInterceptor(loggingInterceptor);
            }
            builder.connectTimeout(CONN_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request request = chain.request()
                                    .newBuilder()
                                    .addHeader("Content-Type", "application/json; charset=UTF-8")
                                    .addHeader("Accept", "application/json")
//                                    .addHeader("id", "")
//                                    .addHeader("access_token", "")
//                                    .addHeader("loginname", "")
//                                    .addHeader("sign", md5("" + "vcyber_@#wesdxt*#@20082018"))
                                    .addHeader("Cookie","gftoken=MjY1ZmQ2ZmY4YnwxNjMyMzc5NjE4OTd8fDAGBgYGBgY; tt_webid=7002533443553838605; csrftoken=tFKE22sx2bG8VfkJzdnvWbmgWbzf1d95; MONITOR_WEB_ID=6ff14595-462e-4948-9445-c7133f136f10; passport_csrf_token_default=ba46f8ed7551719b071e39b6f4b72587; passport_auth_status=74860ba4688468c6453e78acac19db98%2Cfa842825d30df8f3b5699fa5d6f60fd4; passport_auth_status_ss=74860ba4688468c6453e78acac19db98%2Cfa842825d30df8f3b5699fa5d6f60fd4; sid_guard=265fd6ff8bf9480155ae7e87f9f18143%7C1632379617%7C5184000%7CMon%2C+22-Nov-2021+06%3A46%3A57+GMT; uid_tt=06ba993eddc223426359c9d3dbc260f9; uid_tt_ss=06ba993eddc223426359c9d3dbc260f9; sid_tt=265fd6ff8bf9480155ae7e87f9f18143; sessionid=265fd6ff8bf9480155ae7e87f9f18143; sessionid_ss=265fd6ff8bf9480155ae7e87f9f18143; sid_ucp_v1=1.0.0-KGI3NjZjNWRiYTI1MjU4YjljNzEwN2JjNDkwMjdmNWU5YjMzNGM5ZjYKFwi9neCaloyCBhDhxbCKBhimDDgCQPEHGgJsZiIgMjY1ZmQ2ZmY4YmY5NDgwMTU1YWU3ZTg3ZjlmMTgxNDM; ssid_ucp_v1=1.0.0-KGI3NjZjNWRiYTI1MjU4YjljNzEwN2JjNDkwMjdmNWU5YjMzNGM5ZjYKFwi9neCaloyCBhDhxbCKBhimDDgCQPEHGgJsZiIgMjY1ZmQ2ZmY4YmY5NDgwMTU1YWU3ZTg3ZjlmMTgxNDM; passport_csrf_token=ba46f8ed7551719b071e39b6f4b72587; _tea_utm_cache_1442={%22utm_source%22:%22oceanengine%22}; _tea_utm_cache_1581={%22utm_source%22:%22oceanengine%22}; star_sessionid=1150cd2a90f286b1eb96d894841375ac")
                                    .build();
                            return chain.proceed(request);
                        }
                    }).cache(new Cache(UIUtil.getContext().getCacheDir(), 20 * 1024 * 1024));
            OkHttpClient okHttpClient = builder.build();
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(API_SERVER_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();

            GsonBuilder builderTime = new GsonBuilder();

            // Register an adapter to manage the date types as long values
            builderTime.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                @Override
                public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                    return new Date(json.getAsJsonPrimitive().getAsLong());
                }
            });

            builderTime.create();
        }
        return mRetrofit;
    }

    private static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            StringBuilder result = new StringBuilder();
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result.append(temp);
            }
            return result.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
