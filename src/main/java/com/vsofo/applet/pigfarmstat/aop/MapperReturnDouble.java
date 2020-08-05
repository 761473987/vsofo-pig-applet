package com.vsofo.applet.pigfarmstat.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 将mapper 返回的结果为double类型为空的值替换成0
 */
@Aspect
@Component
@Slf4j
public class MapperReturnDouble {

    @Pointcut("execution(* com.vsofo.applet.pigfarmstat.mapper..*(..))")
    private void pointCutMethod() {
    }

    @Around("pointCutMethod()")
    public Object doAfterReturning(JoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Class<?> returnType = signature.getReturnType();
        ProceedingJoinPoint proceedingJoinPoint = (ProceedingJoinPoint) joinPoint;
        Object proceed = proceedingJoinPoint.proceed();
        if (returnType == Double.class){
            if (proceed == null){
                proceed = 0.0;
            }
        }
        return proceed;
    }
}
