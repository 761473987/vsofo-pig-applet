package com.vsofo.applet.pigfarmstat.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: wangtao
 * @date: 2020/5/28
 */
@AllArgsConstructor
@Getter
public enum TitleEnum {
    CURRENT_AMOUNT("1","当前存栏"),
    BOAR_HISTOGRAM("2","公猪存栏"),
    HOG_HISTOGRAM("3","商品猪存栏"),
    SOW_HISTOGRAM("4","母猪存栏"),
    GARDEN("5","大区存栏占比"),
    HOG_SUCH("6","商品猪月度出栏(头)"),
    HOG_CUMULATIVE_SUCH("9","商品猪累计出栏(头)"),
    HOG_CONTRAST("7","商品猪出栏数据对比"),
    INDICATORS("8","系统指标")
    ;

    private String code;
    private String name;
}
