package com.vsofo.authentication.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.vsofo.authentication.exception.annotation.AuthCheck;
import com.vsofo.authentication.mapper.UserUpdateMapper;
import com.vsofo.authentication.service.UsersModifyService;
import com.vsofo.common.utils.MD5Util;
import com.vsofo.common.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * @author lituo
 * @version 1.0
 * @date 2020/7/6 14:25:52
 * @description
 */
@Service
@Slf4j
@DS("slave_2")
public class UsersModifyServiceImpl implements UsersModifyService {

    @Autowired
    UserUpdateMapper userUpdateMapper;

    @Autowired
    UsersDetailsServiceImpl usersDetailsService;

    /**
     * 修改密码
     * @param accessToken
     * @param oldPassword
     * @param password
     * @return 1：修改成功   -1：原密码错误  -2：修改失败
     * @throws Exception
     */
    @Override
    @AuthCheck(descrption = "修改密码")
    public Integer updateUserPassword(String accessToken,String oldPassword, String password) throws Exception {
        String loginName= TokenUtils.parseJWT(accessToken);
        String passwordSalt = userUpdateMapper.getPasswordSalt(loginName);  //从数据库拿到盐
        oldPassword= MD5Util.Md5Encoding(oldPassword + passwordSalt);
        if (userUpdateMapper.getPasswordInLoginName(loginName, oldPassword) < 1) {
            return -1;
        }
        password = MD5Util.Md5Encoding(password + passwordSalt);
        log.info("修改用户账号:"+loginName);
        if(userUpdateMapper.updateUserPassword(loginName,password)==1){
            usersDetailsService.delCache(loginName);
            return 1;
        }
        return -3;
    }



}
