package Link;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LinkedList {
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private ListNode head;

    @Getter
    @Setter(AccessLevel.NONE)
    private int size;

    public void add(int value){
        ListNode newNode =  new ListNode(value);
        if(head==null){
            head = newNode;
        }else{
            ListNode curr = head;
            while (curr.getNext() !=null){
                curr = curr.getNext();
            }
            curr.setNext(newNode);
        }
        size++;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void remove(int index){
        if(head==null) return;
        if(index <0 || index > size-1) return;
        if(index==0){
            head =  head.getNext();
            size--;
            return;
        }
        int current = 0;
        var prev = head;
        while (current<index-1){
            prev = prev.getNext();
            current++;
        }
        prev.setNext(prev.getNext().getNext());
        size--;
    }

    public ListNode getHead() {
        return head;
    }

    public int get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        ListNode curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.getNext();
        }
        return curr.getValue();
    }

    public void addFirst(int value) {
        ListNode newNode = new ListNode(value);
        newNode.setNext(head);
        head = newNode;
        size++;
    }

    public void set(int index, int value) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        ListNode curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.getNext();
        }
        curr.setValue(value);
    }

    public int indexOf(int value) {
        ListNode curr = head;
        int index = 0;
        while (curr != null) {
            if (curr.getValue() == value) return index;
            curr = curr.getNext();
            index++;
        }
        return -1;
    }

    public void clear() {
        head = null;
        size = 0;
    }

    public void reverse() {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.getNext();
            curr.setNext(prev);
            prev = curr;
            curr = next;
        }
        head = prev;
    }

    public void travel(){
        ListNode curr = head;
        while (curr!=null){
            System.out.println(curr.getValue());
            curr = curr.getNext();
        }
    }
}
