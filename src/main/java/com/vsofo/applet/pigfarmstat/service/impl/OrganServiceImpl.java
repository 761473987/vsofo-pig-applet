package com.vsofo.applet.pigfarmstat.service.impl;

import com.vsofo.applet.pigfarmstat.mapper.OrganMapper;
import com.vsofo.applet.pigfarmstat.pojo.Organ;
import com.vsofo.applet.pigfarmstat.service.OrganService;
import com.vsofo.applet.pigfarmstat.vo.farms.FarmsGroupVo;
import com.vsofo.applet.pigfarmstat.vo.farms.FarmsVo;
import com.vsofo.applet.pigfarmstat.vo.organ.OrganGroupVo;
import com.vsofo.applet.pigfarmstat.vo.organ.OrganVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: wangtao
 * @date: 2020/5/25
 */
@Service
@Transactional(isolation = Isolation.READ_UNCOMMITTED)
public class OrganServiceImpl implements OrganService {
    @Autowired
    private OrganMapper organMapper;
    @Deprecated
    @Override
    public List<Organ> findOrgan() {
        return organMapper.findWeChatOrgan();
    }

    /**
     * 获取大区
     * @return
     */
    @Override
    public List<FarmsGroupVo> findOrganVo() {
        FarmsVo farmsVo = new FarmsVo();
        farmsVo.setFarmId(null);
        farmsVo.setChineseSpell("区");
        farmsVo.setFarmId(-1);
        farmsVo.setFarmName("深圳市金新农科技股份有限公司");
        List<FarmsVo> organVo = organMapper.findWeChatOrganVo();
        organVo.add(0,farmsVo);
        List<FarmsGroupVo> farmsGroupVos = new ArrayList<>();
        farmsGroupVos.add(new FarmsGroupVo("区",organVo));
        return farmsGroupVos;
    }

    /**
     * 查询大区并且返回合作猪场
     * @return
     */
    @Override
    public List<Organ> findOrganAndCoop() {
        List<Organ> organ = findOrgan();
        return organ;
    }

    @Override
    public List<FarmsGroupVo> findOrganVoById(Integer id) {
        List<FarmsVo> organVoById = organMapper.findWeChatOrganVoByWxId(id);
        List<FarmsGroupVo> farmsGroupVos = new ArrayList<>();
        farmsGroupVos.add(new FarmsGroupVo("区",organVoById));
        return farmsGroupVos;
    }

    //    public <T> void transcod(List<T> list , Class<T> c ,String idName, String toCname){
//        try {
//            Field field = c.getField(idName);
//            Object o = field.get(c);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
