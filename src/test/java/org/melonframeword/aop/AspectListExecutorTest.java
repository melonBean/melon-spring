package org.melonframeword.aop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.melonframeword.aop.mock.*;
import org.melonframework.aop.AspectListExecutor;
import org.melonframework.aop.aspect.AspectInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author czm 2020/11/1.
 */
public class AspectListExecutorTest {
    @DisplayName("Aspect排序：sortAspectList")
    @Test
    public void sortTest() {
        List<AspectInfo> aspectInfoList = new ArrayList<>();
        aspectInfoList.add(new AspectInfo(3, new Mock1()));
        aspectInfoList.add(new AspectInfo(5, new Mock2()));
        aspectInfoList.add(new AspectInfo(2, new Mock3()));
        aspectInfoList.add(new AspectInfo(6, new Mock4()));
        aspectInfoList.add(new AspectInfo(1, new Mock5()));
        aspectInfoList.add(new AspectInfo(4, new Mock6()));
        AspectListExecutor aspectListExecutor = new AspectListExecutor(null, aspectInfoList);
        List<AspectInfo> sortedAspectInfoList = aspectListExecutor.getSortedAspectInfoList();
        //expected: Mock5 -> Mock3 -> Mock1 -> Mock6 -> Mock2 -> Mock4
        for (AspectInfo aspectInfo : sortedAspectInfoList) {
            System.out.println(aspectInfo.getAspectObject().getClass().getName());
        }
    }
}
