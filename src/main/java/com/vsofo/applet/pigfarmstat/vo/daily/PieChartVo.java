package com.vsofo.applet.pigfarmstat.vo.daily;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 饼图
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PieChartVo implements IChar{
    private String item;
    private int value;
    private double ratio;
}
