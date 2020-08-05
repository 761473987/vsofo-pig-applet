package com.vsofo.applet.pigfarmstat.mapper;

import com.vsofo.applet.pigfarmstat.dto.pigSysn.FybDto;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface PigSysnDataMapper {

    int pigInventoryDailySysn(@Param("sysnDate")String sysnDate);

    int pigInventoryMonthSysn(@Param("sysnDate")String sysnDate);

    int emptyTodayData();

    int emptyToMonthData();

    List<FybDto> findFybXs(@Param("date") String date,@Param("departmentID") Long departmentID);

}
