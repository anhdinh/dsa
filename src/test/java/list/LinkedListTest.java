package list;

import Link.LinkedList;
import org.junit.jupiter.api.Test;

public class LinkedListTest {

    @Test
    public void testAddItem(){
        LinkedList list = new LinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.travel();
        list.remove(3);
        System.out.println("after remove");
        list.travel();
    }
}
