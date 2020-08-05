package com.vsofo.applet.pigfarmstat.mapper;

import com.vsofo.applet.pigfarmstat.dto.monthly.MonthlySearchDto;
import com.vsofo.applet.pigfarmstat.pojo.User;
import com.vsofo.applet.pigfarmstat.vo.DoubleKV;
import com.vsofo.applet.pigfarmstat.vo.IntegerKV;
import com.vsofo.applet.pigfarmstat.vo.daily.AmountDailyDetailValueVo;
import com.vsofo.applet.pigfarmstat.vo.monthly.BreadVo;
import com.vsofo.applet.pigfarmstat.vo.monthly.ColumnarVo;
import com.vsofo.applet.pigfarmstat.vo.monthly.LineChartVo;
import com.vsofo.applet.pigfarmstat.vo.monthly.MoreLineChartVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

/**
 * @author: wangtao
 * @date: 2020/5/29
 */
public interface FarmMonthlyMapper {
    /**
     * 出栏折线图
     * @param monthlySearchDto
     * @param pigType
     * @param user
     * @return
     */
    List<LineChartVo> findLineChartVo(@Param("monthlySearchDto") MonthlySearchDto monthlySearchDto, @Param("pigType") String pigType,@Param("user") User user);

    /**
     * 出栏柱状图
     * @param monthlySearchDto
     * @param user
     * @return
     */
    List<ColumnarVo> findColumnarVo(@Param("monthlySearchDto") MonthlySearchDto monthlySearchDto,@Param("user") User user);

    /**
     * 出栏饼图
     * @param monthlySearchDto
     * @param user
     * @return
     */
    List<BreadVo> findBread(@Param("monthlySearchDto") MonthlySearchDto monthlySearchDto,@Param("user") User user);

    /**
     * 出栏多线折线图
     * @param monthlySearchDto
     * @param user
     * @return
     */
    List<MoreLineChartVo> findMoreLineChartVo(@Param("monthlySearchDto") MonthlySearchDto monthlySearchDto,@Param("user") User user);

    /**
     * 查询存栏折线图
     * @param monthlySearchDto
     * @param pigType
     * @param user
     * @return
     */
    @Deprecated
    List<LineChartVo> findAmountLineChartVo(@Param("monthlySearchDto") MonthlySearchDto monthlySearchDto,@Param("pigType")String pigType,@Param("user") User user);

    /**
     * 查询出栏总和
     * @param monthlySearchDto
     * @param user
     * @return
     */
    int findSuchSum(@Param("monthlySearchDto") MonthlySearchDto monthlySearchDto,@Param("user") User user);

    /**
     * 出栏详情页
     * @param monthlySearchDto
     * @param pigvarietyIds
     * @param pigType
     * @param user
     * @return
     */
    List<AmountDailyDetailValueVo> findSuchMonthDetailValueVo(MonthlySearchDto monthlySearchDto, int[] pigvarietyIds, String pigType, User user);

    /**
     * 查询最后三个养殖场的出栏
     * @param monthlySearchDto
     * @return
     */
    List<DoubleKV> orderByPigSuch(MonthlySearchDto monthlySearchDto);

    /**
     * 根据大区查询养殖场
     * @param monthlySearchDto
     * @return
     */
    List<IntegerKV> findFarmByOrganId(@Param("monthlySearchDto") MonthlySearchDto monthlySearchDto);

}
