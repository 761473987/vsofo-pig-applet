package com.vsofo.applet.pigfarmstat.interceptor;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 替换值
 * 如果organId 和 farmsId为-1的时候替换成空
 */
public class WebFilterParam extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Map<String, String[]> parameterMap = new HashMap<>(request.getParameterMap());
        String[] farmIds = parameterMap.get("farmId");
        String[] organIds = parameterMap.get("organId");
        if (farmIds!=null){
            for (String farmId: farmIds) {
                if(farmId.trim().equals("") || farmId.equals("-1") || farmId.equals("0")){
                    parameterMap.put("farmId",null);
                    break;
                }
            }
        }
        if (organIds!=null){
            for (String organId: organIds) {
                if(organId.trim().equals("") ||organId.equals("-1") || organId.equals("0")){
                    parameterMap.put("organId",null);
                    break;
                }
            }
        }


        ParameterServletRequestWrapper req = new ParameterServletRequestWrapper(request, parameterMap);
        //调用对应的controller
        super.doFilter(req,response,filterChain);

    }
}