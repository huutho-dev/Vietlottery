package com.game24h.vietlottery.entity;

import com.game24h.vietlottery.entity.sub.Max4dPrize;

public class Max4DCurrent extends BaseEntity {
    public Max4dPrize max4dPrize;
    public String soLuongGiaiNhat, soLuongGiaiNhi, soLuongGiaiBa, soLuongGiaiKK1, soLuongGiaiKK2;
    public String giaTriGiaiNhat, giaTriGiaiNhi, giaTriGiaiBa, giaTriGiaiKK1, giaTriGiaiKK2;
    public String currentTime, endTime;

    public Max4DCurrent(Max4dPrize max4dPrize, String soLuongGiaiNhat, String soLuongGiaiNhi,
                        String soLuongGiaiBa, String soLuongGiaiKK1, String soLuongGiaiKK2,
                        String giaTriGiaiNhat, String giaTriGiaiNhi, String giaTriGiaiBa,
                        String giaTriGiaiKK1, String giaTriGiaiKK2, String currentTime, String endTime) {

        this.max4dPrize = max4dPrize;
        this.soLuongGiaiNhat = soLuongGiaiNhat;
        this.soLuongGiaiNhi = soLuongGiaiNhi;
        this.soLuongGiaiBa = soLuongGiaiBa;
        this.soLuongGiaiKK1 = soLuongGiaiKK1;
        this.soLuongGiaiKK2 = soLuongGiaiKK2;
        this.giaTriGiaiNhat = giaTriGiaiNhat;
        this.giaTriGiaiNhi = giaTriGiaiNhi;
        this.giaTriGiaiBa = giaTriGiaiBa;
        this.giaTriGiaiKK1 = giaTriGiaiKK1;
        this.giaTriGiaiKK2 = giaTriGiaiKK2;
        this.currentTime = currentTime;
        this.endTime = endTime;
    }
}
