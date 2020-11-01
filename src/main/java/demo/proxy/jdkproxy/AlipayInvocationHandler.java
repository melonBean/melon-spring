package demo.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author czm 2020/10/31.
 *
 * 织入的切面逻辑
 */
public class AlipayInvocationHandler implements InvocationHandler {

    private Object targetObject;

    public AlipayInvocationHandler(Object targetObject) {
        this.targetObject = targetObject;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] args) throws Throwable {
        beforePay();
        Object result = method.invoke(targetObject, args);
        afterPay();
        return result;
    }

    private void beforePay() {
        System.out.println("~~~~取款~~~~");
    }

    private void afterPay() {
        System.out.println("~~~~支付~~~~");
    }
}
