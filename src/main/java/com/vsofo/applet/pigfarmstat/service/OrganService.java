package com.vsofo.applet.pigfarmstat.service;

import com.vsofo.applet.pigfarmstat.pojo.Organ;
import com.vsofo.applet.pigfarmstat.vo.farms.FarmsGroupVo;
import com.vsofo.applet.pigfarmstat.vo.farms.FarmsVo;
import com.vsofo.applet.pigfarmstat.vo.organ.OrganGroupVo;
import com.vsofo.applet.pigfarmstat.vo.organ.OrganVo;

import java.util.List;
import java.util.Map;

/**
 * @author: wangtao
 * @date: 2020/5/25
 */
public interface OrganService {
    List<Organ> findOrgan();

    List<FarmsGroupVo> findOrganVo();

    public List<Organ> findOrganAndCoop();

    List<FarmsGroupVo> findOrganVoById(Integer id);
}
