package jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LoggingHandler implements InvocationHandler {
    private final Object target;

    public LoggingHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("[LOG TRƯỚC] Bắt đầu gọi: " + method.getName());

        long startTime = System.currentTimeMillis();

        Object result = method.invoke(target, args);

        long duration = System.currentTimeMillis() - startTime;
        System.out.println("[LOG SAU] Hoàn tất " + method.getName() + " sau " + duration + "ms");

        return result;
    }
}
