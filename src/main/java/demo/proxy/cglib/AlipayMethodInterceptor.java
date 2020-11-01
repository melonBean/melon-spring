package demo.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author czm 2020/10/31.
 */
public class AlipayMethodInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        beforePay();
        Object result = methodProxy.invokeSuper(o, args);
        afterPay();
        return result;
    }

    private void beforePay() {
        System.out.println("取钱");
    }

    private void afterPay() {
        System.out.println("花钱");
    }

}
