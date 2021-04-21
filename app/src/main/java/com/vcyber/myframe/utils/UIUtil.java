package com.vcyber.myframe.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.vcyber.myframe.base.BaseApplication;
import com.vcyber.myframe.utils.log.LogUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Create by zjl on 2019/5/6
 * ---- 之前项目的一个工具类，有一部分方法可以使用 ----
 */
public final class UIUtil {

    private static final String TAG = "UIUtil";

    /**
     * 上下文的获取
     *
     * @return
     */
    public static Context getContext() {
        return BaseApplication.getInstance();
    }


    /**
     * 获取主线程Handler
     *
     * @return
     */
    public static Handler getMainThreadHandler() {
        return BaseApplication.getMainThreadHandler();
    }

    /**
     * @param v
     * @param l
     * @param t
     * @param r
     * @param b
     */
    public static void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v
                    .getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

    /**
     * 根据手机的分辨率从 dip 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, double dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dip
     */
    public static int px2dip(Context context, double pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5);
    }

    /**
     * 根据手机分辨率从sp 的单位转化为px(像素)
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 根据手机分辨率从px 的单位转化为sp(像素)
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 获取状态栏的高度
     *
     * @param window 窗口
     * @return 状态栏高度（单位：px）
     */
    public static int getStatusBarHeight(Window window) {
        int statusBarHeight = 0;
        if (null != window) {
            // 获取状态栏的高度
            Rect frame = new Rect();
            window.getDecorView().getWindowVisibleDisplayFrame(frame);
            statusBarHeight = frame.top;
        }
        if (0 == statusBarHeight) {
            statusBarHeight = 50;
        }
        return statusBarHeight;
    }

    /**
     *
     */
    public static int getStatusHeight(Context context) {

        int statusHeight = -1;
        try {
            Class clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    /**
     * 显示或者隐藏状态栏
     *
     * @param window
     * @param show   是否显示
     */
    public static void showOrHideStatusBar(Window window, boolean show) {
        if (window == null) {
            return;
        }

        WindowManager.LayoutParams lp = window.getAttributes();
        if (show) {
            lp.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            window.setAttributes(lp);
            window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        } else {
            lp.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            window.setAttributes(lp);
            window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }




    /**
     * 弹出软键盘
     *
     * @param context 上下文
     * @param view    在哪个视图上显示
     */
    public static void showSoftInput(Context context, View view) {
        if (context == null || view == null) {
            return;
        }

        final InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    /**
     * 弹出软键盘
     *
     * @param context   上下文
     * @param view      在哪个视图上显示
     * @param delayTime 延迟时间（单位：ms）
     */
    public static void showSoftInputDelay(final Context context,
                                          final View view, int delayTime) {
        if (context == null || view == null || delayTime < 0) {
            return;
        }

        if (delayTime == 0) {
            showSoftInput(context, view);
        } else {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {

                @Override
                public void run() {
                    showSoftInput(context, view);
                }
            }, delayTime);
        }
    }

    /**
     * 隐藏软键盘
     *
     * @param context 上下文
     * @param view    在哪个视图上显示
     */
    public static void hideSoftInput(Context context, View view) {
        if (context == null || view == null) {
            return;
        }

        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null && imm.isActive(view)) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * 隐藏软键盘
     *
     * @param activity
     * @author tianpengfei 2015-3-2
     */
    public static void hideSoftInput(Activity activity) {
        if (activity != null) {
            UIUtil.hideSoftInput(activity, activity.getCurrentFocus());
        }
    }

    /**
     * 输入法是否已激活
     *
     * @param context
     * @param packageName 输入法应用的包名
     * @return
     */
    public static boolean isInputMethodEnabled(Context context,
                                               String packageName) {
        return getEnableInputMethodInfor(context, packageName) != null;
    }

    /**
     * 获取已激活输入法的详细信息
     *
     * @param context
     * @param packageName 输入法应用的包名
     * @return
     */
    public static InputMethodInfo getEnableInputMethodInfor(Context context,
                                                            String packageName) {
        if (packageName == null) {
            return null;
        }
        final InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        List<InputMethodInfo> imeInfoList = imm.getEnabledInputMethodList();

        if (imeInfoList != null) {
            for (InputMethodInfo imeInfo : imeInfoList) {
                if (packageName.equals(imeInfo.getPackageName())) {
                    return imeInfo;
                }
            }
        }
        return null;
    }

    /**
     * 输入法是否已启用
     *
     * @param context
     * @param packageName 输入法应用的包名
     * @return
     */
    public static boolean isInputMethodInUse(Context context, String packageName) {
        InputMethodInfo imeInfo = getEnableInputMethodInfor(context,
                packageName);
        if (imeInfo != null) {
            String ourId = imeInfo.getId();
            // 当前输入法id
            String curId = android.provider.Settings.Secure.getString(
                    context.getContentResolver(),
                    android.provider.Settings.Secure.DEFAULT_INPUT_METHOD);

            if (ourId != null && ourId.equals(curId)) {
                return true;
            }
            return false;
        }
        return false;
    }

    private static class XY {
        // 含有2个元素的一维数组，表示距离屏幕左上角的点，此处作为一个域变量是为了避免重复new
        private static int[] locationOfViewOnScreen = new int[2];
    }

    /**
     * 判断触摸点是否在给定的view上
     *
     * @param event 触摸事件
     * @param view  给定的view
     * @return
     */
    public static boolean isInMyView(MotionEvent event, View view) {
        // 如果此时view被隐藏掉了，触摸点肯定不会落在此view上
        if (view.getVisibility() == View.GONE) {
            return false;
        }

        // 获取此view在屏幕上的位置（以屏幕左上角为参照点）
        view.getLocationOnScreen(XY.locationOfViewOnScreen);

        // 获取触摸点相对于屏幕左上角的偏移量
        float rawX = event.getRawX();
        float rawY = event.getRawY();

        // 如果触摸点处于此view的矩形区域内
        return rawX >= XY.locationOfViewOnScreen[0]
                && rawX <= (XY.locationOfViewOnScreen[0] + view.getWidth())
                && rawY >= XY.locationOfViewOnScreen[1]
                && rawY <= (XY.locationOfViewOnScreen[1] + view.getHeight());
    }

    /**
     * 设置在系统中改变字体大小不会影响我们的自体大小，如要考虑到美观
     * 此方法只在第一个Activity中调用就可以了，不需要在所有的Activity中调用
     *
     * @param activity
     */
    public static void remainFont(Activity activity) {
        Resources resources = activity.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.fontScale = 1.0f;
        resources.updateConfiguration(configuration, null);
    }

    /**
     * 设置屏幕方向
     *
     * @param activity 要设置屏幕方向的Activity
     * @param portrait 是否是竖屏
     */
    public static void changeScreenOrientation(Activity activity,
                                               boolean portrait) {
        if (activity == null) {
            return;
        }

        if (portrait) {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    /**
     * 截图 截图会引用一个bitmap对象，有时候会很大，需要释放调用view.destroyDrawingCache();
     *
     * @param view 要截图的对象
     * @return 截图
     */
    public static Bitmap snapshot(View view) {
        if (view == null) {
            return null;
        }

        // 先销毁旧的
        view.destroyDrawingCache();

        // 设置为可以截图
        view.setDrawingCacheEnabled(true);

        // 获取截图
        return view.getDrawingCache();
    }

    /**
     * @param listView
     * @return void
     * @Auther: zml
     * @Description: TODO解决listview嵌套listview问题
     * @Date:2015-9-17下午2:27:01
     * @说明 代码版权归 杭州反盗版中心有限公司 所有
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    /**
     * @param context
     * @param className
     * @param name
     * @return int
     * @Auther: wudd
     * @Description: 获取资源Id
     * @Date:2014-3-5下午12:22:22
     * @说明 代码版权归 杭州反盗版中心有限公司 所有
     */
    @SuppressWarnings("rawtypes")
    public static int getIdByName(Context context, String className, String name) {
        String packageName = context.getPackageName();
        Class r = null;
        int id = 0;
        try {
            r = Class.forName(packageName + ".R");

            Class[] classes = r.getClasses();
            Class desireClass = null;

            for (int i = 0; i < classes.length; ++i) {
                if (classes[i].getName().split("\\$")[1].equals(className)) {
                    desireClass = classes[i];
                    break;
                }
            }

            if (desireClass != null)
                id = desireClass.getField(name).getInt(desireClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return id;
    }

    /**
     * @param view
     * @return Bitmap
     * @Auther: zml
     * @Date:2015-10-27下午5:38:32
     * @说明 代码版权归 杭州反盗版中心有限公司 所有
     */
    public static Bitmap convertViewToBitmap(View view) {
        view.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();

        return bitmap;
    }

    /**
     * @param activity
     * @return
     * @Description: 获取屏幕的高
     */
    public static int getWindowHeight(Activity activity) {
        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric.widthPixels;

    }

    /**
     * @param activity
     * @return
     * @Description: 获取屏幕的宽
     */
    public static int getWindowWidth(Activity activity) {
        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric.heightPixels;

    }

}
