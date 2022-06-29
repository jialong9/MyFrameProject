package com.vcyber.myframe.ui.activity;

import android.os.Bundle;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vcyber.myframe.R;
import com.vcyber.myframe.base.BasePresenter;
import com.vcyber.myframe.base.MvpActivity;
import com.vcyber.myframe.databinding.ActivityTestPullBinding;
import com.vcyber.myframe.utils.log.LogUtil;


/**
 * Create by zjl on 2019/5/10
 * ---- 测试下拉刷新和上拉加载 ----
 */
public class TestPullPushActivity extends MvpActivity<BasePresenter<?>, ActivityTestPullBinding> {


    @Override
    protected BasePresenter<?> initPresenter() {
        return new BasePresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_test_pull;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        binding.refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout a) {
                LogUtil.i("下拉刷新成功");
                binding.refreshLayout.finishRefresh(500);
            }
        });
        binding.refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout a) {
                LogUtil.i("上拉加载成功");
                binding.refreshLayout.finishLoadMore(500);
            }
        });
    }
}
