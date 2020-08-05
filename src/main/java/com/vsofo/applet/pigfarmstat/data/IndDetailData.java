package com.vsofo.applet.pigfarmstat.data;

import java.util.ArrayList;
import java.util.List;

/**
 * 指标详情页指标组数据
 */
public class IndDetailData {
    private static List<QuarterData.Quarter> list = new ArrayList<>();
    static {
        list.add(new QuarterData.Quarter("1", "配种成绩"));
        list.add(new QuarterData.Quarter("2", "配种分娩率"));
        list.add(new QuarterData.Quarter("3", "分娩成绩"));
        list.add(new QuarterData.Quarter("4", "断奶成绩"));
        list.add(new QuarterData.Quarter("5", "存栏"));
        list.add(new QuarterData.Quarter("6", "猪批次"));
    }
    public static List<QuarterData.Quarter> getQuarter(){
        return list;
    }
}
