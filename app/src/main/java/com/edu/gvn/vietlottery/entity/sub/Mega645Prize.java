package com.edu.gvn.vietlottery.entity.sub;

import com.edu.gvn.vietlottery.entity.BaseEntity;

public class Mega645Prize extends BaseEntity {
        public String tenGiaiThuong;
        public String trungKhop;
        public String soLuongGiai;
        public String giaTriGiai;

        public Mega645Prize(String tenGiaiThuong, String trungKhop, String soLuongGiai, String giaTriGiai) {
            this.tenGiaiThuong = tenGiaiThuong;
            this.trungKhop = trungKhop;
            this.soLuongGiai = soLuongGiai;
            this.giaTriGiai = giaTriGiai;
        }


    @Override
    public String toString() {
        return "Mega645Prize{" +
                "tenGiaiThuong='" + tenGiaiThuong + '\'' +
                ", trungKhop='" + trungKhop + '\'' +
                ", soLuongGiai='" + soLuongGiai + '\'' +
                ", giaTriGiai='" + giaTriGiai + '\'' +
                '}';
    }
}