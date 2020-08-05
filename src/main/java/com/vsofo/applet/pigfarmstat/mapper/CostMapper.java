package com.vsofo.applet.pigfarmstat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vsofo.applet.pigfarmstat.dto.monthly.MonthlySearchDto;
import com.vsofo.applet.pigfarmstat.pojo.PigframStatCost;
import com.vsofo.applet.pigfarmstat.pojo.User;
import com.vsofo.applet.pigfarmstat.vo.cost.LineChartVo;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @program: vsofo-pig-parent
 * @description: 成本统计
 * @author: liuzhiyun
 * @create: 2020-07-15 15:56
 **/
public interface CostMapper extends BaseMapper<PigframStatCost> {
    /**
     * 根据年月、养殖场、大区查找成本按项目汇总
     * @param monthlySearchDto
     * @param yearAndMonth
     * @return
     */
    Map<String, BigDecimal> findCoastByPeriod(@Param("monthlySearchDto") MonthlySearchDto monthlySearchDto,
                                              @Param("yearAndMonth") String yearAndMonth,
                                              @Param("user") User user,
                                              @Param("viewType") Integer viewType);
    
    /**
     * 按月查找成本，按折线图汇总
     * @param yearAndMonth
     * @return
     */
    List<LineChartVo> findByMonth (@Param("monthlySearchDto") MonthlySearchDto monthlySearchDto,
                                   @Param("yearAndMonth") String yearAndMonth,
                                   @Param("user") User user);
}
