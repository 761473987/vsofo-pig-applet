package com.vsofo.applet.pigfarmstat.dos.indicators;

import lombok.Data;

/**
 * 大量的这种包装类，所以产生了此类
 * month作为key  value作为作为value的实体
 */
@Data
public class MonthValueDo {
    private int month;
    private double value;
}
