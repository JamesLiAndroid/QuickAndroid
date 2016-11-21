package com.james.li.quickandroid;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class NavigationActivity extends AppCompatActivity {

    ImageView imgIndex;
    ImageView imgNext;
    ImageView imgThird;

    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

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


    }
}
