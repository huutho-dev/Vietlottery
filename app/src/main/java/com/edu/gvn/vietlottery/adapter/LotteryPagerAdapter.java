package com.edu.gvn.vietlottery.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.edu.gvn.vietlottery.R;
import com.edu.gvn.vietlottery.ui.fragment.Max4DFragment;
import com.edu.gvn.vietlottery.ui.fragment.Mega645Fragment;

import java.util.ArrayList;

/**
 * Created by hnc on 29/11/2016.
 */

public class LotteryPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>();

    public LotteryPagerAdapter(Context context, FragmentManager fm) {
        super(fm);

        fragments.add(new Mega645Fragment());
        fragments.add(new Max4DFragment());

        titles.add(context.getResources().getString(R.string.tab_mega));
        titles.add(context.getResources().getString(R.string.tab_max));
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

}
