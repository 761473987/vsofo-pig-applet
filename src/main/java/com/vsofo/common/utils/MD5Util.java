package com.vsofo.common.utils;

import java.security.MessageDigest;

/**
 * @author lituo
 * @version 1.0
 * @date 2020/5/15 13:46
 * @description MD5加密
 */
public class MD5Util {

    /**
     * MD5加密
     * @param token token
     * @return
     * @throws Exception
     */
    public static String Md5Encoding(String token) throws Exception {

        MessageDigest sha = null;

        try {

            sha = MessageDigest.getInstance("SHA");

        } catch (Exception e) {

            System.out.println(e.toString());

            e.getStackTrace();

            return "";

        }

        byte[] byteArray = token.getBytes("utf-8");

        byte[] md5Bytes = sha.digest(byteArray);

        StringBuffer hexValue = new StringBuffer();

        for (int i = 0; i < md5Bytes.length; i++) {

            int val = ((int) md5Bytes[i]) & 0xff;

            if (val < 16) {

                hexValue.append("0");

            }

            hexValue.append(Integer.toHexString(val));

        }

        return hexValue.toString().toUpperCase();

    }

}
