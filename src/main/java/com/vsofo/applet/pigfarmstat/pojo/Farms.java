package com.vsofo.applet.pigfarmstat.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.beans.Transient;

/**
 * @author: wangtao
 * @date: 2020/5/28
 */
@Data
public class Farms {
    /**
     * 养殖场ID
     */
    private Integer farmsId;
    /**
     * 养殖场名称
     */
    private String farmName;
    /**
     * 是否停用
     */
    private String isTop;
    /**
     * 是否删除
     */
    private String isDeleted;

    
    private String chineseSpell;
}
