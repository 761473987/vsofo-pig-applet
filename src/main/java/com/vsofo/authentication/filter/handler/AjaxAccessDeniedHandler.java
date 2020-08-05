package com.vsofo.authentication.filter.handler;

import com.vsofo.authentication.entity.Result;
import com.vsofo.authentication.exception.annotation.AuthCheck;
import com.vsofo.common.constants.enums.AuthCode;
import com.vsofo.common.utils.ResponseUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lituo
 * @version 1.0
 * @date 2020/5/18 11:00
 * @description
 */
@Component
public class AjaxAccessDeniedHandler  implements AccessDeniedHandler {

    /**
     * 权限控制器
     * @param httpServletRequest
     * @param response
     * @param e
     * @throws IOException
     * @throws ServletException
     */
    @Override
    @AuthCheck(descrption = "权限")
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse response, AccessDeniedException e) throws IOException , ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        ResponseUtil.out(response, new Result(AuthCode.PERMISSIONS_FAIL.getCode(), AuthCode.PERMISSIONS_FAIL.getMessage()));
    }
}
