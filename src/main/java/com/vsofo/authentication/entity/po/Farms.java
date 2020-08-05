package com.vsofo.authentication.entity.po;

import java.io.Serializable;

/**
 * @author lituo
 * @version 1.0
 * @date 2020/6/1 11:07
 * @description 养殖场
 */

public class Farms implements Serializable {

    private long farmID;
    private long organID;
    private String farmName;

    @Override
    public String toString() {
        return "Farms{" +
                "farmID=" + farmID +
                ", organID=" + organID +
                ", farmName='" + farmName + '\'' +
                '}';
    }

    public Farms(long farmID, long organID, String farmName) {
        this.farmID = farmID;
        this.organID = organID;
        this.farmName = farmName;
    }

    public Farms() {
    }

    public long getFarmID() {
        return farmID;
    }

    public void setFarmID(long farmID) {
        this.farmID = farmID;
    }

    public long getOrganID() {
        return organID;
    }

    public void setOrganID(long organID) {
        this.organID = organID;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }
}
