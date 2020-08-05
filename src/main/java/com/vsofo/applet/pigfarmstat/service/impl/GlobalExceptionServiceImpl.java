package com.vsofo.applet.pigfarmstat.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.vsofo.applet.pigfarmstat.log.LogAction;
import com.vsofo.applet.pigfarmstat.mapper.GlobalExceptionMapper;
import com.vsofo.applet.pigfarmstat.service.GlobalExceptionService;
import com.vsofo.applet.pojo.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@DS("slave_2")
@Transactional(isolation = Isolation.READ_UNCOMMITTED)
public class GlobalExceptionServiceImpl implements GlobalExceptionService {

    @Autowired
    private GlobalExceptionMapper globalExceptionMapper;
    @Override
    public void save(LogAction logAction) {
        globalExceptionMapper.insert((GlobalException) logAction);
        System.out.println("保存成功");
    }
}
