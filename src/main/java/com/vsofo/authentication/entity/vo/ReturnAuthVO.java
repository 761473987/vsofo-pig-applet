package com.vsofo.authentication.entity.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author lituo
 * @version 1.0
 * @date 2020/7/22 17:50:01
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReturnAuthVO {

    private String openId;

    private String roleName;

    private String accountName;

    private String data;

    private long farmID;

    private String farmName;

    private long organId;

    private String organName;

    private String headImg;

    private long role;

    private Integer verifyCode;

    private boolean verifyFlag;

}
