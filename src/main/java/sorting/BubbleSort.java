package sorting;

import java.util.Random;

public class BubbleSort {
    public static void main(String[] args) {
        int size = 100_000;
        System.out.println("Đang khởi tạo mảng " + size + " phần tử...");
        int[] numbers = new Random().ints(size, 0, 1_000_000).toArray();

        System.out.println("Bắt đầu sắp xếp bằng BubbleSort...");

        // 1. Ghi lại thời điểm bắt đầu
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < numbers.length - 1; i++) {
            boolean isSwaped = false;
            for (int j = 0; j < numbers.length - 1 - i; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    var tem = numbers[j + 1];
                    numbers[j + 1] = numbers[j];
                    numbers[j] = tem;
                    isSwaped = true;
                }
            }
            if (!isSwaped) {
                break;
            }
        }

        // 2. Ghi lại thời điểm kết thúc
        long endTime = System.currentTimeMillis();

        // 3. Tính khoảng thời gian chênh lệch
        long duration = endTime - startTime;

        System.out.println("--- KẾT QUẢ BUBBLE SORT ---");
        System.out.println("Sắp xếp thành công!");
        System.out.println("Thời gian thực thi: " + duration + " ms (" + (duration / 1000.0) + " giây)");

        // Kiểm tra tính đúng đắn của mảng
        System.out.println("Kiểm tra sắp xếp: " + isSorted(numbers));
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