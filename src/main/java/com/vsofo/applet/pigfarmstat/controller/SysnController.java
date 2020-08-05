package com.vsofo.applet.pigfarmstat.controller;

import com.vsofo.applet.pigfarmstat.service.FmsJxnPigStatService;
import com.vsofo.applet.pigfarmstat.service.JzzPigStatService;
import com.vsofo.common.entity.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 数据同步
 */
@RestController
@RequestMapping("sysn")
@Api(hidden = true)
public class SysnController {
    @Autowired
    private FmsJxnPigStatService fmsJxnPigStatService;

    @Autowired
    private JzzPigStatService jzzPigStatService;

    private final String SYSN_PASSWORD = "123456";

    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    @GetMapping("jzz")
    public Result jzz(String startDate, String endDate, String sysnPassword){
        if (!SYSN_PASSWORD.equals(sysnPassword)){
            throw new RuntimeException("同步密码错误");
        }
        LocalDate localStartDate = LocalDate.parse(startDate);
        LocalDate localEndDate = LocalDate.parse(endDate);
        jzzPigStatService.jzzSysnStatDay(localStartDate,localEndDate);
        return new Result("同步成功");
    }

    @GetMapping("fms")
    public Result fms(String startDate, String endDate, String sysnPassword){
        if (!SYSN_PASSWORD.equals(sysnPassword)){
            throw new RuntimeException("同步密码错误");
        }
        LocalDate localStartDate = LocalDate.parse(startDate);
        LocalDate localEndDate = LocalDate.parse(endDate);
        fmsJxnPigStatService.sysnFmxPig( localStartDate,localEndDate);
        return new Result("同步成功");
    }
}
