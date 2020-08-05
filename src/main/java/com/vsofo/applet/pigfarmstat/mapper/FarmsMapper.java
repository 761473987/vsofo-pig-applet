package com.vsofo.applet.pigfarmstat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vsofo.applet.pigfarmstat.dto.farm.FarmDto;
import com.vsofo.applet.pigfarmstat.dto.indicators.IndicatorsDto;
import com.vsofo.applet.pigfarmstat.pojo.User;
import com.vsofo.applet.pigfarmstat.vo.farms.FarmsVo;
import com.vsofo.applet.pojo.Farms;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author: wangtao
 * @date: 2020/5/28
 */
public interface FarmsMapper extends BaseMapper<Farms> {
    /**
     * 根据组织id查询养殖场id
     * @param organId
     * @return
     */
    List<Integer> findFarmsByOrganId(@Param("organId")Integer organId);

    /**
     * 查询养殖场
     * @return
     * @param farmDto
     * @param user
     */
    List<FarmsVo> findFarm(@Param("farmDto") FarmDto farmDto, @Param("user") User user);

    /**
     * 查询养殖场的个数
     * @return
     * @param indicatorsDto
     */
    int findFarmsCount(@Param("indicatorsDto") IndicatorsDto indicatorsDto, @Param("user") User user);
}
