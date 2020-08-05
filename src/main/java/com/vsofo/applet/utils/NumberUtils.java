package com.vsofo.applet.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author: wangtao
 * @date: 2020/5/29
 */
public class NumberUtils {

//    小数的格式化以及百分比表示
    public static String formattedDecimalToPercentage(double decimal) {
        //获取格式化对象
        NumberFormat nt = NumberFormat.getPercentInstance();
        //设置百分数精确度0即保留两位小数
        nt.setMinimumFractionDigits(0);
        return nt.format(decimal);
    }


    //    小数的格式化以及百分比表示
    public static double formatDouble (double decimal,int newScale) {
        //获取格式化对象
        if (Double.isNaN(decimal)){
            return 0;
        }
        if (Double.isInfinite(decimal)){
            return 0;
        }
        BigDecimal bg = new BigDecimal(decimal);
        return bg.setScale(newScale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    //    小数的格式化以及百分比表示
    public static String formatDoubleToString (double decimal,int newScale) {
        return String.valueOf(formatDouble(decimal, newScale));
    }


    public static void main(String[] args) {
        System.out.println(formatDouble(2.5566555,2));;

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.set(2020,4,27);
        for (int i = 1; i < 17 ; i++) {
            System.out.println("当前日期:"+sf.format(c.getTime()));
            c.add(Calendar.DAY_OF_MONTH, 1);
            System.out.println("增加一天后日期:"+sf.format(c.getTime()));
        }


    }
}
