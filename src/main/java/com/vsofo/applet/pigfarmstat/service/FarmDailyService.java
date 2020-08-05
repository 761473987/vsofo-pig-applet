package com.vsofo.applet.pigfarmstat.service;

import com.vsofo.applet.pigfarmstat.dos.daily.CountDaily;
import com.vsofo.applet.pigfarmstat.dto.daily.DailySearchDto;
import com.vsofo.applet.pigfarmstat.pojo.User;
import com.vsofo.applet.pigfarmstat.vo.daily.AmountDailyDetailValueVo;
import com.vsofo.applet.pigfarmstat.vo.daily.AmountVO;
import com.vsofo.applet.pigfarmstat.vo.daily.PigVarietyVo;

import java.util.List;

/**
 * @author: wangtao
 * @date: 2020/5/28
 */
public interface FarmDailyService {
    /**
     * 当前存栏
     * @param dailySearchDto
     * @param user
     * @return
     */
    AmountVO amount(DailySearchDto dailySearchDto, User user);

    /**
     * 查询公猪商品猪母猪数量
     * @param dailySearchDto
     * @param user
     * @return
     */
    CountDaily findCountDaily(DailySearchDto dailySearchDto, User user);


    /**
     * 存栏详情值
     * @param id
     * @param dailySearchDto
     * @param pigvarietyId
     * @param user
     * @return
     */
    List<AmountDailyDetailValueVo> findAmountDailyDetailValueVo(DailySearchDto dailySearchDto, int[] pigvarietyId, String pigType, User user);

    /**
     * 查询品种
     * @return
     */
    List<PigVarietyVo> findPigVariety();


    /**
     * 查询生产状态名称
     * @param pigeTypeId
     * @return
     */
    List<String> findProductionStatus( String pigeTypeId);
}
