package com.vsofo.applet.pigfarmstat.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: wangtao
 * @date: 2020/5/28
 */
@AllArgsConstructor
@Getter
public enum UnitEnum {
    HEAD("1","头");
    private String code;
    private String name;
}
