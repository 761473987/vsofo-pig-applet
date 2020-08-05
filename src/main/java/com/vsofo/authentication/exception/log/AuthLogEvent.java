package com.vsofo.authentication.exception.log;

import org.springframework.context.ApplicationEvent;

/**
 * @author lituo
 * @version 1.0
 * @date 2020/6/19  10:03
 * @description 自定义事件
 */
public class AuthLogEvent extends ApplicationEvent {

    public AuthLogEvent(AuthLog authLog) {
        super(authLog);
    }

}
