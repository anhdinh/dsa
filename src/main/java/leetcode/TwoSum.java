package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoSum {

    // Ban LeetCode goc: de bao dam dung mot dap an, tra ve ngay khi tim thay
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> complementToIndex = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer matchedIndex = complementToIndex.get(nums[i]);
            if (matchedIndex != null) {
                return new int[]{matchedIndex, i};
            }
            complementToIndex.put(target - nums[i], i);
        }
        return new int[0];
    }

    // Ban tim het moi cap: mot phan bu co the co nhieu index dang cho ghep
    public static List<int[]> allPairs(int[] nums, int target) {
        Map<Integer, List<Integer>> complementToIndices = new HashMap<>();
        List<int[]> pairs = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j : complementToIndices.getOrDefault(nums[i], List.of())) {
                pairs.add(new int[]{j, i});
            }
            complementToIndices
                    .computeIfAbsent(target - nums[i], k -> new ArrayList<>())
                    .add(i);
        }
        return pairs;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9)));
        allPairs(new int[]{1, 3, 3, 1}, 4)
                .forEach(p -> System.out.printf("[%d][%d]%n", p[0], p[1]));
    }
}
