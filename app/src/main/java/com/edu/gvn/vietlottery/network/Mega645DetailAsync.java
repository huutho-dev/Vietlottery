package com.edu.gvn.vietlottery.network;

import android.content.Context;
import android.os.AsyncTask;

import com.edu.gvn.vietlottery.Config;
import com.edu.gvn.vietlottery.entity.Mega645Previous;
import com.edu.gvn.vietlottery.entity.sub.Mega645Prize;
import com.edu.gvn.vietlottery.utils.ReadWriteJsonFileUtils;
import com.google.gson.Gson;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class Mega645DetailAsync extends AsyncTask<String, Void, Mega645Previous> {
    private static final String NAME_DETAIL_MEGA645 ="Detail_mega645";
    private static final String NEWS_PAGE = "div.col-xs-12.col-md-10.news-page";
    private static final String GET_DATE_TIME_MEGA_645 = "div.box-result-detail > p.time-result > b";
    private static final String GET_SO_TIEN_GIAI_JACKPOT = "div.box-result-detail > h4.jackpot-value.red > b";
    private static final String GET_TABLE_GIAI_THUONG = "div.result.clearfix.table-responsive > table.table.table-striped";
    private static final String ELEMENT_TITLE_TABLE = "thead > tr > th";
    private static final String GET_VALUE_TABLE = "tbody > tr";


    public interface Mega645AsyncCallback {
        void callBack(Mega645Previous data);
    }

    private Mega645AsyncCallback callback;
    private ReadWriteJsonFileUtils readWriteJsonFileUtils;

    public Mega645DetailAsync(Context context, Mega645AsyncCallback callback) {
        this.callback = callback;
        readWriteJsonFileUtils = new ReadWriteJsonFileUtils(context);
    }

    @Override
    protected Mega645Previous doInBackground(String... strings) {
        Mega645Previous mega645Previous = null;
        ArrayList<Mega645Prize> mega645Prizes = new ArrayList<>();
        try {
            Document document = Jsoup.connect(strings[0]).timeout(Config.REQUEST_TIME_OUT).get();
            Element root = document.select(NEWS_PAGE).first();


                String thoiGianQuayThuong = root.select(GET_DATE_TIME_MEGA_645).text();
                int indexDash = thoiGianQuayThuong.indexOf("|");
                String kyQuayThuong = thoiGianQuayThuong.substring(0, 3).trim() + " " +
                        thoiGianQuayThuong.substring(indexDash - 6, indexDash).trim();
                String ngayQuayThuong = thoiGianQuayThuong.substring(thoiGianQuayThuong.length() - 11).trim();
                String soTienJackpot = root.select(GET_SO_TIEN_GIAI_JACKPOT).text();

                Element table = root.select(GET_TABLE_GIAI_THUONG).first();

                mega645Prizes.add(getHeadTable(table));
                mega645Prizes.addAll(getContentTable(table));

                mega645Previous = new Mega645Previous(kyQuayThuong, ngayQuayThuong, "null", soTienJackpot, mega645Prizes);

            readWriteJsonFileUtils.createJsonFileData(NAME_DETAIL_MEGA645,new Gson().toJson(mega645Previous));
            return mega645Previous;
        } catch (Exception e) {
            e.printStackTrace();
            try{
                mega645Previous = new Gson().fromJson(NAME_DETAIL_MEGA645,Mega645Previous.class);
            }catch (Exception ex){
                mega645Prizes = new ArrayList<>();
                mega645Previous = new Mega645Previous("xxxxx","xx/xx/xxxx","xxxxxx","xxxx",mega645Prizes);
            }
            return mega645Previous;
        }
    }

    @Override
    protected void onPostExecute(Mega645Previous mega645Previous) {
        super.onPostExecute(mega645Previous);
        callback.callBack(mega645Previous);
    }

    private Mega645Prize getHeadTable(Element table) {
        try {
            Elements titleTable = table.select(ELEMENT_TITLE_TABLE);
            String title_GiaiThuong = titleTable.get(0).text();                                // Giải thưởng
            String title_TrungKhop = titleTable.get(1).text();                                 // Trùng khớp
            String title_SoLuongGiai = titleTable.get(2).text();                               // Số lượng giải
            String title_GiaTriGiai = titleTable.get(3).text();                                // Giá trị giải

            return new Mega645Prize(title_GiaiThuong, title_TrungKhop, title_SoLuongGiai, title_GiaTriGiai);
        } catch (Exception e) {
            return new Mega645Prize("Giải thưởng", "Trùng khớp", "Số lượng giải", "Giá trị giải");

        }
    }

    private ArrayList<Mega645Prize> getContentTable(Element table) {
        ArrayList<Mega645Prize> mega645Prizes = new ArrayList<>();
        Elements mega = table.select(GET_VALUE_TABLE);
        int sizeMega = mega.size();
        for (int i = 0; i < sizeMega; i++) {
            Elements td = mega.get(i).select("td");
            // các dòng giá trị của bảng  :
            String content_giaiThuong = td.get(0).select("a").text();                   // Jackpot
            String content_trungKhop = td.get(1).text();                                // Trùng 6 số
            String content_soLuongGiai = td.get(2).text();                              // 1
            String content_giaTriGiai = td.get(3).select("span").text();                // 54.886.916.500

            mega645Prizes.add(new Mega645Prize(content_giaiThuong, content_trungKhop, content_soLuongGiai, content_giaTriGiai));
        }
        return mega645Prizes;
    }

}
