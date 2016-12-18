package com.game24h.vietlottery.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.edu.gvn.vietlottery.R;
import com.game24h.vietlottery.Config;
import com.game24h.vietlottery.adapter.Max4DListPreviousAdapter;
import com.game24h.vietlottery.entity.sub.Max4dPrize;
import com.game24h.vietlottery.network.Max4dPreviousAsync;

import java.util.ArrayList;

public class PreviousMax4DActivity extends BaseActivity implements Max4dPreviousAsync.Max4dPreviousCallback {

    private Toolbar toolbar ;

    private RecyclerView listMax4d;
    private Max4DListPreviousAdapter mAdapter;
    private RecyclerView.LayoutManager linearManager;
    private ArrayList<Max4dPrize> mDatas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_previous_max);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.title_activity_list_previous_max));

        linearManager = new LinearLayoutManager(this);
        mDatas = new ArrayList<>();
        mAdapter = new Max4DListPreviousAdapter(mDatas);

        listMax4d = (RecyclerView) findViewById(R.id.view_list_detail_max);
        listMax4d.setLayoutManager(linearManager);
        listMax4d.setAdapter(mAdapter);

        Max4dPreviousAsync async = new Max4dPreviousAsync(this,this);
        async.execute(Config.VIETLOTT_PREVIOUS_MAX);
    }


    @Override
    public void callBack(ArrayList<Max4dPrize> datas) {
        try{
            if (datas != null) {
                if (mDatas.size() != 0)
                    mDatas.clear();
                mDatas.addAll(datas);
                mAdapter.notifyDataSetChanged();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }
}
