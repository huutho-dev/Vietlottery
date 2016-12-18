package com.game24h.vietlottery.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.edu.gvn.vietlottery.R;
import com.game24h.vietlottery.Config;
import com.game24h.vietlottery.adapter.Mega645ListPreviousAdapter;
import com.game24h.vietlottery.entity.MegaListPrevious;
import com.game24h.vietlottery.entity.RecyclerItemOnClickListener;
import com.game24h.vietlottery.network.MegaListPreviousAsync;

import java.util.ArrayList;

public class PreviousMega645Activity extends BaseActivity implements RecyclerItemOnClickListener {
    private static final int PAGE_HAS = 3;

    private Toolbar toolbar;
    private RecyclerView listPrevious;
    private Mega645ListPreviousAdapter mAdapter;
    private ArrayList<MegaListPrevious> mDatas;
    private LinearLayoutManager linearLayoutManager;

    private int indexPage = 0;
    private int visibleItemCount;
    private int totalItemCount;
    private int pastVisiblesItems;

    private boolean loading = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_previous_mega);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.title_activity_list_previous_mega));

        mDatas = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(this);
        mAdapter = new Mega645ListPreviousAdapter(mDatas, this);
        listPrevious = (RecyclerView) findViewById(R.id.recycler_list_previous);
        listPrevious.setLayoutManager(linearLayoutManager);
        listPrevious.setAdapter(mAdapter);

        retriveData();
        scrollListener();
    }

    @Override
    public void onItemClick(View view, int position) {

        Intent intent = new Intent(this, Mega645DetailActivity.class);
        intent.putExtra(Mega645DetailActivity.BUNDLE_MEGA, mDatas.get(position));
        startActivity(intent);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) onBackPressed();
        return true;
    }

    /**
     * Xử lý scroll RecyclerView
     * Nếu là view cuối cùng thì lấy thêm data ở page mới
     */
    private void scrollListener() {
        listPrevious.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                if (dy > 0) {
                    visibleItemCount = listPrevious.getChildCount();
                    totalItemCount = linearLayoutManager.getItemCount();
                    pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition();

                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loading = false;

                            if (indexPage > PAGE_HAS) {
                                return;
                            }

                            mDatas.add(null);
                            mAdapter.notifyDataSetChanged();
                            retriveData();
                        }
                    }
                }
            }
        });
    }


    /**
     * Phương thức lấy data từ VietLott
     */
    private void retriveData() {
        MegaListPreviousAsync request = new MegaListPreviousAsync(this, new MegaListPreviousAsync.MegaListPreviousAsyncCallback() {
            @Override
            public void callBack(ArrayList<MegaListPrevious> datas) {
                try {
                    if (datas != null) {

                        if (mDatas.size() != 0) {
                            mDatas.remove(mDatas.size() - 1);
                        }

                        mDatas.addAll(datas);
                        mAdapter.notifyDataSetChanged();
                        loading = true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        indexPage++;
        request.execute(Config.VIETLOTT_PREVIOUS_MEGA + indexPage);
    }
}
