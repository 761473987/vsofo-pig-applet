package com.vsofo.applet.pigfarmstat.mapper;

import com.vsofo.applet.pigfarmstat.pojo.JzzHogInvoicingStatStatDay;
import com.vsofo.applet.pigfarmstat.pojo.JzzPigInvoicingStatStatDay;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 访问金智猪的mapper
 */
public interface JzzMapper {
    /**
     * 统计种猪存栏
     * @param columnMap
     * @return
     */
    List<JzzPigInvoicingStatStatDay> findJzzPigStatistical(@Param("columnMap") Map<String, Object> columnMap);

    List<JzzHogInvoicingStatStatDay> findJzzHogStatistical(@Param("columnMap") Map<String, Object> columnMap);
}
