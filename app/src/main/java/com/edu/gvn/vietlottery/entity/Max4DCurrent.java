package com.edu.gvn.vietlottery.entity;

import com.edu.gvn.vietlottery.entity.sub.Max4dPrize;

/**
 * Created by HuuTho on 12/2/2016.
 */

public class Max4DCurrent extends BaseEntity {
    public Max4dPrize max4dPrize ;
    public String soLuongGiaiNhat, soLuongGiaiNhi, soLuongGiaiBa, soLuongGiaiKK1, soLuongGiaiKK2;
    public String giaTriGiaiNhat,giaTriGiaiNhi, giaTriGiaiBa,giaTriGiaiKK1,giaTriGiaiKK2;

    public Max4DCurrent(Max4dPrize max4dPrize, String soLuongGiaiNhat, String soLuongGiaiNhi,
                        String soLuongGiaiBa, String soLuongGiaiKK1, String soLuongGiaiKK2,
                        String giaTriGiaiNhat, String giaTriGiaiNhi, String giaTriGiaiBa,
                        String giaTriGiaiKK1, String giaTriGiaiKK2) {

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
    }
}
