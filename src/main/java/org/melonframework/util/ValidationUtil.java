package org.melonframework.util;

import java.util.Collection;
import java.util.Map;

/**
 * @author czm 2020/8/16.
 */
public class ValidationUtil {

    /**
     * Array是否为null或length为0
     *
     * @param obj Object[]
     * @return 是否为空
     */
    public static boolean isEmpty(Object[] obj) {
        return (obj == null || obj.length == 0);
    }

    /**
     * String是否为null或""
     *
     * @param obj String
     * @return 是否为空
     */
    public static boolean isEmpty(String obj) {
        return (obj == null || "".equals(obj));
    }

    /**
     * Collection是否为null或size为0
     *
     * @param obj Collection
     * @return 是否为空
     */
    public static boolean isEmpty(Collection<?> obj) {
        return obj == null || obj.isEmpty();
    }

    /**
     * Map是否为null或size为0
     *
     * @param obj Map
     * @return 是否为空
     */
    public static boolean isEmpty(Map<?, ?> obj) {
        return obj == null || obj.isEmpty();
    }
}
