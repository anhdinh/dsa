package jdkproxy;

public class OrderService {
    public void processOrder(long orderId) {
        System.out.println("-> [NGHIỆP VỤ THẬT] Đang xử lý đơn hàng: " + orderId);
    }
}
