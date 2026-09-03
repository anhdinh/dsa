# AGENTS.md
## Tổng quan
- luôn trả lời bắt đầu với 3 mặt cười

Project luyện tập Data Structures & Algorithms, gồm:

- **Backend:** Spring Boot 3.5.16, Java 21 (`src/main/java`).
- **Frontend:** React 19 + Vite (`frontend/`), đóng gói thành tĩnh và copy vào `static/` khi build Maven.
- **Docs:** Các quyết định kiến trúc dạng ADR trong `docs/adr/`.

## Cấu trúc thư mục chính

- `src/main/java/{sorting,searching,tree,dynamicProgramming,leetcode,Link,designPT}` — các thuật toán / CTDL độc lập, có `main()` riêng để chạy demo.
- `src/main/java/com/dsa/` — Spring Boot app (controller, config).
- `src/test/java/` — test JUnit 5.

## Lệnh thường dùng

- Build toàn bộ (có build frontend): `./mvnw package`
- Chạy test: `./mvnw test`
- Dev frontend: `cd frontend && npm run dev`
- Chạy demo một thuật toán đứng lẻ: `javac -d . <file>.java && java <package.ClassName>`

## Quy ước

- Code bằng Java 21, không thêm comment trừ khi được yêu cầu.
- Thuật toán demo đứng lẻ nằm ở package tương ứng, có `main()` để chạy thử.
- Class trong Spring Boot app (com/dsa) dùng Lombok.
- Quyết định kiến trúc ghi dạng ADR vào `docs/adr/NNN-mo-ta.md` (xem `docs/adr/001-*.md` làm mẫu).
