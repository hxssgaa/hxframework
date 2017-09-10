package com.hxdavid.hxframework.utils.annotation;

import java.lang.annotation.*;

/**
 * @author hzhuangxin3@corp.netease.com, Huang Xin
 * @date 2016-07-12 14:32
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestParam {

    String value();
    boolean required() default true;

}
