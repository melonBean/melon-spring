package com.melon.aspect;

import lombok.extern.slf4j.Slf4j;
import org.melonframework.aop.annotation.Aspect;
import org.melonframework.aop.annotation.Order;
import org.melonframework.aop.aspect.DefaultAspect;
import org.melonframework.core.annotation.Controller;

import java.lang.reflect.Method;

/**
 * @author czm 2020/11/1.
 */
@Slf4j
@Aspect(value = Controller.class)
@Order(1)
public class ControllerTimeCalculatorAspect extends DefaultAspect {

    long timestampCache;

    @Override
    public void before(Class<?> targetClass, Method method, Object[] args) throws Throwable {
        log.info("开始计时，执行的类是[{}]，执行的方法是[{}]，参数是[{}]",
                targetClass.getName(), method.getName(), args);
        timestampCache = System.currentTimeMillis();
    }

    @Override
    public Object afterReturning(Class<?> targetClass, Method method, Object[] args, Object returnValue) throws Throwable {
        long endTime = System.currentTimeMillis();
        long costTime = endTime - timestampCache;
        log.info("结束计时，执行的类是[{}]，执行的方法是[{}]，参数是[{}]，返回值是[{}]，时间为[{}]ms",
                targetClass.getName(), method.getName(), args, returnValue, costTime);
        return returnValue;
    }
}
