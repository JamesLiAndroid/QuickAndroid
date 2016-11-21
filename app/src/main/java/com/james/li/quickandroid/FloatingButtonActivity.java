package com.james.li.quickandroid;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class FloatingButtonActivity  extends AppCompatActivity {

	ImageView imgIndex;
	ImageView imgNext;
	ImageView imgThird;

	BottomNavigationView navigationView;

	FloatingActionButton fab;

	CoordinatorLayout coordinatorLayout;
	RelativeLayout rl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_floating_button);

		imgIndex = (ImageView) findViewById(R.id.img_index);
		imgNext = (ImageView) findViewById(R.id.img_next);
		imgThird = (ImageView) findViewById(R.id.img_third);


		navigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
		navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
			@Override
			public boolean onNavigationItemSelected(@NonNull MenuItem item) {
				switch (item.getItemId()) {
					case R.id.action_index:
						//Toast.makeText(NavigationActivity.this, "index", Toast.LENGTH_SHORT).show();
						imgIndex.setVisibility(View.VISIBLE);
						imgNext.setVisibility(View.GONE);
						imgThird.setVisibility(View.GONE);
						break;
					case R.id.action_next:
						//Toast.makeText(NavigationActivity.this, "Next", Toast.LENGTH_SHORT).show();
						imgIndex.setVisibility(View.GONE);
						imgNext.setVisibility(View.VISIBLE);
						imgThird.setVisibility(View.GONE);
						break;
					case R.id.action_third:
						//Toast.makeText(NavigationActivity.this, "Third", Toast.LENGTH_SHORT).show();
						imgIndex.setVisibility(View.GONE);
						imgNext.setVisibility(View.GONE);
						imgThird.setVisibility(View.VISIBLE);
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
	}
}
