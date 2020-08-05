package com.vsofo.applet.exception;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.vsofo.applet.pigfarmstat.log.LoggingThreadPool;
import com.vsofo.applet.pojo.GlobalException;
import com.vsofo.common.constants.SecurityConstant;
import com.vsofo.common.entity.Result;
import com.vsofo.common.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


/**
 * @author: wangtao
 * @date: 2020/6/1
 */
@ControllerAdvice(annotations = {ResponseBody.class})
@Slf4j
public class GlobalExceptionHandler {


    /**
     * @param request
     * @param ex
     * @return
     * @desc 参数类型不匹配
     */
    @ResponseBody
    @ExceptionHandler(value = TypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result exceptionTypeMismatchExceptionHandler(HttpServletRequest request, TypeMismatchException ex) {
        GlobalException globalException = setGlobalException(request, ex, "2");
        LoggingThreadPool.getInstance().loggingAsync(globalException);
        return CodeMsg.ILLEGAL_PARAMS.fillArgs(ex.getValue(), ex.getRequiredType().getSimpleName());
    }

    /**
     * @param request
     * @param ex
     * @return
     * @desc 参数类型不匹配
     */
    @ResponseBody
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result exceptionHttpMessageNotReadableExceptionHandler(HttpServletRequest request, HttpMessageNotReadableException ex) {
        GlobalException globalException = setGlobalException(request, ex, "2");
        LoggingThreadPool.getInstance().loggingAsync(globalException);
        return CodeMsg.REQUEST_ILLEGAL;
    }

    /**
     * @param request
     * @param ex
     * @return
     * @desc 缺失必传参数
     */
    @ResponseBody
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result exceptionMissingServletRequestParameterException(HttpServletRequest request, MissingServletRequestParameterException ex) {
        GlobalException globalException = setGlobalException(request, ex, "2");
        LoggingThreadPool.getInstance().loggingAsync(globalException);
        return CodeMsg.MISSING_REQUEST_PARAMETER.fillArgs(ex.getParameterName());
    }



    @ResponseBody
    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Result exceptionHttpRequestMethodNotSupportedException(HttpServletRequest request, HttpRequestMethodNotSupportedException ex) {
        GlobalException globalException = setGlobalException(request, ex, "2");
        LoggingThreadPool.getInstance().loggingAsync(globalException);
        return CodeMsg.REQUEST_ILLEGAL;
    }

    @ResponseBody
    @ExceptionHandler(value = {HttpMediaTypeNotAcceptableException.class})
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public Result exceptionHttpMediaTypeNotAcceptableException(HttpServletRequest request, HttpMediaTypeNotAcceptableException ex) {
        GlobalException globalException = setGlobalException(request, ex, "2");
        LoggingThreadPool.getInstance().loggingAsync(globalException);
        return CodeMsg.REQUEST_ILLEGAL;
    }


    @ResponseBody
    @ExceptionHandler(value = {HttpMediaTypeNotSupportedException.class})
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public Result exceptionHttpMediaTypeNotSupportedException(HttpServletRequest request, HttpMediaTypeNotSupportedException ex) {
        GlobalException globalException = setGlobalException(request, ex, "2");
        LoggingThreadPool.getInstance().loggingAsync(globalException);
        return CodeMsg.REQUEST_ILLEGAL;
    }


    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result exceptionHandler(HttpServletRequest request, Exception e) {
        Result<?> result = null;
        if (e instanceof GeneralException) {
            /**建议用此校验exception代替下面**/
            result = ((GeneralException) e).getCm();
            GlobalException globalException = setGlobalException(request, e, "3");
            LoggingThreadPool.getInstance().loggingAsync(globalException);
        } else if (e instanceof BindException) {
            /**JSR303校验错误异常**/
            BindException ex = (BindException) e;
            List<ObjectError> errors = ex.getAllErrors();
            ObjectError error = errors.get(0);
            /**获取属性名
             tring fieldName = error.getObjectName();**/
            String msg = error.getDefaultMessage();
            result = CodeMsg.BIND_ERROR.fillArgs(msg);
            GlobalException globalException = setGlobalException(request, e, "2");
            LoggingThreadPool.getInstance().loggingAsync(globalException);
        } else if (e instanceof MethodArgumentNotValidException) {
            /**JSR303校验错误异常**/
            MethodArgumentNotValidException ex = ((MethodArgumentNotValidException) e);
            BindingResult bindingResult = ex.getBindingResult();
            result = new Result<>();
            if (bindingResult.hasErrors()) {
                FieldError fieldError = bindingResult.getFieldErrors().get(0);
                /*String filed = fieldError.getField();属性名**/
                String msg = fieldError.getDefaultMessage();
                result = CodeMsg.BIND_ERROR.fillArgs(msg);
            }
            GlobalException globalException = setGlobalException(request, e, "2");
            LoggingThreadPool.getInstance().loggingAsync(globalException);
        }else {
            /**未知错误时前台返回的错误code=0*/
            log.error("【系统异常】" + e.getMessage(), e);
            result = new Result<>();
            result.setMsg("系统故障，请稍后访问！");
            result.setCode(Result.FAIL);
            GlobalException globalException = setGlobalException(request, e, "1");
            LoggingThreadPool.getInstance().loggingAsync(globalException);
        }
        return result;
    }

    private GlobalException setGlobalException(HttpServletRequest request,Exception e,String lev){
        GlobalException globalException = new GlobalException();
        globalException.setAppId("WeChat");
        globalException.setCreateTime(new Date());
        globalException.setErrMsg(e.getMessage());
        globalException.setRequestUrl(request.getRequestURI());
        globalException.setMethodInfo(e.getStackTrace()[0].getClassName()+"#"+e.getStackTrace()[0].getMethodName());
        globalException.setLev(lev);
        String accessToken = request.getHeader(SecurityConstant.HEADER);
        if (StringUtils.isNotBlank(accessToken)){
            if (accessToken.contains("Bearer ")){
                String newToken= accessToken.replace("Bearer ", "");
                try {
                    Integer userId = (Integer) TokenUtils.parseJWTUserId(newToken);
                    globalException.setUserId(userId);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
        return globalException;
    }

}
