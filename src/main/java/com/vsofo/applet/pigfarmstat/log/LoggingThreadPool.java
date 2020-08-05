package com.vsofo.applet.pigfarmstat.log;

import com.vsofo.applet.utils.SpringBeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.*;

@Slf4j
public class LoggingThreadPool {

    private volatile static LoggingThreadPool INSTANCE;

    private ThreadPoolExecutor threadPool = null;
    /**
     * 日志上报缓冲区队列
     */
    private ArrayBlockingQueue<LogAction> logActionBufferQueue = null;
    /**
     * 去重临时缓存区大小
     */
    private final int maxUniqueTempCacheSize = 128;
    /**
     * 去重缓存区
     */
    private Map<LogAction, Boolean> uniqueTempCacheData;

//    private ILog iLog;

    /**
     * 初始化线程池池，队列，启动日志记录线程操作
     */
    public static void init() {
        getInstance();
    }


    private LoggingThreadPool() {
        int processorNum = Runtime.getRuntime().availableProcessors();
        int loggingThreadNum = processorNum > 2 ? 2 : processorNum;
        this.threadPool = new ThreadPoolExecutor(loggingThreadNum, loggingThreadNum,
                0, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(), new LoggingThreadFactory());
        this.logActionBufferQueue = new ArrayBlockingQueue<>(1024);
        this.uniqueTempCacheData = new ConcurrentHashMap<>(maxUniqueTempCacheSize);
        for (int i = 0; i < loggingThreadNum; i++) {
            this.threadPool.execute(new LoggingThread());
        }
    }

    public static LoggingThreadPool getInstance() {
        if (INSTANCE == null) {
            synchronized (LoggingThreadPool.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LoggingThreadPool();
                }
            }
        }
        return INSTANCE;
    }


    /**
     * 阻塞直至日志队列有日志记录，获取并删除队列队首元素
     *
     * @return
     * @throws InterruptedException
     */
    public LogAction take() throws InterruptedException {
        LogAction logAction = this.logActionBufferQueue.take();
        this.uniqueTempCacheData.remove(logAction);
        return logAction;
    }

    /**
     * 记录日志
     *
     * @return
     */
    public boolean loggingAsync(LogAction logAction) {
        if (logAction != null && (!this.uniqueTempCacheData.containsKey(logAction))) {
            if (this.uniqueTempCacheData.size() > maxUniqueTempCacheSize) {
                //随机移除一个
                this.uniqueTempCacheData.remove(this.uniqueTempCacheData.keySet().iterator().next());
            }
            if (this.logActionBufferQueue.offer(logAction)) {
                //入队成功
                this.uniqueTempCacheData.put(logAction, true);
                return true;
            } else {
                try {
                    ILog iLog = getiLog(logAction);
                    iLog.save(logAction);
                    return true;
                } catch (Exception ex) {
                    log.error("【保存日志】同步错误,{}", logAction, ex);
                }
            }
        }
        log.debug("【记录日志】队列重复[{}]", logAction.toString());
        return false;
    }



    /**
     * 停止日志线程，并持久化日志记录到数据库
     */
    public void shutdownAndForcePersistenceToDB() {
        this.threadPool.shutdown();
        try {
            if (!this.threadPool.awaitTermination(2L, TimeUnit.SECONDS)) {
                tailingLogBufferQueue();
            }
            log.info("【清理保存】complete");
        } catch (InterruptedException e) {
            log.error("【清理保存】停掉logging线程池", e);
            try {
                tailingLogBufferQueue();
            } catch (Exception ex) {
                log.error("【清理保存】tailingLogBufferQueue", ex);
            }
            this.threadPool.shutdownNow();
        }


    }

    private void tailingLogBufferQueue() {
        if (this.logActionBufferQueue.size() > 0) {
            Iterator<LogAction> globalExceptionIterator = this.logActionBufferQueue.iterator();
            while (globalExceptionIterator.hasNext()) {
                LogAction currentLogAction = globalExceptionIterator.next();
                log.info("【清理保存】begin,{}", currentLogAction);
                ILog iLog = getiLog(currentLogAction);
                iLog.save(currentLogAction);
                log.info("【清理保存】success,{}", currentLogAction);
            }
        }
    }


    private String firstLetterLowercase(String simpleName) {
        if (StringUtils.isBlank(simpleName))return simpleName;
        char[] chars = simpleName.toCharArray();
        if (Character.isUpperCase(chars[0])){
            chars[0] = Character.toLowerCase(chars[0]);
        }
        return String.valueOf(chars);
    }

    protected ILog getiLog(LogAction logAction) {
        String simpleName = logAction.getClass().getSimpleName();
        simpleName = firstLetterLowercase(simpleName) + "ServiceImpl";
        //若队列容量不足，异步转同步
        return SpringBeanUtil.getBean(simpleName);
    }
}
