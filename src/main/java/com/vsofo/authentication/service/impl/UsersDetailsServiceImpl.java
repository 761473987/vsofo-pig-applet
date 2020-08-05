package com.vsofo.authentication.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.vsofo.authentication.entity.po.UserPO;
import com.vsofo.authentication.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import com.vsofo.authentication.service.UsersDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author lituo
 * @version 1.0
 * @date 2020/5/15 13:54
 * @description  用户认证
 */
@Service
@DS("auth")
@Slf4j
public class UsersDetailsServiceImpl implements  UsersDetailsService{

    @Autowired
    UserMapper userMapper;

    /**
     * 登录用户认证
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    @Cacheable(cacheNames = "user",key = "#username")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserPO user = userMapper.loadUserByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("账户不存在！");
        }
        return user;
    }

    @CacheEvict(cacheNames = "user" ,key="#username")
    public void delCache(String username) {
        log.info("删除用户缓存" + username + "成功！");
    }
}
