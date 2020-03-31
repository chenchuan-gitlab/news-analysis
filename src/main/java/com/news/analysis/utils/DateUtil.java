package com.news.analysis.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 */
public class DateUtil {
    public static String getDate_yMdhms(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());
        return date;
    }

    public static String getDate_yMd(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        return date;
    }

    public static String getDate_hms(){
        String date = DateFormat.getTimeInstance().format(new Date());
        return date;
    }
}
