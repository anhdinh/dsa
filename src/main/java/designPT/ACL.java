package designPT;

public class ACL implements PaymentMethod {
    @Override
    public void pay(Double money) {
        System.out.println("Payment method ACL");
    }
}
