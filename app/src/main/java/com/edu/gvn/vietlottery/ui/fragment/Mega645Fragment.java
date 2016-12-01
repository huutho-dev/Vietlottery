package com.edu.gvn.vietlottery.ui.fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.edu.gvn.vietlottery.Config;
import com.edu.gvn.vietlottery.R;
import com.edu.gvn.vietlottery.adapter.Mega645CurrentAdapter;
import com.edu.gvn.vietlottery.entity.Mega645Current;
import com.edu.gvn.vietlottery.entity.sub.Mega;
import com.edu.gvn.vietlottery.network.Mega645Async;
import com.edu.gvn.vietlottery.utils.DateTimeUtils;
import com.edu.gvn.vietlottery.utils.LogUtils;

import java.util.ArrayList;


public class Mega645Fragment extends Fragment implements Mega645Async.Mega645AsyncCallback, View.OnClickListener {

    private final String TAG = Mega645Fragment.class.getSimpleName();

    private TextView kyQuayThuong, ngayQuayThuong;
    private TextView so1, so2, so3, so4, so5, so6;
    private TextView giaTriUocTinh;
    private TextView ngay, gio, phut, giay;
    private Button lanQuayTruoc;

    private RecyclerView viewResult;
    private RecyclerView.LayoutManager linearManager;
    private Mega645CurrentAdapter mAdapter;
    private ArrayList<Mega> datas;

    CountDownTimer mCountDownTimer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Mega645Async mega645Async = new Mega645Async(this);
        mega645Async.execute(Config.VIETLOTT_HOME);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mega645, container, false);

        kyQuayThuong = (TextView) view.findViewById(R.id.txt_time_number);
        ngayQuayThuong = (TextView) view.findViewById(R.id.txt_time_date);
        so1 = (TextView) view.findViewById(R.id.txt_ball_1);
        so2 = (TextView) view.findViewById(R.id.txt_ball_2);
        so3 = (TextView) view.findViewById(R.id.txt_ball_3);
        so4 = (TextView) view.findViewById(R.id.txt_ball_4);
        so5 = (TextView) view.findViewById(R.id.txt_ball_5);
        so6 = (TextView) view.findViewById(R.id.txt_ball_6);
        giaTriUocTinh = (TextView) view.findViewById(R.id.values_prize);
        ngay = (TextView) view.findViewById(R.id.date);
        gio = (TextView) view.findViewById(R.id.hour);
        phut = (TextView) view.findViewById(R.id.minute);
        giay = (TextView) view.findViewById(R.id.second);

        viewResult = (RecyclerView) view.findViewById(R.id.view_result);
        lanQuayTruoc = (Button) view.findViewById(R.id.btn_previous);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        linearManager = new LinearLayoutManager(getActivity());
        datas = new ArrayList<>();
        mAdapter = new Mega645CurrentAdapter(getActivity(), datas);
        viewResult.setLayoutManager(linearManager);
        viewResult.setAdapter(mAdapter);

        lanQuayTruoc.setOnClickListener(this);
    }

    @Override
    public void callBack(Mega645Current current) {
        String[] arrLuckyNumber = current.mega645Previous.soMayMan.split(" ");
        LogUtils.i(TAG, current.toString());

        kyQuayThuong.setText(current.mega645Previous.kyQuayThuong);
        ngayQuayThuong.setText(current.mega645Previous.ngayQuayThuong);

        so1.setText(arrLuckyNumber[0]);
        so2.setText(arrLuckyNumber[1]);
        so3.setText(arrLuckyNumber[2]);
        so4.setText(arrLuckyNumber[3]);
        so5.setText(arrLuckyNumber[4]);
        so6.setText(arrLuckyNumber[5]);

        giaTriUocTinh.setText(current.giaTriUocTinh);


        datas.clear();
        datas.addAll(current.mega645Previous.megas);
        mAdapter.notifyDataSetChanged();

        DateTimeUtils dateTimeUtils = new DateTimeUtils();
        String remain = dateTimeUtils.remainingTime(current.curentTime, current.endTime);
        countTime(remain, ngay, gio, phut, giay);
    }

    private void countTime(String remain, final TextView ngay, final TextView gio, final TextView phut, final TextView giay) {

        String[] myDateTime = remain.split("-");

        long mInitialTime =
                DateUtils.DAY_IN_MILLIS * Integer.parseInt(myDateTime[0]) +
                        DateUtils.HOUR_IN_MILLIS * Integer.parseInt(myDateTime[1]) +
                        DateUtils.MINUTE_IN_MILLIS * Integer.parseInt(myDateTime[2]) +
                        DateUtils.SECOND_IN_MILLIS * Integer.parseInt(myDateTime[3]);

        mCountDownTimer = new CountDownTimer(mInitialTime, 1000) {
            StringBuilder time = new StringBuilder();

            @Override
            public void onFinish() {
                //mTextView.setText("Times Up!");
            }

            @Override
            public void onTick(long millisUntilFinished) {
                time.setLength(0);

                if (millisUntilFinished > DateUtils.DAY_IN_MILLIS) {
                    long count = millisUntilFinished / DateUtils.DAY_IN_MILLIS;
                    time.append(count).append(":");
                    millisUntilFinished %= DateUtils.DAY_IN_MILLIS;
                } else {
                    time.append("00").append(":");
                }

                time.append(DateUtils.formatElapsedTime(Math.round(millisUntilFinished / 1000d)));


                String[] remain = time.toString().split(":");
                ngay.setText(remain[0]);
                gio.setText(remain[1]);
                phut.setText(remain[2]);
                giay.setText(remain[3]);

            }
        }.start();
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_previous) {

        }
    }
}
