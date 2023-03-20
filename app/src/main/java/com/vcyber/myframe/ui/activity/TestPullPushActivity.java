package com.vcyber.myframe.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.header.FalsifyHeader;
import com.scwang.smartrefresh.layout.header.TwoLevelHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vcyber.myframe.R;
import com.vcyber.myframe.base.BaseAdapter;
import com.vcyber.myframe.base.BasePresenter;
import com.vcyber.myframe.base.BaseViewHolder;
import com.vcyber.myframe.base.MvpActivity;
import com.vcyber.myframe.databinding.ActivityTestPullBinding;
import com.vcyber.myframe.utils.log.LogUtil;

import java.util.ArrayList;
import java.util.List;


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
        binding.rvTestGlide.setLayoutManager(new LinearLayoutManager(this));
        TestAdapter adapter = new TestAdapter(this,R.layout.test_glide);
        binding.rvTestGlide.setAdapter(adapter);
        List<String> list = new ArrayList<>();
        list.add("http://img02.douquwl88.top/2022/08/03/e33f1277-5e19-43bd-9692-e6c421032b0e.jpeg");
        list.add("https://img0.baidu.com/it/u=1079119225,2413269024&fm=253&fmt=auto&app=138&f=JPEG?w=333&h=500");
        list.add("https://img0.baidu.com/it/u=2282968692,33864835&fm=253&fmt=auto&app=138&f=JPEG?w=281&h=500");
        list.add("https://img0.baidu.com/it/u=900584180,3622266176&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=750");
        list.add("https://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&word=%E6%B1%9F%E7%96%8F%E5%BD%B1%20%E5%96%B7%E8%A1%80&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=-1&hd=undefined&latest=undefined&copyright=undefined&cs=3412527704,2871696894&os=3106270017,515517115&simid=4068962087,664586458&pn=2&rn=1&di=7136437450519347201&ln=333&fr=&fmq=1389861203899_R&fm=&ic=0&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&is=0,0&istype=2&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&oriquery=%E6%B1%9F%E7%96%8F%E5%BD%B1&objurl=https%3A%2F%2Fgimg2.baidu.com%2Fimage_search%2Fsrc%3Dhttp%253A%252F%252Fjoy.online.sh.cn%252Fimages_origin%252Fattachement%252Fjpg%252Fsite1%252F20210929%252FIMG0025116ac9cf58291337871.jpg%26refer%3Dhttp%253A%252F%252Fjoy.online.sh.cn%26app%3D2002%26size%3Df9999%2C10000%26q%3Da80%26n%3D0%26g%3D0n%26fmt%3Dauto%3Fsec%3D1666521165%26t%3Db55bfc5e88f1c064d76c5bb9385505d6&rpstart=0&rpnum=0&adpicid=0&nojc=undefined&dyTabStr=MCwzLDEsMiw2LDQsNSw3LDgsOQ%3D%3D");
        adapter.setData(list);
    }

    private static class TestAdapter extends BaseAdapter<String> {
        private final Context context;

        public TestAdapter(Context context, int layoutId) {
            super(context, layoutId);
            this.context = context;
        }

        @Override
        protected void bindData(BaseViewHolder holder, String data, int position) {
           ImageView ivIcon =  holder.getImageView(R.id.iv_icon);
            Glide.with(context).load(data).into(ivIcon);
        }
    }
}
