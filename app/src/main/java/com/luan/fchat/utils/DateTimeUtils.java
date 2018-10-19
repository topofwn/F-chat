package com.luan.fchat.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


public class DateTimeUtils {

    public static String convertFromFullFormatToHourFormat(String fullDate) {
        fullDate = convertUTCToLocalTime(fullDate);
        DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        DateFormat targetFormat = new SimpleDateFormat("hh:mm a", Locale.US);
        try {
            Date date = originalFormat.parse(fullDate);
            return targetFormat.format(date);
        } catch (ParseException e) {
            return "--:--";
        }
    }

    public static String convertFromFullFormatToDayFormat(String fullDate) {
        fullDate = convertUTCToLocalTime(fullDate);
        DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        DateFormat targetFormat = new SimpleDateFormat("MMM dd yyyy", Locale.US);
        try {
            Date date = originalFormat.parse(fullDate);
            return targetFormat.format(date);
        } catch (ParseException e) {
            return "--:--:--";
        }
    }

    public static String getDateWithFullMCFormat() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        return df.format(Calendar.getInstance().getTime());
    }

    private static String convertUTCToLocalTime(String date) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date value = formatter.parse(date);

            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US); //this format changeable
            dateFormatter.setTimeZone(TimeZone.getDefault());
            date = dateFormatter.format(value);

            //Log.d("OurDate", OurDate);
        } catch (ParseException e) {
            date = "0000-00-00 00:00:00";
        }
        return date;
    }
}
