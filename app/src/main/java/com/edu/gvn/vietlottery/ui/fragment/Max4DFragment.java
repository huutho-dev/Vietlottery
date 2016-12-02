package com.edu.gvn.vietlottery.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.edu.gvn.vietlottery.Config;
import com.edu.gvn.vietlottery.R;
import com.edu.gvn.vietlottery.entity.Max4DCurrent;
import com.edu.gvn.vietlottery.network.Max4DCurrentAsync;
import com.edu.gvn.vietlottery.ui.activity.ListPreviousMax4DActivity;
import com.edu.gvn.vietlottery.utils.SequenceUtils;

public class Max4DFragment extends Fragment implements Max4DCurrentAsync.Max4DCurrentAsyncCallback {

    private TextView
            kiQuayThuong, ngayQuayThuong,
            giaiNhatSo1, giaiNhatSo2, giaiNhatSo3, giaiNhatSo4,
            giaiNhi1So1, giaiNhi1So2, giaiNhi1So3, giaiNhi1So4,
            giaiNhi2So1, giaiNhi2So2, giaiNhi2So3, giaiNhi2So4,
            giaiBa1So1, giaiBa1So2, giaiBa1So3, giaiBa1So4,
            giaiBa2So1, giaiBa2So2, giaiBa2So3, giaiBa2So4,
            giaiBa3So1, giaiBa3So2, giaiBa3So3, giaiBa3So4,
            giaiKK1So2, giaiKK1So3, giaiKK1So4,
            giaiKK2So3, giaiKK2So4;

    private TextView soLuongGiaiNhat, soLuongGiaiNhi, soLuongGiaiBa, soLuongGiaiKK1, soLuongGiaiKk2;
    private TextView giaTriGiaiNhat, giaTriGiaiNhi, giaTriGiaiBa, giaTriGiaiKK1, giaTriGiaiKk2;
    private TextView ngay, gio, phut, giay;

    private Button mButtonPrevious ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Max4DCurrentAsync max4DCurrentAsync = new Max4DCurrentAsync(this);
        max4DCurrentAsync.execute(Config.VIETLOTT_HOME);
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

    @Override
    public void callBack(Max4DCurrent max4DCurrent) {
        setDataView(max4DCurrent);
    }

    private void initView(View view) {
        kiQuayThuong = (TextView) view.findViewById(R.id.txt_time_number);
        ngayQuayThuong = (TextView) view.findViewById(R.id.txt_time_date);

        giaiNhatSo1 = (TextView) view.findViewById(R.id.txt_giai_nhat_ball1);
        giaiNhatSo2 = (TextView) view.findViewById(R.id.txt_giai_nhat_ball2);
        giaiNhatSo3 = (TextView) view.findViewById(R.id.txt_giai_nhat_ball3);
        giaiNhatSo4 = (TextView) view.findViewById(R.id.txt_giai_nhat_ball4);

        giaiNhi1So1 = (TextView) view.findViewById(R.id.txt_giai_nhi_1_ball1);
        giaiNhi1So2 = (TextView) view.findViewById(R.id.txt_giai_nhi_1_ball2);
        giaiNhi1So3 = (TextView) view.findViewById(R.id.txt_giai_nhi_1_ball3);
        giaiNhi1So4 = (TextView) view.findViewById(R.id.txt_giai_nhi_1_ball4);

        giaiNhi2So1 = (TextView) view.findViewById(R.id.txt_giai_nhi_2_ball1);
        giaiNhi2So2 = (TextView) view.findViewById(R.id.txt_giai_nhi_2_ball2);
        giaiNhi2So3 = (TextView) view.findViewById(R.id.txt_giai_nhi_2_ball3);
        giaiNhi2So4 = (TextView) view.findViewById(R.id.txt_giai_nhi_2_ball4);

        giaiBa1So1 = (TextView) view.findViewById(R.id.txt_giai_ba_1_ball1);
        giaiBa1So2 = (TextView) view.findViewById(R.id.txt_giai_ba_1_ball2);
        giaiBa1So3 = (TextView) view.findViewById(R.id.txt_giai_ba_1_ball3);
        giaiBa1So4 = (TextView) view.findViewById(R.id.txt_giai_ba_1_ball4);

        giaiBa2So1 = (TextView) view.findViewById(R.id.txt_giai_ba_2_ball1);
        giaiBa2So2 = (TextView) view.findViewById(R.id.txt_giai_ba_2_ball2);
        giaiBa2So3 = (TextView) view.findViewById(R.id.txt_giai_ba_2_ball3);
        giaiBa2So4 = (TextView) view.findViewById(R.id.txt_giai_ba_2_ball4);

        giaiBa3So1 = (TextView) view.findViewById(R.id.txt_giai_ba_3_ball1);
        giaiBa3So2 = (TextView) view.findViewById(R.id.txt_giai_ba_3_ball2);
        giaiBa3So3 = (TextView) view.findViewById(R.id.txt_giai_ba_3_ball3);
        giaiBa3So4 = (TextView) view.findViewById(R.id.txt_giai_ba_3_ball4);

        giaiKK1So2 = (TextView) view.findViewById(R.id.txt_giai_kk_1_ball2);
        giaiKK1So3 = (TextView) view.findViewById(R.id.txt_giai_kk_1_ball3);
        giaiKK1So4 = (TextView) view.findViewById(R.id.txt_giai_kk_1_ball4);

        giaiKK2So3 = (TextView) view.findViewById(R.id.txt_giai_kk_2_ball3);
        giaiKK2So4 = (TextView) view.findViewById(R.id.txt_giai_kk_2_ball4);

        soLuongGiaiNhat = (TextView) view.findViewById(R.id.txt_giai_nhat_so_luong);
        soLuongGiaiNhi = (TextView) view.findViewById(R.id.txt_giai_nhi_so_luong);
        soLuongGiaiBa = (TextView) view.findViewById(R.id.txt_giai_ba_so_luong);
        soLuongGiaiKK1 = (TextView) view.findViewById(R.id.txt_giai_kk_1_so_luong);
        soLuongGiaiKk2 = (TextView) view.findViewById(R.id.txt_giai_kk_2_so_luong);

        giaTriGiaiNhat = (TextView) view.findViewById(R.id.txt_giai_nhat_gia_tri);
        giaTriGiaiNhi = (TextView) view.findViewById(R.id.txt_giai_nhi_gia_tri);
        giaTriGiaiBa = (TextView) view.findViewById(R.id.txt_giai_ba_gia_tri);
        giaTriGiaiKK1 = (TextView) view.findViewById(R.id.txt_giai_kk_1_gia_tri);
        giaTriGiaiKk2 = (TextView) view.findViewById(R.id.txt_giai_kk_2_gia_tri);

        ngay = (TextView) view.findViewById(R.id.date);
        gio = (TextView) view.findViewById(R.id.hour);
        phut = (TextView) view.findViewById(R.id.minute);
        giay = (TextView) view.findViewById(R.id.second);

        mButtonPrevious = (Button) view.findViewById(R.id.btn_previous);
        mButtonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(), ListPreviousMax4DActivity.class));
            }
        });
    }

    private void setDataView(Max4DCurrent max4DCurrent) {
        kiQuayThuong.setText(max4DCurrent.max4dPrize.kyQuayThuong);
        ngayQuayThuong.setText(max4DCurrent.max4dPrize.ngayQuayThuong);

        String[] giaiNhat = SequenceUtils.getInstance().sliptSequence(max4DCurrent.max4dPrize.giaiNhat, " ");
        giaiNhatSo1.setText(giaiNhat[0]);
        giaiNhatSo2.setText(giaiNhat[1]);
        giaiNhatSo3.setText(giaiNhat[2]);
        giaiNhatSo4.setText(giaiNhat[3]);

        String[] giaiNhi1 = SequenceUtils.getInstance().sliptSequence(max4DCurrent.max4dPrize.giaiNhi1, " ");
        giaiNhi1So1.setText(giaiNhi1[0]);
        giaiNhi1So2.setText(giaiNhi1[1]);
        giaiNhi1So3.setText(giaiNhi1[2]);
        giaiNhi1So4.setText(giaiNhi1[3]);

        String[] giaiNhi2 = SequenceUtils.getInstance().sliptSequence(max4DCurrent.max4dPrize.giaiNhi2, " ");
        giaiNhi2So1.setText(giaiNhi2[0]);
        giaiNhi2So2.setText(giaiNhi2[1]);
        giaiNhi2So3.setText(giaiNhi2[2]);
        giaiNhi2So4.setText(giaiNhi2[3]);

        String[] giaiBa1 = SequenceUtils.getInstance().sliptSequence(max4DCurrent.max4dPrize.giaiBa1, " ");
        giaiBa1So1.setText(giaiBa1[0]);
        giaiBa1So2.setText(giaiBa1[1]);
        giaiBa1So3.setText(giaiBa1[2]);
        giaiBa1So4.setText(giaiBa1[3]);

        String[] giaiBa2 = SequenceUtils.getInstance().sliptSequence(max4DCurrent.max4dPrize.giaiBa2, " ");
        giaiBa2So1.setText(giaiBa2[0]);
        giaiBa2So2.setText(giaiBa2[1]);
        giaiBa2So3.setText(giaiBa2[2]);
        giaiBa2So4.setText(giaiBa2[3]);

        String[] giaiBa3 = SequenceUtils.getInstance().sliptSequence(max4DCurrent.max4dPrize.giaiBa3, " ");
        giaiBa3So1.setText(giaiBa3[0]);
        giaiBa3So2.setText(giaiBa3[1]);
        giaiBa3So3.setText(giaiBa3[2]);
        giaiBa3So4.setText(giaiBa3[3]);

        String[] kk1 = SequenceUtils.getInstance().sliptSequence(max4DCurrent.max4dPrize.giaiKK1, " ");
        giaiKK1So2.setText(kk1[1]);
        giaiKK1So3.setText(kk1[2]);
        giaiKK1So4.setText(kk1[3]);

        String[] kk2 = SequenceUtils.getInstance().sliptSequence(max4DCurrent.max4dPrize.giaiKK2, " ");
        giaiKK2So3.setText(kk2[2]);
        giaiKK2So4.setText(kk2[3]);

        soLuongGiaiNhat.setText(max4DCurrent.soLuongGiaiNhat);
        soLuongGiaiNhi.setText(max4DCurrent.soLuongGiaiNhi);
        soLuongGiaiBa.setText(max4DCurrent.soLuongGiaiBa);
        soLuongGiaiKK1.setText(max4DCurrent.soLuongGiaiKK1);
        soLuongGiaiKk2.setText(max4DCurrent.soLuongGiaiKK2);

        giaTriGiaiNhat.setText(max4DCurrent.giaTriGiaiNhat);
        giaTriGiaiNhi.setText(max4DCurrent.giaTriGiaiNhi);
        giaTriGiaiBa.setText(max4DCurrent.giaTriGiaiBa);
        giaTriGiaiKK1.setText(max4DCurrent.giaTriGiaiKK1);
        giaTriGiaiKk2.setText(max4DCurrent.giaTriGiaiKK2);

        ngay.setText("00");
        gio.setText("00");
        phut.setText("00");
        giay.setText("00");
    }

}
