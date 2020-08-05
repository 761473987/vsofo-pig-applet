package com.vsofo.applet.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期工具类
 */
public class DateUtils {
    /**
     * 获取一年种某一周的开始时间
     * @param year
     * @param week
     * @return
     */
    public static LocalDate getDateByWeek(int year , int week){
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        LocalDate ldt = LocalDate.now()
                .withYear(year)
                .with(weekFields.weekOfYear(), week)
                .with(weekFields.dayOfWeek(), 1);
        return ldt;
    }

    /**
     * 获取时间区间，用数组展示
     * @param year
     * @param month
     * @return
     */
    public static String[] getDaySnterval(int year, int month) {
        LocalDate now = LocalDate.now();
        String[] daySnterval = new String[2];
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //本月的第一天
        LocalDate firstday = LocalDate.of(year,month,1);
        //本月的最后一天
        LocalDate lastDay = firstday.with(TemporalAdjusters.lastDayOfMonth());;
        if (year==now.getYear()){
            if (month == now.getMonthValue()){
                lastDay = now;
            }
        }
        String startDay = firstday.format(dateTimeFormatter);
        String endDay = lastDay.format(dateTimeFormatter);
        daySnterval[0] = startDay;
        daySnterval[1] = endDay;
        return daySnterval;
    }

    public static void main(String[] args) {
        getDaySnterval(2020,2);
    }
}
