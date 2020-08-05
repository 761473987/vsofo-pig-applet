package com.vsofo.applet.pigfarmstat.dos.daily;

import com.vsofo.applet.pigfarmstat.vo.daily.IChar;
import lombok.Data;

/**
 * @author: wangtao
 * @date: 2020/5/28
 * 当前存栏柱状图
 */
@Data
public class HistogramDaily {
    private Integer organId;
    private int boar;
    private int sow;
    private int hog;
    private String organName;
}
