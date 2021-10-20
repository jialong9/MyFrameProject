package com.vcyber.myframe.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import com.vcyber.myframe.utils.log.LogUtil;

import java.util.Stack;

/**
 * Create by zjl on 2019/5/8
 * ---- activity管理工具 ----
 */
public class ActivityManagerUtil {
    private static Stack<Activity> mActivityStack;
    private static ActivityManagerUtil mActivityManagerUtils;

    private ActivityManagerUtil() {
    }

    /**ge
     * 单一实例
     */
    public static ActivityManagerUtil getInstance() {
        if (mActivityManagerUtils == null) {
            mActivityManagerUtils = new ActivityManagerUtil();
        }
        return mActivityManagerUtils;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (mActivityStack == null) {
            mActivityStack = new Stack<>();
        }
        mActivityStack.add(activity);
    }

    /**
     * 获取栈顶Activity（堆栈中最后一个压入的）
     */
    public static Activity getTopActivity() {
        return mActivityStack.lastElement();
    }

    /**
     * 结束栈顶Activity（堆栈中最后一个压入的）
     */
    private void killTopActivity() {
        Activity activity = mActivityStack.lastElement();
        killActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void killActivity(Activity activity) {
        if (activity != null) {
            mActivityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void killActivity(Class<?> cls) {
        for (int i = mActivityStack.size() - 1; i > 0; i--) {
            if (mActivityStack.get(i).getClass().equals(cls)) {
                killActivity(mActivityStack.get(i));
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void killAllActivity() {
        for (int i = 0, size = mActivityStack.size(); i < size; i++) {
            if (null != mActivityStack.get(i)) {
                mActivityStack.get(i).finish();
            }
        }
        mActivityStack.clear();
    }

    /**
     * 退出应用程序
     */
    @SuppressWarnings("deprecation")
    public void AppExit(Context context) {
        try {
            killAllActivity();
            ActivityManager activityMgr = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            assert activityMgr != null;
            activityMgr.restartPackage(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
        }
    }

    public static void getActivityStackInfos() {
        Stack<Activity> mActivityStack = ActivityManagerUtil.mActivityStack;
        LogUtil.i("Activity栈中有 " + mActivityStack.size() + "个Activity");
        for (int i = 0; i < mActivityStack.size(); i++) {
            Activity activity = mActivityStack.get(i);
            System.out.println(activity.getClass().getSimpleName());
        }
    }

    public static int activityNum() {
        return ActivityManagerUtil.mActivityStack.size();
    }

    /**
     * 结束主Activity上面所有的Activity
     */
    public void killAllTopActivity() {
        Stack<Activity> mActivityStack = ActivityManagerUtil.mActivityStack;
        for (int i = mActivityStack.size() - 1; i > 0; i--) {
            killTopActivity();
        }
    }

    /**
     * 获取指定类名的Activity
     */
    public Activity getActivity(Class<?> cls) {
        for (int i = mActivityStack.size() - 1; i > 0; i--) {
            if (mActivityStack.get(i).getClass().equals(cls)) {
                return (mActivityStack.get(i));
            }
        }
        return null;
    }
}
