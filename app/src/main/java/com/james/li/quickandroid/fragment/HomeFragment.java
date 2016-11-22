package com.james.li.quickandroid.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.james.li.quickandroid.Base.LazyFragment;
import com.james.li.quickandroid.R;
import java.util.Random;

/**
 * Created by lsy-android on 10/16/16 in zsl-tech.
 */
public class HomeFragment extends LazyFragment {
    private TextView tvLoading;
    private ImageView ivContent;
    private int tabIndex;
    public static final String INTENT_INT_INDEX="index";

    private SwipeRefreshLayout swipeRefreshLayout;

    public static HomeFragment newInstance(int tabIndex,boolean isLazyLoad) {
        Bundle args = new Bundle();
        args.putInt(INTENT_INT_INDEX, tabIndex);
        args.putBoolean(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, isLazyLoad);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_tabmain_item);
        tabIndex = getArguments().getInt(INTENT_INT_INDEX);
        ivContent = (ImageView) findViewById(R.id.iv_content);
        tvLoading = (TextView) findViewById(R.id.tv_loading);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setEnabled(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override public void onRefresh() {
                refreshItems();
            }
        });
        getData();
    }

    private void refreshItems() {
        int i = createRandom(1, 4);
        int id = 0;
        switch (i) {
            case 1:
                id=R.drawable.a;
                break;
            case 2:
                id=R.drawable.b;
                break;
            case 3:
                id=R.drawable.c;
                break;
            case 4:
                id=R.drawable.d;
                break;
        }

        ivContent.setImageResource(id);
        stopRefresh();
    }

    private void stopRefresh() {
        // Stop refresh animation
        swipeRefreshLayout.setRefreshing(false);
    }

    private int createRandom(int min, int max) {
        Random random = new Random();
        int s = random.nextInt(max)  %  (max - min + 1) + min;
        return s;
    }

    private void getData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //异步处理加载数据
                //...
                //完成后，通知主线程更新UI
                handler.sendEmptyMessageDelayed(1, 2000);
            }
        }).start();
    }

    @Override
    public void onDestroyViewLazy() {
        super.onDestroyViewLazy();
        handler.removeMessages(1);
    }

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            tvLoading.setVisibility(View.GONE);
            int id=0;
            switch (tabIndex){
                case 1:
                    id=R.drawable.a;
                    break;
                case 2:
                    id=R.drawable.b;
                    break;
                case 3:
                    id=R.drawable.c;
                    break;
                case 4:
                    id=R.drawable.d;
                    break;
            }
            ivContent.setImageResource(id);
            ivContent.setVisibility(View.VISIBLE);
        }
    };
}
