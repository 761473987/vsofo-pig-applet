package com.vsofo.applet.pigfarmstat.vo.monthly;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: wangtao
 * @date: 2020/5/29
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineChartVo implements MIChar{
    private String month;
    private int value;
}
