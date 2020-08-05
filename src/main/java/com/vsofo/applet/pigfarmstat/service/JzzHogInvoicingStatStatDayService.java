package com.vsofo.applet.pigfarmstat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vsofo.applet.pigfarmstat.pojo.JzzHogInvoicingStatStatDay;

import java.util.Map;

/**
 * 金智猪商品猪存栏
 */
public interface JzzHogInvoicingStatStatDayService extends IService<JzzHogInvoicingStatStatDay> {
    void jzzSysHogStatDay(Map<String, Object> columnMap);
}
