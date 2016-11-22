package com.james.li.quickandroid.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.james.li.quickandroid.Base.BaseFragment;

import com.james.li.quickandroid.Base.LazyFragment;
import java.util.List;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<LazyFragment> fragmentList;

    public MyFragmentPagerAdapter(FragmentManager fm, List<LazyFragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

}
