package com.bruceyu.library;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by BruceYu on 2017/4/9 10:08.
 * Email: 13738725570@163.com
 * Description: 日期工具类
 */

public class DateUtils {

    /**
     *  根据时间差获取新的时间
     * @param oldTime
     * @param diffTime
     * @return
     */
    public static String getNewTime(String oldTime, long diffTime) {
        // 设定时间的模板
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date oldDate = sdf.parse(oldTime);
            // 获取服务器时间
            Date da = new Date(oldDate.getTime() + diffTime * 1000);
            return sdf.format(da);
        } catch (ParseException e) {
            return "";
        }
    }

    /**
     * 根据时间差获取新的年份
     * @param oldTime
     * @param diffTime
     * @return
     */
    public static int getNewYear(String oldTime, long diffTime) {
        String newDate = getNewTime(oldTime, diffTime);
        return Integer.parseInt(newDate.split("-")[0]);
    }

    /**
     * 根据时间差获取新的月份
     * @param oldTime
     * @param diffTime
     * @return
     */
    public static int getNewMonth(String oldTime, long diffTime) {
        String newDate = getNewTime(oldTime, diffTime);
        return Integer.parseInt(newDate.split("-")[1]);
    }
}
