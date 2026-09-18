package oom.test;

import java.util.ArrayList;
import java.util.List;

public class OOMDemo {

    public static void main(String[] args) {
        List<byte[]> holder = new ArrayList<>();
        int count = 0;
        while (true) {
            holder.add(new byte[1024 * 1024]);
            count++;
            if (count % 100 == 0) {
                System.out.println("Allocated: " + count + " MB");
            }
        }
    }
}
