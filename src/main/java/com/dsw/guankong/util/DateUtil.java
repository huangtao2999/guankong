package com.dsw.guankong.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {

    private static final String FORMAT_FULL ="yyyy-MM-dd HH:mm:ss";
    private static final String FORMAT_1 = "yyyyMMddHHmmss";
    private static final String FORMAT_2 = "yyyy-MM-dd";


    public static Long formatTime(String dateStr,DateStyle dateStyle){
        SimpleDateFormat sf = new SimpleDateFormat(dateStyle.getPattern());
        Date date = null;
        try {
            date = sf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }
    public static String formatFull(String dateStr,DateStyle dateStyle){
        Date date = new Date();
        date.setTime(formatTime(dateStr,dateStyle));
        SimpleDateFormat sf = new SimpleDateFormat(DateStyle.FORMAT_FULL.getPattern());
        return  sf.format(date);
    }

    public static String getToday(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DateStyle.FORMAT_2.getPattern());
        LocalDateTime localDateTime  = LocalDateTime.now();
        String today = dateTimeFormatter.format(localDateTime);
        return today;
    }
}
