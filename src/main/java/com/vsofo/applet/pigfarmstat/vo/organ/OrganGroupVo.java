package com.vsofo.applet.pigfarmstat.vo.organ;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @author: wangtao
 * @date: 2020/6/1
 */
@Data
@AllArgsConstructor
public class OrganGroupVo {
    private String chineseSpell;
    private List<OrganVo> list;
}
