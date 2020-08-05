package com.vsofo.applet.pigfarmstat.vo.daily;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 存栏详情值
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AmountDailyDetailValueVo implements Cloneable{
    private String pigType;//1,3公猪，2，4母猪，5商品猪
    private String pigTypeName;
    private String value;

    @Override
    public AmountDailyDetailValueVo clone() throws CloneNotSupportedException {
        return (AmountDailyDetailValueVo) super.clone();
    }
}
