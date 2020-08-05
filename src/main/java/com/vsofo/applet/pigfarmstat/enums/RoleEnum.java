package com.vsofo.applet.pigfarmstat.enums;

import lombok.Getter;

/**
 * @author: wangtao
 * @date: 2020/6/1
 */
@Getter
public enum RoleEnum {
    ADMIN(1,"ADMIN","超级管理员"),
    REGION(2,"REGION","大区管理员"),
    USER(3,"USER","普通用户");

    RoleEnum(int roleId, String role, String dec) {
        this.roleId = roleId;
        this.role = role;
        this.dec = dec;
    }

    private int roleId;
    private String role;
    private String dec;
}
