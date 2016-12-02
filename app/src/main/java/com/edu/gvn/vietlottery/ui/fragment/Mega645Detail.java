package com.edu.gvn.vietlottery.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edu.gvn.vietlottery.Config;
import com.edu.gvn.vietlottery.R;
import com.edu.gvn.vietlottery.adapter.Mega645CurrentAdapter;
import com.edu.gvn.vietlottery.entity.Mega645Previous;
import com.edu.gvn.vietlottery.entity.MegaListPrevious;
import com.edu.gvn.vietlottery.entity.sub.Mega;
import com.edu.gvn.vietlottery.network.Mega645DetailAsync;
import com.edu.gvn.vietlottery.utils.LogUtils;

import java.util.ArrayList;

/**
 * Created by HuuTho on 12/1/2016.
 */

public class Mega645Detail extends Fragment implements Mega645DetailAsync.Mega645AsyncCallback {
    public static final String BUNDLE_MEGA = "bundle.lucky";

    private RecyclerView viewDetail;
    private TextView kyQuayThuong, ngayQuayThuong;
    private TextView so1, so2, so3, so4, so5, so6;
    private TextView giaTriGiaiThuong ;

    private RecyclerView.LayoutManager linearManager;
    private ArrayList<Mega> mDatas;
    private Mega645CurrentAdapter mAdapter;
    private MegaListPrevious detail;

    public static Mega645Detail newInstance(MegaListPrevious detail) {
        Mega645Detail fragment = new Mega645Detail();
        Bundle args = new Bundle();
        args.putSerializable(BUNDLE_MEGA, detail);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            getDataBundle(savedInstanceState);
        } else {
            getDataBundle(getArguments());
        }

        mDatas = new ArrayList<>();
        linearManager = new LinearLayoutManager(getActivity());
        Mega645DetailAsync megaListPrevious = new Mega645DetailAsync(this);
        megaListPrevious.execute(Config.VIETLOTT_DETAIL_MEGA + detail.date);
    }

    private void getDataBundle(Bundle savedInstanceState) {
        detail = (MegaListPrevious) savedInstanceState.getSerializable(BUNDLE_MEGA);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_mega, container, false);
        viewDetail = (RecyclerView) view.findViewById(R.id.view_detail_mega);
        kyQuayThuong = (TextView) view.findViewById(R.id.txt_time_number);
        ngayQuayThuong = (TextView) view.findViewById(R.id.txt_time_date);
        giaTriGiaiThuong = (TextView) view.findViewById(R.id.values_prize);
        so1 = (TextView) view.findViewById(R.id.txt_ball_1);
        so2 = (TextView) view.findViewById(R.id.txt_ball_2);
        so3 = (TextView) view.findViewById(R.id.txt_ball_3);
        so4 = (TextView) view.findViewById(R.id.txt_ball_4);
        so5 = (TextView) view.findViewById(R.id.txt_ball_5);
        so6 = (TextView) view.findViewById(R.id.txt_ball_6);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAdapter = new Mega645CurrentAdapter(getContext(), mDatas);
        viewDetail.setLayoutManager(linearManager);
        viewDetail.setAdapter(mAdapter);

        LogUtils.v("huutho-",detail.result);
        String[] arrLuckyNumber = detail.result.split(" ");
        so1.setText(arrLuckyNumber[0]);
        so2.setText(arrLuckyNumber[1]);
        so3.setText(arrLuckyNumber[2]);
        so4.setText(arrLuckyNumber[3]);
        so5.setText(arrLuckyNumber[4]);
        so6.setText(arrLuckyNumber[5]);
    }

    @Override
    public void callBack(Mega645Previous data) {
        if (mDatas.size() != 0 && mDatas != null) {
            mDatas.clear();
        }

        mDatas.addAll(data.megas);
        mAdapter.notifyDataSetChanged();

        kyQuayThuong.setText(data.kyQuayThuong);
        ngayQuayThuong.setText(data.ngayQuayThuong);
        giaTriGiaiThuong.setText(data.soTienJackpot);

    }
}
