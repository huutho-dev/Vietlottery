package com.edu.gvn.vietlottery.entity;

import com.edu.gvn.vietlottery.entity.sub.Mega;

import java.util.List;

/**
 * Created by hnc on 29/11/2016.
 */

public class Mega645Previous {

    public String kyQuayThuong;
    public String ngayQuayThuong;
    public String soMayMan;
    public String soTienJackpot;
    public List<Mega> megas;

    public Mega645Previous(String kyQuayThuong, String ngayQuayThuong,
                           String soMayMan, String soTienJackpot, List<Mega> megas) {
        this.kyQuayThuong = kyQuayThuong;
        this.ngayQuayThuong = ngayQuayThuong;
        this.soMayMan = soMayMan;
        this.soTienJackpot = soTienJackpot;
        this.megas = megas;
    }

    @Override
    public String toString() {
        return "Mega645Previous{" +
                "kyQuayThuong='" + kyQuayThuong + '\'' +
                ", ngayQuayThuong='" + ngayQuayThuong + '\'' +
                ", soMayMan='" + soMayMan + '\'' +
                ", soTienJackpot='" + soTienJackpot + '\'' +
                ", megas=" + megas +
                '}';
    }
}
