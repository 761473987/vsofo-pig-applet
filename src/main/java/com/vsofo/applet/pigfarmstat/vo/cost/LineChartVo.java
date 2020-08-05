package com.vsofo.applet.pigfarmstat.vo.cost;

import com.vsofo.applet.pigfarmstat.vo.monthly.MIChar;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @description: 折线图
 * @author: liuzhiyun
 * @create: 2020-07-21 11:03
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineChartVo implements MIChar {
    private String month;
    private BigDecimal value;
}
