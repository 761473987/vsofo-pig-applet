package com.vsofo.applet.pigfarmstat.service.impl;

import com.vsofo.applet.pigfarmstat.mapper.UpdateCostMapper;
import com.vsofo.applet.pigfarmstat.pojo.PigframStatCost;
import com.vsofo.applet.pigfarmstat.service.UpdateCostService;
import com.vsofo.applet.pigfarmstat.timedtask.CostUpdateTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: liuzhiyun
 * @create: 2020-07-20 11:16
 **/
@Service
public class UpdateCostServiceImpl implements UpdateCostService {
    private Logger LOGGER = LoggerFactory.getLogger(CostUpdateTask.class);
    
    @Autowired
    private UpdateCostMapper updateCostMapper;
    
    @Override
    public void update() {
        List<PigframStatCost> costs = updateCostMapper.getCostData();
        for (PigframStatCost cost : costs) {
            updateCostMapper.insert(cost);
        }
        System.out.println(costs);
        LOGGER.info("=======更新完成！====");
    }
}
