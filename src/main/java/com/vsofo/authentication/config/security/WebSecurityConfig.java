package com.vsofo.authentication.config.security;

import com.vsofo.authentication.filter.AuthenticationTokenFilter;
import com.vsofo.authentication.filter.LoginAuthenticationProvider;
import com.vsofo.authentication.filter.handler.*;
import com.vsofo.common.constants.UrlConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * @author lituo
 * @version 1.0
 * @date 2020/5/12 17:54
 * @description 登录用户认证
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(2)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private Logger logger= LoggerFactory.getLogger(WebSecurityConfig.class);

    @Resource
    AjaxAuthenticationSuccessHandler authenticationSuccessHandler;  //登陆请求成功处理，返回json

    @Resource
    AjaxAuthenticationFailureHandler authenticationFailureHandler;  //登录请求失败处理，返回json

    @Resource
    AjaxLogoutSuccessHandler logoutSuccessHandler;   //退出登录

    @Resource
    AuthenticationTokenFilter authenticationTokenFilter; // JWT 拦截器

    @Autowired
    AjaxAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    AjaxAccessDeniedHandler accessDeniedHandler;

   // @Autowired
   // MyUsernamePasswordAuthenticationFilter myAuthenticationFilter;



    //自定义认证器
    @Bean
    LoginAuthenticationProvider loginAuthenticationProvider() {
        return new LoginAuthenticationProvider();
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(loginAuthenticationProvider());
    }


    /**
     * 放开静态资源访问
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }

    /**
     * 拦截请求，用户认证
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/sysn/**");
        logger.info("*************开始用户认证*************");
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/sysn/**").permitAll().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .anyRequest()
                .access("@rbacservice.hasPermission(request,authentication)") // RBAC 动态 url 认证

                .and()
                .httpBasic().authenticationEntryPoint(authenticationEntryPoint)

                .and()
                .formLogin()
                .usernameParameter("account")
                .loginPage(UrlConstant.LOGIN_URL)                     //表单登录地址
                .loginProcessingUrl(UrlConstant.DOLOGIN_URL)
                .successHandler(authenticationSuccessHandler)        //成功请求处理
                .failureHandler(authenticationFailureHandler)        //失败请求处理
                .permitAll()

                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)    //没有权限，返回json

                .and()
                .logout()
                .logoutUrl(UrlConstant.LOGOUT_URL)   //退出登录路径
                .logoutSuccessHandler(logoutSuccessHandler)
                .permitAll()

                .and()
                .httpBasic();

        http.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }



}
