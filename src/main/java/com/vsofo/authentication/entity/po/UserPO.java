package com.vsofo.authentication.entity.po;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.beans.Transient;
import java.io.Serializable;
import java.util.*;

/**
 * @author lituo
 * @version 1.0
 * @date 2020/5/15  18:05
 * @description 用户表
 */
public class UserPO implements UserDetails, Serializable {

    private long userId;   //用户id

    private String loginName;   //登录名

    private String username;    //用户名

    private String password;    //密码

    private String passwordSalt;        //盐值

    private Date createTime;        //创建时间

    private boolean enabled;       //是否可用

    private boolean IsDeleted;      //是否删除

    private Date updateTime;       //修改时间

    private Integer shouldChangePasswordOnNextLogin;  //是否是第一次登陆

    public Integer getShouldChangePasswordOnNextLogin() {
        return shouldChangePasswordOnNextLogin;
    }

    public void setShouldChangePasswordOnNextLogin(Integer shouldChangePasswordOnNextLogin) {
        this.shouldChangePasswordOnNextLogin = shouldChangePasswordOnNextLogin;
    }

    private List<RolePO> roles;    //角色列表

    private List<Organ> organs;

    private List<Farms> farms;

    public UserPO() {
    }

    public List<Organ> getOrgans() {
        return organs;
    }

    public void setOrgans(List<Organ> organs) {
        this.organs = organs;
    }

    public List<Farms> getFarms() {
        return farms;
    }

    public void setFarms(List<Farms> farms) {
        this.farms = farms;
    }

    /**
     * 获取角色的roleid
     * @return
     */
    @Transient
    public long getRoleId(){
        long roleid=0;
        if (roles != null) {
            for (RolePO roles : roles) {
                roleid= roles.getRoleId();
            }
        }
        return roleid;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<RolePO> roles = getRoles();
        for (RolePO role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return authorities;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }


    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public List<RolePO> getRoles() {
        return roles;
    }

    public void setRoles(List<RolePO> roles) {
        this.roles = roles;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }



    public boolean isDeleted() {
        return IsDeleted;
    }

    public void setDeleted(boolean deleted) {
        IsDeleted = deleted;
    }


    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "UserPO{" +
                "userId=" + userId +
                ", loginName='" + loginName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", passwordSalt='" + passwordSalt + '\'' +
                ", createTime=" + createTime +
                ", enabled=" + enabled +
                ", IsDeleted=" + IsDeleted +
                ", updateTime=" + updateTime +
                ", roles=" + roles +
                '}';
    }
}
