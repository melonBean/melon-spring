package org.melonframework.aop.aspect;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.melonframework.aop.PointcutLocator;

/**
 * @author czm 2020/11/1.
 */
@AllArgsConstructor
@Getter
public class AspectInfo {
    private int orderIndex;
    private DefaultAspect aspectObject;
    private PointcutLocator pointcutLocator;

}
