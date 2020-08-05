package com.vsofo.applet.pigfarmstat.service;

import com.vsofo.applet.pigfarmstat.dto.indicators.IndicatorsDto;
import com.vsofo.applet.pigfarmstat.pojo.Production;
import com.vsofo.applet.pigfarmstat.pojo.User;
import com.vsofo.applet.pigfarmstat.vo.DoubleKV;
import com.vsofo.applet.pigfarmstat.vo.indicators.IIChartVo;
import com.vsofo.applet.pigfarmstat.vo.indicators.IndDetailVo;

import java.util.List;

/**
 * @author: wangtao
 * @date: 2020/6/2
 */
public interface IndicatorsService {

    List<IIChartVo> findIndicatorsDo(IndicatorsDto indicatorsDto, User user);

    List<Production> getMenuByOrganIdByTime(IndicatorsDto indicatorsDto, User user);

    List<IndDetailVo> findIndicatorsDetail(IndicatorsDto indicatorsDto, String groupId, User user);

    List<IndDetailVo> findIndicatorsDetailOfWeek(IndicatorsDto indicatorsDto, String groupId, User user);

    List<IIChartVo> findILineCharVoV2(IndicatorsDto indicatorsDto, User user);

    List<DoubleKV> orderByIndicators(IndicatorsDto indicatorsDto, String dataType, User user);
}
