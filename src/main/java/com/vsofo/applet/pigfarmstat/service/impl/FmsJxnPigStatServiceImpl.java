package com.vsofo.applet.pigfarmstat.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vsofo.applet.pigfarmstat.dto.pigSysn.FybDto;
import com.vsofo.applet.pigfarmstat.mapper.FmsJxnPigStatMapper;
import com.vsofo.applet.pigfarmstat.pojo.FmsJxnPigStat;
import com.vsofo.applet.pigfarmstat.pojo.WeChatFmsFarm;
import com.vsofo.applet.pigfarmstat.pojo.WeChatSysnLog;
import com.vsofo.applet.pigfarmstat.service.FmsJxnPigStatService;
import com.vsofo.applet.pigfarmstat.service.PigSysnDataService;
import com.vsofo.applet.pigfarmstat.service.WeChatFmsFarmService;
import com.vsofo.applet.pigfarmstat.service.WeChatSysnLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 从放养系统的存栏数据同步到本项目sqlserver
 */
@Service
@Slf4j
public class FmsJxnPigStatServiceImpl extends ServiceImpl<FmsJxnPigStatMapper,FmsJxnPigStat> implements FmsJxnPigStatService, InitializingBean {
    @Autowired
    private FmsJxnPigStatMapper fmsJxnPigStatMapper;

    @Autowired
    private PigSysnDataService pigSysnDataService;


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private WeChatFmsFarmService weChatFmsFarmService;

    @Autowired
    private WeChatSysnLogService weChatSysnLogService;

    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    final String LOCK_NAME = "FMS_INVENTORYDAILY_LOCK";

    @Override
    public List<FmsJxnPigStat> findPreFyb(String format) {
        List<FmsJxnPigStat> fmsJxnPigStats = fmsJxnPigStatMapper.selectList(format);
        return fmsJxnPigStats;
    }

    /**
     * 将放养系统的数据通步到该系统中来
     * @param startDate 开始时间
     * @param endDate 结束时间
     */
    @Override
    public void sysnFmxPig(LocalDate startDate,LocalDate endDate) {
        String startDateFormat = startDate.format(DATETIME_FORMATTER);
        String endDateFormat = endDate.format(DATETIME_FORMATTER);
        if (this.lock(LOCK_NAME)){//获取锁
            WeChatSysnLog weChatSysnLog =  this.saveLog(startDateFormat,endDateFormat);
            try {
                int count = 0;
                List<WeChatFmsFarm> fmsFarms = weChatFmsFarmService.list();
                jdbcTemplate.update("DELETE FROM [dbo].[FMS_JXN_PIG_STAT] WHERE Date BETWEEN '"+startDateFormat+"' and '"+endDateFormat+"'");
                long days = endDate.toEpochDay() - startDate.toEpochDay();
                for (int i = 0; i <= days; i++) {
                    for (WeChatFmsFarm wf:fmsFarms) {
                        Long departmentID = wf.getFmsDepartmentId();
                        LocalDate localDateTime1 = startDate.plusDays(i);
                        List<FybDto> fybXs = pigSysnDataService.findFybXs(localDateTime1.format(DATETIME_FORMATTER),departmentID);//当天数据
                        LocalDate localDateTime = localDateTime1.minusDays(1);//前一天
                        List<FmsJxnPigStat> preFyb = this.findPreFyb(localDateTime.format(DATETIME_FORMATTER));
                        List<FmsJxnPigStat> dbfms = new ArrayList<>();//需要插入到数据库的
                        while (!CollectionUtils.isEmpty(fybXs)) {
                            FmsJxnPigStat bqcl = getBqcl(fybXs.get(0), fybXs);
                            bqcl.setFarmId(40L);
                            dbfms.add(bqcl);
                        }
                        List<FmsJxnPigStat> list = getMasked(preFyb, dbfms);//求交集，并且修改期初数据
                        for (FmsJxnPigStat l: list) {
                            this.save(l);
                            log.warn("保存一条数据成功");
                            count++;
                        }
                        jdbcTemplate.execute("exec task_fms_jxn_pig'"+localDateTime1.format(DATETIME_FORMATTER)+"'");
                    }
                }
                weChatSysnLog.setDesc("同步放养系统，日期为【"+startDateFormat +"至" + endDateFormat +"】完成，一共同步" + count + "条数据");
                weChatSysnLog.setIfSucceed("1");
                this.weChatSysnLogService.updateById(weChatSysnLog);
            }catch (Exception e){
                weChatSysnLog.setDesc("【同步错误】:"+e.getMessage());
                this.weChatSysnLogService.updateById(weChatSysnLog);
                log.warn("同步出现错误");
                throw e;
            }finally {
                this.unLock(LOCK_NAME);//释放锁
            }
        }
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
     *
     * @param preFyb
     * @param dbfms
     * @return
     */
    private List<FmsJxnPigStat> getMasked(List<FmsJxnPigStat> preFyb, List<FmsJxnPigStat> dbfms) {
        List<FmsJxnPigStat> list = new ArrayList<>();
        if (CollectionUtils.isEmpty(preFyb)) {
            return dbfms;
        }
        if (CollectionUtils.isEmpty(dbfms) && !CollectionUtils.isEmpty(preFyb)) {
            for (FmsJxnPigStat bqcl : preFyb) {
                setFmsJxnPigStat(bqcl);
                list.add(bqcl);
            }
        } else {
            //设置期初值
            list.addAll(dbfms);
            List<FmsJxnPigStat> tempList = new ArrayList<>();
            for (FmsJxnPigStat pre : preFyb) {//循环上一天的
                for (FmsJxnPigStat dbf : list) {//循环当天的
                    if (pre.getDepartment().equals(dbf.getDepartment()) && pre.getPigType().equals(dbf.getPigType())) {
                        dbf.setPigCountEnd(pre.getPigCountEnd() + dbf.getPigCountEnd());
                        tempList.add(pre);
                    }
                }
            }
            preFyb.removeAll(tempList);
            for (FmsJxnPigStat f: preFyb) {
                setFmsJxnPigStat(f);
                list.add(f);
            }
        }
        return list;
    }

    private void setFmsJxnPigStat(FmsJxnPigStat bqcl) {
        Calendar c = Calendar.getInstance();
        c.setTime(bqcl.getDate());
        c.add(Calendar.DAY_OF_MONTH, 1);
        bqcl.setDate(c.getTime());
        bqcl.setPigSale(0L);
        bqcl.setPigDeath(0L);
        bqcl.setPigBuy(0L);
    }

    /**
     * 根据放养系统的数据添加到此项目数据库中，
     * @param fybto
     * @param fybXs
     * @return
     */
    private FmsJxnPigStat getBqcl(FybDto fybto, List<FybDto> fybXs) {
        ArrayList<FybDto> fybDtos = new ArrayList<>();
        FmsJxnPigStat fmsJxnPigStat = new FmsJxnPigStat();
        for (FybDto f : fybXs) {
            if (
                    f.getDate().equals(fybto.getDate())
                            && f.getDepartmentId().equals(fybto.getDepartmentId())
                            && f.getPigType().equals(fybto.getPigType())) {
                fybDtos.add(f);
            }
        }
        Long value = 0L;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        fmsJxnPigStat.setDepartment(fybto.getDepartmentName());
        fmsJxnPigStat.setPigType(fybto.getPigType());
        try {
            fmsJxnPigStat.setDate(simpleDateFormat.parse(fybto.getDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (FybDto f : fybDtos) {
            //销售
            if (f.getDataType().equals("1")) {
                value = value - f.getValue();
                fmsJxnPigStat.setPigSale(f.getValue());
            }
            //购入
            if (f.getDataType().equals("2")) {
                value = value + f.getValue();
                fmsJxnPigStat.setPigBuy(f.getValue());

            }
            //死亡
            if (f.getDataType().equals("3")) {
                value = value - f.getValue();
                fmsJxnPigStat.setPigDeath(f.getValue());
            }
            if (f.getDataType().equals("4")) {

            }
            if (f.getDataType().equals("5")) {

            }
        }
        fybXs.removeAll(fybDtos);
        fmsJxnPigStat.setPigCountEnd(value);
        return fmsJxnPigStat;
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
            int rows = jdbcTemplate.update(sql,LOCK_NAME,"放养系统同步锁");
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
                this.sysnFmxPig(LocalDate.parse(q1),LocalDate.parse(q2));
                wc.setIfSucceed("1");//设置成完成标记
                this.weChatSysnLogService.updateById(wc);
            }
            this.unLock(LOCK_NAME);//此时释放锁，非正常关闭的情况下锁可能会没有释放
        },"FY_THREAD");
        thread.start();
    }
}
