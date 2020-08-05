package com.vsofo.applet.pigfarmstat.dto.cost;

import com.vsofo.applet.pigfarmstat.vo.monthly.MIChar;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description: 成本饼图展示对象
 * @author: liuzhiyun
 * @create: 2020-07-15 10:33
 **/
@Data
@ApiModel
@AllArgsConstructor
@NoArgsConstructor
public class PieChartDetailDto implements MIChar, Serializable {
    @ApiModelProperty("属性名")
    private String item;
    @ApiModelProperty("属性值")
    private double value;
    @ApiModelProperty("比率")
    private double ratio;
}
