package com.vsofo.applet.pigfarmstat.dto.monthly;

import com.vsofo.applet.exception.GeneralException;
import com.vsofo.applet.exception.PigCodeMsg;
import com.vsofo.applet.pigfarmstat.data.MonthInterval;
import com.vsofo.applet.pigfarmstat.data.QuarterData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Calendar;

/**
 * @author: wangtao
 * @date: 2020/5/29
 */

public class MonthlySearchDto implements Cloneable{
    @ApiModelProperty(name = "farmId",value = "养殖场ID")
    private Integer farmId;
    @ApiModelProperty(name = "organId",value = "大区ID")
    private Integer organId;
    @ApiModelProperty(name = "dateType",value = "时间段")
    private String dateType = "1234";
    @ApiModelProperty(name = "year",value = "时间")
    private Integer year = Calendar.getInstance().get(Calendar.YEAR);

    @ApiModelProperty(hidden = true)
    private int statMonth = 1;
    @ApiModelProperty(hidden = true)
    private int endMonth = Calendar.getInstance().get(Calendar.MONTH) +1;

    @ApiModelProperty(hidden = true)
    /**
     * 判断是否修改了结束月份
     */
    private boolean updateVesrion = true;


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

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
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

    @Override
    public MonthlySearchDto clone(){
        try {
            return (MonthlySearchDto) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
