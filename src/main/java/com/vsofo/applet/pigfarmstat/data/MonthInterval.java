package com.vsofo.applet.pigfarmstat.data;

/**
 * @author: wangtao
 * @date: 2020/5/27
 * 月份开始时间结束时间
 */
public class MonthInterval {
    private Integer startMonth;
    private Integer endMonth;

    public MonthInterval(Integer startMonth, Integer endMonth) {
        this.startMonth = startMonth;
        this.endMonth = endMonth;
    }

    public Integer getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(Integer startMonth) {
        this.startMonth = startMonth;
    }

    public Integer getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(Integer endMonth) {
        this.endMonth = endMonth;
    }
}
