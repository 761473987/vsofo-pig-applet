package com.vsofo.authentication.exception.annotation;

import java.lang.annotation.*;

/**
 * @author lituo
 * @version 1.0
 * @date 2020/6/19  10:06
 * @description 自定义日志处理注解
 */
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface AuthCheck {

    String descrption() default "" ;//描述

}
