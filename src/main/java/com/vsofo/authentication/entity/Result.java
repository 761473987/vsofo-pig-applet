package com.vsofo.authentication.entity;

import java.io.Serializable;

/**
 * @author huxiongjun
 * @version 1.0
 * @date 2020/5/8 10:21
 * @description 封装结果的实体类
 */
public class Result<T> implements Serializable {

    private boolean flag = true;//是否成功
    private Integer code = 1;//返回码
    private String message = "success";//返回消息
    private T data;//返回数据

    public static final Integer FAIL = 0;

    public Result(boolean flag, Integer code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = (T) data;
    }

    public Result(Integer code, String message) {
        this.message = message;
        this.code = code;
    }


    public Result fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(this.message, args);
        return new Result(code, message);
    }

    public Result(T data) {
        this.data = data;
    }


    public Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public Result() {
        this.message = "操作成功!";
    }

    public String getMsg() {
        return message;
    }

    public Result<T> setMsg(String msg) {
        this.message = msg;
        return this;
    }

    public Integer getCode() {
        return code;
    }


    public Result<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }


    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        return "Result{" +
                "flag=" + flag +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
