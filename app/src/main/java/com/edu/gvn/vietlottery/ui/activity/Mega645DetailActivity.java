package com.edu.gvn.vietlottery.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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

public class Mega645DetailActivity extends BaseActivity implements Mega645DetailAsync.Mega645AsyncCallback {
    public static final String BUNDLE_MEGA = "bundle.lucky";

    private Toolbar toolbar ;
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

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.title_activity_detail_mega));

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

        setTextAndChangeColorBall(so1,arrLuckyNumber[0]);
        setTextAndChangeColorBall(so2,arrLuckyNumber[1]);
        setTextAndChangeColorBall(so3,arrLuckyNumber[2]);
        setTextAndChangeColorBall(so4,arrLuckyNumber[3]);
        setTextAndChangeColorBall(so5,arrLuckyNumber[4]);
        setTextAndChangeColorBall(so6,arrLuckyNumber[5]);

        mDatas = new ArrayList<>();
        linearManager = new LinearLayoutManager(this);
        Mega645DetailAsync megaListPrevious = new Mega645DetailAsync(this);
        megaListPrevious.execute(Config.VIETLOTT_DETAIL_MEGA + detail.date);

        mAdapter = new Mega645CurrentAdapter(this, mDatas);
        viewDetail.setLayoutManager(linearManager);
        viewDetail.setAdapter(mAdapter);
    }
    private void setTextAndChangeColorBall(TextView textView, String values) {
        textView.setText(values);

        int intValues = Integer.parseInt(values);
        if (intValues <= 10) {
            textView.setBackground(getResources().getDrawable(R.drawable.ball_red));
            return;
        }
        if (intValues <= 20) {
            textView.setBackground(getResources().getDrawable(R.drawable.ball_yellow));
            return;
        }
        if (intValues <= 30) {
            textView.setBackground(getResources().getDrawable(R.drawable.ball_green));
            return;
        }
        if (intValues <= 40) {
            textView.setBackground(getResources().getDrawable(R.drawable.ball_blue));
            return;
        }
        if (intValues <= 45) {
            textView.setBackground(getResources().getDrawable(R.drawable.ball_pupple));
        }
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
