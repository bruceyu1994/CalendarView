package com.bruceyu.library;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by BruceYu on 2017/4/7 20:28.
 * Email: 13738725570@163.com
 * Description:
 */

public class CalendarDay  implements Serializable, Comparable<CalendarDay> {
    private static final long serialVersionUID = -5456695978688356202L;
    private Calendar calendar;

    public int day;
    public int month;
    public int year;
    public String tag;

    public CalendarDay(Calendar calendar, String tag) {
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        this.tag = tag;
    }

    public CalendarDay() {
        setTime(System.currentTimeMillis());
    }

    public CalendarDay(int year, int month, int day) {
        setDay(year, month, day);
    }

    public CalendarDay(long timeInMillis) {
        setTime(timeInMillis);
    }

    public CalendarDay(Calendar calendar) {
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    private void setTime(long timeInMillis) {
        if (calendar == null) {
            calendar = Calendar.getInstance();
        }
        calendar.setTimeInMillis(timeInMillis);
        month = this.calendar.get(Calendar.MONTH);
        year = this.calendar.get(Calendar.YEAR);
        day = this.calendar.get(Calendar.DAY_OF_MONTH);
    }

    public void set(CalendarDay calendarDay) {
        year = calendarDay.year;
        month = calendarDay.month;
        day = calendarDay.day;
    }

    public void setDay(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public Date getDate() {
        if (calendar == null) {
            calendar = Calendar.getInstance();
        }
        calendar.clear();
        calendar.set(year, month, day);
        return calendar.getTime();
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{ year: ");
        stringBuilder.append(year);
        stringBuilder.append(", month: ");
        stringBuilder.append(month);
        stringBuilder.append(", day: ");
        stringBuilder.append(day);
        stringBuilder.append(" }");

        return stringBuilder.toString();
    }

    /**
     * 只比较年月日
     *
     * @param calendarDay
     * @return
     */
    @Override
    public int compareTo(CalendarDay calendarDay) {
//            return getDate().compareTo(calendarDay.getDate());
        if (calendarDay == null) {
            throw new IllegalArgumentException("被比较的日期不能是null");
        }

        if (year == calendarDay.year && month == calendarDay.month && day == calendarDay.day) {
            return 0;
        }

        if (year < calendarDay.year ||
                (year == calendarDay.year && month < calendarDay.month) ||
                (year == calendarDay.year && month == calendarDay.month && day < calendarDay.day)) {
            return -1;
        }
        return 1;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof CalendarDay) {
            CalendarDay calendarDay = (CalendarDay) o;
            if (compareTo(calendarDay) == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 大于比较的日期（只比较年月日）
     *
     * @param o
     * @return
     */
    public boolean after(Object o) {
        if (o instanceof CalendarDay) {
            CalendarDay calendarDay = (CalendarDay) o;
            if (compareTo(calendarDay) == 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * 小于比较的日期（只比较年月日）
     *
     * @param o
     * @return
     */
    public boolean before(Object o) {
        if (o instanceof CalendarDay) {
            CalendarDay calendarDay = (CalendarDay) o;
            if (compareTo(calendarDay) == -1) {
                return true;
            }
        }
        return false;
    }
}