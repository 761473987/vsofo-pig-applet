package com.vsofo.authentication.exception.log;

import com.vsofo.authentication.exception.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author lituo
 * @version 1.0
 * @date 2020/6/19  10:04
 * @description 日志监听
 */
@Slf4j
@Component
public class AuthLogListener {

    @Autowired
    private LogService logService;

    /**
     * 保存日志到数据库
     * @param event
     */
    @Async
    @Order
    @EventListener(AuthLogEvent.class)
    public void saveSysLog(AuthLogEvent event) {
        AuthLog sysLog = (AuthLog) event.getSource();
        // 保存日志
        logService.save(sysLog);
    }

}
