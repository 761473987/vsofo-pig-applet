package com.vsofo.applet.pigfarmstat.mapper;

import com.vsofo.applet.pigfarmstat.dos.indicators.IndicatorsDo;
import com.vsofo.applet.pigfarmstat.dos.indicators.MonthValueDo;
import com.vsofo.applet.pigfarmstat.dos.indicators.NpdMonthDo;
import com.vsofo.applet.pigfarmstat.dto.indicators.IndicatorsDto;
import com.vsofo.applet.pigfarmstat.pojo.DataType;
import com.vsofo.applet.pigfarmstat.pojo.Production;
import com.vsofo.applet.pigfarmstat.pojo.User;
import com.vsofo.applet.pigfarmstat.vo.DoubleKV;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductionMonthMapper {
    List<Production> totalAmount(@Param("indicatorsDto") IndicatorsDto indicatorsDto,@Param("user") User user);

    List<Production> avgAmount(@Param("indicatorsDto") IndicatorsDto indicatorsDto,@Param("user") User user);

    List<IndicatorsDo> avgAmountDay(@Param("indicatorsDto") IndicatorsDto indicatorsDto,@Param("user") User user);

    List<IndicatorsDo> totalAmountBetweenMonth(@Param("indicatorsDto") IndicatorsDto indicatorsDto,@Param("user") User user);

    List<IndicatorsDo> avgAmountBetweenMonth(@Param("indicatorsDto") IndicatorsDto indicatorsDto,@Param("user") User user);

    List<Production> totalAmountPolyline(@Param("indicatorsDto") IndicatorsDto indicatorsDto,@Param("user") User user);

    List<Production> avgAmountPolyline(@Param("indicatorsDto") IndicatorsDto indicatorsDto,@Param("user") User user);

    List<NpdMonthDo> findNpdBetweenMonth(@Param("indicatorsDto") IndicatorsDto indicatorsDto,@Param("user") User user);

    List<MonthValueDo> findGestationDayNum(@Param("indicatorsDto") IndicatorsDto indicatorsDto,@Param("user") User user);

    List<MonthValueDo> findLactationDayNum(@Param("indicatorsDto") IndicatorsDto indicatorsDto,@Param("user") User user);

    List<DataType> getDataType();

    List<DoubleKV> orderByIndicators(@Param("indicatorsDto") IndicatorsDto indicatorsDto, @Param("user") User user, @Param("dataType")String dataType, @Param("orderType")String orderType);
}
