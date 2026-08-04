package sorting;

import java.util.Random;

public class SelectionSort {
    public static void main(String[] args) {
        int size = 100_000;
        System.out.println("Đang khởi tạo mảng " + size + " phần tử...");
        int[] numbers = new Random().ints(size, 0, 1_000_000).toArray();

        System.out.println("Bắt đầu sắp xếp bằng SelectionSort...");

        // 1. Ghi lại thời điểm bắt đầu
        long startTime = System.currentTimeMillis();

        selectionSort(numbers);

        // 2. Ghi lại thời điểm kết thúc
        long endTime = System.currentTimeMillis();

        // 3. Tính khoảng thời gian chênh lệch
        long duration = endTime - startTime;

        System.out.println("--- KẾT QUẢ SELECTION SORT ---");
        System.out.println("Sắp xếp thành công!");
        System.out.println("Thời gian thực thi: " + duration + " ms (" + (duration / 1000.0) + " giây)");

        // Kiểm tra tính đúng đắn của mảng
        System.out.println("Kiểm tra sắp xếp: " + isSorted(numbers));
    }

    public static void selectionSort(int[] number) {
        if (number == null) return;

        for (int x = 0; x < number.length - 1; x++) {
            int minIndex = findMinIndex(number, x);
            swap(number, x, minIndex);
        }
    }

    // 2. Hàm tìm vị trí nhỏ nhất
    private static int findMinIndex(int[] number, int start) {
        int minIndex = start;
        for (int y = start + 1; y < number.length; y++) {
            if (number[y] < number[minIndex]) {
                minIndex = y;
            }
        }
        return minIndex;
    }

    // 3. Hàm đổi chỗ 2 vị trí
    private static void swap(int[] number, int i, int j) {
        if (i != j) {
            int temp = number[i];
            number[i] = number[j];
            number[j] = temp;
        }
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