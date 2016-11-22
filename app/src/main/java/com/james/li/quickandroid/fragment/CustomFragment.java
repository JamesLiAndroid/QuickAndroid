package com.james.li.quickandroid.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.james.li.quickandroid.Base.LazyFragment;
import com.james.li.quickandroid.R;
import com.james.li.quickandroid.adapter.CustomRecylerAdapter;
import com.james.li.quickandroid.bean.CardDataUtils;
import com.james.li.quickandroid.bean.CardViewBean;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsy-android on 10/16/16 in zsl-tech.
 */
public class CustomFragment extends LazyFragment {
    private TextView tvLoading;
    private ImageView ivContent;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    CustomRecylerAdapter adapter;

    private int lastVisibleItem;

    private List<CardViewBean> beans = new ArrayList<>();

    private int tabIndex;
    public static final String INTENT_INT_INDEX="index";


    public static CustomFragment newInstance(int tabIndex,boolean isLazyLoad) {

        Bundle args = new Bundle();
        args.putInt(INTENT_INT_INDEX, tabIndex);
        args.putBoolean(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, isLazyLoad);
        CustomFragment fragment = new CustomFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_custom);
        tabIndex = getArguments().getInt(INTENT_INT_INDEX);
        ivContent = (ImageView) findViewById(R.id.iv_content);
        tvLoading = (TextView) findViewById(R.id.tv_loading);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_to_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override public void run() {
                        List<CardViewBean> newBeans = CardDataUtils.getCardViewLimitDatas(3);
                        adapter.refresh(newBeans);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recyler);
        //添加分隔线
        //recyclerView.addItemDecoration(new AdvanceDecoration(this, OrientationHelper.VERTICAL));
        final LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = manager.findLastVisibleItemPosition();
            }

            @Override public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                 if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == adapter.getItemCount()) {
                     adapter.changeMoreStatus(CustomRecylerAdapter.LOADING_MORE);
                     handler.postDelayed(new Runnable() {
                         @Override public void run() {
                             List<CardViewBean> newBeans = CardDataUtils.getCardViewDownDatas(4);
                             adapter.loadMore(newBeans);
                             adapter.changeMoreStatus(CustomRecylerAdapter.PULLUP_LOAD_MORE);
                         }
                     }, 3000);
                 }
            }
        });

        getData();
    }

    private void getData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //异步处理加载数据
                beans = CardDataUtils.getCardViewDatas();
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
            Log.d("TAG","hhhhh: "+beans.get(0).toString());
            adapter = new CustomRecylerAdapter(beans, getActivity());
            recyclerView.setAdapter(adapter);
            swipeRefreshLayout.setVisibility(View.VISIBLE);
        }
    };
}
