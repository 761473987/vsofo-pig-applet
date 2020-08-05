package com.vsofo.authentication.filter;

import com.vsofo.authentication.entity.po.UserPO;
import com.vsofo.authentication.exception.annotation.AuthCheck;
import com.vsofo.authentication.service.impl.UsersDetailsServiceImpl;
import com.vsofo.common.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * @author lituo
 * @version 1.0
 * @date 2020/5/20  10:09
 * @description 自定义认证器
 */
@Component
public class LoginAuthenticationProvider implements AuthenticationProvider {


    @Autowired
    UsersDetailsServiceImpl usersDetailsService;


    /**
     * 用户认证
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    @AuthCheck(descrption = "登录密码效验")
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if ( authentication.getPrincipal() == null) {
            throw new BadCredentialsException("用户不存在！");
        }
        String userName = (String) authentication.getPrincipal(); //拿到用户名
        String password = (String) authentication.getCredentials(); //拿到密码
        UserDetails userDetails = usersDetailsService.loadUserByUsername(userName);
        UserPO users = (UserPO) userDetails;
        try {
            //对密码进行sha1加密处理
            password = MD5Util.Md5Encoding(password + users.getPasswordSalt());
        } catch (Exception e) {
            throw new BadCredentialsException("密码错误");
        }
        //开始用户认证
        if (userDetails.getPassword().equals(password)) {
            /*if (userDetails.getAuthorities().size() == 0) {
                throw new BadCredentialsException("-1");
            }*/
            return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        } else {
            throw new BadCredentialsException("密码错误");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
