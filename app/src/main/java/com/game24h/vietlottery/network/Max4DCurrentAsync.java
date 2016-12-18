package com.game24h.vietlottery.network;

import android.content.Context;
import android.os.AsyncTask;

import com.game24h.vietlottery.Config;
import com.game24h.vietlottery.entity.Max4DCurrent;
import com.game24h.vietlottery.entity.sub.Max4dPrize;
import com.game24h.vietlottery.utils.ReadWriteJsonFileUtils;
import com.google.gson.Gson;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class Max4DCurrentAsync extends AsyncTask<String, Void, Max4DCurrent> {
    private static final String NAME_MAX4D_CURRENT = "Current_max4d";
    private static final String ROOT_TAB_MAX_4D = "div#max-4d";
    private static final String GET_TIME_RESULT = "div#result-games-max4d >  div.box-result-detail > p.time-result";
    private static final String GET_BOX_RESULT = "div#result-games-max4d > div.box-result-detail > ul.result-max4d";
    private static final String GET_VALUE_BOX = "div.box-result-max4d";
    private static final String GET_VALUE = "ul.num-result-max4d";
    private static final String GET_PRIZE = "div.table-responsive.result.clearfix > table.table.table-striped > tbody > tr";

    public interface Max4DCurrentAsyncCallback {
        void callBack(Max4DCurrent max4DCurrent);
    }

    private Max4DCurrentAsyncCallback callback;
    private ReadWriteJsonFileUtils readWriteJsonFileUtils;

    public Max4DCurrentAsync(Context context, Max4DCurrentAsyncCallback callback) {
        this.callback = callback;
        readWriteJsonFileUtils = new ReadWriteJsonFileUtils(context);
    }


    @Override
    protected Max4DCurrent doInBackground(String... strings) {
        Max4dPrize max4dPrize = null;
        Max4DCurrent max4DCurrent = null;
        try {
            Document document = Jsoup.connect(strings[0]).timeout(Config.REQUEST_TIME_OUT).get();
            Element root = document.select(ROOT_TAB_MAX_4D).first();

            String thoiGianQuayThuong = root.select(GET_TIME_RESULT).get(0).text().trim();    //Kỳ quay thưởng #00006 | Ngày quay thưởng 01/12/2016
            int indexDash = thoiGianQuayThuong.indexOf("|");
            String kyQuayThuong = thoiGianQuayThuong.substring(0, 3).trim() + " " +
                    thoiGianQuayThuong.substring(indexDash - 6, indexDash).trim();
            String ngayQuayThuong = thoiGianQuayThuong.substring(thoiGianQuayThuong.length() - 11).trim();

            String timeScrip = "";
            timeScrip = root.select("script").html();
            if (timeScrip.equals("")) {
                timeScrip = " $(function() {\n" +
                        "                             countDownTimer(\"mega-6-45-countdowntimer\", \"11/30/2016 5:45:00 PM\", \"11/30/2016 5:45:00 PM\", regexpReplaceWith);\n" +
                        "                        });";
            }

            int firstMark = timeScrip.indexOf("\"");
            int secondMark = timeScrip.indexOf("\"", firstMark + 1);
            int thirdMark = timeScrip.indexOf("\"", secondMark + 1);
            int fourthMark = timeScrip.indexOf("\"", thirdMark + 1);
            int fifthMark = timeScrip.indexOf("\"", fourthMark + 1);
            int sixth = timeScrip.indexOf("\"", fifthMark + 1);

            String currentTime = timeScrip.substring(thirdMark + 1, fourthMark);
            String endTime = timeScrip.substring(fifthMark + 1, sixth);


            String giaiNhat = root.select(GET_BOX_RESULT).select(GET_VALUE_BOX).select(GET_VALUE).get(0).text();
            String giaiNhi1 = root.select(GET_BOX_RESULT).select(GET_VALUE_BOX).select(GET_VALUE).get(1).text();
            String giaiNhi2 = root.select(GET_BOX_RESULT).select(GET_VALUE_BOX).select(GET_VALUE).get(2).text();
            String giaiBa1 = root.select(GET_BOX_RESULT).select(GET_VALUE_BOX).select(GET_VALUE).get(3).text();
            String giaiBa2 = root.select(GET_BOX_RESULT).select(GET_VALUE_BOX).select(GET_VALUE).get(4).text();
            String giaiBa3 = root.select(GET_BOX_RESULT).select(GET_VALUE_BOX).select(GET_VALUE).get(5).text();
            String giaiKK1 = root.select(GET_BOX_RESULT).select(GET_VALUE_BOX).select(GET_VALUE).get(6).text();
            String giaiKK2 = root.select(GET_BOX_RESULT).select(GET_VALUE_BOX).select(GET_VALUE).get(7).text();

            max4dPrize = new Max4dPrize(kyQuayThuong, ngayQuayThuong, giaiNhat, giaiNhi1, giaiNhi2, giaiBa1, giaiBa2, giaiBa3, giaiKK1, giaiKK2);

            String soLuongGiaiNhat = root.select(GET_PRIZE).get(0).select("td").get(2).text();
            String soLuongGiaiNhi = root.select(GET_PRIZE).get(1).select("td").get(2).text();
            String soLuongGiaiBa = root.select(GET_PRIZE).get(2).select("td").get(2).text();
            String soLuongGiaiKK1 = root.select(GET_PRIZE).get(3).select("td").get(2).text();
            String soLuongGiaiKK2 = root.select(GET_PRIZE).get(4).select("td").get(2).text();

            String giaTriGiaiNhat = root.select(GET_PRIZE).get(0).select("td").get(3).text();
            String giaTriGiaiNhi = root.select(GET_PRIZE).get(1).select("td").get(3).text();
            String giaTriGiaiBa = root.select(GET_PRIZE).get(2).select("td").get(3).text();
            String giaTriGiaiKK1 = root.select(GET_PRIZE).get(3).select("td").get(3).text();
            String giaTriGiaiKK2 = root.select(GET_PRIZE).get(4).select("td").get(3).text();

            max4DCurrent = new Max4DCurrent(max4dPrize, soLuongGiaiNhat, soLuongGiaiNhi,
                    soLuongGiaiBa, soLuongGiaiKK1, soLuongGiaiKK2,
                    giaTriGiaiNhat, giaTriGiaiNhi, giaTriGiaiBa, giaTriGiaiKK1, giaTriGiaiKK2, currentTime, endTime);

            readWriteJsonFileUtils.createJsonFileData(NAME_MAX4D_CURRENT, new Gson().toJson(max4DCurrent));
            return max4DCurrent;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                max4DCurrent = new Gson().fromJson(readWriteJsonFileUtils.readJsonFileData(NAME_MAX4D_CURRENT), Max4DCurrent.class);
            } catch (Exception ex) {
                ex.printStackTrace();
                Max4dPrize max4dPrize1 = new Max4dPrize("xx","xx","xxxx","xxxx","xxxx","xxxx","xxxx","xxxx","xxxx","xxxx");
                max4DCurrent = new Max4DCurrent(max4dPrize1,"xx","xx","xxx","xxxx","xxxx","xxxx","xxxx","xxxx","xxxx","xxxx","11/30/2016 5:45:00 PM","11/30/2016 5:45:00 PM");
            }
            return max4DCurrent;
        }
    }

    @Override
    protected void onPostExecute(Max4DCurrent max4DCurrent) {
        super.onPostExecute(max4DCurrent);
        callback.callBack(max4DCurrent);
    }
}
