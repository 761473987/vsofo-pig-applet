package com.vsofo.applet.pigfarmstat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vsofo.applet.pigfarmstat.dos.daily.CountDaily;
import com.vsofo.applet.pigfarmstat.dos.daily.HistogramDaily;
import com.vsofo.applet.pigfarmstat.dto.daily.DailySearchDto;
import com.vsofo.applet.pigfarmstat.pojo.User;
import com.vsofo.applet.pigfarmstat.vo.daily.AmountDailyDetailValueVo;
import com.vsofo.applet.pigfarmstat.vo.daily.PigVarietyVo;
import com.vsofo.applet.pojo.ExcelModel;
import com.vsofo.applet.pojo.FarmDaily;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: wangtao
 * @date: 2020/5/28
 */
public interface FarmDailyMapper extends BaseMapper<FarmDaily> {
    /**
     * 查询公猪商品猪母猪数量
     * @param dailySearchDto
     * @param user
     * @return
     */
    CountDaily findCountDaily(@Param("dailySearchDto") DailySearchDto dailySearchDto,@Param("user") User user);

    List<HistogramDaily> findHistogram(@Param("dailySearchDto") DailySearchDto dailySearchDto,@Param("user") User user);

    List<AmountDailyDetailValueVo> findAmountDailyDetailValueVo(
            @Param("dailySearchDto") DailySearchDto dailySearchDto
            ,@Param("pigvarietyIds") int[] pigvarietyIds
            ,@Param("pigType") String pigType
            ,@Param("user") User user
    );

    List<PigVarietyVo> findPigVariety();

    List<String> findProductionStatus(@Param("pigeTypeId") String pigeTypeId);

    List<ExcelModel> findExeclModel(@Param("date") String date);
}
