package demo.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * @author czm 2020/11/1.
 */
public class CglibUtil {
    public static <T> T create(T targetObject, MethodInterceptor methodInterceptor) {
        return (T) Enhancer.create(targetObject.getClass(), methodInterceptor);
    }
}
