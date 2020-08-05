package com.vsofo.authentication.entity.po;

import java.util.Date;

/**
 * @author lituo
 * @version 1.0
 * @date 2020/6/9 10:38
 * @description 认证日志处理类
 */
public class AuthLogPO {

    /**
     * 登录异常日志id
     */
    private int id;
    /**
     * 日志级别
     */
    private String level;
    /**
     * 错误信息
     */
    private String errorMsg;
    /**
     * 访问接口
     */
    private String reqInterface;
    /**
     * 记录时间
     */
    private Date createTime;
    /**
     * 功能名
     */
    private String functionName;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 程序id；1:养猪看板小程序 2:物品消毒系统
     */
    private int appId;

    public AuthLogPO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getReqInterface() {
        return reqInterface;
    }

    public void setReqInterface(String reqInterface) {
        this.reqInterface = reqInterface;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    @Override
    public String toString() {
        return "AuthLogPO{" +
                "id=" + id +
                ", level='" + level + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                ", reqInterface='" + reqInterface + '\'' +
                ", createTime=" + createTime +
                ", functionName='" + functionName + '\'' +
                ", userName='" + userName + '\'' +
                ", appId=" + appId +
                '}';
    }

    public AuthLogPO(int id, String level, String errorMsg, String reqInterface, Date createTime, String functionName, String userName, int appId) {
        this.id = id;
        this.level = level;
        this.errorMsg = errorMsg;
        this.reqInterface = reqInterface;
        this.createTime = createTime;
        this.functionName = functionName;
        this.userName = userName;
        this.appId = appId;
    }
}
