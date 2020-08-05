package com.vsofo.applet.pigfarmstat.service.impl;

import com.vsofo.applet.exception.GeneralException;
import com.vsofo.applet.pigfarmstat.dos.indicators.IndicatorsDo;
import com.vsofo.applet.pigfarmstat.dos.indicators.MonthValueDo;
import com.vsofo.applet.pigfarmstat.dos.indicators.NpdMonthDo;
import com.vsofo.applet.pigfarmstat.dto.indicators.IndicatorsDto;
import com.vsofo.applet.pigfarmstat.enums.DataTypeEnum;
import com.vsofo.applet.pigfarmstat.mapper.ProductionMonthMapper;
import com.vsofo.applet.pigfarmstat.pojo.Production;
import com.vsofo.applet.pigfarmstat.pojo.User;
import com.vsofo.applet.pigfarmstat.service.ProductionMonthService;
import com.vsofo.applet.pigfarmstat.vo.indicators.IIChartVo;
import com.vsofo.applet.pigfarmstat.vo.indicators.ProductionVo;
import com.vsofo.applet.utils.NumberUtils;
import com.vsofo.common.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(isolation = Isolation.READ_UNCOMMITTED)
public class ProductionMonthServiceImpl implements ProductionMonthService {


    @Autowired
    ProductionMonthMapper productionMonthMapper;

    @Override
    public List<Production> totalAmount(IndicatorsDto indicatorsDto, User user) {
        return productionMonthMapper.totalAmount(indicatorsDto,user);
    }

    @Override
    public List<Production> avgAmount(IndicatorsDto indicatorsDto, User user) {
        return productionMonthMapper.avgAmount(indicatorsDto,user);
    }

    @Override
    public List<Production> totalAmountPolyline(IndicatorsDto indicatorsDto, User user) {
        return productionMonthMapper.totalAmountPolyline(indicatorsDto,user);
    }

    @Override
    public List<Production> avgAmountPolyline(IndicatorsDto indicatorsDto, User user) {
        return productionMonthMapper.avgAmountPolyline(indicatorsDto,user);
    }

    @Override
    public List<ProductionVo> monthInfoCount(IndicatorsDto indicatorsDto, User user) {
        double npd = 0;
        double avgPregnancyDay = 0; //平均妊娠天数 21
        double avgBreastDay = 0;  //平均哺乳天数  52
        double totalChildbirth = 0;   //配种对应分娩头数  89
        double totalDueDate = 0;  //对应预产期记录数   13
        double totalWeaning = 0;  //断奶仔猪数  44
        double totalWeaningLive = 0;    //断奶对应的出生活仔数  45
        double totalWeaningSow = 0;     //断奶母猪头数  42
        double totaldeathChildcare = 0;  //保育仔猪死亡数 77
        double avgNurseryPiglets = 0;  //保育仔猪平均存栏 67
        double totaldeathFatteningPig = 0;  //育肥猪死亡数 78
        double avgFatteningPig = 0; //育肥猪平均存栏 68

        if (indicatorsDto == null) {
            throw new GeneralException(new Result(100003, "error"));
        }
        List<Production> totalAmount = totalAmount(indicatorsDto,user);
        List<Production> avgAmount = avgAmount(indicatorsDto,user);
        for (Production totalAmounts : totalAmount) {
            if (totalAmounts.getDataType().equals("89")) {
                totalChildbirth = totalAmounts.getReality();
            }
            if (totalAmounts.getDataType().equals("13")) {
                totalDueDate = totalAmounts.getReality();
            }
            if (totalAmounts.getDataType().equals("44")) {
                totalWeaning = totalAmounts.getReality();
            }
            if (totalAmounts.getDataType().equals("45")) {
                totalWeaningLive = totalAmounts.getReality();
            }
            if (totalAmounts.getDataType().equals("42")) {
                totalWeaningSow = totalAmounts.getReality();
            }
            if (totalAmounts.getDataType().equals("77")) {
                totaldeathChildcare = totalAmounts.getReality();
            }
            if (totalAmounts.getDataType().equals("78")) {
                totaldeathFatteningPig = totalAmounts.getReality();
            }
        }
        for (Production avgAmounts : avgAmount) {
            if (avgAmounts.getDataType().equals("65")) {
                npd = avgAmounts.getReality();
            }
            if (avgAmounts.getDataType().equals("21")) {
                avgPregnancyDay = avgAmounts.getReality();
            }
            if (avgAmounts.getDataType().equals("52")) {
                avgBreastDay = avgAmounts.getReality();
            }
            if (avgAmounts.getDataType().equals("67")) {
                avgNurseryPiglets = avgAmounts.getReality();
            }
            if (avgAmounts.getDataType().equals("68")) {
                avgFatteningPig = avgAmounts.getReality();
            }
        }
        double lsy = (365 - npd) / (avgPregnancyDay + avgBreastDay);
        double deliveryRate = totalChildbirth / totalDueDate * 100;
        double survivalRate = totalWeaning / totalWeaningLive;
        double psy = totalWeaningSow * totalWeaning * lsy;
        double msy = psy * (1 - (totaldeathChildcare / avgNurseryPiglets)) * (1 - (totaldeathFatteningPig / avgFatteningPig));
        List<ProductionVo> list=new ArrayList<>();
        list.add(new ProductionVo("224",
                DataTypeEnum.BREEDING.getMenuName(),
                NumberUtils.formatDouble(deliveryRate,2)+"%"
                ,"/indicators/detail")
        );
        list.add(new ProductionVo("221",
                DataTypeEnum.LSY_ALL.getMenuName(),
                NumberUtils.formatDouble(lsy,2)+""
                ,"/indicators/detail")
        );
        list.add(new ProductionVo("225",
                DataTypeEnum.PIGLETS.getMenuName(),
                NumberUtils.formatDouble(survivalRate*100,2)+"%"
                ,"/indicators/detail")
        );
        list.add(new ProductionVo("222",
                DataTypeEnum.PSY_ALL.getMenuName(),
                NumberUtils.formatDouble(psy,2)+""
                ,"/indicators/detail")
        );
        list.add(new ProductionVo("226",
                DataTypeEnum.NPD.getMenuName(),
                NumberUtils.formatDouble(npd,2)+""
                ,"/indicators/detail")
        );
        list.add(new ProductionVo("223",
                DataTypeEnum.MSY.getMenuName(),
                NumberUtils.formatDouble(msy,2)+""
                ,"/indicators/detail")
        );
        return list;
    }

    @Override
    public List<IIChartVo> monthPolylineInfoCount(IndicatorsDto indicatorsDto,User user) {

        return null;
    }

    @Override
    public List<IndicatorsDo> totalAmountBetweenMonth(IndicatorsDto indicatorsDto,User user) {
        return productionMonthMapper.totalAmountBetweenMonth(indicatorsDto,user);
    }

    @Override
    public List<IndicatorsDo> avgAmountBetweenMonth(IndicatorsDto indicatorsDto,User user) {
        return productionMonthMapper.avgAmountBetweenMonth(indicatorsDto,user);
    }

    @Override
    public List<NpdMonthDo> findNpdBetweenMonth(IndicatorsDto indicatorsDto,User user) {
        return productionMonthMapper.findNpdBetweenMonth(indicatorsDto,user);
    }

    @Override
    public List<MonthValueDo> findgestationDayNum(IndicatorsDto indicatorsDto,User user) {
        return productionMonthMapper.findGestationDayNum(indicatorsDto,user);
    }

    @Override
    public List<MonthValueDo> findLactationDayNum(IndicatorsDto indicatorsDto,User user) {
        return productionMonthMapper.findLactationDayNum(indicatorsDto,user);
    }

    @Override
    public List<IndicatorsDo> avgAmountDay(IndicatorsDto indicatorsDto,User user) {
        return productionMonthMapper.avgAmountDay(indicatorsDto,user);
    }
}
