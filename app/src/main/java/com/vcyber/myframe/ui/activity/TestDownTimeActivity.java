package com.vcyber.myframe.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.widget.TextView;

import com.vcyber.myframe.R;
import com.vcyber.myframe.base.BaseAdapter;
import com.vcyber.myframe.base.BaseViewHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * description :
 * author : zjl
 * date : 3/21/23
 */
public class TestDownTimeActivity extends AppCompatActivity {
    private DownAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_downtime);
        RecyclerView rvDown = findViewById(R.id.rv_down);
        rvDown.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DownAdapter(this, R.layout.item_downtime, System.currentTimeMillis());
        rvDown.setAdapter(adapter);
        List<Long> longs = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            longs.add(System.currentTimeMillis() + (i + 1) * 60 * 1000);
        }
        adapter.setData(longs);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public static class DownAdapter extends BaseAdapter<Long> {
        private SparseArray<CountDownTimer> countDownMap;
        public CountDownTimer countDownTimer;
        private long initTime;
        private final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        public DownAdapter(Context context, int layoutId, long initTime) {
            super(context, layoutId);
            this.initTime = initTime;
            countDownMap = new SparseArray<>();

        }

        @Override
        protected void bindData(BaseViewHolder holder, Long data, int position) {
            TextView tvTime = holder.getTextView(R.id.tv_time);
            long newTime = data - (System.currentTimeMillis() - initTime);

            countDownTimer = new CountDownTimer(newTime, 1000) {
                public void onTick(long millisUntilFinished) {
                    tvTime.setText(sdf.format(new Date(millisUntilFinished)));
                }

                public void onFinish() {
                    tvTime.setText("00:00");
                }
            }.start();

            countDownMap.put(holder.hashCode(), countDownTimer);

            Log.e("zjl==", data + "");
        }

        @Override
        public void onViewRecycled(@NonNull BaseViewHolder holder) {
            super.onViewRecycled(holder);
            if (countDownMap != null) {
                CountDownTimer cdt = countDownMap.get(holder.hashCode());
                if (cdt != null) {
                    cdt.cancel();
                }
            }
        }
    }
}