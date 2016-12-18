package com.game24h.vietlottery.utils;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by namIT on 12/8/2016.
 */

public class ReadWriteJsonFileUtils {

    AppCompatActivity activity;
    Context context;

    public ReadWriteJsonFileUtils(Context context) {
        this.context = context;
    }

    public void createJsonFileData(String filename, String mJsonResponse) {
        try {

            LogUtils.d("ReadWriteJsonFileUtils", "write :"+ filename);

            LogUtils.d("readJsonFileData", new String(mJsonResponse));
            File checkFile = new File(context.getApplicationInfo().dataDir + "/cache_pets/");

            if (!checkFile.exists()) {
                checkFile.mkdir();
            }
            FileWriter file = new FileWriter(checkFile.getAbsolutePath() + "/" + filename);
            file.write(mJsonResponse);
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readJsonFileData(String filename) {
        try {

            LogUtils.d("ReadWriteJsonFileUtils", "read :"+ filename);

            File f = new File(context.getApplicationInfo().dataDir + "/cache_pets/" + filename);
            if (!f.exists()) {
                return onNoResult();
            }
            FileInputStream is = new FileInputStream(f);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            LogUtils.d("readJsonFileData", new String(buffer));
            return new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return onNoResult();
    }

    public boolean checkFile(String filename) {
        File f = new File(context.getApplicationInfo().dataDir + "/cache_pets/" + filename);
        return f.exists();
    }

    private String onNoResult() {
        return " {\"data\":[],\"code\":1}";
    }

    public void deleteFile() {
        File f = new File(context.getApplicationInfo().dataDir + "/cache_pets/");
        File[] files = f.listFiles();
        for (File fInDir : files) {
            fInDir.delete();
        }
    }

    public void deleteFile(String fileName) {
        File f = new File(context.getApplicationInfo().dataDir + "/cache_pets/" + fileName);
        if (f.exists()) {
            f.delete();
        }
    }
}