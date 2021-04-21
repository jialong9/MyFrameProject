package com.vcyber.myframe.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.vcyber.myframe.R;
import com.vcyber.myframe.base.BasePresenter;
import com.vcyber.myframe.base.MvpActivity;
import com.vcyber.myframe.utils.GlideUtil;

import butterknife.BindView;


/**
 * Create by zjl on 2019/5/8
 * ---- 测试网络状态的activity ----
 */
public class TestNetWordActivity extends MvpActivity {

    @BindView(R.id.im_load1)
    ImageView im_load1;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_test_netword;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        setNoStatusBar(false);
        GlideUtil.loadPicture(this, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1557493304433&di=b4d686ad5f5c6428d377257542e4856e&imgtype=0&src=http%3A%2F%2Fp1.qhmsg.com%2Ft01fc4a1a7fce9d2569.jpg", im_load1);

    }

    private static class JavaSingleton {
        private static volatile JavaSingleton javaSingleton;

        JavaSingleton() {
        }

        public static JavaSingleton getInstance() {
            if (javaSingleton == null) {
                synchronized (JavaSingleton.class) {
                    if (javaSingleton == null) {
                        javaSingleton = new JavaSingleton();
                    }
                }
            }
            return javaSingleton;
        }
    }
}
