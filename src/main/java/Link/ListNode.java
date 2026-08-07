package Link;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ListNode {
    private int value;
    private ListNode next;

    public ListNode(int value){
        this.value = value;
    }

    public ListNode(int value, ListNode next){
        this.value = value;
        this.next = next;
    }
}
