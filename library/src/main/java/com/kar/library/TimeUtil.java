package com.kar.library;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.format.Time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 获取当前时间，10位
 */
public class TimeUtil {
    private TimeUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 获取当前时间，10位
     */
    public static long getCurrentTime() {
        return (long) (System.currentTimeMillis() / 1000);
    }

    /**
     * 获取增加多少月的时间
     *
     * @return addMonth - 增加多少月
     */
    public static Date getAddMonthDate(int addMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, addMonth);
        return calendar.getTime();
    }

    /**
     * 获取增加多少天的时间
     *
     * @return addDay - 增加多少天
     */
    public static Date getAddDayDate(int addDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, addDay);
        return calendar.getTime();
    }
    /**
     * 获取增加多少小时的时间
     *
     * @return addDay - 增加多少小时
     */
    public static Date getAddHourDate(int addHour) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, addHour);
        return calendar.getTime();
    }

    /**
     * 显示时间格式为 hh:mm
     */
    @SuppressLint("SimpleDateFormat")
    public static String formatTimeShort(Context context, long when) {
        String formatStr = "HH:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        String temp = sdf.format(when);
        if (temp != null && temp.length() == 5 && temp.substring(0, 1).equals("0")) {
            temp = temp.substring(1);
        }
        return temp;
    }

    /**
     * 显示时间格式为今天、昨天、yyyy/MM/dd hh:mm
     */
    public static String formatTimeString(Context context, long when) {
        Time then = new Time();
        then.set(when);
        Time now = new Time();
        now.setToNow();

        String formatStr;
        if (then.year != now.year) {
            formatStr = "yyyy/MM/dd";
        } else if (then.yearDay != now.yearDay) {
            // If it is from a different day than today, show only the date.
            formatStr = "MM/dd";
        } else {
            // Otherwise, if the message is from today, show the time.
            formatStr = "HH:MM";
        }

        if (then.year == now.year && then.yearDay == now.yearDay) {
            return "今天";
        } else if ((then.year == now.year) && ((now.yearDay - then.yearDay) == 1)) {
            return "昨天";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
            String temp = sdf.format(when);
            if (temp != null && temp.length() == 5 && temp.substring(0, 1).equals("0")) {
                temp = temp.substring(1);
            }
            return temp;
        }
    }

    /**
     * 是否同一天
     */
    public static boolean isSameDate(long date1, long date2) {
        long days1 = date1 / (1000 * 60 * 60 * 24);
        long days2 = date2 / (1000 * 60 * 60 * 24);
        return days1 == days2;
    }

}
