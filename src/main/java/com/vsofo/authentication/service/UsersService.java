package com.vsofo.authentication.service;

import com.vsofo.authentication.entity.po.RolePO;
import com.vsofo.authentication.entity.po.UserPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UsersService {

    /**
     * 查询所有访问资源
     * @param roleId 角色ID
     * @return
     */
    RolePO findAllByRole(long roleId);

    List<UserPO> getOrganByLoginName(@Param("loginName") String loginName);

    List<UserPO> getFarmsByLoginName(@Param("loginName") String loginName);
}
