package com.vsofo.authentication.exception.log;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lituo
 * @version 1.0
 * @date 2020/6/19  10:01
 * @description 日志异常封装类
 */
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("WeChat_AuthLog")
public class AuthLog implements Serializable {

    /**
     * 登录异常日志id
     */
    @TableId(type = IdType.AUTO)
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

    /**
     * ip地址
     */
    private String hostAddress;

    private String className;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public AuthLog() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHostAddress() {
        return hostAddress;
    }

    public void setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
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

    public AuthLog(int id, String level, String errorMsg, String reqInterface, Date createTime, String functionName, String userName, int appId) {
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
