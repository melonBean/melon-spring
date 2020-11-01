package demo.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;

/**
 * @author czm 2020/11/1.
 */
public class Demo {
    public static void main(String[] args) {
        MethodInterceptor methodInterceptor = new AlipayMethodInterceptor();
        CommonPay commonPay = CglibUtil.create(new CommonPay(), methodInterceptor);
        commonPay.pay();
    }

    static class CommonPay {
        public void pay() {
            System.out.println("开始~~~~");
        }
    }
}
