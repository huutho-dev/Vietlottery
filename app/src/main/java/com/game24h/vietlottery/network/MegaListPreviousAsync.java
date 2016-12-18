package com.game24h.vietlottery.network;

import android.content.Context;
import android.os.AsyncTask;

import com.game24h.vietlottery.Config;
import com.game24h.vietlottery.entity.MegaListPrevious;
import com.game24h.vietlottery.utils.ReadWriteJsonFileUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class MegaListPreviousAsync extends AsyncTask<String, Void, ArrayList<MegaListPrevious>> {
    private static final String NAME_MEGA_LIST_PREVIOS = "Previos_mega_name";
    private static final String GET_LIST_PREVIOUS = "div.result.clearfix.table-responsive > table.table.table-striped > tbody > tr";

    private MegaListPreviousAsyncCallback callBack;
    private ReadWriteJsonFileUtils readWriteJsonFileUtils;

    public MegaListPreviousAsync(Context context, MegaListPreviousAsyncCallback callBack) {
        this.callBack = callBack;
        readWriteJsonFileUtils = new ReadWriteJsonFileUtils(context);
    }

    @Override
    protected ArrayList<MegaListPrevious> doInBackground(String... strings) {
        ArrayList<MegaListPrevious> datas = new ArrayList<>();
        try {
            Document document = Jsoup.connect(strings[0]).timeout(Config.REQUEST_TIME_OUT).get();
            Elements root = document.select(GET_LIST_PREVIOUS);


            int listSize = root.size();
            for (int i = 0; i < listSize; i++) {
                Element item = root.get(i);
                String link = item.select("td").get(0).select("a").attr("href");
                String date = item.select("td").get(0).select("a").text();
                String result = item.select("td").get(1).select("span").text();

                datas.add(new MegaListPrevious(link, date, result));
            }

            readWriteJsonFileUtils.createJsonFileData(NAME_MEGA_LIST_PREVIOS, new Gson().toJson(datas));
            return datas;
        } catch (Exception e) {
            e.printStackTrace();
            try{
                datas = new Gson().fromJson(readWriteJsonFileUtils.readJsonFileData(NAME_MEGA_LIST_PREVIOS), new TypeToken<List<MegaListPrevious>>() {
                }.getType());
            }catch (Exception ex){
                ex.printStackTrace();
                datas = new ArrayList<>();
            }

            return datas;
        }
    }

    @Override
    protected void onPostExecute(ArrayList<MegaListPrevious> megaListPreviouses) {
        super.onPostExecute(megaListPreviouses);
        callBack.callBack(megaListPreviouses);
    }

    public interface MegaListPreviousAsyncCallback {
        void callBack(ArrayList<MegaListPrevious> datas);
    }
}
