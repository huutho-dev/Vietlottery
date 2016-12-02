package com.edu.gvn.vietlottery.network;

import android.os.AsyncTask;

import com.edu.gvn.vietlottery.entity.Mega645Previous;
import com.edu.gvn.vietlottery.entity.sub.Mega;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by hnc on 29/11/2016.
 */

public class Mega645DetailAsync extends AsyncTask<String, Void, Mega645Previous> {

    private static final String NEWS_PAGE = "div.col-xs-12.col-md-10.news-page";
    private static final String GET_DATE_TIME_MEGA_46_5 = "div.box-result-detail > p.time-result > b";
    private static final String GET_SO_TIEN_GIAI_JACKPOT = "div.box-result-detail > h4.jackpot-value.red > b";
    private static final String GET_TABLE_GIAI_THUONG = "div.result.clearfix.table-responsive > table.table.table-striped";
    private static final String ELEMENT_TITLE_TABLE = "thead > tr > th";
    private static final String GET_VALUE_TABLE = "tbody > tr";


    public interface Mega645AsyncCallback {
        void callBack(Mega645Previous data);
    }

    private Mega645AsyncCallback callback;

    public Mega645DetailAsync(Mega645AsyncCallback callback) {
        this.callback = callback;
    }

    @Override
    protected Mega645Previous doInBackground(String... strings) {
        Mega645Previous mega645Previous;
        ArrayList<Mega> megas = new ArrayList<>();
        try {
            Document document = Jsoup.connect(strings[0]).get();
            Element root = document.select(NEWS_PAGE).first();

            String thoiGianQuayThuong = root.select(GET_DATE_TIME_MEGA_46_5).text();
            int indexDash = thoiGianQuayThuong.indexOf("|");
            String kyQuayThuong = thoiGianQuayThuong.substring(indexDash - 7, indexDash).trim();
            String ngayQuayThuong = thoiGianQuayThuong.substring(thoiGianQuayThuong.length() - 10).trim();
            String soTienJackpot = root.select(GET_SO_TIEN_GIAI_JACKPOT).text();

            Element table = root.select(GET_TABLE_GIAI_THUONG).first();

            megas.add(getHeadTable(table));
            megas.addAll(getContentTable(table));

            mega645Previous = new Mega645Previous(kyQuayThuong, ngayQuayThuong, "null", soTienJackpot, megas);

            return mega645Previous;
        } catch (IOException e) {
            e.printStackTrace();
            mega645Previous = new Mega645Previous("", "", "", "", megas);
            return mega645Previous;
        }
    }

    @Override
    protected void onPostExecute(Mega645Previous mega645Previous) {
        super.onPostExecute(mega645Previous);
        callback.callBack(mega645Previous);
    }

    private Mega getHeadTable(Element table) {
        try {
            Elements titleTable = table.select(ELEMENT_TITLE_TABLE);
            String title_GiaiThuong = titleTable.get(0).text();                                // Giải thưởng
            String title_TrungKhop = titleTable.get(1).text();                                 // Trùng khớp
            String title_SoLuongGiai = titleTable.get(2).text();                               // Số lượng giải
            String title_GiaTriGiai = titleTable.get(3).text();                                // Giá trị giải

            return new Mega(title_GiaiThuong, title_TrungKhop, title_SoLuongGiai, title_GiaTriGiai);
        } catch (Exception e) {
            return new Mega("Giải thưởng", "Trùng khớp", "Số lượng giải", "Giá trị giải");

        }
    }
    private ArrayList<Mega> getContentTable(Element table) {
        ArrayList<Mega> megas = new ArrayList<>();
        Elements mega = table.select(GET_VALUE_TABLE);
        int sizeMega = mega.size();
        for (int i = 0; i < sizeMega; i++) {
            Elements td = mega.get(i).select("td");
            // các dòng giá trị của bảng  :
            String content_giaiThuong = td.get(0).select("a").text();                   // Jackpot
            String content_trungKhop = td.get(1).text();                                // Trùng 6 số
            String content_soLuongGiai = td.get(2).text();                              // 1
            String content_giaTriGiai = td.get(3).select("span").text();                // 54.886.916.500

            megas.add(new Mega(content_giaiThuong, content_trungKhop, content_soLuongGiai, content_giaTriGiai));
        }
        return megas;
    }

}
