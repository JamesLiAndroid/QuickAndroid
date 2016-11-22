package com.james.li.quickandroid;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.james.li.quickandroid.Base.LazyFragment;
import com.james.li.quickandroid.fragment.CustomFragment;
import com.james.li.quickandroid.fragment.HomeFragment;
import com.james.li.quickandroid.fragment.MoreFragment;
import com.james.li.quickandroid.fragment.MyFragmentPagerAdapter;
import com.james.li.quickandroid.view.CustomViewPager;
import java.util.ArrayList;
import java.util.List;

public class BottomNavChangeActivity extends AppCompatActivity {

	ImageView imgIndex;
	ImageView imgNext;
	ImageView imgThird;

	BottomNavigationView navigationView;
	FloatingActionButton fab;
	CoordinatorLayout coordinatorLayout;
	RelativeLayout rl;

	CustomViewPager viewPager;
	List<LazyFragment> fragments = new ArrayList<>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bottom_nav_change);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		imgIndex = (ImageView) findViewById(R.id.img_index);
		imgNext = (ImageView) findViewById(R.id.img_next);
		imgThird = (ImageView) findViewById(R.id.img_third);

		navigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
		navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
			@Override
			public boolean onNavigationItemSelected(@NonNull MenuItem item) {
				switch (item.getItemId()) {
					case R.id.action_index:
						viewPager.setCurrentItem(0);
						//Toast.makeText(NavigationActivity.this, "index", Toast.LENGTH_SHORT).show();
						/*imgIndex.setVisibility(View.VISIBLE);
						imgNext.setVisibility(View.GONE);
						imgThird.setVisibility(View.GONE);*/
						break;
					case R.id.action_next:
						viewPager.setCurrentItem(1);
						//Toast.makeText(NavigationActivity.this, "Next", Toast.LENGTH_SHORT).show();
						/*imgIndex.setVisibility(View.GONE);
						imgNext.setVisibility(View.VISIBLE);
						imgThird.setVisibility(View.GONE);*/
						break;
					case R.id.action_third:
						viewPager.setCurrentItem(2);
						//Toast.makeText(NavigationActivity.this, "Third", Toast.LENGTH_SHORT).show();
						/*imgIndex.setVisibility(View.GONE);
						imgNext.setVisibility(View.GONE);
						imgThird.setVisibility(View.VISIBLE);*/
						break;
				}
				return true;
			}
		});

		rl = (RelativeLayout) findViewById(R.id.content);
		coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);

		fab = (FloatingActionButton) findViewById(R.id.btn_floating_action);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View view) {
				Snackbar.make(rl, "FAB",Snackbar.LENGTH_SHORT).show();
			}
		});

		//
		viewPager = (CustomViewPager) findViewById(R.id.view_pager);
		viewPager.setEnableScroll(true);

		HomeFragment homeFragment = HomeFragment.newInstance(1, true);
		fragments.add(homeFragment);
		CustomFragment cusFragment = CustomFragment.newInstance(2, true);
		fragments.add(cusFragment);
		MoreFragment moreFragment = MoreFragment.newInstance(3, true);
		fragments.add(moreFragment);

		viewPager.setOffscreenPageLimit(fragments.size() - 1);

		MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments);
		viewPager.setAdapter(adapter);

	}
}
