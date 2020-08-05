package com.vsofo.authentication.filter.handler;

import com.vsofo.authentication.entity.Result;
import com.vsofo.authentication.exception.annotation.AuthCheck;
import com.vsofo.common.constants.enums.AuthCode;
import com.vsofo.common.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lituo
 * @version 1.0
 * @date 2020/5/27  17:26
 * @description 退出登录
 */
@Component
public class AjaxLogoutSuccessHandler implements LogoutSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(AjaxLogoutSuccessHandler.class);

    @Override
    @AuthCheck(descrption = "退出")
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    //    logger.info("退出成功 ----------> 退出时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        ResponseUtil.out(response, new Result<>(AuthCode.SUCCESS.getCode(), "退出成功！"));
    }
}
