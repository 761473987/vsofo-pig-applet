package com.vsofo.applet.pigfarmstat.vo.monthly;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 出栏存栏折线图vo
 */
@Data
@NoArgsConstructor
public class MoreCumulativeLineChartVo extends LineChartVo  implements MIChar{
    private String type;

    public MoreCumulativeLineChartVo(String type,String month, int value) {
        super(month, value);
        this.type = type;
    }
}
