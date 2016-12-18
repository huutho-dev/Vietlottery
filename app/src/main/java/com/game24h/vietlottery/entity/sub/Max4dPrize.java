package com.game24h.vietlottery.entity.sub;

import com.game24h.vietlottery.entity.BaseEntity;


public class Max4dPrize extends BaseEntity {
    public String kyQuayThuong, ngayQuayThuong;
    public String giaiNhat;
    public String giaiNhi1, giaiNhi2;
    public String giaiBa1,giaiBa2,giaiBa3;
    public String giaiKK1,giaiKK2;

    public Max4dPrize(String kyQuayThuong, String ngayQuayThuong, String giaiNhat, String giaiNhi1,
                      String giaiNhi2, String giaiBa1, String giaiBa2, String giaiBa3,
                      String giaiKK1, String giaiKK2) {
        this.kyQuayThuong = kyQuayThuong;
        this.ngayQuayThuong = ngayQuayThuong;
        this.giaiNhat = giaiNhat;
        this.giaiNhi1 = giaiNhi1;
        this.giaiNhi2 = giaiNhi2;
        this.giaiBa1 = giaiBa1;
        this.giaiBa2 = giaiBa2;
        this.giaiBa3 = giaiBa3;
        this.giaiKK1 = giaiKK1;
        this.giaiKK2 = giaiKK2;
    }

    @Override
    public String toString() {
        return "Max4dPrize{" +
                "kyQuayThuong='" + kyQuayThuong + '\'' +
                ", ngayQuayThuong='" + ngayQuayThuong + '\'' +
                ", giaiNhat='" + giaiNhat + '\'' +
                ", giaiNhi1='" + giaiNhi1 + '\'' +
                ", giaiNhi2='" + giaiNhi2 + '\'' +
                ", giaiBa1='" + giaiBa1 + '\'' +
                ", giaiBa2='" + giaiBa2 + '\'' +
                ", giaiBa3='" + giaiBa3 + '\'' +
                ", giaiKK1='" + giaiKK1 + '\'' +
                ", giaiKK2='" + giaiKK2 + '\'' +
                '}';
    }
}
