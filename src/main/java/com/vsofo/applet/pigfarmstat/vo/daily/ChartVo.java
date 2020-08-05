package com.vsofo.applet.pigfarmstat.vo.daily;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author: wangtao
 * @date: 2020/5/26
 * 前台图的数据
 */
@Data
@AllArgsConstructor
public class ChartVo {
    private String chartName;
    private String type;
    private List<? extends IChar> data;
}