package com.vsofo.authentication.entity.po;

import org.springframework.util.StringUtils;

import java.beans.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author lituo
 * @version 1.0
 * @date 2020/5/15  18:05
 * @description 角色表
 */
public class RolePO implements Serializable {

    private long roleId;    //角色id

    private long dealerId;  //商家id

    private String roleName;    //角色名称

    private String role;   //角色说明

    private boolean isAdmin;    //是否是管理员

    private Date createTime;    //创建时间

    private boolean isShow;     //是否显示

    private boolean isDeleted;  //是否删除

    private List<AuthorityPO> authoritys;   //权限信息

    /**
     * 登录用户有权限的url
     *
     * @return
     */
    @Transient
    public Set<String> getPermissions() {
        Set<String> urls = new HashSet<>();
        for (AuthorityPO authority : authoritys) {
            String url = authority.getUrl();
            if (!StringUtils.isEmpty(url)) {
                urls.add(url);
            }
        }
        return urls;
    }

    public RolePO() {
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public long getDealerId() {
        return dealerId;
    }

    public void setDealerId(long dealerId) {
        this.dealerId = dealerId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public List<AuthorityPO> getAuthoritys() {
        return authoritys;
    }

    public void setAuthoritys(List<AuthorityPO> authoritys) {
        this.authoritys = authoritys;
    }

    public RolePO(long roleId, long dealerId, String roleName, String role, boolean isAdmin, Date createTime, boolean isShow, boolean isDeleted, List<AuthorityPO> authoritys) {
        this.roleId = roleId;
        this.dealerId = dealerId;
        this.roleName = roleName;
        this.role = role;
        this.isAdmin = isAdmin;
        this.createTime = createTime;
        this.isShow = isShow;
        this.isDeleted = isDeleted;
        this.authoritys = authoritys;
    }

    @Override
    public String toString() {
        return "RolePO{" +
                "roleId=" + roleId +
                ", dealerId=" + dealerId +
                ", roleName='" + roleName + '\'' +
                ", roleIntro='" + role + '\'' +
                ", isAdmin=" + isAdmin +
                ", createTime=" + createTime +
                ", isShow=" + isShow +
                ", isDeleted=" + isDeleted +
                ", authoritys=" + authoritys +
                '}';
    }
}
