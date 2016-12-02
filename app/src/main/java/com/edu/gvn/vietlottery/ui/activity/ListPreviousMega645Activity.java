package com.edu.gvn.vietlottery.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.edu.gvn.vietlottery.Config;
import com.edu.gvn.vietlottery.R;
import com.edu.gvn.vietlottery.adapter.Mega645ListPreviousAdapter;
import com.edu.gvn.vietlottery.entity.MegaListPrevious;
import com.edu.gvn.vietlottery.entity.RecyclerItemOnClickListener;
import com.edu.gvn.vietlottery.network.MegaListPreviousAsync;
import com.edu.gvn.vietlottery.ui.fragment.Mega645Detail;
import com.edu.gvn.vietlottery.utils.LogUtils;

import java.util.ArrayList;

/**
 * Created by hnc on 30/11/2016.
 */

public class ListPreviousMega645Activity extends AppCompatActivity implements RecyclerItemOnClickListener {
    private static final int PAGE_HAS = 3;
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
        LogUtils.v("huutho","click");
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_layout, Mega645Detail.newInstance(mDatas.get(position)));
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack(null);
        ft.commit();
    }


    /*
        Xử lý scroll RecyclerView
        Nếu là view cuối cùng thì lấy thêm data ở page mới
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

    /*
    Phương thức lấy data từ VietLott
     */
    private void retriveData() {
        MegaListPreviousAsync request = new MegaListPreviousAsync(new MegaListPreviousAsync.MegaListPreviousAsyncCallback() {
            @Override
            public void callBack(ArrayList<MegaListPrevious> datas) {

                if (mDatas.size() != 0) {
                    mDatas.remove(mDatas.size() - 1);
                }

                mDatas.addAll(datas);
                mAdapter.notifyDataSetChanged();
                loading = true;
            }
        });
        indexPage++;
        request.execute(Config.VIETLOTT_PREVIOUS_MEGA + indexPage);
    }
}
