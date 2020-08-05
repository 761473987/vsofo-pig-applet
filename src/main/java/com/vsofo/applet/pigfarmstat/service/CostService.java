package com.vsofo.applet.pigfarmstat.service;

import com.vsofo.applet.pigfarmstat.dto.monthly.MonthlySearchDto;
import com.vsofo.applet.pigfarmstat.pojo.User;
import com.vsofo.applet.pigfarmstat.vo.monthly.MChartVo;
import com.vsofo.applet.pigfarmstat.vo.monthly.SuchVo;

/**
 * @description: 成本统计
 * @author: liuzhiyun
 * @create: 2020-07-15 15:51
 **/
public interface CostService {
    /**
     * 成本主页展示数据
     * @param monthlySearchDto
     * @param user
     * @return
     */
    SuchVo findCoastByPeriod (MonthlySearchDto monthlySearchDto, User user);
    
    /**
     * 切换饼图
     * @param monthlySearchDto
     * @param user
     * @param viewType
     * @return
     */
    MChartVo changeViewType(MonthlySearchDto monthlySearchDto, User user, Integer viewType);
}
