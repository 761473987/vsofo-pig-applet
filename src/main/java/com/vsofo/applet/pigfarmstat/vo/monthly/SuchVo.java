package com.vsofo.applet.pigfarmstat.vo.monthly;

import com.vsofo.applet.pigfarmstat.vo.daily.ChartVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: wangtao
 * @date: 2020/5/29
 * 出栏视图
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuchVo {
    private String menuName;
    private String unit;
    private Integer allNum;
    private String total;
    private Integer jtNum;
    private List<MChilVo> chil;
    private List<MChartVo> chart;
    
    public SuchVo(String menuName, String unit, Integer allNum, Integer jtNum, List<MChilVo> chil, List<MChartVo> chart) {
        this.menuName = menuName;
        this.unit = unit;
        this.allNum = allNum;
        this.jtNum = jtNum;
        this.chil = chil;
        this.chart = chart;
    }
}
