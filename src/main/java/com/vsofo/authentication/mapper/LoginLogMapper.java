package com.vsofo.authentication.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vsofo.authentication.exception.log.LoginLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginLogMapper extends BaseMapper<LoginLog> {
}
