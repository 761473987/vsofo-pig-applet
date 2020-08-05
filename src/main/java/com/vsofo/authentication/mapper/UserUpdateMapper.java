package com.vsofo.authentication.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author lituo
 * @version 1.0
 * @date 2020/5/15  13:46
 * @description 用户数据访问层
 */
@Mapper
public interface UserUpdateMapper {
    /**
     * 根据用户名查询密码盐
     * @param username 用户名
     * @return
     */
    String getPasswordSalt(String username);

    Integer updateUserPassword(@Param("loginName") String loginName,@Param("password") String password);

    Integer getPasswordInLoginName(@Param("loginName") String loginName, @Param("password") String password);

}