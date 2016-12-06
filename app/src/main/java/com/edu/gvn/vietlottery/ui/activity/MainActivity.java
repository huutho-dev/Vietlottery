package com.edu.gvn.vietlottery.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.edu.gvn.vietlottery.R;
import com.edu.gvn.vietlottery.adapter.LotteryPagerAdapter;
import com.edu.gvn.vietlottery.service.PushNotiService;
import com.edu.gvn.vietlottery.ui.fragment.Max4DFragment;
import com.edu.gvn.vietlottery.ui.fragment.Mega645Fragment;

public class MainActivity extends BaseActivity {
    private final String TAG = MainActivity.class.getSimpleName();

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private LotteryPagerAdapter mLottAdapter;
    private Mega645Fragment megaFragment;
    private Max4DFragment maxFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabLayout = (TabLayout) findViewById(R.id.tab_lottery);
        mViewPager = (ViewPager) findViewById(R.id.viewpager_lottery);

        maxFragment = new Max4DFragment();
        megaFragment = new Mega645Fragment();

        mLottAdapter = new LotteryPagerAdapter(this, getSupportFragmentManager());
        mLottAdapter.addFragment(megaFragment,getResources().getString(R.string.tab_mega));
        mLottAdapter.addFragment(maxFragment,getResources().getString(R.string.tab_max));

        mViewPager.setAdapter(mLottAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        startService(new Intent(this, PushNotiService.class));
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(isNetworkConnection()){
            maxFragment.requestData();
            megaFragment.requestData();
        }else {
            checkInternet();
        }

    }
}
