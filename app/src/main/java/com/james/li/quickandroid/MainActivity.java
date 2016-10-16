package com.james.li.quickandroid;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.james.li.quickandroid.Base.AppActivity;
import com.james.li.quickandroid.Base.BaseFragment;

import java.util.List;

public class MainActivity extends AppActivity {

    @Override
    protected BaseFragment getFirstFragment() {
        return null;
    }

    ViewPager viewPager;
    private List<BaseFragment> fragmentList;
    Button btnError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //viewPager = (ViewPager) findViewById(R.id.viewPager2);
        btnError = (Button) findViewById(R.id.button);
        btnError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 认为制造Error
                throw new RuntimeException("Boom!");
            }
        });
//
//        fragmentList = new ArrayList<>();
//        fragmentList.add(MoreFragment.newInstance(1,false));
//        fragmentList.add(MoreFragment.newInstance(2,false));
//        fragmentList.add(MoreFragment.newInstance(3,false));
//        fragmentList.add(MoreFragment.newInstance(4,false));
//
//        FragmentManager supportFragmentManager = getSupportFragmentManager();
//        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(supportFragmentManager, fragmentList);
//        viewPager.setAdapter(myFragmentPagerAdapter);
//
//        viewPager.setOffscreenPageLimit(4);
        // 防止getFirstFragment回调时出现空指针的现象


        // 添加Fragment到回退栈
        ((BaseApplication)getApplication()).addActivity(this);
    }

    private long firstClick;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            if(System.currentTimeMillis()-firstClick>2000){
                firstClick=System.currentTimeMillis();
                Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_LONG).show();;
            }else{
                this.getApplication().onTerminate();
            }
            return true;
        }
        return false;
    }
}
