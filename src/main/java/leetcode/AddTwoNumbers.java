package leetcode;

public class AddTwoNumbers {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {}

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        ListNode numbers1 = new ListNode(1, new ListNode(3, new ListNode(4, new ListNode(5))));
        ListNode numbers2 = new ListNode(4, new ListNode(1, new ListNode(3)));

        for (ListNode curr = addTwoNumbers(numbers1, numbers2); curr != null; curr = curr.next) {
            System.out.println(curr.val);
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode dummy = new ListNode(-1); //dummy head

        var current1 = l1;
        var current2 = l2;
        var currentResult = dummy;
        while (current1 != null || current2 != null) {
            int a = 0;
            if (current1 != null) {
                a = current1.val;
                current1 = current1.next;
            }

            int b = 0;
            if (current2 != null) {
                b = current2.val;
                current2 = current2.next;
            }

            int sum = (a + b + carry);
            carry = sum / 10;

            currentResult.next = new ListNode(sum % 10);
            currentResult = currentResult.next;
        }

        if (carry != 0) {
            currentResult.next = new ListNode(carry);
        }

        return dummy.next;
    }
}
