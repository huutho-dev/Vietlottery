package com.edu.gvn.vietlottery.network;

import android.os.AsyncTask;

import com.edu.gvn.vietlottery.Config;
import com.edu.gvn.vietlottery.entity.sub.Max4dPrize;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Max4dPreviousAsync extends AsyncTask<String, Void, ArrayList<Max4dPrize>> {
    private static final String ROOT_TABLE = "div.result.clearfix.table-responsive > table.table.table-striped > tbody > tr";
    private static final String GET_NGAY_QUAY_THUONG = "div.info-result-game";
    private static final String GET_RESULT_MAX4 = "ul.result-max4d";
    private static final String GET_BOX_RESULT = "div.box-result-max4d";
    private static final String GET_RESULT = "ul.num-result-max4d";

    public interface Max4dPreviousCallback {
        void callBack(ArrayList<Max4dPrize> datas);
    }

    private Max4dPreviousCallback callback;

    public Max4dPreviousAsync(Max4dPreviousCallback callback) {
        this.callback = callback;
    }

    @Override
    protected ArrayList<Max4dPrize> doInBackground(String... strings) {

        ArrayList<Max4dPrize> listPrize = new ArrayList<>();
        try {
            Document document = Jsoup.connect(strings[0]).timeout(Config.REQUEST_TIME_OUT).get();
            Elements root = document.select(ROOT_TABLE);

            if (root != null) {
                int itemSize = root.size();

                for (int i = 0; i < itemSize; i++) {
                    Element item = root.get(i);

                    String ngayQuayThuong = item.select("td").get(1).select(GET_NGAY_QUAY_THUONG).select("span").get(0).text();
                    String kyQuayThuong = item.select("td").get(1).select(GET_NGAY_QUAY_THUONG).select("span").get(1).text();

                    Elements boxResult = item.select("td").get(2).select(GET_RESULT_MAX4).select(GET_BOX_RESULT).select(GET_RESULT);
                    String giaiNhat = boxResult.get(0).text();
                    String giaiNhi1 = boxResult.get(1).text();
                    String giaiNhi2 = boxResult.get(2).text();
                    String giaiBa1 = boxResult.get(3).text();
                    String giaiBa2 = boxResult.get(4).text();
                    String giaiBa3 = boxResult.get(5).text();
                    String giaiKK1 = boxResult.get(6).text();
                    String giaiKK2 = boxResult.get(7).text();

                    Max4dPrize max4dPrize = new Max4dPrize(kyQuayThuong, ngayQuayThuong, giaiNhat,
                            giaiNhi1, giaiNhi2, giaiBa1, giaiBa2, giaiBa3, giaiKK1, giaiKK2);

                    listPrize.add(max4dPrize);
                }
            }
            return listPrize;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Max4dPrize> max4dPrizes) {
        super.onPostExecute(max4dPrizes);
        callback.callBack(max4dPrizes);
    }
}
