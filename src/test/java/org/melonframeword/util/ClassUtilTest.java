package org.melonframeword.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.melonframework.util.ClassUtil;

import java.util.Set;

/**
 * @author czm 2020/8/16.
 */
public class ClassUtilTest {

    @DisplayName("提取目标类方法：extractPackageClassTest")
    @Test
    public void extractPackageClassTest() {
        Set<Class<?>> classSet =  ClassUtil.extractPackageClass("com.melon.entity");
        for (Class<?> aClass : classSet) {
            System.out.println(aClass.getName());
        }
        Assertions.assertEquals(4, classSet.size());
    }

}
