package com.vsofo.applet.pigfarmstat.controller;

import com.vsofo.applet.exception.CodeMsg;
import com.vsofo.applet.exception.GeneralException;
import com.vsofo.applet.exception.PigCodeMsg;
import com.vsofo.applet.pigfarmstat.comm.AbstractController;
import com.vsofo.applet.pigfarmstat.data.DefaultAmountDetailData;
import com.vsofo.applet.pigfarmstat.data.MonthInterval;
import com.vsofo.applet.pigfarmstat.data.QuarterData;
import com.vsofo.applet.pigfarmstat.dto.monthly.MonthlySearchDto;
import com.vsofo.applet.pigfarmstat.enums.ChartEnum;
import com.vsofo.applet.pigfarmstat.enums.PigTypeEnums;
import com.vsofo.applet.pigfarmstat.enums.TitleEnum;
import com.vsofo.applet.pigfarmstat.enums.UnitEnum;
import com.vsofo.applet.pigfarmstat.pojo.User;
import com.vsofo.applet.pigfarmstat.service.FarmMonthlyService;
import com.vsofo.applet.pigfarmstat.vo.DoubleKV;
import com.vsofo.applet.pigfarmstat.vo.IntegerKV;
import com.vsofo.applet.pigfarmstat.vo.daily.AmountDailyDetailValueVo;
import com.vsofo.applet.pigfarmstat.vo.daily.AmountDailyDetailVo;
import com.vsofo.applet.pigfarmstat.vo.daily.ChartVo;
import com.vsofo.applet.pigfarmstat.vo.daily.PieChartVo;
import com.vsofo.applet.pigfarmstat.vo.monthly.LineChartVo;
import com.vsofo.applet.pigfarmstat.vo.monthly.MChartVo;
import com.vsofo.applet.pigfarmstat.vo.monthly.SuchVo;
import com.vsofo.applet.utils.NumberUtils;
import com.vsofo.common.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: wangtao
 * @date: 2020/5/29
 */
@RestController
@RequestMapping("/api/applet/monthly")
@Api(tags = "猪场月报-出栏存栏")
public class FarmMonthlyController extends AbstractController {

    @Autowired
    private FarmMonthlyService farmMonthlyService;

    @Autowired
    private DefaultAmountDetailData defaultAmountDetailData;

    @ApiOperation("出栏页面")
    @GetMapping("line")
    public Result line(MonthlySearchDto monthlySearchDto) {
        User user = this.getUser();
        int jtNum = 0;
        setDate(monthlySearchDto);
        int suchSum = farmMonthlyService.findSuchSum(monthlySearchDto, user);
        LocalDate localDate = LocalDate.now();
        List<MChartVo> mChartVos = new ArrayList<>();
        if (monthlySearchDto.getStatMonth() <= localDate.getMonth().getValue()
            || monthlySearchDto.getYear() < localDate.getYear()
        ) {
            MChartVo lineChartVo = farmMonthlyService.findLineChartVo(monthlySearchDto, PigTypeEnums.PIG_5.getId(), user);//折线图
            MChartVo cumulativeLineChartVo = farmMonthlyService.findCumulativeLineChartVo(monthlySearchDto, (List<LineChartVo>) lineChartVo.getData(), user);//累积折线图
            mChartVos.add(cumulativeLineChartVo);
            mChartVos.add(lineChartVo);
            boolean b = monthlySearchDto.getFarmId() == null && monthlySearchDto.getOrganId() == null;
            if (this.getUser() != null && this.getUser().getRoleId() == 1 && b) {
                MChartVo columnarVo = farmMonthlyService.findColumnarVo(monthlySearchDto, user);//出栏柱状图
                MChartVo bread = farmMonthlyService.findBread(monthlySearchDto, user);//饼图
                mChartVos.add(columnarVo);
                mChartVos.add(bread);
            } else {
                MonthlySearchDto clone = monthlySearchDto.clone();
                clone.setFarmId(null);
                clone.setOrganId(null);
                jtNum = farmMonthlyService.findSuchSum(clone, null);
            }

        }
        SuchVo suchVo1 = new SuchVo(TitleEnum.HOG_SUCH.getName(), UnitEnum.HEAD.getName(), suchSum, jtNum, null, mChartVos);
        return new Result(suchVo1);
    }

    @ApiOperation("根据大区ID查询下的所有养殖场")
    @GetMapping("farmByOrganId")
    public Result farmByOrganId(MonthlySearchDto monthlySearchDto){
        setDate(monthlySearchDto);
        List<IntegerKV> list = farmMonthlyService.findFarmByOrganId(monthlySearchDto);
        return new Result(list);
    }

    @ApiOperation("出栏折线图点击")
    @GetMapping("orderByPigSuch")
    public Result orderByPigSuch(MonthlySearchDto monthlySearchDto){
        if (this.getUser().getRoleId() == 3){
            return new Result(CodeMsg.NO_SUCH_REQUEST_404);
        }
        List<DoubleKV> doubleKVS =  farmMonthlyService.orderByPigSuch(monthlySearchDto);
        return new Result(doubleKVS);
    }


    private void setDate(MonthlySearchDto monthlySearchDto) {
        String dateType = monthlySearchDto.getDateType();
        int year = monthlySearchDto.getYear();
        MonthInterval monthInterval = QuarterData.getMonthInterval(StringUtils.isBlank(dateType) ? "1234" : dateType);
        monthlySearchDto.setStatMonth(monthInterval.getStartMonth());
        monthlySearchDto.setEndMonth(monthInterval.getEndMonth());
        LocalDateTime now = LocalDateTime.now();
        int nowYear = now.getYear();
        if (year > nowYear) throw new GeneralException(PigCodeMsg.DATE_NOT_DATA);
        if (nowYear == year) {
            Month month = now.getMonth();
            monthlySearchDto.setEndMonth((month.getValue() + 1));
            monthlySearchDto.setEndMonth(month.getValue() >= monthInterval.getEndMonth() ? monthInterval.getEndMonth() : month.getValue());
        }


    }

    @ApiOperation(hidden = true, value = "出栏年度累计-折线图")
    @GetMapping("lineSum")
    public Result lineSum(MonthlySearchDto monthlySearchDto) {
        return new Result(farmMonthlyService.findLineChartVo(monthlySearchDto, null, this.getUser()));
    }

    @ApiOperation(hidden = true, value = "出栏-柱状图-商品猪出栏数据对比")
    @GetMapping("columnar")
    public Result columnar(MonthlySearchDto monthlySearchDto) {
        return new Result(farmMonthlyService.findColumnarVo(monthlySearchDto, this.getUser()));
    }

    @ApiOperation(hidden = true, value = "出栏-饼图")
    @GetMapping("bread")
    public Result bread(MonthlySearchDto monthlySearchDto) {
        return new Result(farmMonthlyService.findBread(monthlySearchDto, this.getUser()));
    }

    @ApiOperation(hidden = false, value = "出栏-多线折线图")
    @GetMapping("more")
    public Result more(MonthlySearchDto monthlySearchDto) {
        MChartVo moreLineChartVo = farmMonthlyService.findMoreLineChartVo(monthlySearchDto, this.getUser());
        return new Result(moreLineChartVo);
    }

    @ApiOperation(hidden = true, value = "存栏-公猪存栏折线图")
    @GetMapping("sow_line")
    public Result sowLine(MonthlySearchDto monthlySearchDto) {
        MChartVo mChartVo = farmMonthlyService.findaAmountLineChartVo(monthlySearchDto, TitleEnum.BOAR_HISTOGRAM.getCode(), this.getUser());
        return new Result(mChartVo);
    }

    @ApiOperation(hidden = true, value = "存栏-母猪存栏折线图")
    @GetMapping("boar_line")
    public Result boarLine(MonthlySearchDto monthlySearchDto) {
        MChartVo mChartVo = farmMonthlyService.findaAmountLineChartVo(monthlySearchDto, TitleEnum.SOW_HISTOGRAM.getCode(), this.getUser());
        return new Result(mChartVo);
    }

    @GetMapping("detail")
    @ApiOperation("出栏详情")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "品种ID", name = "pigvarietyId")
    }
    )
    public Result detail(MonthlySearchDto monthlySearchDto, String pigvarietyId) throws CloneNotSupportedException {
        setDate(monthlySearchDto);
        User user = this.getUser();
        int[] pigvarietyIds = null;
        if(StringUtils.isNotBlank(pigvarietyId)){
            String[] split = pigvarietyId.split(",");
            if (split != null) {
                pigvarietyIds = Arrays.asList(split).stream().mapToInt(i -> Integer.valueOf(i)).toArray();
            }
        }
        List<AmountDailyDetailValueVo> amountDailyDetailValueVos = farmMonthlyService.findSuchMonthDetailValueVo(monthlySearchDto,pigvarietyIds,"5",user);
        List<PieChartVo> pieChartVos = new ArrayList<>();
        int sum = 0;
        fillDetailData(amountDailyDetailValueVos,"5");
        for (AmountDailyDetailValueVo a:amountDailyDetailValueVos) {
            Integer value = Integer.valueOf(a.getValue());
            sum = sum + value;
            a.setValue(value + "头");
            pieChartVos.add(new PieChartVo(a.getPigTypeName(), value, 0));
        }
        for (PieChartVo p:pieChartVos) {
            p.setRatio(NumberUtils.formatDouble((p.getValue()/(double)sum)*100,2));
        }
        ChartVo chartVo = new ChartVo("", ChartEnum.BREAD_MAP.getCode(), pieChartVos);
        AmountDailyDetailVo amountDailyDetailVo = new AmountDailyDetailVo(amountDailyDetailValueVos, chartVo, sum);
        return new Result(amountDailyDetailVo);
    }

    /**
     * 填充默认数据
     * @param amountDailyDetailValueVos
     * @param pigType
     */
    private void fillDetailData(List<AmountDailyDetailValueVo> amountDailyDetailValueVos, String pigType) throws CloneNotSupportedException {
        List<AmountDailyDetailValueVo> data = defaultAmountDetailData.getData(pigType);
        a:
        for (AmountDailyDetailValueVo a: data) {
            for (AmountDailyDetailValueVo b:amountDailyDetailValueVos) {
                if (b.getPigTypeName().equals(a.getPigTypeName()))continue a;
            }
            amountDailyDetailValueVos.add(a);
        }
    }
}
