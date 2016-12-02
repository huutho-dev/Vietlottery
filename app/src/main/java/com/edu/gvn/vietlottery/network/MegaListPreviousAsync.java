package com.edu.gvn.vietlottery.network;

import android.os.AsyncTask;

import com.edu.gvn.vietlottery.Config;
import com.edu.gvn.vietlottery.entity.MegaListPrevious;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


public class MegaListPreviousAsync extends AsyncTask<String, Void, ArrayList<MegaListPrevious>> {
    private static final String GET_LIST_PREVIOUS = "div.result.clearfix.table-responsive > table.table.table-striped > tbody > tr";

    public interface MegaListPreviousAsyncCallback {
        void callBack(ArrayList<MegaListPrevious> datas);
    }

    private MegaListPreviousAsyncCallback callBack;

    public MegaListPreviousAsync(MegaListPreviousAsyncCallback callBack) {
        this.callBack = callBack;
    }

    @Override
    protected ArrayList<MegaListPrevious> doInBackground(String... strings) {
        ArrayList<MegaListPrevious> datas = new ArrayList<>();
        try {
            Document document = Jsoup
                    .connect(strings[0])
                    .timeout(Config.REQUEST_TIME_OUT)
                    .get();

            Elements root = document.select(GET_LIST_PREVIOUS);

            int listSize = root.size();

            for (int i = 0; i < listSize; i++) {
                Element item = root.get(i);
                String link = item.select("td").get(0).select("a").attr("href");
                String date = item.select("td").get(0).select("a").text();
                String result = item.select("td").get(1).select("span").text();

                datas.add(new MegaListPrevious(link, date, result));
            }
            return datas;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return datas;
    }

    @Override
    protected void onPostExecute(ArrayList<MegaListPrevious> megaListPreviouses) {
        super.onPostExecute(megaListPreviouses);
        callBack.callBack(megaListPreviouses);
    }
}
