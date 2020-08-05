package com.vsofo.applet.exception;


import com.vsofo.common.entity.Result;

/**
 * @author: wangtao
 * @date: 2020/6/1
 */
public class GeneralException extends RuntimeException{
    private Result resultSubBean;

    public GeneralException(Result resultSubBean) {
        super(resultSubBean.getMessage());
        this.resultSubBean = resultSubBean;
    }

    public Result getCm() {
        return resultSubBean;
    }
}
