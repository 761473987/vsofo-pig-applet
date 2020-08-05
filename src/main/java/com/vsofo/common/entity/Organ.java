package com.vsofo.common.entity;

import java.util.Date;

/**
 * @author: wangtao
 * @date: 2020/5/19
 */
public class Organ {
    private Integer organID;
    private Integer dealerID;
    private Integer parentOrganID;
    private String organName;
    private String organCode;
    private String organIntro;
    private String orderID;
    private String isClosed;
    private String isDeleted;
    private Date addTime;
    private String userID;

    public Integer getOrganID() {
        return organID;
    }

    public void setOrganID(Integer organID) {
        this.organID = organID;
    }

    public Integer getDealerID() {
        return dealerID;
    }

    public void setDealerID(Integer dealerID) {
        this.dealerID = dealerID;
    }

    public Integer getParentOrganID() {
        return parentOrganID;
    }

    public void setParentOrganID(Integer parentOrganID) {
        this.parentOrganID = parentOrganID;
    }

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName;
    }

    public String getOrganCode() {
        return organCode;
    }

    public void setOrganCode(String organCode) {
        this.organCode = organCode;
    }

    public String getOrganIntro() {
        return organIntro;
    }

    public void setOrganIntro(String organIntro) {
        this.organIntro = organIntro;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getIsClosed() {
        return isClosed;
    }

    public void setIsClosed(String isClosed) {
        this.isClosed = isClosed;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
