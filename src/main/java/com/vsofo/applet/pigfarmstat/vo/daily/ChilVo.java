package com.vsofo.applet.pigfarmstat.vo.daily;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: wangtao
 * @date: 2020/5/28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChilVo {
    private String id;
    private String menuName;
    private Integer dataValue;
    private String url;
}
