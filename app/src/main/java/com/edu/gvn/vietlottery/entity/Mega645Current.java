package com.edu.gvn.vietlottery.entity;


public class Mega645Current extends BaseEntity {
    public String giaTriUocTinh ;
    public String curentTime, endTime ;
    public Mega645Previous mega645Previous ;

    public Mega645Current(String giaTriUocTinh, String currentTime, String endTime, Mega645Previous mega645Previous) {
        this.giaTriUocTinh = giaTriUocTinh;
        this.curentTime = currentTime ;
        this.endTime = endTime;
        this.mega645Previous = mega645Previous;
    }


}
