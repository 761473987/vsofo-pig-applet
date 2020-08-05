package com.vsofo.applet.pigfarmstat.dto.pigSysn;

import lombok.Data;

/**
 * 同步放养部的数据
 */
@Data
public class FybDto {
    private String date;
    private String departmentId;
    private String departmentName;
    private String pigType;
    private Long value;
    private String dataType;
}
