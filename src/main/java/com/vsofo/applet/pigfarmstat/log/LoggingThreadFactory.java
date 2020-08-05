package com.vsofo.applet.pigfarmstat.log;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class LoggingThreadFactory implements ThreadFactory {

    private final AtomicInteger threadNumber = new AtomicInteger(1);

    @Override
    public Thread newThread(Runnable r) {
        String name = "thread_logging_" + threadNumber.getAndIncrement();
        Thread thread = new Thread(r, name);
        return thread;
    }
}
