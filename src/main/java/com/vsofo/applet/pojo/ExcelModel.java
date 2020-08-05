package com.vsofo.applet.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import java.io.Serializable;

public class ExcelModel extends BaseRowModel implements Serializable {
    @ExcelProperty(value = "养殖场名称", index = 0)
    private String farmName;
    @ExcelProperty(value = "公司名称", index = 1)
    private String companyFullName;
    @ExcelProperty(value = "公猪存栏", index = 2)
    private int boarNum;
    @ExcelProperty(value = "母猪存栏", index = 3)
    private int sowNum;
    @ExcelProperty(value = "商品猪存栏", index = 4)
    private int hogNum;
    @ExcelProperty(value = "存栏总和", index = 5)
    private int sum;
    @ExcelProperty(value = "商品猪出栏", index = 6)
    private int pigSuch;

    @ExcelProperty(value = "来源",index = 7)
    private String appId;

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public String getCompanyFullName() {
        return companyFullName;
    }

    public void setCompanyFullName(String companyFullName) {
        this.companyFullName = companyFullName;
    }

    public int getBoarNum() {
        return boarNum;
    }

    public void setBoarNum(int boarNum) {
        this.boarNum = boarNum;
    }

    public int getSowNum() {
        return sowNum;
    }

    public void setSowNum(int sowNum) {
        this.sowNum = sowNum;
    }

    public int getHogNum() {
        return hogNum;
    }

    public void setHogNum(int hogNum) {
        this.hogNum = hogNum;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getPigSuch() {
        return pigSuch;
    }

    public void setPigSuch(int pigSuch) {
        this.pigSuch = pigSuch;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}
