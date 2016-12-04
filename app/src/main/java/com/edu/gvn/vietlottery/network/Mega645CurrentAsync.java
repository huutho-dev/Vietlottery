package com.edu.gvn.vietlottery.network;

import android.os.AsyncTask;

import com.edu.gvn.vietlottery.Config;
import com.edu.gvn.vietlottery.entity.Mega645Current;
import com.edu.gvn.vietlottery.entity.Mega645Previous;
import com.edu.gvn.vietlottery.entity.sub.Mega645Prize;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Mega645CurrentAsync extends AsyncTask<String, Void, Mega645Current> {

    private static final String TAB_CONTENT_MEGA_645 = "div.tab-content > div#mega-6-45";
    private static final String GET_GIA_TRI_UOC_TINH = "div.jackpot-win > h2";
    private static final String GET_THOI_GIAN_QUAY_THUONG = "div#result-games > div.box-result-detail > p";
    private static final String GET_SO_MAY_MAN = "ul.result-number > li";
    private static final String GET_TABLE = "div.result.clearfix.table-responsive > table.table.table-striped";
    private static final String GET_SO_TIEN_TRUNG_THUONG = "tbody > tr > td";
    private static final String GET_HEADER_TABLE = "thead > tr > th";
    private static final String GET_CONTENT_TABLE = "tbody > tr";

    public interface Mega645AsyncCallback {
        void callBack(Mega645Current current);
    }

    private Mega645AsyncCallback callback;

    public Mega645CurrentAsync(Mega645AsyncCallback callback) {
        this.callback = callback;
    }

    @Override
    protected Mega645Current doInBackground(String... strings) {

        Mega645Current mega645Current = null;
        String giaTriUocTinh;
        String currentTime = null, endTime = null;

        ArrayList<Mega645Prize> mega645Prizes = new ArrayList<>();
        Mega645Previous mega645Previous;

        try {

            String timeScrip = "";
            Document document = Jsoup.connect(strings[0]).timeout(Config.REQUEST_TIME_OUT).get();
            Element root = document.select(TAB_CONTENT_MEGA_645).first();

            if (root != null){
                giaTriUocTinh = root.select(GET_GIA_TRI_UOC_TINH).text();
                timeScrip = root.select("script").html();

                if (timeScrip.equals("")) {
                    timeScrip = " $(function() {\n" +
                            "                             countDownTimer(\"mega-6-45-countdowntimer\", \"11/30/2016 5:45:00 PM\", \"11/30/2016 5:45:00 PM\", regexpReplaceWith);\n" +
                            "                        });";
                }

                /**
                 * $(function() {
                 countDownTimer("mega-6-45-countdowntimer", "11/30/2016 4:50:28 PM", "11/30/2016 5:45:00 PM", regexpReplaceWith);
                 });
                 */


                int firstMark = timeScrip.indexOf("\"");
                int secondMark = timeScrip.indexOf("\"", firstMark + 1);
                int thirdMark = timeScrip.indexOf("\"", secondMark + 1);
                int fourthMark = timeScrip.indexOf("\"", thirdMark + 1);
                int fifthMark = timeScrip.indexOf("\"", fourthMark + 1);
                int sixth = timeScrip.indexOf("\"", fifthMark + 1);

                currentTime = timeScrip.substring(thirdMark + 1, fourthMark);
                endTime = timeScrip.substring(fifthMark + 1, sixth);

                String thoiGianQuayThuong = root.select(GET_THOI_GIAN_QUAY_THUONG).first().text();
                int indexDash = thoiGianQuayThuong.indexOf("|");
                String kyQuayThuong = thoiGianQuayThuong.substring(0,3).trim() +" "+
                        thoiGianQuayThuong.substring(indexDash-6, indexDash).trim();
                String ngayQuayThuong = thoiGianQuayThuong.substring(thoiGianQuayThuong.length()-11).trim();

                String soTrungThuong = root.select(GET_SO_MAY_MAN).text().trim();

                // Có 2 table : table(0) : chứa giá tri jackpot, table(1) chứa data giải thưởng
                String soTienTrungThuong = root.select(GET_TABLE).get(0).select(GET_SO_TIEN_TRUNG_THUONG).text();
                mega645Prizes.add(getHeaderTable(root.select(GET_TABLE).get(1)));
                mega645Prizes.addAll(getContentTable(root.select(GET_TABLE).get(1)));

                mega645Previous = new Mega645Previous(kyQuayThuong, ngayQuayThuong, soTrungThuong, soTienTrungThuong, mega645Prizes);
                mega645Current = new Mega645Current(giaTriUocTinh, currentTime, endTime, mega645Previous);
            }



            return mega645Current;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Mega645Current mega645Current) {
        super.onPostExecute(mega645Current);
        callback.callBack(mega645Current);
    }

    private Mega645Prize getHeaderTable(Element table) {
        String title_GiaiThuong;
        String title_TrungKhop;
        String title_SoLuongGiai;
        String title_GiaTriGiai;
        try {
            Elements head = table.select(GET_HEADER_TABLE);
            title_GiaiThuong = head.get(0).text();              // Giải thưởng
            title_TrungKhop = head.get(1).text();               // Trùng khớp
            title_SoLuongGiai = head.get(2).text();             // Số lượng giải
            title_GiaTriGiai = head.get(3).text();              // Giá trị giải
        } catch (Exception e) {
            e.printStackTrace();
            title_GiaiThuong = "Giải thưởng";
            title_TrungKhop = "Trùng khớp";
            title_SoLuongGiai = "Số lượng giải";
            title_GiaTriGiai = "Giá trị giải";
        }

        return new Mega645Prize(title_GiaiThuong, title_TrungKhop, title_SoLuongGiai, title_GiaTriGiai);
    }

    private ArrayList<Mega645Prize> getContentTable(Element table) {
        ArrayList<Mega645Prize> content = new ArrayList<>();

        Elements rows = table.select(GET_CONTENT_TABLE);
        int numberRow = rows.size();
        for (int i = 0; i < numberRow; i++) {
            Elements oneRow = rows.get(i).select("td");
            String tenGiaiThuong = oneRow.get(0).text().trim();
            String trungKhop = oneRow.get(1).select("i").size() + "";
            String soLuongGiai = oneRow.get(2).text().trim();
            String giaTriGiaiThuong = oneRow.get(3).text().trim();

            content.add(new Mega645Prize(tenGiaiThuong, trungKhop, soLuongGiai, giaTriGiaiThuong));
        }
        return content;
    }

}
