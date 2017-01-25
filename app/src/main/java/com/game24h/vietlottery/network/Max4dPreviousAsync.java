package com.game24h.vietlottery.network;

import android.content.Context;
import android.os.AsyncTask;

import com.game24h.vietlottery.Config;
import com.game24h.vietlottery.entity.sub.Max4dPrize;
import com.game24h.vietlottery.utils.ReadWriteJsonFileUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Max4dPreviousAsync extends AsyncTask<String, Void, ArrayList<Max4dPrize>> {
    private static final String NAME_MAX4D_PREVIOUS = "Previous_max4d";
    private static final String ROOT_TABLE = "div.result.clearfix.table-responsive > table.table.table-striped > tbody > tr";
    private static final String GET_NGAY_QUAY_THUONG = "div.info-result-game";
    private static final String GET_RESULT_MAX4 = "ul.result-max4d";
    private static final String GET_BOX_RESULT = "div.box-result-max4d";
    private static final String GET_RESULT = "ul.num-result-max4d";

    private static final String GET_TOTAL_PAGE = "ul.pagination.pagination-sm > li > a";

    public interface Max4dPreviousCallback {
        void callBack(ArrayList<Max4dPrize> datas);
    }

    private Max4dPreviousCallback callback;
    private ReadWriteJsonFileUtils readWriteJsonFileUtils;

    public Max4dPreviousAsync(Context context, Max4dPreviousCallback callback) {
        this.callback = callback;
        readWriteJsonFileUtils = new ReadWriteJsonFileUtils(context);
    }

    @Override
    protected ArrayList<Max4dPrize> doInBackground(String... strings) {

        ArrayList<Max4dPrize> listPrize = new ArrayList<>();
        try {
            Document document = Jsoup.connect(strings[0]).timeout(Config.REQUEST_TIME_OUT).get();

            Elements totalPage = document.select(GET_TOTAL_PAGE);
            int numberLi = totalPage.size();
            int lastPage = numberLi - 2; // trừ đi 2 cái < và >
            int twoPageAgo = lastPage - 2; // lấy kết quả 2 trang cuối thôi

            for (int i = lastPage; i >= twoPageAgo; i--) {

                ArrayList<Max4dPrize> datasInOnePage = new ArrayList<>();

                String pages = strings[0] + "?p=" + i;
                Document documentPage = Jsoup.connect(pages).timeout(Config.REQUEST_TIME_OUT).get();
                Elements root = documentPage.select(ROOT_TABLE);
                int itemSize = root.size();

                for (int k = 0; k < itemSize; k++) {
                    Element item = root.get(k);

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

                    datasInOnePage.add(max4dPrize);

                }
                Collections.reverse(datasInOnePage);
                listPrize.addAll(datasInOnePage);
            }
            readWriteJsonFileUtils.createJsonFileData(NAME_MAX4D_PREVIOUS, new Gson().toJson(listPrize));

            return listPrize;
        } catch (Exception e) {
            e.printStackTrace();

            try {
                listPrize = new Gson().fromJson(readWriteJsonFileUtils.readJsonFileData(NAME_MAX4D_PREVIOUS), new TypeToken<List<Max4dPrize>>() {
                }.getType());
                Collections.reverse(listPrize);
            } catch (Exception ex) {
                ex.printStackTrace();
                listPrize = new ArrayList<>();
            }
            return listPrize;
        }
    }

    @Override
    protected void onPostExecute(ArrayList<Max4dPrize> max4dPrizes) {
        super.onPostExecute(max4dPrizes);
        callback.callBack(max4dPrizes);
    }
}
