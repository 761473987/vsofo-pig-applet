package com.vsofo.applet.pigfarmstat.interceptor;


import com.vsofo.applet.pigfarmstat.log.LogAction;
import com.vsofo.applet.pigfarmstat.log.LoggingThreadPool;
import com.vsofo.applet.pojo.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LogTimeInterceptor implements  HandlerInterceptor{
    private Long startTime = null;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        this.startTime = System.currentTimeMillis();
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long time = System.currentTimeMillis() - startTime;
        log.warn("访问接口[{}]:消耗时间{}ms",request.getServletPath(),time);
        if (time > 2000){
            log.warn("---------访问超时时间比较长{}ms----------",time);
        }
    }
}
