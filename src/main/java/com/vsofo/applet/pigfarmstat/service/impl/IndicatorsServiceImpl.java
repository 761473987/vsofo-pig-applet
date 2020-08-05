package com.vsofo.applet.pigfarmstat.service.impl;

import com.vsofo.applet.exception.GeneralException;
import com.vsofo.applet.exception.PigCodeMsg;
import com.vsofo.applet.pigfarmstat.data.ProductionEfficiencyMula;
import com.vsofo.applet.pigfarmstat.dos.indicators.IndicatorsDo;
import com.vsofo.applet.pigfarmstat.dos.indicators.MonthValueDo;
import com.vsofo.applet.pigfarmstat.dos.indicators.NpdMonthDo;
import com.vsofo.applet.pigfarmstat.dto.indicators.IndicatorsDto;
import com.vsofo.applet.pigfarmstat.enums.ChartEnum;
import com.vsofo.applet.pigfarmstat.enums.DataTypeEnum;
import com.vsofo.applet.pigfarmstat.mapper.FarmsMapper;
import com.vsofo.applet.pigfarmstat.mapper.IndicatorsMapper;
import com.vsofo.applet.pigfarmstat.mapper.ProductionMonthMapper;
import com.vsofo.applet.pigfarmstat.pojo.Production;
import com.vsofo.applet.pigfarmstat.pojo.User;
import com.vsofo.applet.pigfarmstat.service.IndicatorsService;
import com.vsofo.applet.pigfarmstat.service.ProductionEfficiencyService;
import com.vsofo.applet.pigfarmstat.service.ProductionMonthService;
import com.vsofo.applet.pigfarmstat.vo.DoubleKV;
import com.vsofo.applet.pigfarmstat.vo.indicators.IIChartVo;
import com.vsofo.applet.pigfarmstat.vo.indicators.ILineChartVo;
import com.vsofo.applet.pigfarmstat.vo.indicators.IndDetailVo;
import com.vsofo.applet.utils.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author: wangtao
 * @date: 2020/6/2
 */
@Service
@Transactional(isolation = Isolation.READ_UNCOMMITTED)
public class IndicatorsServiceImpl implements IndicatorsService {

    @Autowired
    private IndicatorsMapper indicatorsMapper;

    @Autowired
    private ProductionEfficiencyService productionEfficiencyService;
    @Autowired
    private ProductionMonthService productionMonthService;

    @Autowired
    private ProductionMonthMapper productionMonthMapper;
    @Autowired
    private FarmsMapper farmsMapper;




    /**
     * 指标折线图
     * 可以优化代码
     * @param indicatorsDto
     * @param user
     * @return
     */
    @Override
    public List<IIChartVo> findIndicatorsDo(IndicatorsDto indicatorsDto, User user) {
        String chartType = ChartEnum.LINE_MAP.getCode();
        List<IIChartVo> iiChartVos = new ArrayList<>();
        List<IndicatorsDo> indicatorsDo = indicatorsMapper.findIndicatorsDo(indicatorsDto,user);
        List<ILineChartVo> lsy = getILineChartVo(indicatorsDto,indicatorsDo, DataTypeEnum.LSY_ALL.getCode());
        iiChartVos.add(new IIChartVo(DataTypeEnum.LSY_ALL.getMenuName(),chartType,lsy));

        List<ILineChartVo> psy = getILineChartVo(indicatorsDto,indicatorsDo, DataTypeEnum.PSY_ALL.getCode());
        iiChartVos.add(new IIChartVo(DataTypeEnum.PSY_ALL.getMenuName(), chartType,psy));

        List<ILineChartVo> breeding = getILineChartVo(indicatorsDto,indicatorsDo, DataTypeEnum.BREEDING.getCode());
        iiChartVos.add(new IIChartVo(DataTypeEnum.BREEDING.getMenuName(), chartType,breeding));

        List<ILineChartVo> msy = getILineChartVo(indicatorsDto,indicatorsDo, DataTypeEnum.MSY.getCode());
        iiChartVos.add(new IIChartVo(DataTypeEnum.MSY.getMenuName(), chartType,msy));

        List<ILineChartVo> npd = getILineChartVo(indicatorsDto,indicatorsDo, DataTypeEnum.NPD.getCode());
        iiChartVos.add(new IIChartVo(DataTypeEnum.NPD.getMenuName(), chartType,npd));

        List<ILineChartVo> piglets = getILineChartVo(indicatorsDto,indicatorsDo, DataTypeEnum.PIGLETS.getCode());
        iiChartVos.add(new IIChartVo(DataTypeEnum.PIGLETS.getMenuName(), chartType,piglets));

        return iiChartVos;
    }

    /**
     * 指标详情
     * @param indicatorsDto
     * @param groupId
     * @param user
     * @return
     */
    @Override
    public List<IndDetailVo> findIndicatorsDetail(IndicatorsDto indicatorsDto, String groupId, User user) {
        return indicatorsMapper.findIndicatorsDetail(indicatorsDto,groupId,user);
    }

    @Override
    public List<IndDetailVo> findIndicatorsDetailOfWeek(IndicatorsDto indicatorsDto, String groupId, User user) {
        return indicatorsMapper.findIndicatorsDetailOfWeek(indicatorsDto,groupId,user);
    }

    @Override
    public List<IIChartVo> findILineCharVoV2(IndicatorsDto indicatorsDto, User user) {
        ExecutorService pool = Executors.newFixedThreadPool(4);
        Future<List<IndicatorsDo>> totalAmountBetweenMonthFuture = pool.submit(() -> productionMonthService.totalAmountBetweenMonth(indicatorsDto,user));
        Future<List<IndicatorsDo>> avgAmountBetweenMonthFuture = pool.submit(() -> productionMonthService.avgAmountBetweenMonth(indicatorsDto,user));
        Future<List<IndicatorsDo>> avgAmountDayFuture = pool.submit(() -> productionMonthService.avgAmountDay(indicatorsDto,user));
        Future<List<NpdMonthDo>> npdMonthDosFuture = pool.submit(() -> productionMonthService.findNpdBetweenMonth(indicatorsDto,user));
        Future<Integer> farmsCountFuture = pool.submit(() -> farmsMapper.findFarmsCount(indicatorsDto,user));
        List<IndicatorsDo> totalAmountBetweenMonth = null;
        List<IndicatorsDo> avgAmountBetweenMonth = null;
        List<IndicatorsDo> avgAmountDay = null;
        List<NpdMonthDo> npdMonthDos = null;
        int farmsCount = 0;
        try {
            totalAmountBetweenMonth = totalAmountBetweenMonthFuture.get();
            avgAmountBetweenMonth = avgAmountBetweenMonthFuture.get();
            avgAmountDay = avgAmountDayFuture.get();
            npdMonthDos =npdMonthDosFuture.get();
            farmsCount = farmsCountFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        pool.shutdown();
        //lsy折线图
        List<ILineChartVo> lsy = getLsyChart(avgAmountBetweenMonth, npdMonthDos,indicatorsDto,farmsCount,user);
        //npd折线图
        List<ILineChartVo> npd = getNpdChart(npdMonthDos, farmsCount, indicatorsDto);
        //配种分娩率
        List<ILineChartVo> pzfml =  getPzfml(totalAmountBetweenMonth,indicatorsDto);
        //仔猪成活率
        List<ILineChartVo> zhchl = getZhchl(totalAmountBetweenMonth,indicatorsDto);
        //PSY(全群指标)
        List<ILineChartVo> psy = getPsy(totalAmountBetweenMonth,lsy,indicatorsDto);
        //MSY	PSY × 保育猪成活率 × 育肥猪成活率
        List<ILineChartVo> msy = getMsy(psy,avgAmountDay,totalAmountBetweenMonth,indicatorsDto,farmsCount);
        List<IIChartVo> iiChartVos = new ArrayList<>();
        String chartType = ChartEnum.LINE_MAP.getCode();
        iiChartVos.add(new IIChartVo(DataTypeEnum.BREEDING.getMenuName(),chartType,pzfml));
        iiChartVos.add(new IIChartVo(DataTypeEnum.PIGLETS.getMenuName(),chartType,zhchl));
        iiChartVos.add(new IIChartVo(DataTypeEnum.PSY_ALL.getMenuName(),chartType,psy));
        iiChartVos.add(new IIChartVo(DataTypeEnum.LSY_ALL.getMenuName(),chartType,lsy));
        iiChartVos.add(new IIChartVo(DataTypeEnum.MSY.getMenuName(),chartType,msy));
        iiChartVos.add(new IIChartVo(DataTypeEnum.NPD.getMenuName(),chartType,npd));
        return iiChartVos;
    }

    private List<ILineChartVo> getMsy(List<ILineChartVo> psy,
                                      List<IndicatorsDo> avgAmountBetweenMonth,
                                      List<IndicatorsDo> totalAmountBetweenMonth,
                                      IndicatorsDto indicatorsDto, int farmsCount) {
        List<IndicatorsDo> indicatorsDos = new ArrayList<>();
        for (int i = 1; i < indicatorsDto.getEndMonth(); i++) {
            //保育猪成活率 = 保育仔猪死亡数 ÷ 保育仔猪平均存栏；育肥猪成活率=育肥猪死亡数 ÷ 育肥猪平均存栏
            double v1 = pd(totalAmountBetweenMonth, "77", i);
            double v2 = pd(totalAmountBetweenMonth, "67", i);
            double v3 = pd(totalAmountBetweenMonth, "78", i);
            double v4 = pd(totalAmountBetweenMonth, "68", i);
//            if (indicatorsDto.getFarmId() == null){
//                v1 = v1 / farmsCount;
//                v3 = v3 / farmsCount;
//            }

            //保育猪成活率
            double divide = 1 -  ProductionEfficiencyMula.divide(v1, v2,4);
            // 育肥猪成活率
            double divide2 = 1 -  ProductionEfficiencyMula.divide(v3, v4,8);
            for (ILineChartVo i1:psy) {
                String month = i1.getMonth();
                String newMonth = month.replace("月","");
                if (Integer.valueOf(newMonth) == i){
                    double value = i1.getValue() * divide * divide2;
                    indicatorsDos.add(new IndicatorsDo(i,value,DataTypeEnum.MSY.getCode()));
                }
            }
        }
        return getILineChartVo(indicatorsDto,indicatorsDos,DataTypeEnum.MSY.getCode());
    }

    /**
     * PSY(全群指标)	=每头母猪断奶数×LSY
     * 每头母猪断奶数=断奶仔猪数÷断奶母猪头数
     * @param totalAmountBetweenMonth
     * @param lsyChart
     * @param indicatorsDto
     * @return
     */
    private List<ILineChartVo> getPsy(List<IndicatorsDo> totalAmountBetweenMonth, List<ILineChartVo> lsyChart, IndicatorsDto indicatorsDto) {
        //每头母猪断奶数
        List<IndicatorsDo> indicatorsDos = new ArrayList<>();
        for (int i = 1; i < indicatorsDto.getEndMonth(); i++) {
            //断奶仔猪数
            double v1 = pd(totalAmountBetweenMonth, "44", i);
            //断奶母猪头数
            double v2 = pd(totalAmountBetweenMonth, "42", i);
            //每头母猪断奶数
            double divide = ProductionEfficiencyMula.divide(v1, v2,4);
            for (ILineChartVo i1:lsyChart) {
                String month = i1.getMonth();
                String newMonth = month.replace("月","");
                if (Integer.valueOf(newMonth) == i){
                    //PSY(全群指标) = 每头母猪断奶数×LSY
                    double value = i1.getValue() * divide;
                    indicatorsDos.add(new IndicatorsDo(i,value,DataTypeEnum.PSY_ALL.getCode()));
                }
            }
        }
        return getILineChartVo(indicatorsDto,indicatorsDos, DataTypeEnum.PSY_ALL.getCode());
    }

    private List<ILineChartVo> getZhchl(List<IndicatorsDo> totalAmountBetweenMonth, IndicatorsDto indicatorsDto) {
        List<IndicatorsDo> indicatorsDos = new ArrayList<>();
        for (int i = 1; i < indicatorsDto.getEndMonth(); i++) {
            double v1 = pd(totalAmountBetweenMonth, "44", i);
            double v2 = pd(totalAmountBetweenMonth, "45", i);
            double divide = ProductionEfficiencyMula.divide(v1, v2,4) * 100;
            indicatorsDos.add(new IndicatorsDo(i,divide,DataTypeEnum.PIGLETS.getCode()));
        }
        return getILineChartVo(indicatorsDto,indicatorsDos, DataTypeEnum.PIGLETS.getCode());
    }

    private List<ILineChartVo> getPzfml(List<IndicatorsDo> totalAmountBetweenMonth, IndicatorsDto indicatorsDto) {
        List<IndicatorsDo> indicatorsDos = new ArrayList<>();
        for (int i = 1; i < indicatorsDto.getEndMonth(); i++) {
            double v1 = pd(totalAmountBetweenMonth, "89", i);
            double v2 = pd(totalAmountBetweenMonth, "13", i);
            double divide = ProductionEfficiencyMula.divide(v1, v2,4) * 100;
            indicatorsDos.add(new IndicatorsDo(i,divide,DataTypeEnum.BREEDING.getCode()));
        }
        return getILineChartVo(indicatorsDto,indicatorsDos, DataTypeEnum.BREEDING.getCode());
    }

    private List<ILineChartVo> getLsyChart(List<IndicatorsDo> avgAmountBetweenMonth, List<NpdMonthDo> npdMonthDos, IndicatorsDto indicatorsDto, int farmsCount,User user){
        ExecutorService pool = Executors.newFixedThreadPool(2);
        Future<List<MonthValueDo>> findgestationDayNumFuture = pool.submit(() -> productionMonthService.findgestationDayNum(indicatorsDto,user));
        Future<List<MonthValueDo>> gestationDayNumFuture = pool.submit(() -> productionMonthService.findLactationDayNum(indicatorsDto,user));
        List<MonthValueDo> gestationDayNum = null;
        List<MonthValueDo> lactationDayNum = null;
        try {
            gestationDayNum = findgestationDayNumFuture.get();
            lactationDayNum = gestationDayNumFuture.get() ;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        pool.shutdown();
        List<IndicatorsDo> indicatorsDos = new ArrayList<>();
        for (int i = 1; i < indicatorsDto.getEndMonth(); i++) {
            //平均妊娠天数
            double v1 =  ProductionEfficiencyMula.getValueByMonth(gestationDayNum,i);
            //平均哺乳天数
            double v2 =  ProductionEfficiencyMula.getValueByMonth(lactationDayNum,i);
            double npd = ProductionEfficiencyMula.npd(npdMonthDos, i)/farmsCount;
            double lsy = ProductionEfficiencyMula.divide(365-npd,v1+v2);
            indicatorsDos.add(new IndicatorsDo(i,lsy,DataTypeEnum.LSY_ALL.getCode()));
        }
        return getILineChartVo(indicatorsDto,indicatorsDos,DataTypeEnum.LSY_ALL.getCode());
    }

    private List<ILineChartVo> getNpdChart(List<NpdMonthDo> npdMonthDos,int farmsCount,IndicatorsDto indicatorsDto){
        List<IndicatorsDo> indicatorsDos = new ArrayList<>();
        for (NpdMonthDo n : npdMonthDos) {
            IndicatorsDo indicatorsDo = new IndicatorsDo(n.getMonth(),
                    NumberUtils.formatDouble(n.getValue()/farmsCount,2),
                    DataTypeEnum.NPD.getCode());
            indicatorsDos.add(indicatorsDo);
        }
        return getILineChartVo(indicatorsDto,indicatorsDos, DataTypeEnum.NPD.getCode());
    }
    /**
     * 根据大区Id和时间统计
     * @param indicatorsDto
     * @param user
     * @return
     */
    @Override
    public List<Production> getMenuByOrganIdByTime(IndicatorsDto indicatorsDto, User user) {
        List<Production> menuByOrganIdByTime = indicatorsMapper.getMenuByOrganIdByTime(indicatorsDto,user);
        return menuByOrganIdByTime;
    }

    /**
     * 指标lsy，psy，msy折线图
     * @param indicatorsDto
     * @param indicatorsDo
     * @param dataType
     * @return
     */
    private List<ILineChartVo> getILineChartVo(
            IndicatorsDto indicatorsDto,
            List<IndicatorsDo> indicatorsDo,
            String dataType) {
        List<ILineChartVo> list = new ArrayList<>();
        int year = indicatorsDto.getYear();
        LocalDateTime now = LocalDateTime.now();
        int nowYear = now.getYear();
        if (year > nowYear)throw new GeneralException(PigCodeMsg.DATE_NOT_DATA);
        int endMonth = 12;
        if (nowYear == year){
            Month month = now.getMonth();
            endMonth = month.getValue();
        }
        for (int i = 1;
             i <= endMonth; i++) {
            String istr = String.valueOf(i);
            ILineChartVo iLineChartVo = new ILineChartVo(istr + "月", 0.0,dataType);
            for (IndicatorsDo indicator:indicatorsDo) {
                if (indicator.getMonth() == i && dataType.equals(indicator.getDataType())){
                    iLineChartVo.setValue(NumberUtils.formatDouble(indicator.getReality(),2));
                }
            }
            list.add(iLineChartVo);
        }
        return list;
    }
    private double pd(List<IndicatorsDo> sndicatorsDos , String dataType , int month){
        for (IndicatorsDo p: sndicatorsDos) {
            if (dataType.equals(p.getDataType()) && month == p.getMonth()){
                return p.getReality();
            }
        }
        return 0;
    }


    /**
     * 集团和大区层面指标相关折线图点击月份指标数展示指标倒数1-3的场（根据页面展示情况评估展示的场数） （前端梁永国，后端王涛）
     * 取值方式：
     * 配种分娩率取最低
     * 仔猪成活率取最低
     * PSY取最低（暂定）
     * LSY取最低（暂定）
     * MSY取最低（暂定）
     * NPD取最高
     * @param indicatorsDto
     * @param dataType
     * @param user
     * @return
     */
    @Override
    public List<DoubleKV> orderByIndicators(IndicatorsDto indicatorsDto, String dataType, User user) {
        String orderType = "1";
        if ("65".equals(dataType)){
            orderType = "2";
        }
        return productionMonthMapper.orderByIndicators(indicatorsDto,user,dataType,orderType);
    }
}
