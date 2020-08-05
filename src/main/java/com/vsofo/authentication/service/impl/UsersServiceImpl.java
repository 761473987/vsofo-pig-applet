package com.vsofo.authentication.service.impl;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.vsofo.authentication.entity.po.RolePO;
import com.vsofo.authentication.entity.po.UserPO;
import com.vsofo.authentication.mapper.UserMapper;
import com.vsofo.authentication.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@DS("auth")
public class UsersServiceImpl implements UsersService {

    @Autowired
    UserMapper userMapper;


    @Override
    public RolePO findAllByRole(long roleId) {
        return userMapper.findAllByRole(roleId);
    }


    @Override
    public List<UserPO> getOrganByLoginName(String loginName) {
        List<UserPO> list = userMapper.getOrganByLoginName(loginName);
        return list == null ? new ArrayList<>() : list;
    }

    @Override
    public List<UserPO> getFarmsByLoginName(String loginName) {
        List<UserPO> list = userMapper.getFarmsByLoginName(loginName);
        return list == null ? new ArrayList<>() : list;
    }
}
