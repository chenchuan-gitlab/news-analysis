package com.news.analysis.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * IDUtil
 */
public class IDUtil {
    public static String getID(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = sdf.format(new Date());
        int random = (int)((Math.random()*9+1)*1000);
        return date+String.valueOf(random);
    }

    public static String getRoleID(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = sdf.format(new Date());
        return date;
    }
}
