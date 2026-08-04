package sorting;

import java.util.Random;

public class QuickSort {
    public static void main(String[] args) {
        int size = 100_000;
        System.out.println("Đang khởi tạo mảng " + size + " phần tử...");
        int[] numbers = new Random().ints(size, 0, 1_000_000).toArray();

        System.out.println("Bắt đầu sắp xếp...");

        // 1. Ghi lại thời điểm bắt đầu (tính bằng miligiây)
        long startTime = System.currentTimeMillis();

        quickSort(numbers, 0, numbers.length - 1);

        // 2. Ghi lại thời điểm kết thúc
        long endTime = System.currentTimeMillis();

        // 3. Tính độ lệch thời gian
        long duration = endTime - startTime;

        System.out.println("--- KẾT QUẢ ---");
        System.out.println("Sắp xếp thành công!");
        System.out.println("Thời gian thực thi: " + duration + " ms (" + (duration / 1000.0) + " giây)");

        // Kiểm tra nhanh xem mảng đã thực sự được sắp xếp đúng chưa
        System.out.println("Kiểm tra sắp xếp: " + isSorted(numbers));
    }

    public static void quickSort(int[] numbers, int start, int end) {
        if (start < end) {
            var pivotIndex = partition(numbers, start, end);
            quickSort(numbers, start, pivotIndex - 1);
            quickSort(numbers, pivotIndex + 1, end);
        }
    }

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

    // Hàm phụ trợ kiểm tra xem mảng đã được sắp xếp tăng dần chưa
    public static boolean isSorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }
}