package com.vsofo.applet.pigfarmstat.pojo;

import lombok.Data;

/**
 * @author: wangtao
 * @date: 2020/6/1
 */
public class User {
    private String username;
    private String role;
    private int roleId;
    private int employeeId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}
