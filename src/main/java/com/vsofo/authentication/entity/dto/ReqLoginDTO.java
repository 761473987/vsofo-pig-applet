package com.vsofo.authentication.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqLoginDTO {

    private String account;

    private String username;

    private String password;

    private String passwordSalt;

    private String wxCode;

    private String wxAppId;

    private String wxSecret;
}
