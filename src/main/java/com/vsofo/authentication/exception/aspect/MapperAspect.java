package com.vsofo.authentication.exception.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author lituo
 * @version 1.0
 * @date 2020/6/30 09:21:04
 * @description SQL语句执行时间
 */
@Aspect
@Component
@Slf4j
public class MapperAspect {

    /**
     * 监控com.vsofo.mapper包
     */
    @Pointcut("execution(* com.vsofo.authentication.mapper..*(..))")
    private void pointCutMethod() {
    }

    /**
     * 声明环绕通知
     * @param p
     * @return
     * @throws Throwable
     */
    @Around("pointCutMethod()")
    public Object doAround(ProceedingJoinPoint p) throws Throwable {
        long begin = System.nanoTime();
        Object obj = p.proceed();
        long end = System.nanoTime();
        log.info("调用Mapper方法：{}，参数：{}，执行耗时：{}纳秒，耗时：{}毫秒",
                p.getSignature().toString(), Arrays.toString(p.getArgs()),
                (end - begin), (end - begin) / 1000000);
        return obj;
    }
}
