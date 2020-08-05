package com.vsofo.applet.pigfarmstat.vo.daily;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: wangtao
 * @date: 2020/5/28
 */
@Data
@AllArgsConstructor
public class PigHistogramVo implements IChar{
    private String area;
    private int value;
}
