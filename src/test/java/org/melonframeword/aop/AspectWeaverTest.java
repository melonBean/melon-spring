package org.melonframeword.aop;

import com.melon.controller.superadmin.HeadLineOperationController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.melonframework.aop.AspectWeaver;
import org.melonframework.core.BeanContainer;
import org.melonframework.inject.DependencyInjector;

/**
 * @author czm 2020/11/1.
 */
public class AspectWeaverTest {

    @DisplayName("织入通用逻辑测试：dopAop")
    @Test
    public void doAopTest() {
        BeanContainer beanContainer = BeanContainer.getInstance();
        beanContainer.loadBeans("com.melon");
        //先aop，使对应的对象都为代理对象，再ioc
        new AspectWeaver().doAop();
        new DependencyInjector().doIoc();
        HeadLineOperationController headLineOperationController = (HeadLineOperationController) beanContainer.getBean(HeadLineOperationController.class);
        headLineOperationController.addHeadLine(null, null);
    }
}
