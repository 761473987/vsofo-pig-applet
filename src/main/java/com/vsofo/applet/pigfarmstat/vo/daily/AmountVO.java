package com.vsofo.applet.pigfarmstat.vo.daily;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: wangtao
 * @date: 2020/5/28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "存栏")
public class AmountVO {
    @ApiModelProperty("标题名称")
    private String menuName;
    @ApiModelProperty("单位")
    private String unit;
    @ApiModelProperty("总存栏")
    private Integer allNum;
    @ApiModelProperty("当前存栏数组")
    private List<ChilVo> chil;
    @ApiModelProperty("当前存栏图表")
    private List<ChartVo> chart;

}
