package com.vsofo.applet.pigfarmstat.service;

import com.vsofo.applet.pigfarmstat.pojo.JzzHogInvoicingStatStatDay;
import com.vsofo.applet.pigfarmstat.pojo.JzzPigInvoicingStatStatDay;

import java.util.List;
import java.util.Map;

/**
 * 访问金智猪数据库的接口
 */
public interface JzzService {
    List<JzzPigInvoicingStatStatDay> findJzzPigStatistical(Map<String, Object> columnMap);

    List<JzzHogInvoicingStatStatDay> JzzHogStatistical(Map<String, Object> columnMap);
}
