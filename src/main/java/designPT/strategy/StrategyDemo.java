package designPT.strategy;

import java.util.Comparator;
import java.util.List;
import java.util.function.UnaryOperator;

import static designPT.strategy.RuleType.FIXED_ADD;
import static designPT.strategy.RuleType.PERCENT_ADD;
import static designPT.strategy.RuleType.PERCENT_DISCOUNT;

public class StrategyDemo {

    public static double calculatePrice(double price, List<UnaryOperator<Double>> rules) {
        double result = price;
        for (UnaryOperator<Double> rule : rules) {
            result = rule.apply(result);
        }
        return result;
    }

    public static void main(String[] args) {
        List<RuleRow> rows = List.of(
                new RuleRow(1, PERCENT_DISCOUNT, 10, "", 3),
                new RuleRow(2, PERCENT_ADD, 8, "", 1),
                new RuleRow(3, FIXED_ADD, 15, "", 2)
        );

        List<UnaryOperator<Double>> rules = rows.stream()
                .sorted(Comparator.comparingLong(RuleRow::applyOrder))
                .map(StrategyDemo::toRule)
                .toList();

        double finalPrice = calculatePrice(1000, rules);
        System.out.println("Giá cuối cùng: " + finalPrice);
    }

    public static UnaryOperator<Double> toRule(RuleRow row) {
        return switch (row.type()) {
            case PERCENT_DISCOUNT -> price -> price * (1 - row.value() / 100);
            case PERCENT_ADD      -> price -> price * (1 + row.value() / 100);
            case FIXED_DISCOUNT   -> price -> price - row.value();
            case FIXED_ADD        -> price -> price + row.value();
        };
    }
}
