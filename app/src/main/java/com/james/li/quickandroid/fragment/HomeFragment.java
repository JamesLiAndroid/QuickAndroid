package com.james.li.quickandroid.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.james.li.quickandroid.Base.LazyFragment;
import com.james.li.quickandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsy-android on 10/16/16 in zsl-tech.
 */
public class HomeFragment extends LazyFragment {
    private TextView tvLoading;
    private RelativeLayout rlContent;

    private TabLayout tabLayout;
    private ViewPager viewPagerP;

    private int tabIndex;
    public static final String INTENT_INT_INDEX="index";

    private List<LazyFragment> list = new ArrayList<>();
    private MyFragmentPagerAdapter adapter;

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
        setContentView(R.layout.fragment_home);
        tabIndex = getArguments().getInt(INTENT_INT_INDEX);
        rlContent = (RelativeLayout) findViewById(R.id.rl_content);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPagerP = (ViewPager) findViewById(R.id.view_pager_p);
        tvLoading = (TextView) findViewById(R.id.tv_loading);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        MoreFragment fragment1 = MoreFragment.newInstance(1, false);
        MoreFragment fragment2 = MoreFragment.newInstance(2, false);
        MoreFragment fragment3 = MoreFragment.newInstance(3, false);

        list.add(fragment1);
        list.add(fragment2);
        list.add(fragment3);

        adapter = new MyFragmentPagerAdapter(getChildFragmentManager(), list);
        viewPagerP.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPagerP);
        viewPagerP.setOffscreenPageLimit(list.size() - 1);
        getData();
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
            rlContent.setVisibility(View.VISIBLE);
        }
    };
}
