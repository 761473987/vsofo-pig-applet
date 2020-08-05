package com.vsofo.applet.pigfarmstat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vsofo.applet.pigfarmstat.pojo.JzzPigInvoicingStatStatDay;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 金智猪种猪存栏
 */
public interface JzzPigInvoicingStatStatDayService extends IService<JzzPigInvoicingStatStatDay> {
    void jzzSysPigStatDay(Date startDate, Date endDate);

    void jzzSysPigStatDay(Map<String, Object> columnMap);

}
