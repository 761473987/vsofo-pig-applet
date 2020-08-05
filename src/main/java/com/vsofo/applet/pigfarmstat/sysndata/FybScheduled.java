package com.vsofo.applet.pigfarmstat.sysndata;

import com.vsofo.applet.pigfarmstat.service.FmsJxnPigStatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

@Configuration
@EnableScheduling
@Slf4j
public class FybScheduled {

    @Autowired
    private FmsJxnPigStatService fmsJxnPigStatService;


    /**
     * 每天统计凌晨统计当月的数据
     * 避免开发时开启多个服务同时进行同步
     * 加锁保证同步一次
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void pigInventoryDailySysn(){
        log.warn("同步放养系统开始");
        LocalDate date = LocalDate.now();
        LocalDate first = date.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate last = date.with(TemporalAdjusters.lastDayOfMonth());
        fmsJxnPigStatService.sysnFmxPig(first,last);
        log.warn("同步放养系统结束");
    }

    /**
     * 每天隔20分钟执行一次
     */
    @Scheduled(cron = "0 41 * * * ?")
    public void pigInventoryHoursSysn(){
        log.warn("同步放养系统开始");
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(3);//前三天
        fmsJxnPigStatService.sysnFmxPig(startDate,endDate);
        log.warn("同步放养系统结束");
    }

}
