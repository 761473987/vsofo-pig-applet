package com.vsofo.applet.pigfarmstat.vo.daily;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 存栏详情页
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AmountDailyDetailVo {
    private List<AmountDailyDetailValueVo> valueList;

    private ChartVo data;

    private Integer sumStockNum;
}
