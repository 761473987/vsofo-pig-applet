package com.vsofo.applet.pigfarmstat.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("WeChat_Fms_Farm")
@Data
public class WeChatFmsFarm {
    @TableField("ID")
    private Long id;
    @TableField("FarmID")
    private Long farmId;
    @TableField("FMSDepartmentID")
    private Long FmsDepartmentId;
    @TableField("Explain")
    private String explain;
}
