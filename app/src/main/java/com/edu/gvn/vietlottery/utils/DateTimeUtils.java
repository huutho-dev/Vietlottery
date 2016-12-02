package com.edu.gvn.vietlottery.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by hnc on 30/11/2016.
 */

public class DateTimeUtils {
    private SimpleDateFormat simpleDateFormat =
            new SimpleDateFormat("M/dd/yyyy hh:mm:ss a", Locale.US);

    public DateTimeUtils() {

    }

    public String remainingTime(String startTime, String endTime) {

        StringBuilder remainTimeBuilder = new StringBuilder();
        try {
            Date start = simpleDateFormat.parse(startTime);
            Date end = simpleDateFormat.parse(endTime);

            long different = start.getTime() - end.getTime();

            long secondsInMilli = 1000;
            long minutesInMilli = secondsInMilli * 60;
            long hoursInMilli = minutesInMilli * 60;
            long daysInMilli = hoursInMilli * 24;

            long elapsedDays = different / daysInMilli;
            different = different % daysInMilli;

            long elapsedHours = different / hoursInMilli;
            different = different % hoursInMilli;

            long elapsedMinutes = different / minutesInMilli;
            different = different % minutesInMilli;

            long elapsedSeconds = different / secondsInMilli;


            remainTimeBuilder.append(convertTimeLongToString(elapsedDays));
            remainTimeBuilder.append("-");
            remainTimeBuilder.append(convertTimeLongToString(elapsedHours));
            remainTimeBuilder.append("-");
            remainTimeBuilder.append(convertTimeLongToString(elapsedMinutes));
            remainTimeBuilder.append("-");
            remainTimeBuilder.append(convertTimeLongToString(elapsedSeconds));

            return remainTimeBuilder.toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return remainTimeBuilder.append("00").append("-").append("00").append("-").append("00").append("-").append("00").toString();
    }

    private String convertTimeLongToString(long value) {
        if (value < 0) {
            value = -value;
            return String.valueOf(value);

        } else if (value == 0) {
            return "00";

        } else {
            return String.valueOf(value);
        }
    }


}
