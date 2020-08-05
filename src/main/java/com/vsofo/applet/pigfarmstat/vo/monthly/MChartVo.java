package com.vsofo.applet.pigfarmstat.vo.monthly;

import com.vsofo.applet.pigfarmstat.vo.daily.IChar;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: wangtao
 * @date: 2020/5/26
 * 前台图的数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MChartVo {
    private String chartName;
    private String type;
    private List<? extends MIChar> data;
}