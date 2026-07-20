package designPT;

public class Test {
    public static void main(String[] args) {
        AbstractPaymentMethodFactory paymentMethodFactory = new PaymentVoucherFactory();
        paymentMethodFactory.pay(100D);
    }
}
