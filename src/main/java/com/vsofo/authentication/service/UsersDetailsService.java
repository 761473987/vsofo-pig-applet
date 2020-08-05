package com.vsofo.authentication.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.vsofo.authentication.entity.po.UserPO;
import com.vsofo.authentication.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author lituo
 * @version 1.0
 * @date 2020/5/15 13:54
 * @description  用户认证
 */
public interface UsersDetailsService {

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
