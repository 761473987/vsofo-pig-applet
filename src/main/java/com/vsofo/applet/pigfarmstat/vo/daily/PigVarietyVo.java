package com.vsofo.applet.pigfarmstat.vo.daily;

import lombok.Data;

/**
 * 品种vo
 */
@Data
public class PigVarietyVo {
    private Integer pigVarietyId;
    private String pigVarietyName;
    private boolean select = false;
}
