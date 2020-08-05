package com.vsofo.applet.pigfarmstat.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.vsofo.applet.pigfarmstat.mapper.JzzMapper;
import com.vsofo.applet.pigfarmstat.pojo.JzzHogInvoicingStatStatDay;
import com.vsofo.applet.pigfarmstat.pojo.JzzPigInvoicingStatStatDay;
import com.vsofo.applet.pigfarmstat.service.JzzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 访问金智猪数据库的接口实现
 */
@Service
@DS("jzz")
public class JzzServiceImpl implements JzzService {
    @Autowired
    private JzzMapper jzzMapper;

    @Override
    public List<JzzPigInvoicingStatStatDay> findJzzPigStatistical(Map<String, Object> columnMap) {
        return jzzMapper.findJzzPigStatistical(columnMap);
    }

    @Override
    public List<JzzHogInvoicingStatStatDay> JzzHogStatistical(Map<String, Object> columnMap) {
        return jzzMapper.findJzzHogStatistical(columnMap);
    }
}
