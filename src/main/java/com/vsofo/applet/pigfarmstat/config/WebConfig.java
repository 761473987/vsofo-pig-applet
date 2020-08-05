package com.vsofo.applet.pigfarmstat.config;
 
import com.vsofo.applet.pigfarmstat.interceptor.WebFilterParam;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 

@Configuration
public class WebConfig{
 

    @Bean
    public FilterRegistrationBean WebFilter(){
        //配置过滤器
        FilterRegistrationBean frBean = new FilterRegistrationBean();
        frBean.setFilter(new WebFilterParam());
        frBean.addUrlPatterns("/*");
        frBean.setName("webFilter");
        //springBoot会按照order值的大小，从小到大的顺序来依次过滤。
        frBean.setOrder(0);
        return frBean;
    }

}