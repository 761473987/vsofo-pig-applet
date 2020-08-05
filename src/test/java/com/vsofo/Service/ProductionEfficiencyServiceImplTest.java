package com.vsofo.Service;

import com.vsofo.VsofoPigAppletApp;
import com.vsofo.applet.pigfarmstat.dos.indicators.IndicatorsDo;
import com.vsofo.applet.pigfarmstat.dos.indicators.NpdMonthDo;
import com.vsofo.applet.pigfarmstat.dto.indicators.IndicatorsDto;
import com.vsofo.applet.pigfarmstat.dto.pigSysn.FybDto;
import com.vsofo.applet.pigfarmstat.pojo.FmsJxnPigStat;
import com.vsofo.applet.pigfarmstat.pojo.Production;
import com.vsofo.applet.pigfarmstat.pojo.User;
import com.vsofo.applet.pigfarmstat.service.*;
import com.vsofo.applet.pigfarmstat.vo.indicators.IIChartVo;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@SpringBootTest(classes = VsofoPigAppletApp.class)
@RunWith(SpringRunner.class)
public class ProductionEfficiencyServiceImplTest {

    @Autowired
    private ProductionEfficiencyService productionEfficiencyService;
    @Autowired
    private ProductionMonthService productionMonthService;

    @Autowired
    private PigSysnDataService pigSysnDataService;

    @Autowired
    private FmsJxnPigStatService fmsJxnPigStatService;

    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    private IndicatorsService indicatorsService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void getProduction() {
        IndicatorsDto indicatorsDto = new IndicatorsDto();
//        indicatorsDto.setStatMonth(6);
//        indicatorsDto.setEndMonth(6);
        indicatorsDto.setStatDate("2020-07");
        setIndicatorsDto(indicatorsDto);
        String dateType = "2";
        String groupId = "4";
//        indicatorsDto.setFarmId(29);
        productionEfficiencyService.getProduction(indicatorsDto, dateType, groupId, new User());
    }

    private void setIndicatorsDto(IndicatorsDto indicatorsDto) {

    }

    @Test
    public void test() {
        IndicatorsDto indicatorsDto = new IndicatorsDto();
        indicatorsDto.setStatDate("2020-06");
        User user = new User();
        List<Production> productions = productionMonthService.avgAmountPolyline(indicatorsDto, user);

    }

    @Test
    public void test2() {
        IndicatorsDto indicatorsDto = new IndicatorsDto();
        indicatorsDto.setStatDate("2020-06");
        indicatorsDto.setStatMonth(1);
        indicatorsDto.setEndMonth(8);
        List<IIChartVo> chart = indicatorsService.findILineCharVoV2(indicatorsDto, new User());
    }

    private double pd(List<IndicatorsDo> sndicatorsDos, String dataType, int month) {
        for (IndicatorsDo p : sndicatorsDos) {
            if (dataType.equals(p.getDataType()) && month == p.getMonth()) {
                return p.getReality();
            }
        }
        return 0;
    }

    private double npd(List<NpdMonthDo> sndicatorsDos, int month) {
        for (NpdMonthDo p : sndicatorsDos) {
            if (p.getMonth() == month) {
                return p.getValue();
            }
        }
        return 0;
    }


    @Test
    public void test4() {
        jdbcTemplate.execute("truncate table [dbo].[FMS_JXN_PIG_STAT]");
        List<FybDto> preFybDto = null;
        LocalDate now = LocalDate.of(2020, 07, 01);
        LocalDate now1 = LocalDate.now();
        long days = now1.toEpochDay() - now.toEpochDay();
        for (int i = 0; i <= days; i++) {
            LocalDate localDateTime1 = now.plusDays(i);
            List<FybDto> fybXs = pigSysnDataService.findFybXs(localDateTime1.format(DATETIME_FORMATTER),1022L);//当天数据
            LocalDate localDateTime = localDateTime1.minusDays(1);//前一天
            List<FmsJxnPigStat> preFyb = fmsJxnPigStatService.findPreFyb(localDateTime.format(DATETIME_FORMATTER));
            List<FmsJxnPigStat> dbfms = new ArrayList<>();//需要插入到数据库的
            while (!CollectionUtils.isEmpty(fybXs)) {
                FmsJxnPigStat bqcl = getBqcl(fybXs.get(0), fybXs);
                bqcl.setFarmId(40L);
                dbfms.add(bqcl);
            }
            List<FmsJxnPigStat> list = getMasked(preFyb, dbfms);//求交集，并且修改期初数据
            for (FmsJxnPigStat l: list) {
                fmsJxnPigStatService.save(l);
            }
        }
        jdbcTemplate.execute("exec task_fms_jxn_pig ");
    }

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

    @Test
    public void test5(){
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(30);//前三天
        fmsJxnPigStatService.sysnFmxPig(startDate,endDate);
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


    @Test
    public void test3() {
        List<FybDto> preFybDto = null;
        LocalDateTime now = LocalDateTime.of(2020, 07, 01, 0, 0);
        for (int i = 0; i <= 23; i++) {
            LocalDateTime localDateTime1 = now.plusDays(i);
            long start = System.currentTimeMillis();
            List<FybDto> fybXs = pigSysnDataService.findFybXs(localDateTime1.format(DATETIME_FORMATTER),1022L);//销售
            ArrayList<FmsJxnPigStat> fmsJxnPigStats = new ArrayList<>();
            LocalDateTime localDateTime = localDateTime1.minusDays(1);
            List<FmsJxnPigStat> preFyb = fmsJxnPigStatService.findPreFyb(localDateTime.format(DATETIME_FORMATTER));
            if (!CollectionUtils.isEmpty(fybXs)) {
                while (!CollectionUtils.isEmpty(fybXs)) {
                    FmsJxnPigStat bqcl = getBqcl(fybXs.get(0), fybXs);
                    bqcl.setFarmId(40L);
                    fmsJxnPigStats.add(bqcl);
                    if (CollectionUtils.isEmpty(preFyb)) {
                        this.fmsJxnPigStatService.save(bqcl);
                    }
                }
                for (; !CollectionUtils.isEmpty(preFyb); ) {
                    FmsJxnPigStat bqcl = preFyb.get(0);
                    for (FmsJxnPigStat f : fmsJxnPigStats) {
                        if (f.getDepartment().equals(bqcl.getDepartment()) && f.getPigType().equals(bqcl.getPigType())) {
                            f.setPigCountEnd(f.getPigCountEnd() + bqcl.getPigCountEnd());
                            this.fmsJxnPigStatService.save(f);
                            preFyb.remove(bqcl);
                        }
                    }
                }
                for (FmsJxnPigStat bqcl : preFyb) {
                    setFmsJxnPigStat(bqcl);
                    this.fmsJxnPigStatService.save(bqcl);
                }
            } else {
                for (FmsJxnPigStat f : preFyb) {
                    setFmsJxnPigStat(f);
                    fmsJxnPigStatService.save(f);
                }
            }
        }

    }

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
}