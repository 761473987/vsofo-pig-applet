package com.vsofo.authentication.filter;

import com.vsofo.authentication.service.UsersDetailsService;
import com.vsofo.common.constants.SecurityConstant;
import com.vsofo.common.utils.TokenUtils;
import io.jsonwebtoken.Claims;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lituo
 * @version 1.0
 * @date 2020/5/19 11:46
 * @description
 */
@Component
@Slf4j
public class AuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    UsersDetailsService userDetailsService;

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        //拿到请求头的token
        String authHeader = request.getHeader(SecurityConstant.HEADER);
        //判断token是否存在并且token前缀是否是Bearer
        if (authHeader != null && authHeader.startsWith(SecurityConstant.TOKEN_SPLIT)) {
            //去除Bearer 拿到token
            String authToken = authHeader.substring(SecurityConstant.TOKEN_SPLIT.length());// The part after "Bearer "
            if (!TokenUtils.isTokenExpired(authToken)) {
                //根据token拿到用户名
                Claims claims = TokenUtils.parseBody(authToken);
                String username = (String)claims.get("userName");
                log.info("checking username:{}", username);
                //如果用户名不为空并且没有进行security安全认证
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    //调用loadUserByUsername拿到认证后的信息
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    //效验token的有效性
                    if (TokenUtils.validateToken(authToken, userDetails)) {
                        //验证用户信息，验证密码是否正确
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        log.info("authenticated user:{}", username);
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }
        }
        chain.doFilter(request, response);
    }
}
