package com.vsofo.common.utils;


import com.google.gson.Gson;
import com.vsofo.authentication.entity.po.RolePO;
import com.vsofo.authentication.entity.po.UserPO;
import com.vsofo.common.constants.SecurityConstant;
import io.jsonwebtoken.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lituo
 * @version 1.0
 * @date 2020/5/15 13:46
 * @description token工具类
 */
public class TokenUtils {

    /**
     * 生成新的token方法，并将角色保存
     *
     * @param authUserDetails 用户认证成功信息
     * @param roleId          角色id
     * @param userId          用户id
     * @param userName        用户名
     * @param roleName        角色名
     * @return
     */
    public static String createAccessJwtToken(UserPO authUserDetails, long roleId, long userId, String userName, String roleName) {
        if (StringUtils.isEmpty(authUserDetails.getUsername())) {
            throw new IllegalArgumentException("用户名为空无法创建token");
        }

        Claims claims = Jwts.claims().setSubject(authUserDetails.getUsername());

        //存入角色信息
        List<String> list = new ArrayList<>();
        for (RolePO roleInfo : authUserDetails.getRoles()) {
            list.add(roleInfo.getRole());
            claims.put("roleId", roleId);
            claims.put("userId", userId);
            claims.put("userName", userName);
            claims.put("roleName", roleName);
            break;
        }
        //自定义角色标识
        claims.put(SecurityConstant.AUTHORITIES, new Gson().toJson(list));
        claims.put("userId", authUserDetails.getUserId());

        LocalDateTime currentTime = LocalDateTime.now();

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer(SecurityConstant.tokenIssuer)
                .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(currentTime        //token 72小时过期时间
                        .plusMinutes(SecurityConstant.tokenExpirationTime)
                        .atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, SecurityConstant.tokenSigningKey)    //jwt签名方式
                .compact();
        return token;
    }

    /**
     * @param token       客户端传入的token
     * @param userDetails 从数据库中查询出来的用户信息
     */
    public static boolean validateToken(String token, UserDetails userDetails) {
        String username = parseJWT(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     *  验证token是否失效
     *  true:过期   false:没过期
     */
    public static Boolean isTokenExpired(String token) {
        try {
            final Date expiration = getExpirationDateFromToken(token);
            return expiration.before(new Date());
        } catch (ExpiredJwtException expiredJwtException) {
            return true;
        }
    }


    /**
     * 获取jwt失效时间
     */
    public static Date getExpirationDateFromToken(String token) {
        String key = SecurityConstant.tokenSigningKey;  //签名秘钥，和生成的签名的秘钥一模一样
        return Jwts.parser()  //得到DefaultJwtParser
                .setSigningKey(key)         //设置签名的秘钥
                .parseClaimsJws(token).getBody().getExpiration();
    }


    /**
     * 解析token方法，获取token保存数据信息
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static String parseJWT(String token) throws SignatureException {
        String subject = null;
        String key = SecurityConstant.tokenSigningKey;  //签名秘钥，和生成的签名的秘钥一模一样
        Claims claims = Jwts.parser()  //得到DefaultJwtParser
                .setSigningKey(key)         //设置签名的秘钥
                .parseClaimsJws(token).getBody();//设置需要解析的jwt
        subject = claims.getSubject().trim();
        return subject;
    }

    /**
     * 解析token方法，获取token保存数据信息 ，去除Bearer前缀
     * @param token
     * @return
     * @throws Exception
     */
    public static String parseJWTToken(String token) throws SignatureException {
        String newToken = token.replace("Bearer ", "");
        if (StringUtils.isEmpty(newToken)) {
            return null;
        }
        String subject = null;
        String key = SecurityConstant.tokenSigningKey;  //签名秘钥，和生成的签名的秘钥一模一样
        Claims claims = Jwts.parser()  //得到DefaultJwtParser
                .setSigningKey(key)         //设置签名的秘钥
                .parseClaimsJws(newToken).getBody();//设置需要解析的jwt
        subject = claims.getSubject().trim();
        return subject;
    }


    /**
     * 解析token方法，获取token保存的角色ID
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static Object parseJWTRoleId(String token) throws Exception {
        String key = SecurityConstant.tokenSigningKey;  //签名秘钥，和生成的签名的秘钥一模一样
        Claims claims = Jwts.parser()  //得到DefaultJwtParser
                .setSigningKey(key)         //设置签名的秘钥
                .parseClaimsJws(token).getBody();//设置需要解析的jwt
        return claims.get("roleId");
    }

    /**
     * 解析token方法，获取token保存的用户ID
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static Object parseJWTUserId(String token) throws Exception {
        String key = SecurityConstant.tokenSigningKey;  //签名秘钥，和生成的签名的秘钥一模一样
        Claims claims = Jwts.parser()  //得到DefaultJwtParser
                .setSigningKey(key)         //设置签名的秘钥
                .parseClaimsJws(token).getBody();//设置需要解析的jwt
        return claims.get("userId");
    }

    /**
     * 解析token方法，获取token保存的用户ID
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static Object parseJWTUserName(String token) throws Exception {
        String key = SecurityConstant.tokenSigningKey;  //签名秘钥，和生成的签名的秘钥一模一样
        Claims claims = Jwts.parser()  //得到DefaultJwtParser
                .setSigningKey(key)         //设置签名的秘钥
                .parseClaimsJws(token).getBody();//设置需要解析的jwt
        return claims.get("userName");
    }

    /**
     * 解析token方法，获取token保存的用户ID
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static Claims parseBody(String token) throws Exception {
        String key = SecurityConstant.tokenSigningKey;  //签名秘钥，和生成的签名的秘钥一模一样
        Claims claims = Jwts.parser()  //得到DefaultJwtParser
                .setSigningKey(key)         //设置签名的秘钥
                .parseClaimsJws(token).getBody();//设置需要解析的jwt
        return claims;
    }


    /**
     *  验证token是否失效
     *  true:过期   false:没过期
     */
  /*  public static Boolean isTokenTimeExpired(String token) {
        String newToken = token.replace("Bearer ", "");
        try {
            final Date expiration = getExpirationDateFromToken(newToken);
            return expiration.before(new Date());
        } catch (ExpiredJwtException expiredJwtException) {
            return true;
        }
    }*/

}