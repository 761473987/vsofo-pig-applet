package com.vsofo.applet.pigfarmstat.vo.indicators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ILineChartVo implements IIChar{
    private String month;
    private double value;
    private String dataType;
}