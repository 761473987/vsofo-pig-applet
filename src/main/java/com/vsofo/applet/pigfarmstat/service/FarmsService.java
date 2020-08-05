package com.vsofo.applet.pigfarmstat.service;

import com.vsofo.applet.pigfarmstat.dto.farm.FarmDto;
import com.vsofo.applet.pigfarmstat.pojo.User;
import com.vsofo.applet.pigfarmstat.vo.farms.FarmsGroupVo;

import java.util.List;

/**
 * @author: wangtao
 * @date: 2020/5/29
 */
public interface FarmsService {
    List<FarmsGroupVo> findFarm(FarmDto farmDto, User user );

    void selectedFarm(User user, Integer farmId);

    Integer getSelectedFarm(User user);
}
