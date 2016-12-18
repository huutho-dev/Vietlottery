package com.game24h.vietlottery.entity;


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
