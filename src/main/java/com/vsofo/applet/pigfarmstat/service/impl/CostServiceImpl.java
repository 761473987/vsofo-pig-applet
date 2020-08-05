package com.vsofo.applet.pigfarmstat.service.impl;

import com.vsofo.applet.exception.GeneralException;
import com.vsofo.applet.exception.PigCodeMsg;
import com.vsofo.applet.pigfarmstat.comm.Constant;
import com.vsofo.applet.pigfarmstat.data.MonthInterval;
import com.vsofo.applet.pigfarmstat.data.QuarterData;
import com.vsofo.applet.pigfarmstat.dto.cost.PieChartDetailDto;
import com.vsofo.applet.pigfarmstat.dto.monthly.MonthlySearchDto;
import com.vsofo.applet.pigfarmstat.enums.ChartEnum;
import com.vsofo.applet.pigfarmstat.mapper.CostMapper;
import com.vsofo.applet.pigfarmstat.pojo.User;
import com.vsofo.applet.pigfarmstat.service.CostService;
import com.vsofo.applet.pigfarmstat.vo.cost.LineChartVo;
import com.vsofo.applet.pigfarmstat.vo.monthly.MChartVo;
import com.vsofo.applet.pigfarmstat.vo.monthly.SuchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

/**
 * @description:
 * @author: liuzhiyun
 * @create: 2020-07-15 15:54
 **/
@Service
public class CostServiceImpl implements CostService {
    
    @Autowired
    private CostMapper costMapper;
    /**
     * 根据日期和养殖场信息展示成本
     * @param monthlySearchDto
     * @return
     */
    @Override
    public SuchVo findCoastByPeriod(MonthlySearchDto monthlySearchDto, User user) {
        String dateType = monthlySearchDto.getDateType();
        //根据日期类型判断开始结束月份
        String monthStr = getMonthsByQuarter(monthlySearchDto.getYear().toString(), dateType);
        ArrayList<MChartVo> mChartVos = new ArrayList<>();
        //成本页面的显示对象
        SuchVo suchVo = new SuchVo(Constant.PAGETITLE, Constant.YUAN, 0,"0", null,null, mChartVos);
        //获取饼图数据
        MChartVo pieChart = getPieChart(monthlySearchDto, suchVo, monthStr, user, null);
        //处理季度的开始结束月份
        handleMonth(monthlySearchDto);
        List<LineChartVo> lineChartData = costMapper.findByMonth(monthlySearchDto, monthStr, user);
        //获取折线图数据
        MChartVo lineChart = getLineChart(monthlySearchDto,lineChartData);
        //累计折线图
        MChartVo accumulativeLineChart = getAccumulativeLineChart(monthlySearchDto, lineChartData);
        mChartVos.add(accumulativeLineChart);
        mChartVos.add(lineChart);
        mChartVos.add(pieChart);
        return suchVo;
    }
    
    /**
     * 切换饼图
     * @param monthlySearchDto
     * @param user
     * @param viewType
     * @return
     */
    @Override
    public MChartVo changeViewType(MonthlySearchDto monthlySearchDto, User user, Integer viewType) {
        String dateType = monthlySearchDto.getDateType();
        //根据日期类型判断开始结束月份
        String monthStr = getMonthsByQuarter(monthlySearchDto.getYear().toString(), dateType);
        return getPieChart(monthlySearchDto,new SuchVo(),monthStr,user, viewType);
    }
    
    /**
     * 获取折线图数据
     * @param monthlySearchDto
     * @param lineChartData
     * @return
     */
    private MChartVo getLineChart (MonthlySearchDto monthlySearchDto,List<LineChartVo> lineChartData) {
        //处理各月数据,以月份展示
        List<LineChartVo> lineChartDisplayData = formatToMothData(lineChartData, monthlySearchDto, false);
        //将折线图放入到图对象
        MChartVo lineChart = new MChartVo(monthlySearchDto.getYear() + QuarterData.getQuarterName(monthlySearchDto.getDateType()) + Constant.COST_LINECHART_NAME + Constant.YUAN_TITLE, ChartEnum.LINE_MAP.getCode(), lineChartDisplayData);
        return lineChart;
    }
    
    /**
     * 获取累计折线图数据
     * @param monthlySearchDto
     * @param lineChartData
     * @return
     */
    private MChartVo getAccumulativeLineChart(MonthlySearchDto monthlySearchDto, List<LineChartVo> lineChartData) {
        List<LineChartVo> lineChartDisplayData = formatToMothData(lineChartData, monthlySearchDto, true);
        MChartVo lineChart = new MChartVo(monthlySearchDto.getYear() + QuarterData.getQuarterName(monthlySearchDto.getDateType()) + Constant.COST_ACCUMULATIVE_LINECHART_NAME + Constant.YUAN_TITLE, ChartEnum.CUMULATIVE_LINE_COST.getCode(), lineChartDisplayData);
        return lineChart;
    }
    /**
     * 获取饼图数据
     * @param monthlySearchDto
     * @param suchVo
     * @param yearAndMonth
     * @return
     */
    private MChartVo getPieChart (MonthlySearchDto monthlySearchDto, SuchVo suchVo, String yearAndMonth, User user, Integer viewType) {
        ArrayList<PieChartDetailDto> pieChartDetailDtos = new ArrayList<>();
        Map<String, BigDecimal> map = costMapper.findCoastByPeriod(monthlySearchDto, yearAndMonth, user, viewType == null ? 1 : viewType);
        if (map == null) {
            map = new HashMap<>();
        }
        //饼图详情对象
        PieChartDetailDto pieChartDetailDto = null;
        Iterator<String> iterator = map.keySet().iterator();
        BigDecimal total = BigDecimal.ZERO;
        //从查询结果中取出饼图的属性名和属性值
        while (iterator.hasNext()) {
            String key = iterator.next();
            BigDecimal value =  map.get(key);
            total = total.add(value.stripTrailingZeros());
            pieChartDetailDto = new PieChartDetailDto(key, value.stripTrailingZeros().doubleValue(), 0);
            pieChartDetailDtos.add(pieChartDetailDto);
        }
        //总成本
        suchVo.setTotal(total.toString());
        //计算比率
        if (!Objects.equals(total, BigDecimal.ZERO)) {
            for (PieChartDetailDto chartDetailDto : pieChartDetailDtos) {
                BigDecimal result = new BigDecimal(chartDetailDto.getValue()).divide(total,4, BigDecimal.ROUND_HALF_UP);
                chartDetailDto.setRatio(result.doubleValue());
            }
        }
        //将饼图填充到图对象
        MChartVo pieChart = new MChartVo(monthlySearchDto.getYear()+ QuarterData.getQuarterName(monthlySearchDto.getDateType()) + Constant.COST_PIECHART_NAME, ChartEnum.BREAD_MAP.getCode(), pieChartDetailDtos);
        return pieChart;
    }
    /**
     * 将数据转换以月份展示方便折线图显示
     * @param lineChartVos
     * @param monthlySearchDto
     * @param isAccumulative 是否是累计
     * @return
     */
    private List<LineChartVo> formatToMothData(List<LineChartVo> lineChartVos, MonthlySearchDto monthlySearchDto, boolean isAccumulative) {
        String moth;
        ArrayList<LineChartVo> newLineChartVos = new ArrayList<>();
        BigDecimal accumulativeValue = BigDecimal.ZERO;
        loop:for (int i = monthlySearchDto.getStatMonth(); i <= monthlySearchDto.getEndMonth(); i++) {
            for (LineChartVo lineChartVo : lineChartVos) {
                moth  = lineChartVo.getMonth();
                if (String.valueOf(i).equals(moth)) {
                    if (isAccumulative) {
                        accumulativeValue = accumulativeValue.add(lineChartVo.getValue().stripTrailingZeros());
                        newLineChartVos.add(new LineChartVo(moth+"月", accumulativeValue));
                    } else {
                        newLineChartVos.add(new LineChartVo(moth+"月", lineChartVo.getValue().stripTrailingZeros()));
                    }
                    continue loop;
                }
            }
            newLineChartVos.add(new LineChartVo(i+"月", BigDecimal.ZERO));
            
        }
        return newLineChartVos;
    }
    
    /**
     * 按季度处理开始结束月
     * @param monthlySearchDto
     */
    private void handleMonth (MonthlySearchDto monthlySearchDto) {
        String dateType = monthlySearchDto.getDateType();
        MonthInterval monthInterval = QuarterData.getMonthInterval(org.apache.commons.lang.StringUtils.isBlank(dateType) ? "1234" : dateType);
        LocalDate now = LocalDate.now();
        int currentMoth = now.getMonth().getValue();
        int currentYear = now.getYear();
        if (monthlySearchDto.getYear() > currentYear) {
            throw new GeneralException(PigCodeMsg.DATE_NOT_DATA);
        }
        monthlySearchDto.setStatMonth(monthInterval.getStartMonth());
        if (monthlySearchDto.getYear() == currentYear && monthInterval.getEndMonth() > currentMoth) {
            monthlySearchDto.setEndMonth(currentMoth);
        } else {
            monthlySearchDto.setEndMonth(monthInterval.getEndMonth());
        }
    }
    
    /**
     * 将数字除以10000再四舍五入保留两位
     * @param value
     * @return
     */
    private BigDecimal aroundNum (String value) {
        BigDecimal bigDecimal = new BigDecimal(value);
        //除以10000再四舍五入保留两位
        BigDecimal result = bigDecimal.divide(new BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP);
        return result;
    }
    
    private String getMonthsByQuarter(String year, String quarter) {
        switch (quarter) {
            case "1":
             return generateYearAndMonth(year,1, 3);
            case "2":
                return generateYearAndMonth(year, 4, 6);
            case "3":
                return generateYearAndMonth(year, 7, 9);
            case "4":
                return generateYearAndMonth(year, 10, 12);
            case "12":
                return generateYearAndMonth(year, 1, 6);
            case "34":
                return generateYearAndMonth(year, 7, 12);
            case "1234":
                return generateYearAndMonth(year, 1, 12);
            default:
                return generateYearAndMonth(year, 1, 12);
        }
    }
    
    /**
     * 根据季度生成年月集合用于查询
     * @param year
     * @param start
     * @param end
     * @return
     */
    private String generateYearAndMonth (String year, int start, int end) {
        String dates = "";
        String monthNum;
        int currentMonth = LocalDate.now().getMonth().getValue();
        int currentYear = LocalDate.now().getYear();
        if (String.valueOf(currentYear).equals(year) && start > currentMonth) {
            throw new GeneralException(PigCodeMsg.DATE_NOT_DATA);
        }
         end = (String.valueOf(currentYear).equals(year)) && (end > currentMonth) ? currentMonth : end;
        for (int i = start; i <= end; i++) {
            monthNum = String.format("%02d", i);
            dates += i < end ? monthNum + "," : monthNum;
        }
        return StringUtils.isEmpty(dates) ? null : dates;
    }
}
