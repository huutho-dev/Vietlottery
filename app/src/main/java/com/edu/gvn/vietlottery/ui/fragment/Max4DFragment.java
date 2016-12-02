package com.edu.gvn.vietlottery.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edu.gvn.vietlottery.R;

public class Max4DFragment extends Fragment {

    private TextView
            kiQuayThuong, ngayQuayThuong,
            giaiNhatSo_1, giaiNhatSo_2, giaiNhatSo_3, giaiNhatSo_4,
            giaiNhi_1_So_1, giaiNhi_1_So_2, giaiNhi_1_So_3, giaiNhi_1_So_4,
            giaiNhi_2_So_1, giaiNhi_2_So_2, giaiNhi_2_So_3, giaiNhi_2_So_4,
            giaiBa_1_So_1, giaiBa_1_So_2, giaiBa_1_So_3, giaiBa_1_So_4,
            giaiBa_2_So_1, giaiBa_2_So_2, giaiBa_2_So_3, giaiBa_2_So_4,
            giaiBa_3_So_1, giaiBa_3_So_2, giaiBa_3_So_3, giaiBa_3_So_4,
            giaiKK_1_So_2, giaiKK_1_So_3, giaiKK_1_So_4,
            giaiKK_2_So_3, giaiKK_2_So_4;

    private TextView ngay, gio, phut, giay;
    ;

    @Override

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_max4d, container, false);
        initView(view);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    private void initView(View view) {
        kiQuayThuong = (TextView) view.findViewById(R.id.txt_time_number);
        ngayQuayThuong = (TextView) view.findViewById(R.id.txt_time_date);

        giaiNhatSo_1 = (TextView) view.findViewById(R.id.txt_giai_nhat_ball1);
        giaiNhatSo_2 = (TextView) view.findViewById(R.id.txt_giai_nhat_ball2);
        giaiNhatSo_3 = (TextView) view.findViewById(R.id.txt_giai_nhat_ball3);
        giaiNhatSo_4 = (TextView) view.findViewById(R.id.txt_giai_nhat_ball4);

        giaiNhi_1_So_1 = (TextView) view.findViewById(R.id.txt_giai_nhi_1_ball1);
        giaiNhi_1_So_2 = (TextView) view.findViewById(R.id.txt_giai_nhi_1_ball2);
        giaiNhi_1_So_3 = (TextView) view.findViewById(R.id.txt_giai_nhi_1_ball3);
        giaiNhi_1_So_4 = (TextView) view.findViewById(R.id.txt_giai_nhi_1_ball4);

        giaiNhi_2_So_1 = (TextView) view.findViewById(R.id.txt_giai_nhi_2_ball1);
        giaiNhi_2_So_2 = (TextView) view.findViewById(R.id.txt_giai_nhi_2_ball2);
        giaiNhi_2_So_3 = (TextView) view.findViewById(R.id.txt_giai_nhi_2_ball3);
        giaiNhi_2_So_4 = (TextView) view.findViewById(R.id.txt_giai_nhi_2_ball4);

        giaiBa_1_So_1 = (TextView) view.findViewById(R.id.txt_giai_ba_1_ball1);
        giaiBa_1_So_2 = (TextView) view.findViewById(R.id.txt_giai_ba_1_ball2);
        giaiBa_1_So_3 = (TextView) view.findViewById(R.id.txt_giai_ba_1_ball3);
        giaiBa_1_So_4 = (TextView) view.findViewById(R.id.txt_giai_ba_1_ball4);

        giaiBa_2_So_1 = (TextView) view.findViewById(R.id.txt_giai_ba_2_ball1);
        giaiBa_2_So_2 = (TextView) view.findViewById(R.id.txt_giai_ba_2_ball2);
        giaiBa_2_So_3 = (TextView) view.findViewById(R.id.txt_giai_ba_2_ball3);
        giaiBa_2_So_4 = (TextView) view.findViewById(R.id.txt_giai_ba_2_ball4);

        giaiBa_3_So_1 = (TextView) view.findViewById(R.id.txt_giai_ba_3_ball1);
        giaiBa_3_So_2 = (TextView) view.findViewById(R.id.txt_giai_ba_3_ball2);
        giaiBa_3_So_3 = (TextView) view.findViewById(R.id.txt_giai_ba_3_ball3);
        giaiBa_3_So_4 = (TextView) view.findViewById(R.id.txt_giai_ba_3_ball4);

        giaiKK_1_So_2 = (TextView) view.findViewById(R.id.txt_giai_kk_1_ball2);
        giaiKK_1_So_3 = (TextView) view.findViewById(R.id.txt_giai_kk_1_ball3);
        giaiKK_1_So_4 = (TextView) view.findViewById(R.id.txt_giai_kk_1_ball4);

        giaiKK_2_So_3 = (TextView) view.findViewById(R.id.txt_giai_kk_2_ball3);
        giaiKK_2_So_4 = (TextView) view.findViewById(R.id.txt_giai_kk_2_ball4);

        ngay = (TextView) view.findViewById(R.id.date);
        gio = (TextView) view.findViewById(R.id.hour);
        phut = (TextView) view.findViewById(R.id.minute);
        giay = (TextView) view.findViewById(R.id.second);
    }
}
