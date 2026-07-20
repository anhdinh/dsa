package designPT;

public abstract class AbstractPaymentMethodFactory {

   abstract  PaymentMethod createPaymentMethod() ;

   public void pay(Double money) {
      var ca =  createPaymentMethod();
      ca.pay(money);
   }
}
