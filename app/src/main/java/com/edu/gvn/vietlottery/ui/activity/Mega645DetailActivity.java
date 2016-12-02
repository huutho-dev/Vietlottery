package com.edu.gvn.vietlottery.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import com.edu.gvn.vietlottery.Config;
import com.edu.gvn.vietlottery.R;
import com.edu.gvn.vietlottery.adapter.Mega645CurrentAdapter;
import com.edu.gvn.vietlottery.entity.Mega645Previous;
import com.edu.gvn.vietlottery.entity.MegaListPrevious;
import com.edu.gvn.vietlottery.entity.sub.Mega645Prize;
import com.edu.gvn.vietlottery.network.Mega645DetailAsync;
import com.edu.gvn.vietlottery.utils.SequenceUtils;

import java.util.ArrayList;

/**
 * Created by HuuTho on 12/1/2016.
 */

public class Mega645DetailActivity extends AppCompatActivity implements Mega645DetailAsync.Mega645AsyncCallback {
    public static final String BUNDLE_MEGA = "bundle.lucky";

    private RecyclerView viewDetail;
    private TextView kyQuayThuong, ngayQuayThuong;
    private TextView so1, so2, so3, so4, so5, so6;
    private TextView giaTriGiaiThuong;

    private RecyclerView.LayoutManager linearManager;
    private ArrayList<Mega645Prize> mDatas;
    private Mega645CurrentAdapter mAdapter;
    private MegaListPrevious detail;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mega);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Jackpot");

        detail = (MegaListPrevious) getIntent().getSerializableExtra(BUNDLE_MEGA);


        viewDetail = (RecyclerView) findViewById(R.id.view_detail_mega);
        kyQuayThuong = (TextView) findViewById(R.id.txt_time_number);
        ngayQuayThuong = (TextView) findViewById(R.id.txt_time_date);
        giaTriGiaiThuong = (TextView) findViewById(R.id.values_prize);
        so1 = (TextView) findViewById(R.id.txt_ball_1);
        so2 = (TextView) findViewById(R.id.txt_ball_2);
        so3 = (TextView) findViewById(R.id.txt_ball_3);
        so4 = (TextView) findViewById(R.id.txt_ball_4);
        so5 = (TextView) findViewById(R.id.txt_ball_5);
        so6 = (TextView) findViewById(R.id.txt_ball_6);

        String[] arrLuckyNumber =  SequenceUtils.getInstance().sliptSequence(detail.result," ");
        so1.setText(arrLuckyNumber[0]);
        so2.setText(arrLuckyNumber[1]);
        so3.setText(arrLuckyNumber[2]);
        so4.setText(arrLuckyNumber[3]);
        so5.setText(arrLuckyNumber[4]);
        so6.setText(arrLuckyNumber[5]);

        mDatas = new ArrayList<>();
        linearManager = new LinearLayoutManager(this);
        Mega645DetailAsync megaListPrevious = new Mega645DetailAsync(this);
        megaListPrevious.execute(Config.VIETLOTT_DETAIL_MEGA + detail.date);

        mAdapter = new Mega645CurrentAdapter(this, mDatas);
        viewDetail.setLayoutManager(linearManager);
        viewDetail.setAdapter(mAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) onBackPressed();
        return true ;
    }


    @Override
    public void callBack(Mega645Previous data) {
        if (mDatas.size() != 0 && mDatas != null) {
            mDatas.clear();
        }

        mDatas.addAll(data.mega645Prizes);
        mAdapter.notifyDataSetChanged();

        kyQuayThuong.setText(data.kyQuayThuong);
        ngayQuayThuong.setText(data.ngayQuayThuong);
        giaTriGiaiThuong.setText(data.soTienJackpot);

    }
}
