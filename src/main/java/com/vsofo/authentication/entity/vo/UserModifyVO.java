package com.vsofo.authentication.entity.vo;


/**
 * @author lituo
 * @version 1.0
 * @date 2020/7/8 14:16:27
 * @description
 */
public class UserModifyVO {

    private String oldPassword;

    private String password;

    private String passwordVerify;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordVerify() {
        return passwordVerify;
    }

    public void setPasswordVerify(String passwordVerify) {
        this.passwordVerify = passwordVerify;
    }
}
