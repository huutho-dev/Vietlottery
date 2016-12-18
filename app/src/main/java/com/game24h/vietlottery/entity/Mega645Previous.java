package com.game24h.vietlottery.entity;

import com.game24h.vietlottery.entity.sub.Mega645Prize;

import java.util.List;

/**
 * Created by hnc on 29/11/2016.
 */

public class Mega645Previous extends BaseEntity {

    public String kyQuayThuong;
    public String ngayQuayThuong;
    public String soMayMan;
    public String soTienJackpot;
    public List<Mega645Prize> mega645Prizes;

    public Mega645Previous(String kyQuayThuong, String ngayQuayThuong,
                           String soMayMan, String soTienJackpot, List<Mega645Prize> mega645Prizes) {
        this.kyQuayThuong = kyQuayThuong;
        this.ngayQuayThuong = ngayQuayThuong;
        this.soMayMan = soMayMan;
        this.soTienJackpot = soTienJackpot;
        this.mega645Prizes = mega645Prizes;
    }

    @Override
    public String toString() {
        return "Mega645Previous{" +
                "kyQuayThuong='" + kyQuayThuong + '\'' +
                ", ngayQuayThuong='" + ngayQuayThuong + '\'' +
                ", soMayMan='" + soMayMan + '\'' +
                ", soTienJackpot='" + soTienJackpot + '\'' +
                ", mega645Prizes=" + mega645Prizes +
                '}';
    }
}
