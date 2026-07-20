package designPT;

public class Voucher implements PaymentMethod {
    @Override
    public void pay(Double money) {
        System.out.println("Payment by Voucher");
    }
}
