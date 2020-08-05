package com.vsofo.common.utils;

import com.google.gson.Gson;
import com.vsofo.authentication.entity.Result;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author lituo
 * @version 1.0
 * @date 2020/5/18 10:34
 * @description  response输出json格式
 */
public class ResponseUtil {

    /**
     * 使用response输出JSON
     *
     * @param response
     * @param res
     */
    public static void out(HttpServletResponse response, String res) {
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Cache-Control","no-store, max-age=0, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        //response.setStatus(JSONObject.parseObject(res).getInteger("code"));
        /*//统一用过滤器设置跨域
        response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE,PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization");
        response.setHeader("Access-Control-Allow-Credentials","true");*/
        try {
            PrintWriter out = response.getWriter();
            out.write(new Gson().toJson(res));
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void out(HttpServletResponse response, Result res) {
        try {
            PrintWriter out = response.getWriter();
            out.write(new Gson().toJson(res));
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
