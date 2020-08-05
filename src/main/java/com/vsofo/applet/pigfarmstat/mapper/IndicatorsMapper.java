package com.vsofo.applet.pigfarmstat.mapper;

import com.vsofo.applet.pigfarmstat.dos.indicators.IndicatorsDo;
import com.vsofo.applet.pigfarmstat.dto.indicators.IndicatorsDto;
import com.vsofo.applet.pigfarmstat.pojo.Production;
import com.vsofo.applet.pigfarmstat.pojo.User;
import com.vsofo.applet.pigfarmstat.vo.indicators.ILineChartVo;
import com.vsofo.applet.pigfarmstat.vo.indicators.IndDetailVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author: wangtao
 * @date: 2020/6/2
 */
public interface IndicatorsMapper {
    List<IndicatorsDo> findIndicatorsDo(@Param("indicatorsDto") IndicatorsDto indicatorsDto,@Param("user") User user);

    /**
     * 显示指标infp
     * @return
     */
    List<Production> getMenuByOrganIdByTime(@Param("indicatorsDto") IndicatorsDto indicatorsDto,@Param("user") User user);

    Map<String,Object> findDataType(@Param("dataTypeName") String dataTypeName);

    /**
     * 指标详情请
     * @param indicatorsDto
     * @param groupId
     * @return
     */
    List<IndDetailVo> findIndicatorsDetail(@Param("indicatorsDto") IndicatorsDto indicatorsDto,@Param("groupId") String groupId,@Param("user") User user);

    /**
     * 指标详情，按周查询
     * @param indicatorsDto
     * @param groupId
     * @return
     */
    List<IndDetailVo> findIndicatorsDetailOfWeek(@Param("indicatorsDto") IndicatorsDto indicatorsDto,@Param("groupId") String groupId,@Param("user") User user);
}
