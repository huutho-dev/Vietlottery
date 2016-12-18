package com.game24h.vietlottery.utils;

import android.util.Log;

import com.game24h.vietlottery.Config;


public class LogUtils {
    public static void v(String tag, String msg) {
        if (Config.DEBUG) {
            Log.v(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (Config.DEBUG) {
            Log.e(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (Config.DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (Config.DEBUG) {
            Log.i(tag, msg);
        }
    }
}
