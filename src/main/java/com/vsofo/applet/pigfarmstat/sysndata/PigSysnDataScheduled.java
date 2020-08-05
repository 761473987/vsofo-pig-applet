package com.vsofo.applet.pigfarmstat.sysndata;

import com.vsofo.applet.pigfarmstat.service.PigSysnDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//@Configuration
//@EnableScheduling
@Slf4j
public class PigSysnDataScheduled {
    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private PigSysnDataService pigSysnDataService;

    private final String LOCK_KEY = "sysn";

    @Scheduled(cron = "0 32 * * * ?")
    public void pigInventoryDailySysn(){
        long start = System.currentTimeMillis();
        log.warn("------------------------InventoryDaily同步开始-------------------------------");
        log.warn("-----------------------开始清空当天数据--------------------------");
        int emptyNum = pigSysnDataService.emptyTodayData();
        log.warn("----------------------清空数据完成，一共清空{}条--------------------",emptyNum);
        LocalDateTime now = LocalDateTime.now();
        String format = now.format(DATETIME_FORMATTER);
        log.warn("---------------------------开始同步数据---------------------------");
        int i = pigSysnDataService.pigInventoryDailySysn(format);
        log.warn("------------------------同步数据据，一共同步{}条-------------------",i);
        log.warn("-------------------------InventoryDaily同步结束,共消耗{}s-----------------------",start-System.currentTimeMillis());

        long start1 = System.currentTimeMillis();
        log.warn("------------------------InventoryMonth同步开始-------------------------------");
        log.warn("-----------------------开始清空当天数据--------------------------");
        LocalDateTime now1 = LocalDateTime.now();
        int emptyNum1 = pigSysnDataService.emptyToMonthData();
        log.warn("----------------------清空数据完成，一共清空{}条--------------------",emptyNum1);
        String format1 = now1.format(DATETIME_FORMATTER);
        log.warn("---------------------------开始同步数据---------------------------");
        int i1 = pigSysnDataService.pigInventoryMonthSysn(format1);
        log.warn("------------------------同步数据据，一共同步{}条-------------------",i1);
        log.warn("-------------------------InventoryMonth同步结束,共消耗{}s-----------------------",start1-System.currentTimeMillis());
    }

}
