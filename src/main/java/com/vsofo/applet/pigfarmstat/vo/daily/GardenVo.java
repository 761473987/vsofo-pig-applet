package com.vsofo.applet.pigfarmstat.vo.daily;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: wangtao
 * @date: 2020/5/29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GardenVo implements IChar{
    private Integer organId;
    private String item;
    private int value;
    private double ratio;
    private String const1;
}
