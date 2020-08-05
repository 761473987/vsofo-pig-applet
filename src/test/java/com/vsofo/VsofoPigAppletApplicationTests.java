package com.vsofo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vsofo.authentication.entity.vo.UserModifyVO;
import com.vsofo.common.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.*;

@SpringBootTest(classes = VsofoPigAppletApplicationTests.class)
@RunWith(SpringRunner.class)
@Slf4j
public class VsofoPigAppletApplicationTests {

    @Value("${wx.appId}")
    private String appId;
    @Value("${wx.appSecret}")
    private String appSecret;
    @Value("${wx.getUrl}")
    private String getUrl;

    @Test
    public void ttt() {
        String code = "0237JHX90iXwJB1ZaOZ90Y5HX907JHXA";
        String wxspAppid = appId;
        String wxspSecret = appSecret;
        // 授权（必填）
        String grant_type = "authorization_code";
        // 请求参数
        String params = "appid=" + wxspAppid + "&secret=" + wxspSecret + "&js_code=" + code + "&grant_type="
                + grant_type;
        log.info("解析code请求参数：" + params);
        // 发送请求
        String sr = HttpClientUtil.doGet(getUrl, params);
        System.out.println(getUrl);
        // 解析相应内容（转换成json对象）
        System.out.println(sr);
        JSONObject json = JSONObject.parseObject(sr);
        log.info("解析code请求结果:" + json.toString());
        // 获取会话密钥（session_key）
        String session_key = json.getString("session_key");
        String openid = json.getString("openid");
        log.info("openid:" + openid);
    }

    @Test
    public void aaa() {
        ExecutorService es = new ThreadPoolExecutor(50, 100,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue
                <Runnable>(100000));
        ThreadPoolExecutor tpe = ((ThreadPoolExecutor) es);
        System.out.println();
        int queueSize = tpe.getQueue().size();
        System.out.println("当前排队线程数：" + queueSize);
        int activeCount = tpe.getActiveCount();
        System.out.println("当前活动线程数：" + activeCount);
        long completedTaskCount = tpe.getCompletedTaskCount();
        System.out.println("执行完成线程数：" + completedTaskCount);
        long taskCount = tpe.getTaskCount();
        System.out.println("总线程数：" + taskCount);
    }

    @Test
    public void bbb() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(3);
        CountDownLatch latch = new CountDownLatch(3);
        System.out.println(latch.getCount());
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            Runnable runnable = new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        latch.countDown();
                    }
                }
            };
            service.execute(runnable);
        }
        latch.await();
        System.out.println(latch.getCount());
        System.out.println(String.format("耗时：%sms", System.currentTimeMillis() - start));
    }

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private String getString() {
        return threadLocal.get();
    }

    private void setString(String string) {
        threadLocal.set(string);
    }

    @Test
    public void ddd() {
        int threads = 10;
        VsofoPigAppletApplicationTests demo = new VsofoPigAppletApplicationTests();
        CountDownLatch countDownLatch = new CountDownLatch(threads);
        for (int i = 0; i < threads; i++) {
            Thread thread = new Thread(() -> {
                demo.setString(Thread.currentThread().getName());
                System.out.println(demo.getString());
                countDownLatch.countDown();
            }, "thread - " + i);
            thread.start();
        }
    }


    @Test
    public void eee() {
        String str = "{\"oldPassword\":\"a1234567\",\"password\":\"a1234567\",\"passwordVerify\":\"a1234567\"}";
        UserModifyVO userModify = JSON.parseObject(str, UserModifyVO.class);
        System.out.println(userModify.getOldPassword());
    }

}
