package com.vsofo.applet.pigfarmstat.dto.indicators;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Calendar;

/**
 * @author: wangtao
 * @date: 2020/6/2
 * 指标搜索条件
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class IndicatorsDto implements Cloneable{
    @ApiModelProperty(hidden = true)
    private int year = Calendar.getInstance().get(Calendar.YEAR);
    @ApiModelProperty(hidden = true)
    private int statMonth = 1;
    @ApiModelProperty(hidden = true)
    private int endMonth = 12;

    @ApiModelProperty(hidden = true)
    private int month = Calendar.getInstance().get(Calendar.MONTH);;

    @ApiModelProperty(value = "时间")
    private String statDate;
    @ApiModelProperty(value = "大区ID")
    private Integer organId;
    @ApiModelProperty(value = "养殖场ID")
    private Integer farmId;

    @ApiModelProperty(hidden = true)
    private String week;

    @ApiModelProperty(hidden = true)
    //开始时间
    private String beginDate;
    //结束时间
    @ApiModelProperty(hidden = true)
    private String endDate;

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getStatMonth() {
        return statMonth;
    }

    public void setStatMonth(int statMonth) {
        this.statMonth = statMonth;
    }

    public int getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(int endMonth) {
        this.endMonth = endMonth;
    }

    public String getStatDate() {
        return statDate;
    }

    public void setStatDate(String statDate) {
        this.statDate = statDate;
    }

    public Integer getOrganId() {
        return organId;
    }

    public void setOrganId(Integer organId) {
        this.organId = organId;
    }

    public Integer getFarmId() {
        return farmId;
    }

    public void setFarmId(Integer farmId) {
        if (farmId == -1) this.farmId = null;
        this.farmId = farmId;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public IndicatorsDto clone() throws CloneNotSupportedException {
        return (IndicatorsDto) super.clone();
    }
}
