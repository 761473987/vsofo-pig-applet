package com.vsofo.applet.pigfarmstat.service.impl;

import com.vsofo.applet.pigfarmstat.dto.farm.FarmDto;
import com.vsofo.applet.pigfarmstat.mapper.FarmsMapper;
import com.vsofo.applet.pigfarmstat.pojo.User;
import com.vsofo.applet.pigfarmstat.service.FarmsService;
import com.vsofo.applet.pigfarmstat.vo.farms.FarmsGroupVo;
import com.vsofo.applet.pigfarmstat.vo.farms.FarmsVo;
import com.vsofo.applet.utils.ChinesePinyinUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * @author: wangtao
 * @date: 2020/5/29
 * 养殖场
 */
@Service
@Transactional(isolation = Isolation.READ_UNCOMMITTED)
public class FarmsServiceImpl implements FarmsService {
    @Autowired
    private FarmsMapper farmsMapper;



    @Override
    public List<FarmsGroupVo> findFarm(FarmDto farmDto, User user) {
        FarmsVo farmsVo = new FarmsVo();
        farmsVo.setFarmId(-1);
        farmsVo.setFarmName("当前所有养殖场");
        farmsVo.setChineseSpell("D");
        List<FarmsVo> farm = farmsMapper.findFarm(farmDto,user);
        List<FarmsGroupVo> farmsGroupVos = farmsGroupVos(farm);
        return farmsGroupVos;
    }

    /**
     * 选中养殖场
     * @param user
     * @param farmId
     */
    @Override
    public void selectedFarm(User user, Integer farmId) {
//        redisTemplate.opsForValue().set(user.getUsername() + RedisConstant.USER_SELECTED_FARM,farmId);
    }

    /**
     * 获取上一次选中的养殖场
     * @param user
     * @return
     */
    @Override
    public Integer getSelectedFarm(User user) {
//        Integer farmId = (Integer) redisTemplate.opsForValue().get(user.getUsername() + RedisConstant.USER_SELECTED_FARM);
//        if (farmId == null){
//            throw new GeneralException(FarmCodeMsg.NOT_FARM);
//        }
//        return farmId;
        return null;
    }

    public List<FarmsGroupVo> farmsGroupVos(List<FarmsVo> farm){
        TreeMap<String, List<FarmsVo>> map = new TreeMap<>();
        for (FarmsVo f: farm) {
            String pinYinHeadChar = ChinesePinyinUtil.getPinYinHeadChar(f.getFarmName().substring(0,1));
            pinYinHeadChar = pinYinHeadChar.toUpperCase();
            f.setChineseSpell(pinYinHeadChar);
            List<FarmsVo> farmsVos1 = map.get(pinYinHeadChar);
            if (CollectionUtils.isEmpty(farmsVos1)){
                farmsVos1 = new ArrayList<>();
            }
            farmsVos1.add(f);
            map.put(pinYinHeadChar,farmsVos1);

        }
        ArrayList<FarmsGroupVo> farmsGroupVos = new ArrayList<>();
        map.forEach((k,v)->{
            farmsGroupVos.add(new FarmsGroupVo(k,v));
        });
        return farmsGroupVos;
    }
}
