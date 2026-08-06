package dynamicProgramming;

import java.util.HashMap;
import java.util.Map;

public class ClimbingStairsTopDown {

    public static int MAX_STAIRS = 45;

    public static void main(String[] args) {
        System.out.println(topDown(10));
    }

    public static int topDown(int n) {
        if (n < 0 || n > MAX_STAIRS) {
            throw new IllegalArgumentException(String.format("n %d khong duoc am cung nhu khong lon hon %d", n, MAX_STAIRS));
        }
        Map<Integer, Integer> cachingMap = new HashMap<>();
        return recurse(n, cachingMap);
    }

    private static int recurse(int n, Map<Integer, Integer> cachingMap) {
        if (n <= 1) {
            return 1;
        }
        Integer result = cachingMap.get(n);
        if (result == null) {
            result = recurse(n - 1, cachingMap) + recurse(n - 2, cachingMap);
            cachingMap.put(n, result);
        }
        return result;
    }
}
