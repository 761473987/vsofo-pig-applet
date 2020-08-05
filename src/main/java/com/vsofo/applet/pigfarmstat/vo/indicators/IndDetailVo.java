package com.vsofo.applet.pigfarmstat.vo.indicators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class IndDetailVo {
    private String dataType;
    private String dataTypeName;
    private Object reality;
    private boolean isTitle = false;


    public IndDetailVo() {
    }

    public IndDetailVo(String dataType, Object reality) {
        this.dataType = dataType;
        this.reality = reality;
    }

    public IndDetailVo(String dataType, String dataTypeName, Object reality) {
        this.dataType = dataType;
        this.dataTypeName = dataTypeName;
        this.reality = reality;
    }

    public IndDetailVo(String dataType, String dataTypeName, Object reality, boolean isTitle) {
        this.dataType = dataType;
        this.dataTypeName = dataTypeName;
        this.reality = reality;
        this.isTitle = isTitle;
    }
}
