package com.vsofo.applet.exception;


import com.vsofo.common.entity.Result;

/**
 * @author: wangtao
 * @date: 2020/6/1
 */
public class CodeMsg {
    public final static Result LOSE_USER_STATE = new Result(100001, "登录状态已丢失，请重新登录！");

    public final static Result SERVER_ERROR = new Result(500100, "服务器异常！");

    public final static Result BIND_ERROR = new Result(500101, "参数校验异常：%s");

    public final static Result REQUEST_ILLEGAL = new Result(500102, "请求非法");

    public final static Result ILLEGAL_SECRET = new Result(500103, "非法的SECRET");

    public final static Result ACCESS_LIMIT_REACHED = new Result(500104, "访问太频繁！");

    public static final Result ILLEGAL_BEGIN_MORE_THAN_END_TIMES = new Result(500105, "开始时间不能大于结束时间");

    public static final Result PAGE_QUERY_OUT_OF_RANGE = new Result(500106, "查询页码超出最大结果集范围");

    public final static Result ILLEGAL_PARAMS = new Result(400, "参数类型不匹配,参数%s类型应该为%s");

    public static final Result MISSING_REQUEST_PARAMETER = new Result(400, "缺少必填参数%s");

    public static final Result NO_SUCH_REQUEST_404 = new Result(404, "未匹配到请求资源");

}
