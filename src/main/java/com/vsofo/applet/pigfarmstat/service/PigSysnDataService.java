package com.vsofo.applet.pigfarmstat.service;

import com.vsofo.applet.pigfarmstat.dto.pigSysn.FybDto;

import java.util.List;

public interface PigSysnDataService {
    int pigInventoryDailySysn(String sysnDate);

    int pigInventoryMonthSysn(String sysnDate);

    int emptyTodayData();

    int emptyToMonthData();

    List<FybDto> findFybXs(String format,Long departmentID);

}
