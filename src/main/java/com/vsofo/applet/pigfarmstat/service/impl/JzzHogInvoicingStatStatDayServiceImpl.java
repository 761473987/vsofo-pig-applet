package com.vsofo.applet.pigfarmstat.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vsofo.applet.pigfarmstat.mapper.JzzHogInvoicingStatStatDayMapper;
import com.vsofo.applet.pigfarmstat.pojo.JzzHogInvoicingStatStatDay;
import com.vsofo.applet.pigfarmstat.pojo.JzzPigInvoicingStatStatDay;
import com.vsofo.applet.pigfarmstat.service.JzzHogInvoicingStatStatDayService;
import com.vsofo.applet.pigfarmstat.service.JzzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/*
* 金智猪商品猪存栏
* */
@Service
@Slf4j
public class JzzHogInvoicingStatStatDayServiceImpl extends ServiceImpl<JzzHogInvoicingStatStatDayMapper, JzzHogInvoicingStatStatDay> implements JzzHogInvoicingStatStatDayService {

    @Autowired
    private JzzHogInvoicingStatStatDayMapper jzzHogInvoicingStatStatDayMapper;
    @Autowired
    private JzzService jzzService;

    @Override
    public void jzzSysHogStatDay(Map<String, Object> columnMap) {
        this.removeByMap(columnMap);
        List<JzzHogInvoicingStatStatDay> list = jzzService.JzzHogStatistical(columnMap);
//        list.forEach(h->this.save(h));
        int i = jzzHogInvoicingStatStatDayMapper.batchInsert(list);
        log.warn(i==0?"批量保存失败":"批量保存成功");
    }
}
