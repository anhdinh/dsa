package jdkproxy;

import org.springframework.aop.framework.ProxyFactory;

public class Main2 {

    public static void main(String[] args) {
        OrderService realService = new OrderService();

        // 2. Sử dụng ProxyFactory của Spring
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(realService); // Gán target
        proxyFactory.addAdvice(new MyTransactionInterceptor()); // Thêm logic can thiệp

        // Ép Spring dùng CGLIB (Kế thừa class thay vì dùng JDK Interface)
        // Spring Boot mặc định luôn bật cấu hình này: spring.aop.proxy-target-class=true
        proxyFactory.setProxyTargetClass(true);

        // 3. Lấy đối tượng Proxy ra
        // Chú ý: Ép kiểu thẳng về OrderService (class cụ thể), không cần Interface!
        OrderService proxy = (OrderService) proxyFactory.getProxy();

        // 4. In thông tin để quan sát Class Type
        System.out.println("Class của realService : " + realService.getClass().getName());
        System.out.println("Class của proxyService: " + proxy.getClass().getName());
        System.out.println("--------------------------------------------------");

        // 5. Chạy thử
        proxy.processOrder(9999L);
    }

}
