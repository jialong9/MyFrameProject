package com.vcyber.myframe.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * description ：
 * author : zjl
 * date : 2021/3/16
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    private LayoutInflater inflater;
    private List<T> data;
    private int layoutId;
    protected OnItemClickListener onItemClickListener;//单击事件
    protected OnItemLongClickListener onItemLongClickListener;//长按单击事件

    private boolean removRefresh = false;

    public BaseAdapter(Context context, int layoutId) {
        this.layoutId = layoutId;
        this.inflater = LayoutInflater.from(context);
    }

    /**
     * 设置数据
     */
    public void setData(List<T> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    /**
     * 设置数据
     */
    public void reFreshData(List<T> data) {
        this.data = data;
    }

    /**
     * 添加部分数据
     */
    public void addData(List<T> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public T getItemData(int position) {
        if (data != null && !data.isEmpty()) {
            return data.get(position);
        }
        return null;
    }

    public List<T> getAllData() {
        if (data != null && !data.isEmpty()) {
            return data;
        }
        return null;
    }

    /**
     * 删除单条数据
     */
    public void remove(int position) {
        if (data != null && !data.isEmpty()) {
            data.remove(position);
            notifyDataSetChanged();
        }
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder(inflater.inflate(layoutId, parent, false));
    }


    /**
     * 清除所有数据
     */
    public void clean() {
        if (data != null && !data.isEmpty()) {
            data.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        bindData(holder, data.get(position), position);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position, List<Object> payloads) {
//        super.onBindViewHolder(holder, position, payloads);
        if (payloads.size() == 0) {
            onBindViewHolder(holder, position);
        } else {
            if (payloads.get(0) instanceof Bundle) {
                Bundle payload = (Bundle) payloads.get(0);
                bindData(holder, data.get(position), position, payload);
            } else {
                super.onBindViewHolder(holder, position, payloads);
            }
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    protected abstract void bindData(BaseViewHolder holder, T data, int position);

    protected void bindData(BaseViewHolder holder, T data, int position, Bundle bundle) {
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListner(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public interface OnItemClickListener {
        void onItemClickListener(View v, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClickListener(View v, int position);
    }
}
