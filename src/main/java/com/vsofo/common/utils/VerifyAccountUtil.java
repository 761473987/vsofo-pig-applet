package com.vsofo.common.utils;


import com.vsofo.common.constants.AccountCode;

import java.util.regex.Pattern;

/**
 * @author lituo
 * @version 1.0
 * @date 2020/7/6 14:25:52
 * @description 信息效验
 */
public final class VerifyAccountUtil {

    /**
     * 密码修改效验
     * @param password  密码
     * @param passwordVerify  二次输入密码
     * @return
     */
    public static String verifyPassword(String password,String passwordVerify) {
        Pattern pattern = Pattern.compile("[0-9]*");
        if (pattern.matcher(password).matches()) {
            return AccountCode.PASSWORD_NOTNUMBER;
        }
        if (!password.equals(passwordVerify)) {
            return AccountCode.PASSWORD_COMPARED;
        }
        if (password.length() < 8 || password.length() > 30) {
            return AccountCode.PASSWORD_LENGTH;
        }
        return "";
    }
}
