package com.james.li.quickandroid;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.Toast;

import com.james.li.quickandroid.Base.AppActivity;
import com.james.li.quickandroid.Base.BaseFragment;
import com.james.li.quickandroid.example.MoreFragment;
import com.james.li.quickandroid.example.MyFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppActivity {

    @Override
    protected BaseFragment getFirstFragment() {
        return fragmentList.get(0);
    }

    ViewPager viewPager;
    private List<BaseFragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewPager2);

        fragmentList = new ArrayList<>();
        fragmentList.add(MoreFragment.newInstance(1,false));
        fragmentList.add(MoreFragment.newInstance(2,true));
        fragmentList.add(MoreFragment.newInstance(3,true));
        fragmentList.add(MoreFragment.newInstance(4,true));
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(supportFragmentManager, fragmentList);
        viewPager.setAdapter(myFragmentPagerAdapter);

        viewPager.setOffscreenPageLimit(4);
        // 防止getFirstFragment回调时出现空指针的现象
        super.onCreate(savedInstanceState);

        // 添加Fragment到回退栈
        ((BaseApplication)getApplication()).addActivity(this);
    }

    private long firstClick;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode==KeyEvent.KEYCODE_BACK){
            if(System.currentTimeMillis()-firstClick>2000){
                firstClick=System.currentTimeMillis();
                Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_LONG).show();;
            }else{
                System.exit(0);
            }
            return true;
        }
        return false;
    }
}
