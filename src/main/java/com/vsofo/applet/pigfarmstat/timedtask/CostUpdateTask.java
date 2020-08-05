package com.vsofo.applet.pigfarmstat.timedtask;

import com.vsofo.applet.pigfarmstat.pojo.PigframStatCost;
import com.vsofo.applet.pigfarmstat.service.UpdateCostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: 定时更新成本统计表
 * @author: liuzhiyun
 * @create: 2020-07-20 10:57
 **/
@Component
public class CostUpdateTask {
    private Logger LOGGER = LoggerFactory.getLogger(CostUpdateTask.class);
    
    @Autowired
    private UpdateCostService updateCostService;
    
//    @Scheduled(cron = "0 0/1 * ? * ?")
//    private void updateCostTable () {
//        updateCostService.update();
//        LOGGER.info("执行定时任务！");
//    }
}
