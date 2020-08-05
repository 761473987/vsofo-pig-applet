package com.vsofo.applet.pigfarmstat.log;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class LoggingThread implements Runnable {


    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            LogAction logAction = null;
            try {
                //阻塞直至队列里有数据
                LoggingThreadPool instance = LoggingThreadPool.getInstance();
                logAction = instance.take();
                ILog iLog = instance.getiLog(logAction);
                iLog.save(logAction);
            } catch (Exception ex) {
                log.error("【保存日志】异步错误,{}", logAction != null ? logAction.toString() : "", ex);
            }
        }
    }
}
