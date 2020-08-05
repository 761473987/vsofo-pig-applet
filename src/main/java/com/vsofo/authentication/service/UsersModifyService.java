package com.vsofo.authentication.service;

/**
 * @author lituo
 * @version 1.0
 * @date 2020/7/6 14:27:12
 * @description 用户service
 */
public interface UsersModifyService {


    Integer updateUserPassword(String loginName,String oldPassword,String password)  throws Exception;

}
