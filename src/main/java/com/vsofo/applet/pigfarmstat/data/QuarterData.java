package com.vsofo.applet.pigfarmstat.data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wangtao
 * @date: 2020/5/26
 */
public class QuarterData {
    private static List<Quarter> list = new ArrayList<>();
    static {
        list.add(new Quarter("1234","全年"));
        list.add(new Quarter("12","上半年"));
        list.add(new Quarter("34","下半年"));
        list.add(new Quarter("1","第一季度"));
        list.add(new Quarter("2","第二季度"));
        list.add(new Quarter("3","第三季度"));
        list.add(new Quarter("4","第四季度")); }

    public static List<Quarter> getList() {
        return list;
    }

    public static Quarter getQuarter(String code){
        for (Quarter q: list) {
            if (q.getCode().equals(code)){
                return q;
            }
        }
        return null;
    }

    public static String getQuarterName(String code){
        return getQuarter(code).getName();
    }

    public static MonthInterval getMonthInterval(String quarterId){
        System.out.println(quarterId);
        switch (quarterId){
            case "1" : return new MonthInterval(1,3);
            case "2" : return new MonthInterval(4,6);
            case "3" : return new MonthInterval(7,9);
            case "4" : return new MonthInterval(10,12);
            case "12" : return new MonthInterval(1,6);
            case "34" : return new MonthInterval(7,12);
            case "1234" : return new MonthInterval(1,12);
            default:return new MonthInterval(1,12);
        }
    }

    static final class Quarter{
       String code;
       String name;

        public Quarter(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }
    }
}
