package com.vsofo.applet.pigfarmstat.service;

import com.vsofo.applet.pigfarmstat.dos.indicators.IndicatorsDo;
import com.vsofo.applet.pigfarmstat.dos.indicators.MonthValueDo;
import com.vsofo.applet.pigfarmstat.dos.indicators.NpdMonthDo;
import com.vsofo.applet.pigfarmstat.dto.indicators.IndicatorsDto;
import com.vsofo.applet.pigfarmstat.pojo.Production;
import com.vsofo.applet.pigfarmstat.pojo.User;
import com.vsofo.applet.pigfarmstat.vo.indicators.IIChartVo;
import com.vsofo.applet.pigfarmstat.vo.indicators.ProductionVo;

import java.util.List;

public interface ProductionMonthService {

    List<Production> totalAmount(IndicatorsDto indicatorsDto, User user);

    List<Production> avgAmount(IndicatorsDto indicatorsDto, User user);

    List<Production> totalAmountPolyline(IndicatorsDto indicatorsDto, User user);

    List<Production> avgAmountPolyline(IndicatorsDto indicatorsDto, User user);

    List<ProductionVo> monthInfoCount(IndicatorsDto indicatorsDto, User user);

    List<IIChartVo> monthPolylineInfoCount(IndicatorsDto indicatorsDto, User user);

    List<IndicatorsDo> totalAmountBetweenMonth(IndicatorsDto indicatorsDto, User user);

    List<IndicatorsDo> avgAmountBetweenMonth(IndicatorsDto indicatorsDto, User user);

    List<NpdMonthDo> findNpdBetweenMonth(IndicatorsDto indicatorsDto, User user);

    List<MonthValueDo> findgestationDayNum(IndicatorsDto indicatorsDto, User user);

    List<MonthValueDo> findLactationDayNum(IndicatorsDto indicatorsDto, User user);

    List<IndicatorsDo> avgAmountDay(IndicatorsDto indicatorsDto, User user);
}
