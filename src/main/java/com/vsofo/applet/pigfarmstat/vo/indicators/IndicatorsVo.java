package com.vsofo.applet.pigfarmstat.vo.indicators;

import com.vsofo.applet.pigfarmstat.pojo.Production;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: wangtao
 * @date: 2020/6/2
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class IndicatorsVo {
    private String menuName;
    private String unit;
    private List<ProductionVo> chil;
    private List<IIChartVo> chart;
}
