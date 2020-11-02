package org.melonframework.aop.annotation;

import java.lang.annotation.*;

/**
 * @author czm 2020/11/1.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {

    //Class<? extends Annotation> value();

    String pointcut();
}
