package com.vsofo.applet.pigfarmstat.service.impl;

import com.vsofo.applet.pigfarmstat.dto.pigSysn.FybDto;
import com.vsofo.applet.pigfarmstat.mapper.PigSysnDataMapper;
import com.vsofo.applet.pigfarmstat.service.PigSysnDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 猪场同步
 */
@Service
@Transactional(isolation = Isolation.READ_UNCOMMITTED)
public class PigSysnDataServiceImpl implements PigSysnDataService {


    @Autowired
    private PigSysnDataMapper pigSysnDataMapper;

    /**
     * 猪场存栏出栏日报同步
     * @return
     */
    @Override
    public int pigInventoryDailySysn(String sysnDate) {
        return pigSysnDataMapper.pigInventoryDailySysn(sysnDate);
    }

    @Override
    public int pigInventoryMonthSysn(String sysnDate) {
        return pigSysnDataMapper.pigInventoryMonthSysn(sysnDate);
    }

    @Override
    public int emptyTodayData() {
        return pigSysnDataMapper.emptyTodayData();
    }


    @Override
    public int emptyToMonthData() {
        return pigSysnDataMapper.emptyToMonthData();
    }

    @Override
    public List<FybDto> findFybXs(String format,Long departmentID) {
        return pigSysnDataMapper.findFybXs(format,departmentID);
    }

}
