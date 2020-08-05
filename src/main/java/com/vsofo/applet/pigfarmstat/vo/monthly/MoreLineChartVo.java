package com.vsofo.applet.pigfarmstat.vo.monthly;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: wangtao
 * @date: 2020/6/1
 * 大区对比出栏多线折线图
 */
@Data
@NoArgsConstructor
public class MoreLineChartVo implements MIChar,Comparable<MoreLineChartVo>{
    private Integer organId;
    private String organName;
    private String month;
    private int value;
    private int order;

    public MoreLineChartVo(Integer organId, String organName, String month , int value,int order) {
        this.organId = organId;
        this.organName = organName;
        this.month = month;
        this.value = value;
        this.order = order;
    }


    @Override
    public int compareTo(MoreLineChartVo o) {
        if (this.getOrder() >= o.getOrder()) return 1;
        return 0;
    }
}
