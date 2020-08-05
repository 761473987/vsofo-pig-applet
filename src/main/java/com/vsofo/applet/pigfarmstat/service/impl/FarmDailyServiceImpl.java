package com.vsofo.applet.pigfarmstat.service.impl;

import com.google.common.util.concurrent.AtomicDouble;
import com.vsofo.applet.pigfarmstat.dos.daily.CountDaily;
import com.vsofo.applet.pigfarmstat.dos.daily.HistogramDaily;
import com.vsofo.applet.pigfarmstat.dto.daily.DailySearchDto;
import com.vsofo.applet.pigfarmstat.enums.ChartEnum;
import com.vsofo.applet.pigfarmstat.enums.PigTypeEnums;
import com.vsofo.applet.pigfarmstat.enums.TitleEnum;
import com.vsofo.applet.pigfarmstat.enums.UnitEnum;
import com.vsofo.applet.pigfarmstat.mapper.FarmDailyMapper;
import com.vsofo.applet.pigfarmstat.mapper.FarmsMapper;
import com.vsofo.applet.pigfarmstat.pojo.User;
import com.vsofo.applet.pigfarmstat.service.FarmDailyService;
import com.vsofo.applet.pigfarmstat.vo.daily.*;
import com.vsofo.applet.utils.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: wangtao
 * @date: 2020/5/28
 */
@Service
@Transactional(isolation = Isolation.READ_UNCOMMITTED)
public class FarmDailyServiceImpl implements FarmDailyService {
    @Autowired
    private FarmDailyMapper farmDailyMapper;
    @Autowired
    private FarmsMapper farmsMapper;

    /**
     * 当前存栏首页-admin和集团
     * @param dailySearchDto
     * @param user
     * @return
     */
    @Override
    public AmountVO amount(DailySearchDto dailySearchDto, User user) {
        CountDaily countDaily = this.findCountDaily(dailySearchDto,user);
        List<ChilVo> chilVos = getChilVos(dailySearchDto, countDaily,user);
        List<ChartVo> chartVos = new ArrayList<>();
        if (user != null && user.getRoleId() == 1 && dailySearchDto.getOrganId() == null && dailySearchDto.getFarmId() == null){
            chartVos = getChartVos(dailySearchDto,user);
        }else {
            chartVos = getPigTypeChart(dailySearchDto,countDaily);
        }
        AmountVO amountVO = new AmountVO(
                TitleEnum.CURRENT_AMOUNT.getName(),
                UnitEnum.HEAD.getName(),
                countDaily.getBoarNum() + countDaily.getHogNum() + countDaily.getSowNum(),
                chilVos,chartVos);
        return amountVO;
    }

    /**
     * 猪的种类饼图
     * @param dailySearchDto
     * @param countDaily
     * @return
     */
    private List<ChartVo> getPigTypeChart(DailySearchDto dailySearchDto, CountDaily countDaily) {
        List<GardenVo> gardens = new ArrayList<>();
        int boarNum = countDaily.getBoarNum();
        int hogNum = countDaily.getHogNum();
        int sowNum = countDaily.getSowNum();
        double sum = boarNum + hogNum + sowNum;
        gardens.add(new GardenVo(-1,"公猪",boarNum,NumberUtils.formatDouble(boarNum/sum,4),"const1"));
        gardens.add(new GardenVo(-1,"母猪",sowNum,NumberUtils.formatDouble(sowNum/sum,4),"const1"));
        gardens.add(new GardenVo(-1,"商品猪",hogNum,NumberUtils.formatDouble(hogNum/sum,4),"const1"));
        ChartVo chartVo = new ChartVo("猪只类型占比", ChartEnum.BREAD_MAP.getCode(), gardens);
        return Arrays.asList(chartVo);
    }

    /**
     * 设置当前首页表的值
     * @param dailySearchDto
     * @param user
     * @return
     */
    private List<ChartVo> getChartVos(DailySearchDto dailySearchDto, User user) {
        List<HistogramDaily> histogram = farmDailyMapper.findHistogram(dailySearchDto,user);
        List<PigHistogramVo> boars = new ArrayList<>();
        List<PigHistogramVo> hogs = new ArrayList<>();
        List<PigHistogramVo> sows = new ArrayList<>();
        for (HistogramDaily h:histogram) {
            PigHistogramVo boar = new PigHistogramVo(h.getOrganName(), h.getBoar());
            boars.add(boar);
            PigHistogramVo hog = new PigHistogramVo(h.getOrganName(), h.getHog());
            hogs.add(hog);
            PigHistogramVo sow = new PigHistogramVo(h.getOrganName(), h.getSow());
            sows.add(sow);
        }
        List<GardenVo> gardens = new ArrayList<>();
        double sum = 0;
        for (HistogramDaily h:histogram) {//存值
            int sumNum = h.getBoar()+h.getHog()+h.getSow();
            sum = sum + sumNum;
            GardenVo gardenVo = new GardenVo(h.getOrganId(),h.getOrganName(),sumNum,0,"const");
            gardens.add(gardenVo);
        }
        for (GardenVo h:gardens) {//计算ratio
            double ratio = h.getValue() / sum ;
            double s = 0;
            if (!Double.isNaN(ratio)){
                s= NumberUtils.formatDouble(ratio,4);
            }
            h.setRatio(s);
        }
        return Arrays.asList(
                new ChartVo(TitleEnum.BOAR_HISTOGRAM.getName(), ChartEnum.COLUMNAR_MAP.getCode(),boars),
                new ChartVo(TitleEnum.SOW_HISTOGRAM.getName(),ChartEnum.COLUMNAR_MAP.getCode(),sows),
                new ChartVo(TitleEnum.HOG_HISTOGRAM.getName(),ChartEnum.COLUMNAR_MAP.getCode(),hogs),
//                new ChartVo(TitleEnum.GARDEN.getName(),ChartEnum.BREAD_MAP.getCode(),gardens)
                columnaToBread(new ChartVo(TitleEnum.BOAR_HISTOGRAM.getName()+"占比", ChartEnum.BREAD_MAP.getCode(),boars)),
                columnaToBread(new ChartVo(TitleEnum.SOW_HISTOGRAM.getName()+"占比",ChartEnum.BREAD_MAP.getCode(),sows)),
                columnaToBread(new ChartVo(TitleEnum.HOG_HISTOGRAM.getName()+"占比",ChartEnum.BREAD_MAP.getCode(),hogs))
                );

    }

    /**
     * 柱状图转饼图
     * @param chartVo
     * @return
     */
    private ChartVo columnaToBread(ChartVo chartVo) {
        List<PigHistogramVo> data = (List<PigHistogramVo>) chartVo.getData();
        AtomicDouble sum = new AtomicDouble(0);
        List<GardenVo> gardens = new ArrayList<>();
        data.forEach(p ->{
            int value = p.getValue();
            sum.set(sum.get() + value);
            gardens.add(new GardenVo(null,p.getArea(),p.getValue(),0,"const"));

        });
        for (GardenVo h:gardens) {//计算ratio
            double ratio = h.getValue() / sum.get() ;
            double s = 0;
            if (!Double.isNaN(ratio)){
                s= NumberUtils.formatDouble(ratio,4);
            }
            h.setRatio(s);
        }
        chartVo.setData(gardens);
        return chartVo;
    }

    /**
     * 设置当前存栏母猪，公猪，商品猪的存栏数量
     * @param dailySearchDto
     * @param countDaily
     * @param user
     * @return
     */
    private List<ChilVo> getChilVos(DailySearchDto dailySearchDto, CountDaily countDaily, User user) {
        List<ChilVo> chilVos = new ArrayList<>();
        chilVos.add(
                new ChilVo(
                        PigTypeEnums.BOAR.getId(),
                        PigTypeEnums.BOAR.getDec(),
                         countDaily.getBoarNum(),
                        PigTypeEnums.BOAR.getUrl()+dailySearchDto.getStateDate()
                ));
        chilVos.add(
                new ChilVo(
                        PigTypeEnums.HOG.getId()
                        ,PigTypeEnums.HOG.getDec()
                        ,countDaily.getHogNum()
                        ,PigTypeEnums.HOG.getUrl()+dailySearchDto.getStateDate()
                ));
        chilVos.add(
                new ChilVo(
                        PigTypeEnums.SOW.getId()
                        ,PigTypeEnums.SOW.getDec()
                        ,countDaily.getSowNum(),
                        PigTypeEnums.SOW.getUrl()+dailySearchDto.getStateDate()
                ));
        return chilVos;
    }

    /**
     * 查询当前存栏母猪公猪商品猪的存栏数量
     * @param dailySearchDto
     * @param user
     * @return
     */
    @Override
    public CountDaily findCountDaily(DailySearchDto dailySearchDto, User user) {
        return farmDailyMapper.findCountDaily(dailySearchDto,user);
    }

    /**
     * 存栏详情页值
     * @param dailySearchDto
     * @param pigvarietyIds
     * @param user
     * @return
     */
    @Override
    public List<AmountDailyDetailValueVo> findAmountDailyDetailValueVo(DailySearchDto dailySearchDto, int[] pigvarietyIds, String pigType, User user) {
        return farmDailyMapper.findAmountDailyDetailValueVo(dailySearchDto,pigvarietyIds,pigType,user);
    }

    @Override
    public List<PigVarietyVo> findPigVariety() {
        return farmDailyMapper.findPigVariety();
    }

    @Override
    public List<String> findProductionStatus(String pigeTypeId) {
        return farmDailyMapper.findProductionStatus(pigeTypeId);
    }
}
