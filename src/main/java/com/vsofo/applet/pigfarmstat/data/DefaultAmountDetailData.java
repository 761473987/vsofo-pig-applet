package com.vsofo.applet.pigfarmstat.data;

import com.vsofo.applet.pigfarmstat.service.FarmDailyService;
import com.vsofo.applet.pigfarmstat.vo.daily.AmountDailyDetailValueVo;
import com.vsofo.applet.utils.SpringBeanUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 存栏详情页，获取pigType 对应的生产状态
 */
@Component
public class DefaultAmountDetailData implements InitializingBean {
    @Autowired
    private FarmDailyService farmDailyService;

    private List<AmountDailyDetailValueVo> amountDailyDetailValueVos = new ArrayList<>();


    public DefaultAmountDetailData() {

    }

    public List<AmountDailyDetailValueVo> getData(){
        amountDailyDetailValueVos.add(new AmountDailyDetailValueVo("1","种公猪","0"));
        amountDailyDetailValueVos.add(new AmountDailyDetailValueVo("2","种母猪","0"));
        amountDailyDetailValueVos.add(new AmountDailyDetailValueVo("1","后备公猪","0"));
        amountDailyDetailValueVos.add(new AmountDailyDetailValueVo("2","后备母猪","0"));
        List<String> productionStatus = farmDailyService.findProductionStatus("5");
        for (String name:productionStatus) {
            amountDailyDetailValueVos.add(new AmountDailyDetailValueVo("5",name,"0"));
        }
        return amountDailyDetailValueVos;
    }

    /**
     * 根据类型获取生产状态
     * @param pigType
     * @return
     */
    public List<AmountDailyDetailValueVo> getData(String pigType) throws CloneNotSupportedException {
        List<AmountDailyDetailValueVo> list = new ArrayList<>();
        if (StringUtils.isBlank(pigType) || "-1".equals(pigType)){
            for (AmountDailyDetailValueVo a:amountDailyDetailValueVos) {
                list.add(a.clone());
            }
        }else {
            for (AmountDailyDetailValueVo a: amountDailyDetailValueVos) {
                if (a.getPigType().equals(pigType)){
                    list.add(a.clone());
                }
            }
        }
        return list;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        getData();
    }
}
