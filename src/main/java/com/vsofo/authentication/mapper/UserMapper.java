package com.vsofo.authentication.mapper;

import com.vsofo.authentication.entity.po.RolePO;
import com.vsofo.authentication.entity.po.UserPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lituo
 * @version 1.0
 * @date 2020/5/15  13:46
 * @description 用户数据访问层
 */
@Mapper
public interface UserMapper {

    /**
     * security认证
     * @param username 用户名
     * @return
     */
    UserPO loadUserByUsername(String username);


    /**
     * 查询所有访问资源
     * @param roleId 角色ID
     * @return
     */
    RolePO findAllByRole(long roleId);

    /**
     * 根据登录名查询所属大区
     * @param loginName
     * @return
     */
    List<UserPO> getOrganByLoginName(@Param("loginName") String loginName);

    /**
     * 根据登录名查询所属养殖场
     * @param loginName
     * @return
     */
    List<UserPO> getFarmsByLoginName(@Param("loginName") String loginName);
}