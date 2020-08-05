package com.vsofo.applet.pigfarmstat.controller;

import com.vsofo.applet.pigfarmstat.comm.AbstractController;
import com.vsofo.applet.pigfarmstat.dto.indicators.IndicatorsDto;
import com.vsofo.applet.pigfarmstat.service.ProductionMonthService;
import com.vsofo.applet.pigfarmstat.vo.indicators.ProductionVo;
import com.vsofo.common.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/applet/production")
@Scope("request")
public class ProductionMonthController extends AbstractController {

    @Autowired
    ProductionMonthService productionMonthService;

    @RequestMapping("/monthlyReport")
    public Result monthlyReport(IndicatorsDto indicatorsDto) {
        List<ProductionVo> productionVos = productionMonthService.monthInfoCount(indicatorsDto,this.getUser());
        return new Result(true,1,"success",productionVos);
    }

}
