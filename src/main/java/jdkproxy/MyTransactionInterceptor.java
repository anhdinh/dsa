package jdkproxy;



import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MyTransactionInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println(">>> [SPRING PROXY] Bắt đầu Transaction cho hàm: "
                + invocation.getMethod().getName());

        try {
            // Thực thi phương thức tiếp theo trong chuỗi hoặc hàm thật
            Object result = invocation.proceed();

            System.out.println(">>> [SPRING PROXY] Commit Transaction thành công!");
            return result;
        } catch (Exception e) {
            System.out.println(">>> [SPRING PROXY] Rollback Transaction do lỗi: " + e.getMessage());
            throw e;
        }
    }
}