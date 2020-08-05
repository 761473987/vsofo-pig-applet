package com.vsofo.applet.pigfarmstat.controller;

import com.vsofo.applet.exception.CodeMsg;
import com.vsofo.applet.exception.GeneralException;
import com.vsofo.applet.exception.PigCodeMsg;
import com.vsofo.applet.pigfarmstat.comm.AbstractController;
import com.vsofo.applet.pigfarmstat.data.IndDetailData;
import com.vsofo.applet.pigfarmstat.dto.indicators.IndicatorsDto;
import com.vsofo.applet.pigfarmstat.enums.DataTypeEnum;
import com.vsofo.applet.pigfarmstat.enums.TitleEnum;
import com.vsofo.applet.pigfarmstat.enums.UnitEnum;
import com.vsofo.applet.pigfarmstat.pojo.Production;
import com.vsofo.applet.pigfarmstat.pojo.User;
import com.vsofo.applet.pigfarmstat.service.IndicatorsService;
import com.vsofo.applet.pigfarmstat.service.ProductionEfficiencyService;
import com.vsofo.applet.pigfarmstat.vo.DoubleKV;
import com.vsofo.applet.pigfarmstat.vo.indicators.*;
import com.vsofo.applet.utils.NumberUtils;
import com.vsofo.common.entity.Result;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: wangtao
 * @date: 2020/6/2
 * 指标
 */
@RestController
@RequestMapping("/api/applet/indicators")
@Api(tags = "指标-月报")
public class IndicatorsController extends AbstractController {
    @Autowired
    private IndicatorsService indicatorsService;
    @Autowired
    private ProductionEfficiencyService efficiencyService;

    @GetMapping
    public Result indicators(IndicatorsDto indicatorsDto) {
        setIndicatorsDto(indicatorsDto);
        List<IIChartVo> iLineChartVo = this.findILineCharVoV2(indicatorsDto,this.getUser());
        List<ProductionVo> production = this.getProductionV2(iLineChartVo,indicatorsDto,this.getUser());
        IndicatorsVo indicatorsVo = new IndicatorsVo(
                TitleEnum.INDICATORS.getName(),
                UnitEnum.HEAD.getName(),
                production, iLineChartVo);
        return new Result(indicatorsVo);
    }

    @GetMapping("orderByIndicators")
    @ApiModelProperty("指标倒数3")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dataType",value = "指标类型"),
    })
    public Result orderByIndicators(IndicatorsDto indicatorsDto,String dataType){
        if (this.getUser().getRoleId() == 3){
            return new Result(CodeMsg.NO_SUCH_REQUEST_404);
        }
        setIndicatorsDto(indicatorsDto);
        List<DoubleKV> list =  this.indicatorsService.orderByIndicators(indicatorsDto,dataType,this.getUser());
        return new Result(list);
    }

    private void setIndicatorsDto(IndicatorsDto indicatorsDto) {
        String statDate = indicatorsDto.getStatDate();
        int year=Integer.valueOf(statDate.substring(0,4));
        String statMonth=statDate.substring(5);
        indicatorsDto.setYear(year);
        indicatorsDto.setStatMonth(1);
        indicatorsDto.setEndMonth(12);
        LocalDateTime now = LocalDateTime.now();
        int nowYear = now.getYear();
        if (year > nowYear)throw new GeneralException(PigCodeMsg.DATE_NOT_DATA);
        if (nowYear == year){
            Month month = now.getMonth();
            indicatorsDto.setEndMonth((month.getValue() + 1)) ;
        }
        indicatorsDto.setWeek(statDate.replace("-",""));
        indicatorsDto.setMonth(Integer.valueOf(statMonth));
    }

    @GetMapping("tetstt")
    @ApiOperation("折线图")
    public List<IIChartVo> findILineChartVo(IndicatorsDto indicatorsDto) {
        List<IIChartVo> iIChartVo = indicatorsService.findIndicatorsDo(indicatorsDto,this.getUser());
        return iIChartVo;
    }

    public List<IIChartVo> findILineCharVoV2(IndicatorsDto indicatorsDto, User user) {
        return indicatorsService.findILineCharVoV2(indicatorsDto,user);
    }

    public List<ProductionVo> getProductionV2(List<IIChartVo> iLineChartVo, IndicatorsDto indicatorsDto, User user) {
        List<ProductionVo> list1 = new ArrayList<>();
        for (IIChartVo i : iLineChartVo) {
            caseName(indicatorsDto,list1,DataTypeEnum.BREEDING.getMenuName(),i.getChartName(),i,"224");
            caseName(indicatorsDto,list1,DataTypeEnum.PIGLETS.getMenuName(),i.getChartName(),i,"225");
            caseName(indicatorsDto,list1,DataTypeEnum.PSY_ALL.getMenuName(),i.getChartName(),i,"222");
            caseName(indicatorsDto,list1,DataTypeEnum.LSY_ALL.getMenuName(),i.getChartName(),i,"221");
            caseName(indicatorsDto,list1,DataTypeEnum.MSY.getMenuName(),i.getChartName(),i,"223");
            caseName(indicatorsDto,list1,DataTypeEnum.NPD.getMenuName(),i.getChartName(),i,"226");
        }
        return list1;
    }
    public void  caseName(IndicatorsDto indicatorsDto,List<ProductionVo> list,String chartName , String menuName,IIChartVo v1,String id){
        List<ILineChartVo> data = (List<ILineChartVo>) v1.getData();
        if (chartName.equals(menuName)) {
            for (ILineChartVo i : data){
                String month = i.getMonth();
                String newMonth = month.replace("月","");
                if (newMonth.equals(String.valueOf(indicatorsDto.getMonth()))){
                    setProduction(chartName,list,i.getValue(),id);
                }
            }
        }
    }
    public void setProduction(String dataTypeName,List<ProductionVo> list1,double v,String id){
        list1.add(new ProductionVo(id,
                dataTypeName,
                NumberUtils.
                        formatDoubleToString(v, 2)
                , "/indicators/detail")
        );
    }
    //    @GetMapping
//    @ApiOperation("指标统计")
    public List<ProductionVo> getProduction(IndicatorsDto indicatorsDto,User user) {
        List<Production> list = indicatorsService.getMenuByOrganIdByTime(indicatorsDto,user);
        List<ProductionVo> list1 = new ArrayList<>();
        for (Production production : list) {
            String dataTypeName = production.getDataTypeName();
            //LSY(全群指标)
            if (dataTypeName.equals(DataTypeEnum.LSY_ALL.getName())) {
                list1.add(new ProductionVo("221",
                        DataTypeEnum.LSY_ALL.getMenuName(),
                        NumberUtils.
                                formatDoubleToString(production.getReality(), 2)
                        , "/indicators/detail")
                );
            }
            //PSY(全群指标)
            if (dataTypeName.equals(DataTypeEnum.PSY_ALL.getName())) {
                list1.add(new ProductionVo("222"
                        , DataTypeEnum.PSY_ALL.getMenuName()
                        , NumberUtils
                        .formatDoubleToString(production.getReality(), 2)
                        , "/indicators/detail")
                );
            }
            //MSY
            if (dataTypeName.equals(DataTypeEnum.MSY.getName())) {
                list1.add(new ProductionVo("223"
                        , DataTypeEnum.MSY.getMenuName()
                        , NumberUtils.formatDoubleToString(production.getReality(), 2)
                        , "/indicators/detail")
                );
            }
            //配种分娩率
            if (dataTypeName.equals(DataTypeEnum.BREEDING.getName())) {
                list1.add(new ProductionVo("224"
                        , DataTypeEnum.BREEDING.getMenuName()
                        , NumberUtils.formatDouble(production.getReality(), 2) + "%",
                        "/indicators/detail")
                );
            }
            //仔猪成活率
            if (dataTypeName.equals(DataTypeEnum.PIGLETS.getName())) {
                list1.add(new ProductionVo("225"
                        , DataTypeEnum.PIGLETS.getMenuName()
                        , NumberUtils.formatDouble(production.getReality(), 0) + "%"
                        , "/indicators/detail")
                );
            }
            //NPD
            if (dataTypeName.equals(DataTypeEnum.NPD.getName())) {
                list1.add(new ProductionVo("226"
                        , DataTypeEnum.NPD.getMenuName()
                        , NumberUtils.formatDoubleToString(production.getReality(), 0)
                        , "/indicators/detail")
                );
            }
        }
        return list1;
    }

    @GetMapping("detailQuater")
    public Result detailQuater() {
        return new Result(IndDetailData.getQuarter());
    }

    /**
     * @param indicatorsDto
     * @param groupId
     * @param dateType
     * @return
     */
    @GetMapping("detail")
    @ApiOperation("指标详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupId", value = "指标组ID"),
            @ApiImplicitParam(name = "dateType", value = "时间类型ID(1、周，2、月)")
    })
    public Result detail(
            IndicatorsDto indicatorsDto, String groupId
            , @RequestParam(defaultValue = "1") String dateType) {
//        if (dateType.equals("2")) {
//            list = indicatorsService.findIndicatorsDetail(indicatorsDto, groupId,user);
//        } else {
//            list = indicatorsService.findIndicatorsDetailOfWeek(indicatorsDto, groupId,user);
//        }
        List<IndDetailVo> list = efficiencyService.getProduction(indicatorsDto, dateType, groupId, this.getUser());
        if (CollectionUtils.isEmpty(list)) return null;
        list.add(0, new IndDetailVo( "指标", "指标", "实际成绩" ,true));
        return new Result(list);
    }
}
