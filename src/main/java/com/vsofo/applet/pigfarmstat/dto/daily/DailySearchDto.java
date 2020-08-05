package com.vsofo.applet.pigfarmstat.dto.daily;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;

import java.util.Date;

/**
 * @author: wangtao
 * @date: 2020/5/28
 */

public class DailySearchDto {
    @ApiModelProperty(name = "farmId",value = "养殖场ID")
    private Integer farmId;
    @ApiModelProperty(name = "organId",value = "大区ID")
    private Integer organId;
    @ApiModelProperty(name = "stateDate",value = "日期")

    private String stateDate = DateFormatUtils.format(System.currentTimeMillis(),"yyyy-MM-dd");

    public Integer getFarmId() {
        return farmId;
    }

    public void setFarmId(Integer farmId) {
        this.farmId = farmId;
    }

    public Integer getOrganId() {
        return organId;
    }

    public void setOrganId(Integer organId) {
        this.organId = organId;
    }

    public String getStateDate() {
        return stateDate;
    }

    public void setStateDate(String stateDate) {
        this.stateDate = stateDate;
    }
}

