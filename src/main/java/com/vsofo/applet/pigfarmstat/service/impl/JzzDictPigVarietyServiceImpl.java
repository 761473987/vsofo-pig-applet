package com.vsofo.applet.pigfarmstat.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vsofo.applet.pigfarmstat.mapper.JzzDictPigVarietyMapper;
import com.vsofo.applet.pigfarmstat.pojo.JzzDictPigVariety;
import com.vsofo.applet.pigfarmstat.service.JzzDictPigVarietyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *金智猪品种实现
 */
@Service
@Slf4j
public class JzzDictPigVarietyServiceImpl extends ServiceImpl<JzzDictPigVarietyMapper, JzzDictPigVariety> implements JzzDictPigVarietyService {

    @Cacheable(cacheNames = "dic",key = "dictPigVariety")
    @Override
    public List<JzzDictPigVariety> cachelist() {
        return this.list();
    }
}
