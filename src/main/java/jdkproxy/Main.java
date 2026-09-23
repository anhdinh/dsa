package jdkproxy;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        PaymentService realService = new PaymentServiceImpl();
        PaymentService proxyService = (PaymentService) Proxy.newProxyInstance(
                realService.getClass().getClassLoader(),     // ClassLoader của target
                realService.getClass().getInterfaces(),       // Mảng các Interface mà proxy cần implement
                new LoggingHandler(realService)              // InvocationHandler xử lý logic
        );

        // 3. Thực thi thông qua Proxy
        proxyService.pay(250000);
    }
}
