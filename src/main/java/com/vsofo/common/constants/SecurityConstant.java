package com.vsofo.common.constants;

/**
 * @author lituo
 * @version 1.0
 * @date 2020/5/18 15:51:30
 * @description jwt常量
 */
public interface SecurityConstant {

    /**
     * token参数头
     */
    String HEADER = "Authorization";

    /**
     * token分割
     */
    String TOKEN_SPLIT = "Bearer ";

    /**
     * token中自定义权限标识
     */
    String AUTHORITIES = "authorities";
    /**
     * token失效时间
     */
    Integer tokenExpirationTime = 60 * 24;//60 * 24 * 7

    /**
     * Token 发行人
     */
    String tokenIssuer = "vsofo";

    /**
     * JWT签名加密key
     */
    String tokenSigningKey = "vsofo_token";

    /**
     * 刷新Token时间
     */
    Integer refreshTokenExpTime = 720;
}
