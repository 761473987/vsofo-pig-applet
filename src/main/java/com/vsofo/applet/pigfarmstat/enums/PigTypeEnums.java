package com.vsofo.applet.pigfarmstat.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 区分页面上的猪只类型
 * @author: wangtao
 * @date: 2020/5/28
 */
@Getter
@AllArgsConstructor
public enum  PigTypeEnums {
    PIG_1("1","种公猪"),
    PIG_2("2","种母猪"),
    PIG_3("3","后备种公猪"),
    PIG_4("4","后备种母猪"),
    PIG_5("5","商品猪"),
    BOAR("1","../inventorydetail/index?id=1&pigType=1&statDate=","公猪"),
    SOW("2","../inventorydetail/index?id=2&pigType=2&statDate=","母猪"),
    HOG("5","../inventorydetail/index?id=5&pigType=5&statDate=","商品猪")
    ;
    private String id;
    private String url;
    private String dec;


    PigTypeEnums(String id, String dec) {
        this.id = id;
        this.dec = dec;
    }
}
