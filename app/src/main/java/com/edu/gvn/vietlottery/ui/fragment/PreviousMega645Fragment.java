package com.edu.gvn.vietlottery.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edu.gvn.vietlottery.Config;
import com.edu.gvn.vietlottery.R;
import com.edu.gvn.vietlottery.adapter.Mega645ListPreviousAdapter;
import com.edu.gvn.vietlottery.entity.MegaListPrevious;
import com.edu.gvn.vietlottery.network.MegaListPreviousAsync;

import java.util.ArrayList;

/**
 * Created by hnc on 30/11/2016.
 */

public class PreviousMega645Fragment extends Fragment {
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
        mDatas = new ArrayList<>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_previous_mega, container, false);
        listPrevious = (RecyclerView) view.findViewById(R.id.recycler_list_previous);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new Mega645ListPreviousAdapter(mDatas);
        listPrevious.setLayoutManager(linearLayoutManager);
        listPrevious.setAdapter(mAdapter);

        retriveData();
        scrollListener();
    }

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
