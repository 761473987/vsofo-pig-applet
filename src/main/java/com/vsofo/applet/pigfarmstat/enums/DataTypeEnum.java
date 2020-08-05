package com.vsofo.applet.pigfarmstat.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * @author: wangtao
 * @date: 2020/6/2
 */
@Getter
@AllArgsConstructor
public enum DataTypeEnum {
    LSY_ALL("40","LSY(全群指标)","LSY"),
    PSY_ALL("53","PSY(全群指标)","PSY"),
    MSY("82","MSY","MSY"),
    BREEDING("23","配种分娩率","配种分娩率"),
    PIGLETS("49","仔猪成活率","仔猪成活率"),
    NPD("65","平均母猪NPD","NPD"),
    ;
    private String code;
    private String name;
    private String menuName;
}
