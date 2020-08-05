package com.vsofo.applet.pigfarmstat.vo.monthly;

import lombok.Data;

/**
 * @author: wangtao
 * @date: 2020/5/29
 * 饼图
 */
@Data
public class BreadVo implements MIChar{
    private Integer organId;
    private String item;
    private int value;
    private String const1;
    private double ratio;
    private int dataType;
}
