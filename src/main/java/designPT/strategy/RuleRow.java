package designPT.strategy;

public record RuleRow(
        long id,
        RuleType type,
        double value,
        String condition,   // nhãn đơn giản; điều kiện phức tạp KHÔNG thuộc về đây
        int applyOrder      // chỉ thêm nếu nghiệp vụ cần tự đổi thứ tự
) {}