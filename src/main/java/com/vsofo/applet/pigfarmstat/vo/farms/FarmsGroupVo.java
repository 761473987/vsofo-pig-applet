package com.vsofo.applet.pigfarmstat.vo.farms;

import com.vsofo.applet.pigfarmstat.pojo.Farms;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @author: wangtao
 * @date: 2020/5/29
 */
@Data
@AllArgsConstructor
public class FarmsGroupVo {
    private String chineseSpell;
    private List<FarmsVo> list;
}
