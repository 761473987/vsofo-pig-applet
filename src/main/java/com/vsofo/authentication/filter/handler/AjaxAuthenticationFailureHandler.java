package com.vsofo.authentication.filter.handler;

import com.vsofo.authentication.entity.Result;
import com.vsofo.authentication.exception.annotation.AuthCheck;
import com.vsofo.common.utils.ResponseUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lituo
 * @version 1.0
 * @date 2020/5/18 11:12
 * @description 登录失败跳转
 */
@Component
public class AjaxAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    @AuthCheck(descrption = "登录失败")
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {

            response.setContentType("application/json;charset=utf-8");
            //错误状态码201
            response.setStatus(HttpServletResponse.SC_OK);
            //错误根据信息
            String msg = "";
            int code = -3;
            //AuthException.save("用户名或密码错误","onAuthenticationFailure",this.getClass().getName());
            if (e.getMessage().equals("-1")) {
                code = -1;
                msg = "权限不足，请联系管理员＞︿＜！！";
            } else if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
                msg = "用户名或密码错误";
            } else if (e instanceof DisabledException) {
                msg = "账户被禁用";
            } else {
                msg = "登录失败";
            }
            ResponseUtil.out(response,new Result<>(code, msg));
    }
}
