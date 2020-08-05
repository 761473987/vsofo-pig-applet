package com.vsofo.authentication.exception.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.vsofo.authentication.exception.log.AuthLog;
import com.vsofo.authentication.exception.log.LoginLog;
import com.vsofo.authentication.exception.service.LogService;
import com.vsofo.authentication.mapper.AuthLogMapper;
import com.vsofo.authentication.mapper.LoginLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lituo
 * @version 1.0
 * @date 2020/6/19  10:16
 * @description 日志
 */
@Service
@DS("slave_1")
public class LogServiceImpl implements LogService {

    @Autowired
    AuthLogMapper authLogMapper;

    @Autowired
    LoginLogMapper loginLogMapper;

    @Override
    public void save(AuthLog authLog) {
        authLogMapper.insert(authLog);
    }

    @Override
    public void loginLog(LoginLog loginLog) {
        loginLogMapper.insert(loginLog);
    }
}
