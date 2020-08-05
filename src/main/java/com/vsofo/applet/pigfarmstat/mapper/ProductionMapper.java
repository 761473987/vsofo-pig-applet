package com.vsofo.applet.pigfarmstat.mapper;

import com.vsofo.applet.pigfarmstat.dto.indicators.IndicatorsDto;
import com.vsofo.applet.pigfarmstat.pojo.Production;
import com.vsofo.applet.pigfarmstat.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProductionMapper {
    List<Production> totalAmountWeek(@Param("indicatorsDto") IndicatorsDto indicatorsDto,@Param("user") User user);

    List<Production> avgAmountWeek(@Param("indicatorsDto") IndicatorsDto indicatorsDto,@Param("user") User user);

    /**
     * 淘汰母猪平均胎龄	状态为淘汰的母猪平均胎龄
     * @param indicatorsDto
     * @param user
     * @return
     */
    Double findTtmzpjtl(@Param("indicatorsDto") IndicatorsDto indicatorsDto,@Param("user") User user);

    /**
     * PSY(正品数指标)
     * 统计期内销售和自留培育仔猪数÷统计期向前115天母猪日均平均存栏×365.25÷统计期
     * @param indicatorsDto
     * @param user
     * @return
     */
    Double findLsyScmzzb(@Param("indicatorsDto") IndicatorsDto indicatorsDto,@Param("user") User user);

    /**
     * 平均妊娠天数
     * @param indicatorsDto
     * @param user
     * @return
     */
    Double findGestationDayNum(@Param("indicatorsDto") IndicatorsDto indicatorsDto,@Param("user") User user);

    /**
     * 平均哺乳天数
     * @param indicatorsDto
     * @param user
     * @return
     */
    Double findLactationDayNum(@Param("indicatorsDto") IndicatorsDto indicatorsDto,@Param("user") User user);

    /**
     *分娩窝重之和
     * @param indicatorsDto
     * @return
     */
    Double findFmwszlzh(@Param("indicatorsDto") IndicatorsDto indicatorsDto,@Param("user") User user);

    /**
     * 断奶窝重之和
     * @param indicatorsDto
     * @param user
     * @return
     */
    Double findDnwzzh(@Param("indicatorsDto") IndicatorsDto indicatorsDto,@Param("user") User user);

    /**
     *断奶-配种间隔天数
     * @param indicatorsDto
     * @param user
     * @return
     */
    Double findDnpzjgts(@Param("indicatorsDto") IndicatorsDto indicatorsDto,@Param("user") User user);

    /**
     * 分娩间隔
     * @param indicatorsDto
     * @param user
     * @return
     */
    Double findFmjg(@Param("indicatorsDto") IndicatorsDto indicatorsDto,@Param("user") User user);

    /**
     * 分娩活仔数
     * @param indicatorsDto
     * @param user
     * @return
     */
    Double findFmhzs(@Param("indicatorsDto") IndicatorsDto indicatorsDto,@Param("user") User user);

    /**
     * npd
     * @param indicatorsDto
     * @param user
     * @return
     */
    Double findNpd(@Param("indicatorsDto") IndicatorsDto indicatorsDto,@Param("user") User user);

    /**
     * 分娩母猪平均胎龄
     * @param indicatorsDto
     * @param user
     * @return
     */
    Double findFmmzPjtl(@Param("indicatorsDto") IndicatorsDto indicatorsDto,@Param("user") User user);

//    2020-07-16
    /**
     * 不包含后备猪NPD
     * @param indicatorsDto
     * @param user
     * @return
     */
    Double findNPDNoHbz(@Param("indicatorsDto") IndicatorsDto indicatorsDto,@Param("user") User user);

    /**
     * LSY(NPB)
     * @param indicatorsDto
     * @param user
     * @return
     */
    Double findLsyNpb(@Param("indicatorsDto") IndicatorsDto indicatorsDto,@Param("user") User user);

    Double findPigSale(@Param("indicatorsDto") IndicatorsDto indicatorsDto,@Param("user") User user);

    /**
     *
     *
     * @param indicatorsDto
     * @param user
     * @return pjcl:统计期向前115天母猪日均平均存栏 tjq:统计期
     */
    Map<String,Double> findTjq(@Param("indicatorsDto") IndicatorsDto indicatorsDto, @Param("user") User user);
}
