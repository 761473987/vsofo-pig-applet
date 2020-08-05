package com.vsofo.applet.pigfarmstat.service;

import com.vsofo.applet.pigfarmstat.dto.monthly.MonthlySearchDto;
import com.vsofo.applet.pigfarmstat.pojo.User;
import com.vsofo.applet.pigfarmstat.vo.DoubleKV;
import com.vsofo.applet.pigfarmstat.vo.IntegerKV;
import com.vsofo.applet.pigfarmstat.vo.daily.AmountDailyDetailValueVo;
import com.vsofo.applet.pigfarmstat.vo.monthly.LineChartVo;
import com.vsofo.applet.pigfarmstat.vo.monthly.MChartVo;

import java.util.List;

/**
 * @author: wangtao
 * @date: 2020/5/29
 */
public interface FarmMonthlyService {
    /***
     * 出栏折线图
     * @param id
     * @param monthlySearchDto
     * @param user
     * @return
     */
    MChartVo findLineChartVo(MonthlySearchDto monthlySearchDto, String pigType, User user);

    /**
     * 累计出栏折线图
     * @return
     */
    MChartVo findCumulativeLineChartVo(MonthlySearchDto monthlySearchDto, List<LineChartVo> lineChartVos, User user);

    /**
     * 出栏柱状图
     * @param monthlySearchDto
     * @param user
     * @return
     */
    MChartVo findColumnarVo(MonthlySearchDto monthlySearchDto, User user);

    /**
     * 出栏饼图
     * @param monthlySearchDto
     * @param user
     * @return
     */
    MChartVo findBread(MonthlySearchDto monthlySearchDto, User user);

    /**
     * 多折线图
     * @param monthlySearchDto
     * @param user
     * @return
     */
    MChartVo findMoreLineChartVo(MonthlySearchDto monthlySearchDto, User user);


    MChartVo findaAmountLineChartVo(MonthlySearchDto monthlySearchDto, String pigType, User user);

    int findSuchSum(MonthlySearchDto monthlySearchDto, User user);

    List<AmountDailyDetailValueVo> findSuchMonthDetailValueVo(MonthlySearchDto monthlySearchDto, int[] pigvarietyIds, String pigType, User user);

    List<DoubleKV> orderByPigSuch(MonthlySearchDto monthlySearchDto);

    List<IntegerKV> findFarmByOrganId(MonthlySearchDto monthlySearchDto);
}
