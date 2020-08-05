package com.vsofo.applet.pigfarmstat.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

public interface JzzPigStatService {

    void jzzSysnStatDay(LocalDate startDate, LocalDate endDate);


    boolean lock(String lock_name);

    void unLock(String lock_name);
}
