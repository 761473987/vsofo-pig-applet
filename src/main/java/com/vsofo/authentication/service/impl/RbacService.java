package com.vsofo.authentication.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.vsofo.authentication.entity.po.RolePO;
import com.vsofo.authentication.entity.po.UserPO;
import com.vsofo.authentication.mapper.UserMapper;
import com.vsofo.authentication.service.impl.UsersServiceImpl;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author lituo
 * @version 1.0
 * @date 2020/7/6 14:25:52
 * @description
 */
@Component("rbacservice")
@DS("auth")
public class RbacService {

    @Autowired
    UsersServiceImpl usersService;

    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object userInfo = authentication.getPrincipal();
        boolean hasPermission = false;
        if (userInfo instanceof UserDetails) {
            RolePO roles = usersService.findAllByRole(((UserPO) userInfo).getRoleId());
            //获取资源
            Set<String> urls = roles.getPermissions();
            AntPathMatcher antPathMatcher = new AntPathMatcher();
            for (String url : urls) {
                if (antPathMatcher.match(url, request.getRequestURI())) {
                    hasPermission = true;
                    break;
                }
            }
            return hasPermission;
        } else {
            return false;
        }
    }

}

