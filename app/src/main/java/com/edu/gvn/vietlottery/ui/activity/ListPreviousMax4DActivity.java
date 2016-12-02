package com.edu.gvn.vietlottery.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.edu.gvn.vietlottery.Config;
import com.edu.gvn.vietlottery.R;
import com.edu.gvn.vietlottery.adapter.Max4DListPreviousAdapter;
import com.edu.gvn.vietlottery.entity.sub.Max4dPrize;
import com.edu.gvn.vietlottery.network.Max4dPreviousAsync;

import java.util.ArrayList;

/**
 * Created by hnc on 02/12/2016.
 */

public class ListPreviousMax4DActivity extends AppCompatActivity implements Max4dPreviousAsync.Max4dPreviousCallback {
    private RecyclerView listMax4d;
    private Max4DListPreviousAdapter mAdapter;
    private RecyclerView.LayoutManager linearManager;
    private ArrayList<Max4dPrize> mDatas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_previous_max);

        linearManager = new LinearLayoutManager(this);
        mDatas = new ArrayList<>();
        mAdapter = new Max4DListPreviousAdapter(mDatas);

        listMax4d = (RecyclerView) findViewById(R.id.view_list_detail_max);
        listMax4d.setLayoutManager(linearManager);
        listMax4d.setAdapter(mAdapter);

        Max4dPreviousAsync async = new Max4dPreviousAsync(this);
        async.execute(Config.VIETLOTT_PREVIOUS_MAX);
    }

    @Override
    public void callBack(ArrayList<Max4dPrize> datas) {
        if (datas != null) {
            if (mDatas.size() != 0)
                mDatas.clear();
            mDatas.addAll(datas);
            mAdapter.notifyDataSetChanged();
        }
    }
}
