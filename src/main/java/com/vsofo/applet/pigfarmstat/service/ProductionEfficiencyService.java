package com.vsofo.applet.pigfarmstat.service;

import com.vsofo.applet.pigfarmstat.dto.indicators.IndicatorsDto;
import com.vsofo.applet.pigfarmstat.pojo.DataType;
import com.vsofo.applet.pigfarmstat.pojo.User;
import com.vsofo.applet.pigfarmstat.vo.indicators.IndDetailVo;

import java.util.List;

public interface ProductionEfficiencyService {
    List<IndDetailVo> getProduction(IndicatorsDto indicatorsDto, String dateType, String s, User user);

    List<DataType> getDataType();
}
