package dynamicProgramming;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HouseRobberTopDown {

    private HouseRobberTopDown() {
        throw new UnsupportedOperationException("Lớp tiện ích, không cho phép khởi tạo");
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 4, 5, 6, 7, 7};
        System.out.println(maxMoney(nums));
    }

    public static int maxMoney(int[] nums) {
        Objects.requireNonNull(nums, "nums không được null");
        if (nums.length == 0) {
            throw new IllegalArgumentException("nums không được rỗng");
        }
        Map<Integer, Integer> cachingMap = new HashMap<>();
        return recurse(nums, nums.length - 1, cachingMap);
    }

    private static int recurse(int[] nums, int i, Map<Integer, Integer> cachingMap) {
        if (i < 0) {
            return 0;
        }
        if (i == 0) {
            return nums[0];
        }
        Integer result = cachingMap.get(i);
        if (result == null) {
            int rob = nums[i] + recurse(nums, i - 2, cachingMap);
            int skip = recurse(nums, i - 1, cachingMap);
            result = Math.max(rob, skip);
            cachingMap.put(i, result);
        }
        return result;
    }
}
