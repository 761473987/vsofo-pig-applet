package com.vsofo.common.constants.enums;

import com.vsofo.common.constants.api.IResultCode;

public enum AuthCode implements IResultCode {
    SUCCESS(1, "登录成功！"),
    PERMISSIONS_FAIL(-1, "对不起，请联系管理员！"),
    FAIL(-2, "已过期，请重新登录！"),
    VERIFY_FAIL(-5,""),
    AUTHORIZE_FAIL(401, "对不起，请联系管理员！"),
    FORBIDDEN(403, "没有相关权限！");
    private int code;
    private String message;

    AuthCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
