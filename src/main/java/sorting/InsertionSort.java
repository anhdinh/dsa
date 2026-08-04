package sorting;

import java.util.Random;

public class InsertionSort {

    public static void main(String[] args) {
        int size = 100_000;
        System.out.println("Đang khởi tạo mảng " + size + " phần tử...");
        int[] numbers = new Random().ints(size, 0, 1_000_000).toArray();

        System.out.println("Bắt đầu sắp xếp bằng InsertionSort...");

        // 1. Ghi lại thời điểm bắt đầu
        long startTime = System.currentTimeMillis();

        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] < numbers[i - 1]) {
                var temp = numbers[i];
                var indexTemp = shiftAndFindIndex(i, numbers, temp);
                if (indexTemp != -1) {
                    numbers[indexTemp] = temp;
                }
            }
        }

        // 2. Ghi lại thời điểm kết thúc
        long endTime = System.currentTimeMillis();

        // 3. Tính khoảng thời gian chênh lệch
        long duration = endTime - startTime;

        System.out.println("--- KẾT QUẢ INSERTION SORT ---");
        System.out.println("Sắp xếp thành công!");
        System.out.println("Thời gian thực thi: " + duration + " ms (" + (duration / 1000.0) + " giây)");

        // Kiểm tra tính đúng đắn của mảng
        System.out.println("Kiểm tra sắp xếp: " + isSorted(numbers));
    }

    public static int shiftAndFindIndex(int i, int[] numbers, int temp) {
        int y = i - 1;
        while (y >= 0 && numbers[y] > temp) {
            numbers[y + 1] = numbers[y];
            y--;
        }
        return y + 1;
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