package org.melonframework.mvc.annotation;

import org.melonframework.mvc.type.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author czm 2020/11/8.
 *
 * 标识Controller的方法与请求路径和方法的映射关系
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
    //请求路径
    String value() default "";
    //请求方法
    RequestMethod method() default RequestMethod.GET;
}
