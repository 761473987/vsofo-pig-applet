package com.vsofo.applet.pigfarmstat.vo.farms;

import lombok.Data;

import java.beans.Transient;

/**
 * @author: wangtao
 * @date: 2020/5/28
 */
@Data
public class FarmsVo {
    /**
     * 养殖场ID
     */
    private Integer farmId;
    /**
     * 养殖场名称
     */
    private String farmName;


    private String chineseSpell;
}
