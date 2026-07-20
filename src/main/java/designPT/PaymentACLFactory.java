package designPT;

public class PaymentACLFactory extends AbstractPaymentMethodFactory{
    @Override
    PaymentMethod createPaymentMethod() {
        return new ACL();
    }
}
