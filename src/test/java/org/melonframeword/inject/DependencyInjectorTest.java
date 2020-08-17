package org.melonframeword.inject;

import com.melon.controller.frontend.MainPageController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.melonframework.core.BeanContainer;
import org.melonframework.inject.DependencyInjector;

/**
 * @author czm 2020/8/17.
 */
public class DependencyInjectorTest {

    @DisplayName("依赖注入doIoc")
    @Test
    public void doIocTest() {
        BeanContainer beanContainer = BeanContainer.getInstance();
        beanContainer.loadBeans("com.melon");
        Assertions.assertTrue(beanContainer.isLoaded());
        MainPageController mainPageController = (MainPageController) beanContainer.getBean(MainPageController.class);
        Assertions.assertTrue(mainPageController instanceof MainPageController);
        Assertions.assertEquals(null, mainPageController.getHeadLineShopCategoryCombineService());
        new DependencyInjector().doIoc();
        Assertions.assertNotEquals(null, mainPageController.getHeadLineShopCategoryCombineService());
    }

}
