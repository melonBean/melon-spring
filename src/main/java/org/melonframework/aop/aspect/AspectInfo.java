package org.melonframework.aop.aspect;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author czm 2020/11/1.
 */
@AllArgsConstructor
@Getter
public class AspectInfo {
    private int orderIndex;
    private DefaultAspect aspectObject;
}
