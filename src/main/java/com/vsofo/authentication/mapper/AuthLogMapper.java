package com.vsofo.authentication.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vsofo.authentication.exception.log.AuthLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lituo
 * @version 1.0
 * @date 2020/6/9  10:44
 * @description 认证日志
 */
@Mapper
public interface AuthLogMapper extends BaseMapper<AuthLog> {
}
