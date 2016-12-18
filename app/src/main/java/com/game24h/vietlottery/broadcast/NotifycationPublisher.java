package com.game24h.vietlottery.broadcast;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.edu.gvn.vietlottery.R;
import com.game24h.vietlottery.ui.activity.MainActivity;

/**
 * Created by hnc on 07/12/2016.
 */

public class NotifycationPublisher extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, getNotifycation(context));
    }


    private Notification getNotifycation(Context context) {
        NotificationCompat.Builder notification
                = new NotificationCompat.Builder(context)
                .setContentTitle(context.getResources().getString(R.string.time_up))
                .setContentText(context.getResources().getString(R.string.turn_on_tv))
                .setSmallIcon(R.drawable.bell)
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis());


        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        notification.setSound(alarmSound);

        Intent intent = new Intent(context.getApplicationContext(),MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent goHome = PendingIntent.getActivity(context,1,intent,PendingIntent.FLAG_UPDATE_CURRENT );
        notification.setContentIntent(goHome);

        return notification.build();
    }
}
