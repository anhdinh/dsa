package jdkproxy;

public class PaymentServiceImpl implements PaymentService {
    @Override
    public void pay(double amount) {
        System.out.println("Xử lý số tiền "+ amount+" VND");
    }
}
