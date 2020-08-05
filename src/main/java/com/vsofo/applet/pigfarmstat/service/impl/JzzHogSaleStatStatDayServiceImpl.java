package com.vsofo.applet.pigfarmstat.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vsofo.applet.pigfarmstat.mapper.JzzHogSaleStatStatDayMapper;
import com.vsofo.applet.pigfarmstat.pojo.JzzHogSaleStatStatDay;
import com.vsofo.applet.pigfarmstat.service.JzzHogSaleStatStatDayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 金智猪销售
 */
@Service
@Slf4j
@DS("jzz")
public class JzzHogSaleStatStatDayServiceImpl extends ServiceImpl<JzzHogSaleStatStatDayMapper, JzzHogSaleStatStatDay> implements JzzHogSaleStatStatDayService {
}
