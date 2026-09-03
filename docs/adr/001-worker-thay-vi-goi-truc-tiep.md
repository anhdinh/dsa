# ADR-001: Đẩy việc gửi dữ liệu sang hệ thống đối tác qua worker thay vì gọi trực tiếp trong API

- **Trạng thái:** Accepted
- **Ngày:** 2026-08-07
- **Người quyết định:** <tên bạn>, <tech lead>

## Bối cảnh

API `POST /orders` hiện phải đẩy dữ liệu đơn hàng sang hệ thống đối tác.
Hệ thống đó nằm ngoài tầm kiểm soát của chúng tôi: độ trễ không ổn định
và có những khoảng bảo trì không báo trước.

Gọi trực tiếp trong luồng request khiến thời gian phản hồi của API phụ
thuộc vào một bên thứ ba, và mỗi lần bên đó chậm là user của chúng tôi
chịu chung.

Người dùng không cần biết kết quả đồng bộ này ngay tại thời điểm tạo đơn.

## Quyết định

API chỉ ghi đơn hàng và publish một message vào queue rồi trả về ngay.
Một worker riêng tiêu thụ message và gọi sang hệ thống đối tác.

## Các phương án đã cân nhắc

**1. Gọi trực tiếp trong API (giữ nguyên hiện trạng).**
Đơn giản nhất, có transaction, dễ debug. Nhưng thời gian phản hồi phụ
thuộc bên thứ ba, và bên đó chết thì API chết theo.

**2. Gọi trực tiếp nhưng chạy async trong thread pool của API.**
Không cần thêm hạ tầng. Nhưng mất dữ liệu khi service restart, và không
có cơ chế retry bền vững.

**3. Worker + message queue (đã chọn).**
Chi phí vận hành cao hơn, nhưng chịu được lỗi và cô lập được rủi ro.

## Hệ quả

**Tích cực**

- API không còn phụ thuộc thời gian sống của hệ thống đối tác.
- Message tồn tại qua restart; retry tự động khi bên kia phục hồi.
- Queue hấp thụ tải thay vì dồn áp lực sang bên nhận (back-pressure).

**Tiêu cực**

- Mất tính nhất quán tức thời: API trả 200 không đồng nghĩa dữ liệu đã
  sang bên kia. Nghiệp vụ nào cần bảo đảm đó phải xử lý riêng.
- Thêm hạ tầng phải vận hành và giám sát (queue, worker, DLQ).
- Khó lần vết hơn: một nghiệp vụ giờ trải trên hai tiến trình.

**Ràng buộc kéo theo**

- Queue giao *at-least-once*, nên worker phải idempotent — dùng
  idempotency key, TTL của kho lưu khoá phải dài hơn thời gian giữ DLQ.
- Message lỗi quá `<N>` lần đi vào DLQ; cần alert và người trực xử lý.
- Circuit breaker phía worker để không dồn tải khi bên nhận đang yếu.
