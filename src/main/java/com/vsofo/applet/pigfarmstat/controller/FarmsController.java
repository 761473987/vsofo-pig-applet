package com.vsofo.applet.pigfarmstat.controller;

import com.vsofo.applet.pigfarmstat.comm.AbstractController;
import com.vsofo.applet.pigfarmstat.dto.farm.FarmDto;
import com.vsofo.applet.pigfarmstat.pojo.User;
import com.vsofo.applet.pigfarmstat.service.FarmsService;
import com.vsofo.applet.pigfarmstat.service.OrganService;
import com.vsofo.applet.pigfarmstat.vo.farms.FarmsGroupVo;
import com.vsofo.applet.pigfarmstat.vo.farms.FarmsVo;
import com.vsofo.common.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wangtao
 * @date: 2020/5/28
 * 养殖场
 */
@RestController
@RequestMapping("/api/applet/farm")
@Api(tags = "养殖场")
public class FarmsController extends AbstractController {

    @Autowired
    private FarmsService farmsService;

    @Autowired
    private OrganService organService;

    @GetMapping
    @ApiOperation("养殖场列表")
    public Result farm(FarmDto farmDto){
        List<FarmsGroupVo> farmsGroupVos = new ArrayList<>();
        User user = this.getUser();
        if (user != null){
            if (user.getRoleId() != 3){
                if (farmDto.getOrganId()==null){
                    farmsGroupVos = organService.findOrganVo();
                } else {
                    farmsGroupVos = organService.findOrganVoById(farmDto.getOrganId());
                }
            }else {
                List<FarmsVo> farmsVos = new ArrayList<>();
                FarmsVo farmsVo = new FarmsVo();
                farmsVo.setFarmId(-1);
                farmsVo.setFarmName("当前所有养殖场");
                farmsVo.setChineseSpell("全部");
                farmsVos.add(farmsVo);
                farmsGroupVos.add(new FarmsGroupVo("区",farmsVos));
            }
        }
        List<FarmsGroupVo> list = farmsService.findFarm(farmDto,user);
        farmsGroupVos.addAll(list);
        return new Result(farmsGroupVos);
    }

    @GetMapping("selectedFarm")
    @ApiOperation("选择养殖场大区")
    public Result selectedFarm(Integer farmId){
        User user = this.getUser();
//        farmsService.selectedFarm(user,farmId);
        return new Result("切换成功");
    }

    @GetMapping("getSelectedFarm")
    @ApiOperation("获取上一次选中的养殖场")
    public Result getSelectedFarm(){
        User user = this.getUser();
        Integer farmId = farmsService.getSelectedFarm(user);
        return new Result(farmId);
    }
}
