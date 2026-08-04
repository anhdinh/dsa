package sorting;

import java.util.Random;
import java.util.Stack;

public class IterativeQuickSort {

    public static void main(String[] args) {
        int size = 100_000;
        int[] numbers = new Random().ints(size, 0, 1_000_000).toArray();

        long startTime = System.currentTimeMillis();

        quickSortIterativeFast(numbers, 0, numbers.length - 1);

        long endTime = System.currentTimeMillis();

        System.out.println("Thời gian thực thi (Không đệ quy): " + (endTime - startTime) + " ms");
        System.out.println("Kiểm tra sắp xếp: " + isSorted(numbers));
    }

    public static void quickSortIterativeFast(int[] numbers, int start, int end) {
        // Tự chế Stack bằng mảng nguyên thủy (tránh hoàn toàn Auto-boxing và Synchronized)
        int[] stack = new int[end - start + 1];
        int top = -1;

        // Push start và end
        stack[++top] = start;
        stack[++top] = end;

        while (top >= 0) {
            // Pop end và start
            int currentEnd = stack[top--];
            int currentStart = stack[top--];

            int pivotIndex = partition(numbers, currentStart, currentEnd);

            if (pivotIndex - 1 > currentStart) {
                stack[++top] = currentStart;
                stack[++top] = pivotIndex - 1;
            }

            if (pivotIndex + 1 < currentEnd) {
                stack[++top] = pivotIndex + 1;
                stack[++top] = currentEnd;
            }
        }
    }

    // Hàm Partition giữ nguyên 100% logic của bạn
    public static int partition(int[] numbers, int start, int end) {
        Random random = new Random();
        int randomIndex = start + random.nextInt(end - start + 1);

        var randomTemp = numbers[randomIndex];
        numbers[randomIndex] = numbers[start];
        numbers[start] = randomTemp;

        var pivot = numbers[start];
        int m = start;
        for (int i = start + 1; i <= end; i++) {
            if (numbers[i] <= pivot) {
                m = m + 1;
                var tem = numbers[m];
                numbers[m] = numbers[i];
                numbers[i] = tem;
            }
        }
        var tem = numbers[m];
        numbers[m] = pivot;
        numbers[start] = tem;
        return m;
    }

    public static boolean isSorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) return false;
        }
        return true;
    }
}