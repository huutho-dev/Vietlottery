package com.edu.gvn.vietlottery.ui.fragment;

import android.content.Intent;
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
import com.edu.gvn.vietlottery.entity.sub.Mega645Prize;
import com.edu.gvn.vietlottery.network.Mega645CurrentAsync;
import com.edu.gvn.vietlottery.ui.activity.PreviousMega645Activity;
import com.edu.gvn.vietlottery.utils.DateTimeUtils;
import com.edu.gvn.vietlottery.utils.LogUtils;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;


public class Mega645Fragment extends Fragment implements Mega645CurrentAsync.Mega645AsyncCallback, View.OnClickListener {

    private final String TAG = Mega645Fragment.class.getSimpleName();

    private TextView kyQuayThuong, ngayQuayThuong;
    private TextView so1, so2, so3, so4, so5, so6;
    private TextView giaTriUocTinh;
    private TextView ngay, gio, phut, giay;
    private Button lanQuayTruoc;

    private RecyclerView viewResult;
    private RecyclerView.LayoutManager linearManager;
    private Mega645CurrentAdapter mAdapter;
    private ArrayList<Mega645Prize> datas;

    CountDownTimer mCountDownTimer;

    private InterstitialAd mInterstitialAd;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MobileAds.initialize(getContext(), getResources().getString(R.string.banner_id));

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mega645, container, false);

        AdView mAdView = (AdView) view.findViewById(R.id.qc);
        mInterstitialAd = createNewIntAd();
        loadIntAdd();
        showBannerAd(mAdView);

        initView(view);
        return view;
    }

    public void requestData() {
        Mega645CurrentAsync mega645Async = new Mega645CurrentAsync(this);
        mega645Async.execute(Config.VIETLOTT_HOME);
    }

    private void initView(View view) {
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

        if (current != null) {
            String[] arrLuckyNumber = current.mega645Previous.soMayMan.split(" ");

            kyQuayThuong.setText(current.mega645Previous.kyQuayThuong);
            ngayQuayThuong.setText(current.mega645Previous.ngayQuayThuong);

            setTextAndChangeColorBall(so1, arrLuckyNumber[0]);
            setTextAndChangeColorBall(so2, arrLuckyNumber[1]);
            setTextAndChangeColorBall(so3, arrLuckyNumber[2]);
            setTextAndChangeColorBall(so4, arrLuckyNumber[3]);
            setTextAndChangeColorBall(so5, arrLuckyNumber[4]);
            setTextAndChangeColorBall(so6, arrLuckyNumber[5]);

            giaTriUocTinh.setText(current.giaTriUocTinh);


            datas.clear();
            datas.addAll(current.mega645Previous.mega645Prizes);
            mAdapter.notifyDataSetChanged();

            DateTimeUtils dateTimeUtils = new DateTimeUtils();
            String remain = dateTimeUtils.remainingTime(current.curentTime, current.endTime);
            countTime(remain, ngay, gio, phut, giay);
        }
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


    private void countTime(String remain, final TextView ngay, final TextView gio, final TextView phut, final TextView giay) {

        String[] myDateTime = remain.split("-");

        LogUtils.v("huutho",remain);

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
                    long day= millisUntilFinished / DateUtils.DAY_IN_MILLIS;
                    time.append(day).append(":");
                    long hour = (millisUntilFinished %= DateUtils.HOUR_IN_MILLIS)/ DateUtils.HOUR_IN_MILLIS ;
                    time.append(hour).append(":");

                    millisUntilFinished %= DateUtils.HOUR_IN_MILLIS;

                } else {
                  //  time.append("00").append(":");
                }

                time.append(DateUtils.formatElapsedTime(Math.round(millisUntilFinished / 1000d)));

                ngay.setText("00");
                gio.setText("00");
                phut.setText("00");
                giay.setText("00");

                LogUtils.v("huutho",time.toString());

                String[] remain = time.toString().split(":");
                if (remain.length == 4) {
                    ngay.setText(remain[0]);
                    gio.setText(remain[1]);
                    phut.setText(remain[2]);
                    giay.setText(remain[3]);
                } else if (remain.length == 3) {
                    gio.setText(remain[0]);
                    phut.setText(remain[1]);
                    giay.setText(remain[2]);
                } else {
                    phut.setText(remain[0]);
                    giay.setText(remain[1]);
                }
            }
        }.start();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_previous) {
            //  getActivity().startActivity(new Intent(getActivity(), PreviousMega645Activity.class));
            showIntAdd();
        }
    }


    // add quảng cáo vào view
    private void showBannerAd(AdView mAdView) {
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    private InterstitialAd createNewIntAd() {
        InterstitialAd intAd = new InterstitialAd(getActivity());
        intAd.setAdUnitId(getString(R.string.banner_full_id));
        intAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {

            }

            @Override
            public void onAdFailedToLoad(int errorCode) {

            }

            @Override
            public void onAdClosed() {
                getActivity().startActivity(new Intent(getActivity(), PreviousMega645Activity.class));
            }
        });
        return intAd;
    }

    // Show quảng cáo nếu đã load, chưa load thì startActivity
    private void showIntAdd() {
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            getActivity().startActivity(new Intent(getActivity(), PreviousMega645Activity.class));
        }
    }

    // Load quảng cáo
    private void loadIntAdd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest);
    }

}
