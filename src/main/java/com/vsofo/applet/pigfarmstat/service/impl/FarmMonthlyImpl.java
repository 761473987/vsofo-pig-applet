package com.vsofo.applet.pigfarmstat.service.impl;

import com.vsofo.applet.pigfarmstat.data.DataTypeDic2;
import com.vsofo.applet.pigfarmstat.data.QuarterData;
import com.vsofo.applet.pigfarmstat.dto.monthly.MonthlySearchDto;
import com.vsofo.applet.pigfarmstat.enums.ChartEnum;
import com.vsofo.applet.pigfarmstat.enums.TitleEnum;
import com.vsofo.applet.pigfarmstat.mapper.FarmMonthlyMapper;
import com.vsofo.applet.pigfarmstat.pojo.Organ;
import com.vsofo.applet.pigfarmstat.pojo.User;
import com.vsofo.applet.pigfarmstat.service.FarmMonthlyService;
import com.vsofo.applet.pigfarmstat.service.OrganService;
import com.vsofo.applet.pigfarmstat.vo.DoubleKV;
import com.vsofo.applet.pigfarmstat.vo.IntegerKV;
import com.vsofo.applet.pigfarmstat.vo.daily.AmountDailyDetailValueVo;
import com.vsofo.applet.pigfarmstat.vo.monthly.*;
import com.vsofo.applet.utils.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: wangtao
 * @date: 2020/5/29
 */

@Service
@Transactional(isolation = Isolation.READ_UNCOMMITTED)
public class FarmMonthlyImpl implements FarmMonthlyService {

    @Autowired
    private FarmMonthlyMapper farmMonthlyMapper;

    @Autowired
    private OrganService organService;


    /**
     * 出栏折线图
     *
     * @param monthlySearchDto
     * @param pigType
     * @param user
     * @return
     */
    @Override
    public MChartVo findLineChartVo(MonthlySearchDto monthlySearchDto, String pigType, User user) {
        List<LineChartVo> lineChartVo = farmMonthlyMapper.findLineChartVo(monthlySearchDto,pigType,user);
        lineChartVo = formatLineChartVo(monthlySearchDto,lineChartVo);
        return new MChartVo(
                monthlySearchDto.getYear() +
                        QuarterData.getQuarterName(monthlySearchDto.getDateType()) +
                        TitleEnum.HOG_SUCH.getName(), ChartEnum.LINE_MAP.getCode(),
                lineChartVo);
    }


    /**
     * 累计出栏
     * @return
     */
    @Override
    public MChartVo findCumulativeLineChartVo(MonthlySearchDto monthlySearchDto, List<LineChartVo> lineChartVos, User user) {
        AtomicInteger sum = new AtomicInteger();
        ArrayList<LineChartVo> newLineChartVos = new ArrayList<>();
        lineChartVos.forEach(l -> {
            sum.addAndGet(l.getValue());
            newLineChartVos.add(new LineChartVo(l.getMonth(),sum.get()));
        });
        if (CollectionUtils.isEmpty(lineChartVos))return null;
        return new MChartVo(
                monthlySearchDto.getYear() +
                        QuarterData.getQuarterName(monthlySearchDto.getDateType()) +
                        TitleEnum.HOG_CUMULATIVE_SUCH.getName(), ChartEnum.CUMULATIVE_LINE_MAP.getCode(),
                newLineChartVos);
    }

    /**
     * 折线图-填充默认数据
     * @param monthlySearchDto
     * @param lineChartVo
     * @return
     */
    private List<LineChartVo> formatLineChartVo(MonthlySearchDto monthlySearchDto, List<LineChartVo> lineChartVo) {
        List<LineChartVo> line = new ArrayList<>();
        for (int i = monthlySearchDto.getStatMonth(); i <= monthlySearchDto.getEndMonth() ; i++) {
            String istr = String.valueOf(i);
            int value = 0;
            for (LineChartVo l : lineChartVo){
                if (l.getMonth().equals(istr)){
                    value = l.getValue();
                    break;
                }
            }
            line.add(new LineChartVo(istr + "月",value));
        }
        return line;
    }

    /**
     * 柱状图
     * @param monthlySearchDto
     * @param user
     * @return
     */
    @Override
    public MChartVo findColumnarVo(MonthlySearchDto monthlySearchDto, User user) {
        List<ColumnarVo> columnarVo = farmMonthlyMapper.findColumnarVo(monthlySearchDto,user);
        columnarVo = formatColumnarVo(monthlySearchDto,columnarVo);
        return new MChartVo(
                monthlySearchDto.getYear()
                        + QuarterData.getQuarterName(monthlySearchDto.getDateType())
                        + TitleEnum.HOG_CONTRAST.getName(),ChartEnum.MORE_COLUMNAR_MAP.getCode(),
                    columnarVo
                );
    }


    /**
     * 出栏饼图
     * @param monthlySearchDto
     * @param user
     * @return
     */
    @Override
    public MChartVo findBread(MonthlySearchDto monthlySearchDto, User user) {
        List<BreadVo> breadVos = farmMonthlyMapper.findBread(monthlySearchDto,user);
        if (CollectionUtils.isEmpty(breadVos))return null;
        double sum = breadVos
                .stream()
                .mapToDouble(BreadVo::getValue)
                .summaryStatistics()
                .getSum();
        for (BreadVo b:breadVos) {
            double v = b.getValue() / (double) sum;
            double s = 0;
            if (!Double.isNaN(v)){
              s= NumberUtils.formatDouble(v,4);
            }
            b.setDataType(DataTypeDic2.OUT4);
            b.setRatio(s);
        }
        return new MChartVo(
                monthlySearchDto.getYear()
                        + QuarterData.getQuarterName(monthlySearchDto.getDateType())
                        + TitleEnum.HOG_CONTRAST.getName(),ChartEnum.BREAD_MAP.getCode(),
                breadVos
        );
    }

    /**
     * 出栏多线折线图
     * @param monthlySearchDto
     * @param user
     * @return
     */
    @Override
    public MChartVo findMoreLineChartVo(MonthlySearchDto monthlySearchDto, User user) {
        List<MoreLineChartVo> moreLineChartVo = farmMonthlyMapper.findMoreLineChartVo(monthlySearchDto,user);
        moreLineChartVo = format(monthlySearchDto,moreLineChartVo);
        return new MChartVo(
                monthlySearchDto.getYear()
                        + QuarterData.getQuarterName(monthlySearchDto.getDateType())
                        + TitleEnum.HOG_CONTRAST.getName(),ChartEnum.MORE_LINE_MAP.getCode(),
                moreLineChartVo
        );
    }

    /**
     * 存栏-折线图
     * @param monthlySearchDto
     * @param user
     * @return
     */
    @Override
    public MChartVo findaAmountLineChartVo(MonthlySearchDto monthlySearchDto, String pigType, User user) {
        List<LineChartVo> lineChartVo = farmMonthlyMapper.findAmountLineChartVo(monthlySearchDto,pigType,user);
        lineChartVo = formatLineChartVo(monthlySearchDto,lineChartVo);
        String name = pigType.equals(TitleEnum.BOAR_HISTOGRAM.getName())?TitleEnum.BOAR_HISTOGRAM.getName():TitleEnum.SOW_HISTOGRAM.getName();
        return new MChartVo(
                monthlySearchDto.getYear() +
                        QuarterData.getQuarterName(monthlySearchDto.getDateType()) +
                        name, ChartEnum.LINE_MAP.getCode(),
                lineChartVo);
    }

    /**
     * 填充数据-多线折线图，大区商品猪出栏数据对比
     * @param monthlySearchDto
     * @param moreLineChartVo
     * @return
     */
    private List<MoreLineChartVo> format(MonthlySearchDto monthlySearchDto, List<MoreLineChartVo> moreLineChartVo) {
        List<MoreLineChartVo> moreLineChartVos = new ArrayList<>();
        List<Organ> organ = organService.findOrganAndCoop();
        for (int i = monthlySearchDto.getStatMonth() ; i<=monthlySearchDto.getEndMonth();i++){
            String monthStr = String.valueOf(i);
            for (Organ o:organ){
                int value = 0;
                for (MoreLineChartVo m:moreLineChartVo){
                    if (m.getMonth().equals(monthStr)&&m.getOrganId().equals(o.getOrganId())){
                        value = m.getValue();
                        break;
                    }
                }
                moreLineChartVos.add(new MoreLineChartVo(o.getOrganId(),o.getOrganName(),monthStr+"月",value,i));
            }
        }
        return moreLineChartVos;
    }
    /**
     * 填充数据，柱状图
     * 大区商品猪出栏数据对比
     * @param monthlySearchDto
     * @param columnarVo
     * @return
     */
    private List<ColumnarVo> formatColumnarVo(MonthlySearchDto monthlySearchDto, List<ColumnarVo> columnarVo) {
        List<ColumnarVo> columnarVos = new ArrayList<>();
        List<Organ> organ = organService.findOrganAndCoop();
        for (int i = monthlySearchDto.getStatMonth() ; i<=monthlySearchDto.getEndMonth();i++) {
            String monthStr = String.valueOf(i);
            for (Organ o:organ) {
                int value = 0 ;
                for (ColumnarVo c : columnarVo) {
                    if (c.getMonth().equals(monthStr)&&c.getOrganId().equals(o.getOrganId())){
                        value = c.getValue();
                        break;
                    }
                }
                columnarVos.add(new ColumnarVo(o.getOrganId(),o.getOrganName(),monthStr+"月",value,i));
            }
        }
        return columnarVos;
    }

    @Override
    public int findSuchSum(MonthlySearchDto monthlySearchDto, User user) {
        return farmMonthlyMapper.findSuchSum(monthlySearchDto,user);
    }

    /**
     * 出栏详细页
     * @param monthlySearchDto
     * @param pigvarietyIds
     * @param pigType
     * @param user
     * @return
     */
    @Override
    public List<AmountDailyDetailValueVo> findSuchMonthDetailValueVo(MonthlySearchDto monthlySearchDto, int[] pigvarietyIds, String pigType, User user) {
        return farmMonthlyMapper.findSuchMonthDetailValueVo(monthlySearchDto,pigvarietyIds,pigType,user);
    }

    @Override
    public List<DoubleKV> orderByPigSuch(MonthlySearchDto monthlySearchDto) {
        return farmMonthlyMapper.orderByPigSuch(monthlySearchDto);
    }

    /**
     * 根据大区查询养殖场
     * @return
     */
    @Override
    public List<IntegerKV> findFarmByOrganId(MonthlySearchDto monthlySearchDto) {

        return farmMonthlyMapper.findFarmByOrganId(monthlySearchDto);
    }
}
