package com.vsofo.applet.pigfarmstat.controller;

import com.vsofo.applet.exception.GeneralException;
import com.vsofo.applet.pigfarmstat.comm.AbstractController;
import com.vsofo.applet.pigfarmstat.comm.ViewTypes;
import com.vsofo.applet.pigfarmstat.data.QuarterData;
import com.vsofo.applet.pigfarmstat.dto.farm.FarmDto;
import com.vsofo.common.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: wangtao
 * @date: 2020/6/1
 */
@RestController
@RequestMapping("/api/applet/comm")
@Api(tags = "公用")
public class CommController extends AbstractController {
    @GetMapping("quarter")
    @ApiOperation("季度条件查询")
    public Result quarter(){
        return new Result(QuarterData.getList());
    }
    
    @GetMapping("/viewTypes")
    @ApiOperation("获取成本查看方式")
    public Result<List> getViewTypes(){
        return new Result(ViewTypes.getViewTypeList());
    }

    @GetMapping("testExption")
    public Result testExption(int i){
        if (i == 1){
            throw new GeneralException(new Result(50005,"测试自定义异常"));
        }
        return new Result();
    }

    @GetMapping("testBad")
    public Result testBad(FarmDto farmDto){
        return new Result("测试参数错误异常");
    }

    @GetMapping("testExption1")
    public Result testExption1(FarmDto farmDto) throws Exception {
        throw new Exception("错误");
    }

}
