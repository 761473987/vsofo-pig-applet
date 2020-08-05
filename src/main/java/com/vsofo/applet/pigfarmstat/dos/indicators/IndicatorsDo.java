package com.vsofo.applet.pigfarmstat.dos.indicators;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: wangtao
 * @date: 2020/6/2
 */
@Data
@AllArgsConstructor
public class IndicatorsDo implements Cloneable{
    private int month;
    private double reality;
    private String dataType;
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
