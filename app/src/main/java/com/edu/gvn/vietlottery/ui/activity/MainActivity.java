package com.edu.gvn.vietlottery.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.edu.gvn.vietlottery.R;
import com.edu.gvn.vietlottery.adapter.LotteryPagerAdapter;
import com.edu.gvn.vietlottery.service.PushNotiService;

public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName();

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private LotteryPagerAdapter mLottAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabLayout = (TabLayout) findViewById(R.id.tab_lottery);
        mViewPager = (ViewPager) findViewById(R.id.viewpager_lottery);

        mLottAdapter = new LotteryPagerAdapter(this, getSupportFragmentManager());
        mViewPager.setAdapter(mLottAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        startService(new Intent(this, PushNotiService.class));

    }
}
