package tree.general;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Heap {

    private final ArrayList<Integer> heap = new ArrayList<>();


    @Override
    public String toString() {
        return heap.toString();
    }

    public List<Integer> toList() {
        return new ArrayList<>(heap);
    }

    public Integer peek() {
        return heap.isEmpty() ? null : heap.getFirst();
    }

    public int size() {
        return heap.size();
    }

    public void clear() {
        heap.clear();
    }

    public Integer extractMin() {
        if (heap.isEmpty()) return null;
        Integer min = heap.getFirst();
        Integer last = heap.removeLast();
        if (!heap.isEmpty()) {
            heap.set(0, last);
            int i = 0;
            while (true) {
                int left = 2 * i + 1;
                int right = 2 * i + 2;
                int smallest = i;
                if (left < heap.size() && heap.get(left) < heap.get(smallest))
                    smallest = left;
                if (right < heap.size() && heap.get(right) < heap.get(smallest))
                    smallest = right;
                if (smallest == i) break;
                Collections.swap(heap, i, smallest);
                i = smallest;
            }
        }
        return min;
    }

    public void insertHeap(Integer value){
        heap.add(value);
        int current = heap.size() - 1;
        while (current>0){
            int parent = (current-1)>>1;
            if(heap.get(current)<heap.get(parent)){
                Collections.swap(heap,current,parent);
                current = parent;
            }else{
                break;
            }
        }
    }
}
