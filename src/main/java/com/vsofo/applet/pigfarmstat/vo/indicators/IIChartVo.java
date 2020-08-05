package com.vsofo.applet.pigfarmstat.vo.indicators;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author: wangtao
 * @date: 2020/6/2
 */
@Data
@AllArgsConstructor
public class IIChartVo {
    private String chartName;
    private String type;
    private List<? extends IIChar> data;
}
