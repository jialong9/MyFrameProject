package com.vcyber.myframe.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.vcyber.myframe.R;
import com.vcyber.myframe.base.BasePresenter;
import com.vcyber.myframe.base.MvpActivity;
import com.vcyber.myframe.utils.GlideUtil;

import butterknife.BindView;

/**
 * Create by zjl on 2019/5/10
 * ---- 测试glide加载图片 ----
 */
public class TestGlideActivity extends MvpActivity {

    @BindView(R.id.im_load1)
    ImageView im_load1;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_test_glide;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
//        RequestOptions requestOptions = new RequestOptions()
//                .placeholder(R.mipmap.icon_glide_default);//占位符
//
//        Glide.with(this)//初始化
//                .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1557493304433&di=b4d686ad5f5c6428d377257542e4856e&imgtype=0&src=http%3A%2F%2Fp1.qhmsg.com%2Ft01fc4a1a7fce9d2569.jpg")//图片地址
//                .apply(requestOptions)//支持项
//                .into(im_load1);//view
        GlideUtil.loadPicture(this, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1557493304433&di=b4d686ad5f5c6428d377257542e4856e&imgtype=0&src=http%3A%2F%2Fp1.qhmsg.com%2Ft01fc4a1a7fce9d2569.jpg", im_load1);
    }
}
