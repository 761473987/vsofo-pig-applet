package com.vsofo.applet.pigfarmstat.comm;

import com.vsofo.applet.exception.GeneralException;
import com.vsofo.applet.pigfarmstat.pojo.User;
import com.vsofo.common.constants.SecurityConstant;
import com.vsofo.common.entity.Result;
import com.vsofo.common.utils.TokenUtils;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取用户信息抽象类
 * @author: wangtao
 * @date: 2020/6/1
 */
public abstract class AbstractController {

    @Autowired
    private HttpServletRequest request;

    public User getUser()  {
        User user = new User();
        String accessToken = request.getHeader(SecurityConstant.HEADER);
        if (StringUtils.isNotBlank(accessToken)){
            if (accessToken.startsWith("Bearer ")){
                String newToken= accessToken.replace("Bearer ", "");
                Integer obj = null;
                String userName =null;
                try {
                    userName = TokenUtils.parseJWT(newToken);
                    Claims claims = TokenUtils.parseBody(newToken);
                    int role = (int) claims.get("roleId");
                    int userId = (int) claims.get("userId");
                    user.setRoleId(role);
                    user.setUsername(userName);
                    user.setEmployeeId(userId);
                } catch (Exception e) {
                    throw new GeneralException(new Result(-2,"请登录"));
                }
            }
        }
        return user;
    }
}
