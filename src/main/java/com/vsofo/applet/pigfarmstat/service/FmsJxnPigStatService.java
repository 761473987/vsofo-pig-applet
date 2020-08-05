package com.vsofo.applet.pigfarmstat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vsofo.applet.pigfarmstat.dto.pigSysn.FybDto;
import com.vsofo.applet.pigfarmstat.pojo.FmsJxnPigStat;

import java.time.LocalDate;
import java.util.List;

public interface FmsJxnPigStatService extends IService<FmsJxnPigStat> {

    List<FmsJxnPigStat> findPreFyb(String format);

    void sysnFmxPig(LocalDate startDate,LocalDate endDate);

    boolean lock(String lock_name);

    void unLock(String lock_name);
}
