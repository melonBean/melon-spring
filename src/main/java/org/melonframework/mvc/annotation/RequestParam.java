package org.melonframework.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author czm 2020/11/8.
 *
 * 请求的方法参数名称
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestParam {
    //方法参数名称
    String value() default "";
    //该参数是否是必须的
    boolean required() default true;
}
