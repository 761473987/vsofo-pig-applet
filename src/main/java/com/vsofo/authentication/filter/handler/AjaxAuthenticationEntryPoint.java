package com.vsofo.authentication.filter.handler;

import com.vsofo.authentication.entity.Result;
import com.vsofo.authentication.exception.annotation.AuthCheck;
import com.vsofo.common.constants.enums.AuthCode;
import com.vsofo.common.utils.ResponseUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AjaxAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    @AuthCheck(descrption = "用户访问受保护资源" )
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        ResponseUtil.out(response, new Result(AuthCode.FAIL.getCode(), AuthCode.FAIL.getMessage()));
    }
}