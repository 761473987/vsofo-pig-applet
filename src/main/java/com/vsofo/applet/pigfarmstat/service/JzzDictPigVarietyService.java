package com.vsofo.applet.pigfarmstat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vsofo.applet.pigfarmstat.pojo.JzzDictPigVariety;

import java.util.List;

/**
 * 金智猪品种
 */
public interface JzzDictPigVarietyService extends IService<JzzDictPigVariety> {
    List<JzzDictPigVariety> cachelist();
}
