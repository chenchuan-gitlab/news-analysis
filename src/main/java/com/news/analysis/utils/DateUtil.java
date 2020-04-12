package com.news.analysis.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * DateUtil
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

    public static String getDate_ym(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String date = sdf.format(new Date());
        return date;
    }

    /**
     * 获取月份中所有天数列表
     *
     * @param monthStr 月份
     * @return 天数列表
     * @throws Exception
     */
    public static List<String> getDaysByMonth(String monthStr) throws Exception {
        List<String> days = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sdf.parse(monthStr));
        int year = calendar.get(Calendar.YEAR);
        int m = calendar.get(Calendar.MONTH) + 1;
        int dayNumOfMonth = getDaysByYearMonth(year, m);
        // 从一号开始
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        for (int i = 0; i < dayNumOfMonth; i++, calendar.add(Calendar.DATE, 1)) {
            Date d = calendar.getTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            days.add(simpleDateFormat.format(d));
        }
        return days;
    }

    /**
     * 计算某年的 某月 有多少天
     *
     * @param year  年份
     * @param month 月份
     * @return 返回当月的天数
     */
    public static int getDaysByYearMonth(int year, int month) {

        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }
}
