package com.vsofo.applet.pigfarmstat.vo.monthly;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: wangtao
 * @date: 2020/5/29
 * 圆柱图需要的数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColumnarVo implements MIChar,Comparable<ColumnarVo>{
    private Integer organId;
    private String organName;
    private String month;
    private int value;
    private int order;

    @Override
    public int compareTo(ColumnarVo o) {
        return order-o.getOrder();
    }
}
