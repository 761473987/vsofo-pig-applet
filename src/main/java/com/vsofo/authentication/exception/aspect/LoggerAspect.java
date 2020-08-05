package com.vsofo.authentication.exception.aspect;

import com.vsofo.authentication.exception.log.AuthLog;
import com.vsofo.authentication.exception.log.AuthLogEvent;
import com.vsofo.common.utils.IpAddressUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

/**
 * @author lituo
 * @version 1.0
 * @date 2020/6/19  10:05
 * @description 日志切面类
 */
@Aspect
@Component
@Slf4j
public class LoggerAspect {

    //private ThreadLocal<AuthLog> authLog = new ThreadLocal<>();

    @Autowired
    private ApplicationContext applicationContext;

    @Pointcut("@annotation(com.vsofo.authentication.exception.annotation.AuthCheck))")
    public void loggerAspect() {
    }

    public LoggerAspect() {
    }

    /**
     * 异常通知
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "loggerAspect()", throwing = "e",argNames = "joinPoint,e")
    public void doAfterThrowable(JoinPoint joinPoint, Throwable e) throws UnknownHostException {
        AuthLog authLogPO=new AuthLog();
        authLogPO.setLevel("error");
        authLogPO.setAppId(1);
        authLogPO.setErrorMsg(e.getMessage());
        authLogPO.setCreateTime(new Date());
        authLogPO.setUserName("");
        authLogPO.setReqInterface(joinPoint.getSignature().getName());
        authLogPO.setFunctionName("登录");
        authLogPO.setClassName(joinPoint.getTarget().getClass().getName());
        authLogPO.setHostAddress("");
        applicationContext.publishEvent(new AuthLogEvent(authLogPO));
//        LogService bean = SpringBeanUtil.getBean(LogService.class);
    }
}
