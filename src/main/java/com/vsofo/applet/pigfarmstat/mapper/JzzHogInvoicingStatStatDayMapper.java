package com.vsofo.applet.pigfarmstat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vsofo.applet.pigfarmstat.pojo.JzzHogInvoicingStatStatDay;
import com.vsofo.applet.pigfarmstat.pojo.JzzPigInvoicingStatStatDay;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JzzHogInvoicingStatStatDayMapper extends BaseMapper<JzzHogInvoicingStatStatDay> {

    int batchInsert(@Param("list") List<JzzHogInvoicingStatStatDay> list);

}
