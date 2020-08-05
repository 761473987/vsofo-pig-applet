package com.vsofo.applet.pigfarmstat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vsofo.applet.pigfarmstat.mapper.JzzPigInvoicingStatStatDayMapper;
import com.vsofo.applet.pigfarmstat.pojo.JzzDictPigVariety;
import com.vsofo.applet.pigfarmstat.pojo.JzzPigInvoicingStatStatDay;
import com.vsofo.applet.pigfarmstat.service.JzzDictPigVarietyService;
import com.vsofo.applet.pigfarmstat.service.JzzPigInvoicingStatStatDayService;
import com.vsofo.applet.pigfarmstat.service.JzzService;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 金智猪种猪存栏
 */
@Service
@Slf4j
public class JzzPigInvoicingStatStatDayServiceImpl extends ServiceImpl<JzzPigInvoicingStatStatDayMapper, JzzPigInvoicingStatStatDay> implements JzzPigInvoicingStatStatDayService {

    @Autowired
    private JzzPigInvoicingStatStatDayMapper jzzPigInvoicingStatStatDayMapper;

    @Autowired
    private JzzDictPigVarietyService jzzDictPigVarietyService;

    @Autowired
    private JzzService jzzService;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public void jzzSysPigStatDay(Date startDate, Date endDate) {

    }

    /**
     * 统计金智猪存栏
     *
     * @param columnMap
     */
    @Override
    public void jzzSysPigStatDay(Map<String, Object> columnMap) {
        this.removeByMap(columnMap);
        List<JzzPigInvoicingStatStatDay> list = jzzService.findJzzPigStatistical(columnMap);
        List<JzzDictPigVariety> dict = jzzDictPigVarietyService.cachelist();//从缓存中获取品种
        list.forEach(p ->
            dict.forEach(d -> {
                if (d.getJzzPigVarietyId().equals(p.getJzzPigVarietyId())) {
                    p.setPigTypeId(d.getPigVarietyId());
//                    this.save(p);
                    log.warn("金智猪种猪保存id为{}成功",p.getId());
                    return;
                }
            })
        );
        int i = this.jzzPigInvoicingStatStatDayMapper.batchInsert(list);
        log.warn(i==0?"批量保存失败":"批量保存成功");
    }


}
