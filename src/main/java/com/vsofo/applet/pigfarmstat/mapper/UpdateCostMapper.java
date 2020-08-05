package com.vsofo.applet.pigfarmstat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vsofo.applet.pigfarmstat.pojo.PigframStatCost;

import java.util.List;

/**
 * @program: vsofo-pig-applet
 * @description: 定时更新成本统计表
 * @author: liuzhiyun
 * @create: 2020-07-20 11:17
 **/
public interface UpdateCostMapper extends BaseMapper<PigframStatCost> {
    List<PigframStatCost> getCostData();
}
