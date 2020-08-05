package com.vsofo.applet.pigfarmstat.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.vsofo.applet.pigfarmstat.pojo.WeChatSysnLog;
import com.vsofo.applet.pigfarmstat.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 同步金智猪小程序2.0的实现
 */
@Service
@Slf4j
public class JzzPigStatServiceImpl implements JzzPigStatService , InitializingBean {

    @Autowired
    private JzzHogInvoicingStatStatDayService jzzHogInvoicingStatStatDayService;


    @Autowired
    private JzzPigInvoicingStatStatDayService jzzPigInvoicingStatStatDayService;

    @Autowired
    private WeChatSysnLogService weChatSysnLogService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    final String LOCK_NAME = "JZZ_INVENTORYDAILY_LOCK";

    /**
     * 统计金智猪存栏
     */
    public void jzzSysnStatDay(LocalDate startDate,LocalDate endDate){
        String startDateFormat = startDate.format(DATETIME_FORMATTER);
        String endDateFormat = endDate.format(DATETIME_FORMATTER);
        long days = endDate.toEpochDay() - startDate.toEpochDay();
        log.warn("尝试获取锁");
        if (this.lock(LOCK_NAME)){
            WeChatSysnLog weChatSysnLog =  this.saveLog(startDateFormat,endDateFormat);
            log.warn("获取锁成功");
            try {
                for (int i = 0; i <= days ; i++){
                    LocalDate localDateTime1 = startDate.plusDays(i);
                    HashMap<String, Object> columnMap = new HashMap<>();
                    String statDateFormat = localDateTime1.format(DATETIME_FORMATTER);
                    columnMap.put("statDate",statDateFormat);
                    //统计金智猪种猪存栏
                    jzzPigInvoicingStatStatDayService.jzzSysPigStatDay(columnMap);
                    //统计金智猪商品猪存栏
                    jzzHogInvoicingStatStatDayService.jzzSysHogStatDay(columnMap);
                    //将金智猪的存栏和商品猪的存栏同步到Wechat_PigInventory_Daily
                    jdbcTemplate.execute("exec Pig_JZZ_Wechat_PigInventory_Daily '"+ statDateFormat+"'");
                    log.warn("同步"+statDateFormat+"完成");
                }
                weChatSysnLog.setDesc("同步金智猪存栏日期为【"+startDateFormat +"至" +endDateFormat +"】完成,");
                weChatSysnLog.setIfSucceed("1");
                this.weChatSysnLogService.updateById(weChatSysnLog);
            }catch (Exception e){
                weChatSysnLog.setDesc("【同步错误】:"+e.getMessage());
                this.weChatSysnLogService.updateById(weChatSysnLog);
                e.getMessage();
                throw e;
            }finally {
                this.unLock(LOCK_NAME);//释放锁
                log.warn("释放锁成功");
            }
        }

    }


    /**
     * 加锁
     * @param lock_name
     * @return
     */
    @Override
    public boolean lock(String lock_name) {
        String sql = "INSERT INTO [dbo].[WeChat_BusLock]([LockName], [Desc]) VALUES (?, ?);";
        try {
            int rows = jdbcTemplate.update(sql,LOCK_NAME,"金智猪同步锁");
            return rows == 1;
        }catch (Exception e){
            log.warn(e.getMessage());
            return false;
        }
    }

    /**
     * 释放锁
     * @param lock_name
     */
    @Override
    public void unLock(String lock_name) {
        jdbcTemplate.update("DELETE FROM [dbo].[WeChat_BusLock] WHERE LockName = ? ",lock_name);
    }

    /**
     * 保存日志
     * @return
     * @param startDateFormat
     * @param endDateFormat
     */
    private WeChatSysnLog saveLog(String startDateFormat, String endDateFormat) {
        WeChatSysnLog weChatSysnLog = new WeChatSysnLog();
        weChatSysnLog.setCreateTime(new Date());
        weChatSysnLog.setSysnCondition(startDateFormat+"#:#"+endDateFormat);
        weChatSysnLog.setIfSucceed("0");//设置未完成状态
        weChatSysnLog.setSysnIdentify(LOCK_NAME);
        this.weChatSysnLogService.save(weChatSysnLog);
        return weChatSysnLog;
    }

    /**
     * 启动查看日志是否有未成功的信息
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        Thread thread = new Thread(() -> {
            List<WeChatSysnLog> list = this.weChatSysnLogService.lambdaQuery().eq(WeChatSysnLog::getIfSucceed, "0").list();
            for (WeChatSysnLog wc: list) {
                String sysnCondition = wc.getSysnCondition();
                String[] split = sysnCondition.split("#:#");
                String q1 = split[0];//获取第一个条件
                String q2 = split[1];//获取第二个条件
                this.jzzSysnStatDay(LocalDate.parse(q1),LocalDate.parse(q2));
                wc.setIfSucceed("1");//设置成完成标记
                this.weChatSysnLogService.updateById(wc);
            }
            this.unLock(LOCK_NAME);//此时释放锁，非正常关闭的情况下锁可能会没有释放
        },"JZZ_THREAD");
        thread.start();
    }
}
