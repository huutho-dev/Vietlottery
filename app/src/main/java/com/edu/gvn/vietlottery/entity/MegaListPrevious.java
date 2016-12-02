package com.edu.gvn.vietlottery.entity;

/**
 * Created by hnc on 30/11/2016.
 */

public class MegaListPrevious extends BaseEntity {
    public String link;
    public String date ;
    public String result ;

    public MegaListPrevious(String link, String date, String result) {
        this.link = link;
        this.date = date;
        this.result = result;
    }
}
