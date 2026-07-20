package designPT;

public class PaymentVoucherFactory extends AbstractPaymentMethodFactory{

    @Override
    PaymentMethod createPaymentMethod() {
        return new Voucher();
    }
}
