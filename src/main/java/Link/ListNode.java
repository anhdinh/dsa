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
}
