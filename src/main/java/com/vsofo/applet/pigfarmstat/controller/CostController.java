package com.vsofo.applet.pigfarmstat.controller;

import com.vsofo.applet.pigfarmstat.comm.AbstractController;
import com.vsofo.applet.pigfarmstat.dto.monthly.MonthlySearchDto;
import com.vsofo.applet.pigfarmstat.pojo.User;
import com.vsofo.applet.pigfarmstat.service.CostService;
import com.vsofo.applet.pigfarmstat.vo.monthly.MChartVo;
import com.vsofo.applet.pigfarmstat.vo.monthly.SuchVo;
import com.vsofo.common.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 成本统计
 * @author: liuzhiyun
 * @create: 2020-07-15 16:14
 **/
@RestController
@Api(tags = "成本页面")
@RequestMapping("/api/cost")
public class CostController extends AbstractController {
    @Autowired
    private CostService costService;
    
    @ApiOperation("成本统计")
    @GetMapping("/findCoastByPeriod")
    public Result findCoastByPeriod (MonthlySearchDto monthlySearchDto) {
        User user = this.getUser();
        SuchVo suchVo = costService.findCoastByPeriod(monthlySearchDto, user);
        return new Result(suchVo);
    }
    @ApiOperation("成本饼图切换")
    @GetMapping("/changeViewType")
    public Result changeViewType (MonthlySearchDto monthlySearchDto, Integer viewType) {
        User user = this.getUser();
        MChartVo chartVo = costService.changeViewType(monthlySearchDto, user, viewType);
        return new Result(chartVo);
    }
}
