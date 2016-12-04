package com.edu.gvn.vietlottery.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.format.Time;

import com.edu.gvn.vietlottery.R;

/**
 * Created by HuuTho on 12/4/2016.
 */

public class PushNotiService extends Service {
    private Handler handler;
    private boolean isToday = true;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                checkTimeForPush();
                handler.postDelayed(this, 20000);
            }
        }, 20000);

        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    public void checkTimeForPush() {
        Time now = new Time();
        now.setToNow();

        if (isToday && now.hour == 16 && now.minute == 52) {
            NotificationManager notif = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            Notification notify = new Notification.Builder
                    (getApplicationContext())
                    .setContentTitle(getResources().getString(R.string.time_up))
                    .setContentText(getResources().getString(R.string.content)).
                            setContentTitle(getResources().getString(R.string.turn_on_tv))
                    .setSmallIcon(R.drawable.bell)
                    .setWhen(System.currentTimeMillis())
                    .build();


            notify.flags |= Notification.FLAG_AUTO_CANCEL;
            notif.notify(0, notify);
            isToday = false;
            return;
        }
        if (!isToday && now.hour == 0 && now.minute == 0) {
            isToday = true;
        }
    }


}
