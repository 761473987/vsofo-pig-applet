package com.vsofo.applet.pigfarmstat.sysndata;

import com.vsofo.applet.pigfarmstat.service.FmsJxnPigStatService;
import com.vsofo.applet.pigfarmstat.service.JzzPigStatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

@Configuration
@EnableScheduling
@Slf4j
public class JzzScheduled {

    @Autowired
    private JzzPigStatService jzzPigStatService;

    @Scheduled(cron = "0 20 * * * ?")
    public void jzzPigSysn() {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(3);
        jzzPigStatService.jzzSysnStatDay(startDate,endDate);
    }
}
