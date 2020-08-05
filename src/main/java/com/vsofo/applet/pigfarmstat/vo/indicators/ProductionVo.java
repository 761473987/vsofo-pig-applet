package com.vsofo.applet.pigfarmstat.vo.indicators;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: wangtao
 * @date: 2020/6/2
 */
@Data
@AllArgsConstructor
public class ProductionVo {
    private String id;
    private String menuName;
    private String dataValue;
    private String url;
}
