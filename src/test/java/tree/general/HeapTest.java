package tree.general;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HeapTest {

    @Test
    public void testInsertAndVisualize() {
        Heap heap = new Heap();
        int[] values = {5, 3, 8, 1, 6, 9, 2, 7, 4};

        for (int v : values) {
            heap.insertHeap(v);
        }

        printHeap(heap);
    }

    private void printHeap(Heap heap) {
        String heapStr = heap.toString();
        if (heapStr == null || heapStr.equals("[]")) {
            System.out.println("Heap is empty");
            return;
        }

        String[] parts = heapStr.replaceAll("[\\[\\] ]", "").split(",");
        int[] arr = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            arr[i] = Integer.parseInt(parts[i].trim());
        }

        int levels = (int) (Math.log(arr.length) / Math.log(2)) + 1;
        int index = 0;

        System.out.println("Heap array: " + heapStr);
        System.out.println("--- Visual ---");

        for (int level = 0; level < levels; level++) {
            int count = (int) Math.pow(2, level);
            int indent = (int) Math.pow(2, levels - level) - 1;

            System.out.print("  ".repeat(indent));
            for (int i = 0; i < count && index < arr.length; i++) {
                System.out.printf("%2d", arr[index++]);
                System.out.print("  ".repeat((int) Math.pow(2, levels - level + 1) - 1));
            }
            System.out.println();
        }
        System.out.println("--------------");
    }

    @Test
    public void testMinHeapProperty() {
        Heap heap = new Heap();
        heap.insertHeap(10);
        heap.insertHeap(5);
        heap.insertHeap(3);
        heap.insertHeap(7);
        heap.insertHeap(1);
        heap.insertHeap(2);

        String heapStr = heap.toString();
        String[] parts = heapStr.replaceAll("[\\[\\] ]", "").split(",");
        int[] arr = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            arr[i] = Integer.parseInt(parts[i].trim());
        }

        assertEquals(1, arr[0], "Root must be the smallest element");

        for (int i = 0; i < arr.length; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            if (left < arr.length) {
                if (arr[i] > arr[left]) {
                    System.out.println("vi pham min-heap tai index " + i + " (value=" + arr[i] + ") > left child (value=" + arr[left] + ")");
                }
            }
            if (right < arr.length) {
                if (arr[i] > arr[right]) {
                    System.out.println("vi pham min-heap tai index " + i + " (value=" + arr[i] + ") > right child (value=" + arr[right] + ")");
                }
            }
        }
    }
}
