package com.vsofo.applet.pigfarmstat.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 成本统计数据
 * @author: liuzhiyun
 * @create: 2020-07-20
 **/
@Data
@ApiModel("成本统计")
public class PigframStatCost implements Serializable {
  @ApiModelProperty("主键")
  private long id;
  @ApiModelProperty("养殖场id")
  private long farmId;
  @ApiModelProperty("饲料")
  private String fodder;
  @ApiModelProperty("药品")
  private String drug;
  @ApiModelProperty("精液")
  private String semen;
  @ApiModelProperty("人员工资、奖金、福利")
  private String staffSalary;
  @ApiModelProperty("其他")
  private String other;
  @ApiModelProperty("固定资产折旧")
  private String depreciationOfFixedAssets;
  @ApiModelProperty("维修费")
  private String maintenanceFees;
  @ApiModelProperty("能耗费用")
  private String energyCosts;
  @ApiModelProperty("低值易耗品费用")
  private String lowValueConsumables;
  @ApiModelProperty("外购猪苗")
  private String purchasedPiglets;
  @ApiModelProperty("更新时间")
  private Date updateTime;
  @ApiModelProperty("创建时间")
  private Date createTime;
  @ApiModelProperty("生产线id")
  private long productionLineId;
  @ApiModelProperty("期")
  private String period;
}
