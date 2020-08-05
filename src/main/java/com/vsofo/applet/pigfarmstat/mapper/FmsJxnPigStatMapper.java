package com.vsofo.applet.pigfarmstat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vsofo.applet.pigfarmstat.pojo.FmsJxnPigStat;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

public interface FmsJxnPigStatMapper extends BaseMapper<FmsJxnPigStat> {
    @Select("SELECT * FROM FMS_JXN_PIG_STAT WHERE DateDiff(DD,Date,#{format}) = 0")
    List<FmsJxnPigStat> selectList(@Param("format") String format);
}
