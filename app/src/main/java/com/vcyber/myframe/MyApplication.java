package com.vcyber.myframe;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.vcyber.myframe.base.BaseApplication;
import com.vcyber.myframe.retorfit.AppClient;
import com.vcyber.myframe.utils.ActivityManagerUtil;

/**
 * Create by zjl on 2019/5/7
 * ---- 程序的application ----
 */
public class MyApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化Logger
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)
                .tag("mLogger")
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, @Nullable String tag) {
                return BuildConfig.DEBUG;
            }
        });
        //设置网络请求baseUrl
        AppClient.setApiServerUrl(ApiStore.Url.BASE_URL);
        getActivityStatus();
    }

    /**
     * 获取当前启动的activity的生命周期
     */
    private void getActivityStatus() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                ActivityManagerUtil.getInstance().addActivity(activity);
                Logger.i("当前onActivityCreated的是----------" + activity.getClass().getSimpleName());
            }

            @Override
            public void onActivityStarted(Activity activity) {
                Logger.i("当前onActivityStarted的是----------" + activity.getClass().getSimpleName());
            }

            @Override
            public void onActivityResumed(Activity activity) {
                Logger.i("当前onActivityResumed的是----------" + activity.getClass().getSimpleName());
            }

            @Override
            public void onActivityPaused(Activity activity) {
                Logger.i("当前onActivityPaused的是----------" + activity.getClass().getSimpleName());
            }

            @Override
            public void onActivityStopped(Activity activity) {
                Logger.i("当前onActivityStopped的是----------" + activity.getClass().getSimpleName());
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                Logger.i("当前onActivitySaveInstanceState的是----------" + activity.getClass().getSimpleName());
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                ActivityManagerUtil.getInstance().killActivity(activity);
                Logger.i("当前onActivityDestroyed的是----------" + activity.getClass().getSimpleName());
            }
        });
    }
}
