package com.vsofo.applet.pigfarmstat.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * @author: wangtao
 * @date: 2020/5/29
 */
@AllArgsConstructor
@Getter
public enum ChartEnum {
    LINE_MAP("LINE","折线图"),
    CUMULATIVE_LINE_MAP("CUMULATIVE_LINE","累计折线图"),
    CUMULATIVE_LINE_COST("CUMULATIVE_LINE_COST","成本累计折线图"),
    COLUMNAR_MAP("COLUMNAR","柱状图"),
    MORE_COLUMNAR_MAP("MORE_COLUMNAR","柱状图"),
    BREAD_MAP("BREAD","饼图"),
    MORE_LINE_MAP("MORE","多线折线图")
    ;

    private String code;
    private String name;
}
