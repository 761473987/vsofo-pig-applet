package com.vsofo.applet.pigfarmstat.controller;

import com.vsofo.applet.pigfarmstat.comm.AbstractController;
import com.vsofo.applet.pigfarmstat.data.DefaultAmountDetailData;
import com.vsofo.applet.pigfarmstat.data.QuarterData;
import com.vsofo.applet.pigfarmstat.dto.daily.DailySearchDto;
import com.vsofo.applet.pigfarmstat.enums.ChartEnum;
import com.vsofo.applet.pigfarmstat.pojo.User;
import com.vsofo.applet.pigfarmstat.service.FarmDailyService;
import com.vsofo.applet.pigfarmstat.vo.daily.*;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: wangtao
 * @date: 2020/5/28
 * 猪场日报
 */
@RestController
@RequestMapping("/api/applet/daily")
@Api(tags = "猪场日报-存栏")
public class FarmDailyController extends AbstractController {

    @Autowired
    private FarmDailyService farmDailyService;

    @Autowired
    private DefaultAmountDetailData defaultAmountDetailData;

    @GetMapping("quarter")
    @ApiOperation("查询条件")
    public Result getQuarter(){
        return new Result(QuarterData.getList());
    }

    @GetMapping("amount")
    @ApiOperation("当前存栏")
    public Result amount(DailySearchDto dailySearchDto){
        AmountVO amountVO = farmDailyService.amount(dailySearchDto,this.getUser());
        return new Result(amountVO);
    }

    @GetMapping("detail")
    @ApiOperation("存栏详情页")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "品种ID", name = "pigvarietyId"),
            @ApiImplicitParam(value = "猪只类型", name = "pigType")
        }
    )
    public Result detail(DailySearchDto dailySearchDto, String pigvarietyId,String pigType) throws CloneNotSupportedException {
        User user = this.getUser();
        int[] pigvarietyIds = null;
        if(StringUtils.isNotBlank(pigvarietyId)){
            String[] split = pigvarietyId.split(",");
            if (split != null) {
                pigvarietyIds = Arrays.asList(split).stream().mapToInt(i -> Integer.valueOf(i)).toArray();
            }
        }

        List<AmountDailyDetailValueVo> amountDailyDetailValueVos = farmDailyService.findAmountDailyDetailValueVo(dailySearchDto,pigvarietyIds,pigType,user);
        List<PieChartVo> pieChartVos = new ArrayList<>();
        int sum = 0;
        fillDetailData(amountDailyDetailValueVos,pigType);
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

    @GetMapping("pigVariety")
    @ApiOperation("查询品种")
    public Result findPigVariety(){
        List<PigVarietyVo> pigVarietyVos = farmDailyService.findPigVariety();
        return new Result(pigVarietyVos);
    }

}
