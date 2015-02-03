package com.e_lliam.app.video.server.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static Date now() {
        return new Date();
    }

    /**
     * 获取本周的星期一
     * @return
     */
    public static String getWeekFirshDay()
    {
        Calendar cal =Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); //获取本周一的日期
        return df.format(cal.getTime());
    }

    public static String getDay(){
        Calendar cal =Calendar.getInstance();
        return cal.get(Calendar.DATE)+"";
    }

    public static String getMonthDay(){
        Calendar cal =Calendar.getInstance();
        return cal.get(Calendar.MONTH)+1+"";
    }

    public static void main(String[] args){
        System.out.println(getWeekFirshDay());
        System.out.println(getDay());
        System.out.println(getMonthDay());
    }

    public static String getFormat(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("Y-MM-dd HH:mm:ss");

        return format.format(date);
    }

    public static boolean compareDate(Date createDate) {
        Long time = createDate.getTime() - now().getTime();
        if (time < 1000 * 60 * 60 * 24)
            return true;
        else return false;
    }
}
