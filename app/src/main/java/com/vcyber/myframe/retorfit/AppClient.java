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
                                    .addHeader("id", "")
                                    .addHeader("access_token", "")
                                    .addHeader("loginname", "")
                                    .addHeader("sign", md5("" + "vcyber_@#wesdxt*#@20082018"))
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
