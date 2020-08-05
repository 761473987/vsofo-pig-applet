package com.vsofo.applet.pigfarmstat.mapper;

import com.vsofo.applet.pigfarmstat.pojo.Organ;
import com.vsofo.applet.pigfarmstat.vo.farms.FarmsVo;
import com.vsofo.applet.pigfarmstat.vo.organ.OrganVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author: wangtao
 * @date: 2020/5/25
 */
public interface OrganMapper {
    /**
     * 查询各大区
     * @return
     */
    @Deprecated
    List<Organ> findOrgan();

    @Deprecated
    List<FarmsVo> findOrganVo();

    @Deprecated
    List<FarmsVo> findOrganVoById(@Param("id") Integer id);

    List<FarmsVo> findWeChatOrganVoByWxId(@Param("wxOrganId") Integer wxOrganId);

    List<FarmsVo> findWeChatOrganVo();

    List<Organ> findWeChatOrgan();


}
