package com.james.li.quickandroid;

import android.os.Bundle;

import com.james.li.quickandroid.Base.AppActivity;
import com.james.li.quickandroid.Base.BaseFragment;

public class MainActivity extends AppActivity {

    @Override
    protected BaseFragment getFirstFragment() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
